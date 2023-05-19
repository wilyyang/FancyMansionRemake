package com.cheesejuice.fancymansion.datasource.localAppStorage.model

import com.cheesejuice.core.common.Comparison
import com.cheesejuice.core.common.NOT_ASSIGN_COUNT
import com.cheesejuice.core.common.NOT_ASSIGN_ID
import com.cheesejuice.core.common.Relation
import com.cheesejuice.fancymansion.data.mapper.book.ConditionMapper

data class ConditionData(
    val conditionId : Long,
    val targetId1 : Long = NOT_ASSIGN_ID,
    val targetId2 : Long = NOT_ASSIGN_ID,
    val targetCount : Int = NOT_ASSIGN_COUNT,
    val comparison : String = Comparison.EQUAL.name,
    val nextRelation : String = Relation.OR.name
)

fun ConditionData.asMapper() = ConditionMapper(
    conditionId = conditionId,
    targetId1 = targetId1,
    targetId2 = targetId2,
    targetCount = targetCount,
    comparison = comparison,
    nextRelation = nextRelation
)

fun ConditionMapper.asData() = ConditionData(
    conditionId = conditionId,
    targetId1 = targetId1,
    targetId2 = targetId2,
    targetCount = targetCount,
    comparison = comparison,
    nextRelation = nextRelation
)