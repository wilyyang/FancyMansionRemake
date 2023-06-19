package com.cheesejuice.domain.entity.makebook.book

import com.cheesejuice.core.common.Comparison
import com.cheesejuice.core.common.NOT_ASSIGN_COUNT
import com.cheesejuice.core.common.NOT_ASSIGN_ID
import com.cheesejuice.core.common.Relation
import com.cheesejuice.domain.entity.readbook.book.ConditionEntity

data class EditableConditionEntity(
    var conditionId : Long,
    var targetId1 : Long = NOT_ASSIGN_ID,
    var targetId2 : Long = NOT_ASSIGN_ID,
    var targetCount : Int = NOT_ASSIGN_COUNT,
    var comparison : String = Comparison.EQUAL.name,
    var nextRelation : String = Relation.OR.name
)

fun ConditionEntity.toEditable() = EditableConditionEntity(
    conditionId = conditionId,
    targetId1 = targetId1,
    targetId2 = targetId2,
    targetCount = targetCount,
    comparison = comparison,
    nextRelation = nextRelation
)