package io.acari.n7.util

import java.util.*

fun <T> T?.toOptional(): Optional<T> = Optional.ofNullable(this)