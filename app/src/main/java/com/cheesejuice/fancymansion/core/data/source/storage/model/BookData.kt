package com.cheesejuice.fancymansion.core.data.source.storage.model

import com.cheesejuice.fancymansion.core.common.*
import com.cheesejuice.fancymansion.core.entity.book.*

data class ConfigData(
    val bookId : String = "",

    val version : Long = 0L,
    val publishCode : String = "",
    val updateTime : Long = System.currentTimeMillis(),

    val userId : String = "",
    val user : String = "",
    val email : String = "",

    val writer : String = "",
    val illustrator : String = "",

    val title : String = "",
    val description : String = "",
    val coverImage : String = "",
    val tagList : List<String> = listOf(),

    val readMode : String = ReadMode.edit.name,
    val defaultStartPageId : Long = DEFAULT_START_PAGE_ID,
    val defaultEndPageId : Long = DEFAULT_END_PAGE_ID,

    val downloads : Int = 0,
    val good : Int = 0,
    val report : Int = 0
)

data class PageContentData(
    val pageId : Long,
    val pageTitle : String,
    val pageImage : String = "",
    val description : String = "",
    val question : String
)

data class LogicData(
    val bookId : String,
    var logics : List<PageLogicData> = listOf()
)

data class PageLogicData(
    val pageId : Long,
    val pageTitle : String,
    val type : Int = PageType.NORMAL.ordinal,
    val choiceItems : List<ChoiceItemData> = listOf()
)

data class ChoiceItemData(
    val choiceId : Long,
    val title : String,
    val showConditions : List<ConditionData> = listOf(),
    val routes : List<RouteData> = listOf()
)

data class RouteData(
    val routeId : Long,
    val routePageId : Long = NOT_ASSIGN_PAGE,
    val routeConditions : List<ConditionData> = listOf()
)

data class ConditionData(
    val conditionId : Long,
    val targetId1 : Long = NOT_ASSIGN_ID,
    val targetId2 : Long = NOT_ASSIGN_ID,
    val targetCount : Int = NOT_ASSIGN_COUNT,
    val comparison : String = Comparison.EQUAL.name,
    val nextRelation : String = Relation.OR.name
)

fun ConfigData.asEntity() = ConfigEntity(
    bookId = bookId,

    version = version,
    publishCode = publishCode,
    updateTime = updateTime,

    userId = userId,
    user = user,
    email = email,

    writer = writer,
    illustrator = illustrator,

    title = title,
    description = description,
    coverImage = coverImage,
    tagList = tagList.toMutableList(),

    readMode = readMode,
    defaultStartPageId = defaultStartPageId,
    defaultEndPageId = defaultEndPageId,

    downloads = downloads,
    good = good,
    report = report
)

fun PageContentData.asEntity() = PageContentEntity(
    pageId = pageId,
    pageTitle = pageTitle,
    pageImage = pageImage,
    description = description,
    question = question
)

fun LogicData.asEntity() = LogicEntity(
    bookId = bookId,
    logics = logics.map { it.asEntity() }.toMutableList()
)

fun PageLogicData.asEntity() = PageLogicEntity(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asEntity() }.toMutableList()
)

fun ChoiceItemData.asEntity() = ChoiceItemEntity(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asEntity() }.toMutableList(),
    routes = routes.map { it.asEntity() }.toMutableList()
)

fun RouteData.asEntity() = RouteEntity(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asEntity() }.toMutableList()
)

fun ConditionData.asEntity() = ConditionEntity(
    conditionId = conditionId,
    targetId1 = targetId1,
    targetId2 = targetId2,
    targetCount = targetCount,
    comparison = comparison,
    nextRelation = nextRelation
)


fun ConfigEntity.asData() = ConfigData(
    bookId = bookId,

    version = version,
    publishCode = publishCode,
    updateTime = updateTime,

    userId = userId,
    user = user,
    email = email,

    writer = writer,
    illustrator = illustrator,

    title = title,
    description = description,
    coverImage = coverImage,
    tagList = tagList,

    readMode = readMode,
    defaultStartPageId = defaultStartPageId,
    defaultEndPageId = defaultEndPageId,

    downloads = downloads,
    good = good,
    report = report
)

fun PageContentEntity.asData() = PageContentData(
    pageId = pageId,
    pageTitle = pageTitle,
    pageImage = pageImage,
    description = description,
    question = question
)

fun LogicEntity.asData() = LogicData(
    bookId = bookId,
    logics = logics.map { it.asData() }
)

fun PageLogicEntity.asData() = PageLogicData(
    pageId = pageId,
    pageTitle = pageTitle,
    type = type,
    choiceItems = choiceItems.map { it.asData() }
)

fun ChoiceItemEntity.asData() = ChoiceItemData(
    choiceId = choiceId,
    title = title,
    showConditions = showConditions.map { it.asData() },
    routes = routes.map { it.asData() }
)

fun RouteEntity.asData() = RouteData(
    routeId = routeId,
    routePageId = routePageId,
    routeConditions = routeConditions.map { it.asData() }
)

fun ConditionEntity.asData() = ConditionData(
    conditionId = conditionId,
    targetId1 = targetId1,
    targetId2 = targetId2,
    targetCount = targetCount,
    comparison = comparison,
    nextRelation = nextRelation
)