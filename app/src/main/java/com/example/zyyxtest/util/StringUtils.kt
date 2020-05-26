package com.example.zyyxtest.util

/**
 * Regex only number no duplicate and length = 6
 */
fun String.checkPassword(): Boolean {
    val pattern = Regex("^(?!.*(.).*\\1)[0-9]{6}+\$")
    return pattern.containsMatchIn(this)
}