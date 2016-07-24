package com.example.compiler.info

/**
 * Created by grass on 16/7/23.
 */
class BindingInfo {
    val items: MutableSet<BindItemInfo> = mutableSetOf()
    val parsers: MutableSet<BindParserInfo> = mutableSetOf()
    val tests: MutableSet<BindTestInfo> = mutableSetOf()
}