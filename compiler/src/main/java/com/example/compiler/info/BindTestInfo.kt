package com.example.compiler.info

import com.squareup.javapoet.ClassName

/**
 * Created by grass on 16/7/24.
 */
data class BindTestInfo(val type: Int, val name: String, val des: String, val bindcase: ClassName) {
}