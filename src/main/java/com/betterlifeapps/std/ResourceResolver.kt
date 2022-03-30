package com.betterlifeapps.std

import androidx.annotation.StringRes

interface ResourceResolver {

    fun getString(@StringRes resId: Int): String

    fun getString(@StringRes resId: Int, vararg formatArgs: Any): String
}