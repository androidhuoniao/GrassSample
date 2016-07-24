package com.example.compiler

import com.example.anno.BindItem
import com.example.anno.BindParser
import com.example.compiler.info.BindItemInfo
import com.example.compiler.info.BindParserInfo
import com.example.compiler.info.BindingInfo
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic
import kotlin.properties.Delegates

/**
 * Created by grass on 16/7/23.
 */
class GrassProcessor : AbstractProcessor() {

    companion object {
        val PACKAGE_NAME = "com.example"
        val COMMON_ITEM_FACTORY = ClassName.get("com.grass.core.base.adapter", "CommonItemFactory")
    }

    var elementUtils: Elements by Delegates.notNull()
    var typeUtils: Types by Delegates.notNull()
    var messanger: Messager by Delegates.notNull()
    var filer: Filer by Delegates.notNull()

    var round = 0

    val bindInfo = BindingInfo()


    override fun init(env: ProcessingEnvironment) {
        super.init(env)
        elementUtils = env.elementUtils
        typeUtils = env.typeUtils
        messanger = env.messager
        filer = env.filer
    }

    override fun process(annotations: MutableSet<out TypeElement>, env: RoundEnvironment): Boolean {
        try {
            process(env)
            round++
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false;
    }

    fun process(env: RoundEnvironment) {
        val items = env.getElementsAnnotatedWith(BindItem::class.java).filter { it is TypeElement }.map { it as TypeElement }
        val parsers = env.getElementsAnnotatedWith(BindParser::class.java).filter { it is TypeElement }
                .map { it as TypeElement }
        onWarning("===========================" + items.size + " " + parsers.size)
        if (listOf(items, parsers).all { it.isEmpty() }) {
            return
        }
        onWarning("==============2=============" + items.size + " " + parsers.size)
        parserBindItem(items)
        parseParser(parsers)

        generateBindItemCode(bindInfo.items)
        generateParserCode(bindInfo.parsers)
    }

    fun parserBindItem(items: List<TypeElement>) {
        val result = items.map {
            val name = ClassName.get(it)
            val annotation = it.getAnnotation(BindItem::class.java)
            val target = try {
                ClassName.get(elementUtils.getTypeElement(annotation.value.java.canonicalName))
            } catch(mte: MirroredTypeException) {
                ClassName.get((mte.typeMirror as DeclaredType).asElement() as TypeElement)
            }
            BindItemInfo(name, target)
        }
        bindInfo.items.addAll(result)
        onWarning("==============parserBindItem=============" + bindInfo.items.size)
    }

    fun parseParser(parsers: List<TypeElement>) {
        val result = parsers.map {
            val name = ClassName.get(it)
            BindParserInfo(name)
        }
        bindInfo.parsers.addAll(result)
    }

    fun generateBindItemCode(items: Iterable<BindItemInfo>) {
        onWarning("==============generateBindItemCode=============" + items.count())
        if (items.none()) {
            return
        }
        items.generateCode(PACKAGE_NAME, "BindingItemStartup", {
            it.addStatement("$1T.registerCreator($2T.class, new $3T())", COMMON_ITEM_FACTORY, iteminfo, creator)
        })
    }

    fun generateParserCode(parsers: Iterable<BindParserInfo>) {
        if (parsers.none()) {
            return
        }

    }

    fun <E> Iterable<E>.generateCode(packageName: String, className: String, action: E.(CodeBlock.Builder) -> Unit) {
        onWarning("===========generateCode======================" + action)
        val codeBuilder = CodeBlock.builder();
        forEach {
            it.action(codeBuilder)
        }
        onWarning("===========generateCode codeblock======================" + codeBuilder.build().toString())
        val type = TypeSpec.classBuilder(className).apply {
            addModifiers(Modifier.PUBLIC)
            addStaticBlock(codeBuilder.build())
        }.build()

        onWarning("===========generateCode======================" + type.toString())
        JavaFile.builder(packageName, type)
                .build().writeTo(filer)
    }


    override fun getSupportedSourceVersion(): SourceVersion? {
        return SourceVersion.latest()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String>? {
        return setOf(BindItem::class,
                BindParser::class).map {
            it.java.canonicalName
        }.toMutableSet()
    }


    fun onWarning(mes: String) {
        messanger.printMessage(Diagnostic.Kind.WARNING, mes)
    }

    fun onError(mes: String) {
        messanger.printMessage(Diagnostic.Kind.ERROR, mes)
    }
}