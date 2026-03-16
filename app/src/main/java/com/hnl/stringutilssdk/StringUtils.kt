package com.hnl.stringutilssdk


object StringUtils {

    fun capitalize(text: String): String {
        return text.replaceFirstChar { it.uppercase() }
    }

    fun reverseWords(text: String): String {
        return text.split(" ").reversed().joinToString(" ")
    }

    fun countWords(text: String): Int {
        return text.trim().split("\\s+".toRegex()).size
    }
}