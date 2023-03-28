package com.cheesejuice.fancymansion.ui.theme.value.string

import java.util.Locale

object StringSystem {

    // Member
    var locale : Locale? = null
    var Strings : StringSet = StringSetDefault
        private set

    fun setStringsByLocale(locale: Locale) {
        if (this.locale != locale) {
            this.locale = locale
            Strings = when(this.locale){
                Locale.KOREA -> StringSetKR
                else -> StringSetDefault
            }
        }
    }
}