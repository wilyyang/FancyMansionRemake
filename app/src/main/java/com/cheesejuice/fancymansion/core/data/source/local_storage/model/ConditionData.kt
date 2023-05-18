package com.cheesejuice.fancymansion.core.data.source.local_storage.model

import com.cheesejuice.fancymansion.core.common.Comparison
import com.cheesejuice.fancymansion.core.common.NOT_ASSIGN_COUNT
import com.cheesejuice.fancymansion.core.common.NOT_ASSIGN_ID
import com.cheesejuice.fancymansion.core.common.Relation
import com.cheesejuice.fancymansion.core.entity.book.ConditionEntity

data class ConditionData(
    val conditionId : Long,
    val targetId1 : Long = NOT_ASSIGN_ID,
    val targetId2 : Long = NOT_ASSIGN_ID,
    val targetCount : Int = NOT_ASSIGN_COUNT,
    val comparison : String = Comparison.EQUAL.name,
    val nextRelation : String = Relation.OR.name
)

fun ConditionData.asMapper() = ConditionEntity(
    conditionId = conditionId,
    targetId1 = targetId1,
    targetId2 = targetId2,
    targetCount = targetCount,
    comparison = comparison,
    nextRelation = nextRelation
)

fun ConditionEntity.asData() = ConditionData(
    conditionId = conditionId,
    targetId1 = targetId1,
    targetId2 = targetId2,
    targetCount = targetCount,
    comparison = comparison,
    nextRelation = nextRelation
)