package com.example.compiler

import com.example.anno.BindItem
import com.example.anno.BindParser
import com.example.anno.BindTestCase
import com.example.compiler.info.BindItemInfo
import com.example.compiler.info.BindParserInfo
import com.example.compiler.info.BindTestInfo
import com.example.compiler.info.BindingInfo
import com.squareup.javapoet.ClassName
import com.squareup.javapoet.CodeBlock
import com.squareup.javapoet.JavaFile
import com.squareup.javapoet.TypeSpec
import com.squareup.moshi.Moshi
import java.io.File
import java.net.URLClassLoader
import java.util.zip.ZipFile
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Modifier
import javax.lang.model.element.TypeElement
import javax.lang.model.type.DeclaredType
import javax.lang.model.type.MirroredTypeException
import javax.lang.model.util.Elements
import javax.lang.model.util.Types
import javax.tools.Diagnostic
import javax.tools.StandardLocation
import kotlin.properties.Delegates

/**
 * Created by grass on 16/7/23.
 */
class GrassProcessor : AbstractProcessor() {

    companion object {
        val FILE_NAME = "BINDING.json"
        val PACKAGE_NAME = "com.example"
        val COMMON_ITEM_FACTORY = ClassName.get("com.grass.core.base.adapter", "CommonItemFactory")
        val APP_SAMPLE_STORE = ClassName.get("com.grass.core.data", "AppSamplesStore")
        val CATEGORY_SAMPLE_ITEM_INFO = ClassName.get("com.grass.core.bean", "CategorySampleItemInfo")
    }

    var elementUtils: Elements by Delegates.notNull()
    var typeUtils: Types by Delegates.notNull()
    var messanger: Messager by Delegates.notNull()
    var filer: Filer by Delegates.notNull()

    val adapter = Moshi.Builder().build().adapter(BindingInfo::class.java)

    var round = 0

    val bindInfo = BindingInfo()

    var isApplication = false


    override fun init(env: ProcessingEnvironment) {
        super.init(env)
        elementUtils = env.elementUtils
        typeUtils = env.typeUtils
        messanger = env.messager
        filer = env.filer
        isApplication = env.options["isApplication"] == "true"
        val moduleName = env.options["moduleName"]
        onWarning("isApplication " + isApplication + " moduleName " + moduleName)
    }

    override fun process(annotations: MutableSet<out TypeElement>, env: RoundEnvironment): Boolean {
        try {
            onWarning("===============process is working====================================")
            process(env)
            round++
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false;
    }

    fun process(env: RoundEnvironment) {
        processBindItems(env)
        processBindParsers(env)
        processBindTests(env)
        if (isApplication) {
            readJarFile(FILE_NAME)
        } else {
            saveBindingInfo()
        }
    }

    fun processBindItems(env: RoundEnvironment) {
        val items = env.getElementsAnnotatedWith(BindItem::class.java).filter { it is TypeElement }.map { it as TypeElement }
        onWarning("=========================== items: " + items.size)
        if (items.isEmpty()) {
            return
        }
        parserBindItem(items)
        generateBindItemCode(bindInfo.items)
    }


    fun processBindParsers(env: RoundEnvironment) {
        val parsers = env.getElementsAnnotatedWith(BindParser::class.java).filter { it is TypeElement }
                .map { it as TypeElement }

        onWarning("===========================" + " parsers: " + parsers.size)
        if (parsers.isEmpty()) {
            return;
        }
        parseParser(parsers)
        generateParserCode(bindInfo.parsers)
    }


    fun processBindTests(env: RoundEnvironment) {
        val tests = env.getElementsAnnotatedWith(BindTestCase::class.java).filter { it is TypeElement }.map { it as TypeElement }
        onWarning("=========================== " + " tests: " + tests
                .size)
        if (tests.isEmpty()) {
            return;
        }
        parseTests(tests)
        generateBindTestsCode(bindInfo.tests)

    }

    private fun parseTests(test: List<TypeElement>) {
        val result = test.map {
            val test = ClassName.get(it)
            val annotation = it.getAnnotation(BindTestCase::class.java)
            val type = annotation.type;
            val name = annotation.name;
            val des = annotation.des;
            BindTestInfo(type, name, des, test)
        }
        bindInfo.tests.addAll(result)
    }

    /**
    AppSamplesStore.registerCategoryTestCase(CategorySampleItemInfo.createInstance(SampleType.FRAGMENT, "", "", Test::class.java))

     */
    private fun generateBindTestsCode(test: Iterable<BindTestInfo>) {
        test.generateCode(PACKAGE_NAME, "BindingTestStartup", {
            it.addStatement("$1T.registerCategoryTestCase($2T.createInstance($3L, $4S, $5S, $6T" +
                    ".class))"
                    , APP_SAMPLE_STORE, CATEGORY_SAMPLE_ITEM_INFO, type, name, des, bindcase)
        })
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


    fun saveBindingInfo() {
        onWarning("saveBindingInfo items : " + bindInfo.items.size)
        onWarning("saveBindingInfo tests : " + bindInfo.tests.size)
        onWarning("saveBindingInfo parsers : " + bindInfo.parsers.size)
        if (listOf(bindInfo.items, bindInfo.tests, bindInfo.parsers).all { it.isEmpty() }) {
            return;
        }
        var file = filer.createResource(StandardLocation.CLASS_OUTPUT, "", FILE_NAME)
        onWarning("saveBindingInfo: " + file.name + " output: " + StandardLocation.CLASS_OUTPUT.toString() + " " +
                "" + StandardLocation.CLASS_OUTPUT.name)
        file.openWriter().use {
            it.write(adapter.toJson(bindInfo))
            it.flush()
            it.close()
        }
    }

    fun readJarFile(name: String): List<String> {
        onWarning("=====================readJarFile is working======================")
        val loader = javaClass.classLoader
        val fooClass = GrassProcessor::class.java
        onWarning("readJarFile: " + (fooClass == javaClass))
        if (loader is URLClassLoader) {
            var list = loader.urLs.map { File(it.file) }
                    .filter {
                        onWarning("it " + it.absolutePath)
                        it.extension == "jar" && it.exists()
                    }.map { ZipFile(it) }.filter { it.getEntry(name) != null }
                    .map {
                it.getInputStream(it.getEntry(name)).reader().use {
                    it.readText()
                }
            }
            return list;
        }
        return listOf()
    }

    //==============================================================================
    override fun getSupportedSourceVersion(): SourceVersion? {
        return SourceVersion.latest()
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String>? {
        return setOf(BindItem::class,
                BindParser::class,
                BindTestCase::class).map {
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