package com.cheesejuice.fancymansion

enum class PageType{
    START, NORMAL, END;

    companion object {
        fun type(index : Int) : PageType = values()[index]
    }
}

enum class ReadMode{
    READ_ONLY, EDIT
}

const val LOG_TAG = "CraneLog"

const val NOT_ASSIGN_ID = -1L
const val NOT_ASSIGN_PAGE = -1L
const val NOT_ASSIGN_COUNT = -1

const val ID_NOT_FOUND = -1L
const val DEFAULT_END_PAGE_ID = -1L

enum class Comparison(
    val enName : String,
    val koName : String,
    val compare : (Int, Int) -> Boolean
){
    OVER (enName = "over",  koName = "초과", compare = { n1, n2 -> n1 > n2 }),
    UNDER(enName = "under", koName = "미만", compare = { n1, n2 -> n1 < n2 }),
    EQUAL(enName = "equal", koName = "같음", compare = { n1, n2 -> n1 == n2 }),
    NOT  (enName = "not",   koName = "다름", compare = { n1, n2 -> n1 != n2 });

    companion object {
        fun from(name : String?): Comparison = values().find { it.name == name } ?: EQUAL
    }
}
enum class Relation(
    val enName : String,
    val koName : String,
    val check : (Boolean, Boolean) -> Boolean
) {
    AND(enName = "and", koName = "그리고", check = { p1, p2 -> p1 && p2 }),
    OR (enName = "or",  koName = "또는",   check = { p1, p2 -> p1 || p2 });

    companion object {
        fun from(name : String?) : Relation = values().find { it.name == name } ?: OR
    }
}