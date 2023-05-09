package com.cheesejuice.fancymansion.core.entity.user

data class CountEntity(
    val userId : String,
    val readMode : String,
    val bookId : String,
    val elementId : Long,
    val count : Int = 0
)