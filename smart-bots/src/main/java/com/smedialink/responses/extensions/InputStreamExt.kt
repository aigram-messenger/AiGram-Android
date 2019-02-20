package com.smedialink.responses.extensions

import java.io.InputStream

fun InputStream.asString(): String =
    this.bufferedReader().use { it.readText() }
