package com.example.compiler

/**
 * Created by grass on 16/7/23.
 */
class BindingInfo {
    val items: MutableSet<BindItemInfo> = mutableSetOf()
    val parsers: MutableSet<BindParserInfo> = mutableSetOf()
}