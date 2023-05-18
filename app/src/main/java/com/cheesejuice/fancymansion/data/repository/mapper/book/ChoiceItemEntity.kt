package com.cheesejuice.fancymansion.data.repository.mapper.book

data class ChoiceItemEntity(
    val choiceId : Long,
    val title : String,
    val showConditions : List<ConditionEntity> = listOf(),
    val routes : List<RouteEntity> = listOf()
)