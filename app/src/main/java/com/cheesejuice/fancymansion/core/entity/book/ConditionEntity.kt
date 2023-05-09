package com.cheesejuice.fancymansion.core.entity.book

import com.cheesejuice.fancymansion.core.common.Comparison
import com.cheesejuice.fancymansion.core.common.NOT_ASSIGN_COUNT
import com.cheesejuice.fancymansion.core.common.NOT_ASSIGN_ID
import com.cheesejuice.fancymansion.core.common.Relation

data class ConditionEntity(
    val conditionId : Long,
    val targetId1 : Long = NOT_ASSIGN_ID,
    val targetId2 : Long = NOT_ASSIGN_ID,
    val targetCount : Int = NOT_ASSIGN_COUNT,
    val comparison : String = Comparison.EQUAL.name,
    val nextRelation : String = Relation.OR.name
)