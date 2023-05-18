package com.cheesejuice.fancymansion.data.mapper.book

data class ChoiceItemEntity(
    val choiceId : Long,
    val title : String,
    val showConditions : List<ConditionEntity> = listOf(),
    val routes : List<RouteEntity> = listOf()
)