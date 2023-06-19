package com.cheesejuice.domain.entity.makebook.book

import com.cheesejuice.domain.entity.readbook.book.ChoiceItemEntity

data class EditableChoiceItemEntity(
    var choiceId : Long,
    var title : String,
    var showConditions : MutableList<EditableConditionEntity> = mutableListOf(),
    var routes : MutableList<EditableRouteEntity> = mutableListOf()
)

fun ChoiceItemEntity.toEditable() = EditableChoiceItemEntity(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.toEditable() }.toMutableList(),
    routes = routes.map { it.toEditable() }.toMutableList()
)