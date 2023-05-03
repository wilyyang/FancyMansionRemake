package com.cheesejuice.fancymansion.core.entity.book

import com.cheesejuice.fancymansion.core.common.*
import java.io.File

/**
 * # [BookEntity]
 * ## 1. [ConfigEntity]
 * ```
 * 책 관련 정보 { 출시, 계정, 작가, 커버, 평가 }
 * ```
 * ## 2. Page List { [PageContentEntity] }
 * ```
 * 내용을 구성하는 페이지 목록
 * ```
 * ## 3. [LogicEntity]
 * ```
 * 페이지 간 이동을 제어하는 로직
 * ㄴ 3.1 PageLogic List
 * ```
 * [PageLogicEntity] : 하나의 페이지가 가지는 선택지와 로직
 *
 * [ChoiceItemEntity] : 유저가 선택하는 선택지
 *
 * [RouteEntity] : 선택지를 통해 이동하는 다음 페이지 루트
 *
 * [ConditionEntity] : 선택지가 보여지는 여부나 루트를 결정하는 조건
 *
 * ```
 * PageLogic {
 *      choiceItems {
 *          ChoiceItem {
 *              routes {
 *                  Route {
 *                      routeConditions { Condition ... }
 *                  } ...
 *              }
 *              showConditions { Condition ... }
 *          } ...
 *      }
 * }
 * ```
 * ```
 * ID RULE = 00_00_00_00_00
 * = { 00(#page)_
 *     00(#choice)_
 *     00(#show condition)_
 *     00(#route)_
 *     00(#route condition) }
 * ```
 */
data class BookEntity(
    val config : ConfigEntity,
    val pageContents : List<PageContentEntity>,
    val logic : LogicEntity
)

data class ConfigEntity(
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

data class PageEntity(
    val content : PageContentEntity,
    val logic : PageLogicEntity,
    val image : File? = null
)

data class PageContentEntity(
    val pageId : Long,
    val pageTitle : String,
    val pageImage : String = "",
    val description : String = "",
    val question : String
)

data class LogicEntity(
    val bookId : String,
    val logics : List<PageLogicEntity> = listOf()
)

data class PageLogicEntity(
    val pageId : Long,
    val pageTitle : String,
    val type : Int = PageType.NORMAL.ordinal,
    val choiceItems : List<ChoiceItemEntity> = listOf()
)

data class ChoiceItemEntity(
    val choiceId : Long,
    val title : String,
    val showConditions : List<ConditionEntity> = listOf(),
    val routes : List<RouteEntity> = listOf()
)

data class RouteEntity(
    val routeId : Long,
    val routePageId : Long = NOT_ASSIGN_PAGE,
    val routeConditions : List<ConditionEntity> = listOf()
)

data class ConditionEntity(
    val conditionId : Long,
    val targetId1 : Long = NOT_ASSIGN_ID,
    val targetId2 : Long = NOT_ASSIGN_ID,
    val targetCount : Int = NOT_ASSIGN_COUNT,
    val comparison : String = Comparison.EQUAL.name,
    val nextRelation : String = Relation.OR.name
)

object InitEntity{
    val condition = ConditionEntity(conditionId = INIT_ID)
    val route = RouteEntity(routeId = INIT_ID)
    val choice = ChoiceItemEntity(choiceId = INIT_ID, title = "")

    val pageLogic = PageLogicEntity(pageId = INIT_ID, pageTitle = "")
    val pageContent = PageContentEntity(pageId = INIT_ID, pageTitle = "", question = "")
    val page = PageEntity(content = pageContent, logic = pageLogic)

    val logic = LogicEntity(bookId = INIT_BOOK_ID)
    val config = ConfigEntity()
}