package com.cheesejuice.fancymansion.data.mapper.book

data class ChoiceItemMapper(
    val choiceId : Long,
    val title : String,
    val showConditions : List<ConditionMapper> = listOf(),
    val routes : List<RouteMapper> = listOf()
)