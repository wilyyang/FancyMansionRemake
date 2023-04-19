package com.cheesejuice.fancymansion.data.model

import com.cheesejuice.fancymansion.*
import kotlinx.serialization.Serializable

/**
 * # [Book]
 * ## 1. [Config]
 * ```
 * 책 관련 정보 { 출시, 계정, 작가, 커버, 평가 }
 * ```
 * ## 2. Page List { [Page] }
 * ```
 * 내용을 구성하는 페이지 목록
 * ```
 * ## 3. [Logic]
 * ```
 * 페이지 간 이동을 제어하는 로직
 * ㄴ 3.1 PageLogic List
 * ```
 * [PageLogic] : 하나의 페이지가 가지는 선택지와 로직
 *
 * [ChoiceItem] : 유저가 선택하는 선택지
 *
 * [Route] : 선택지를 통해 이동하는 다음 페이지 루트
 *
 * [Condition] : 선택지가 보여지는 여부나 루트를 결정하는 조건
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
@Serializable
data class Book(
    val config : Config,
    var pages : MutableList<Page>,
    var logic : Logic
)

@Serializable
data class Config(
    var bookId : Long = 0L,

    var version : Long = 0L,
    var publishCode : String = "",
    var updateTime : Long = System.currentTimeMillis(),

    var userId : String = "",
    var user : String = "",
    var email : String = "",

    var writer : String = "",
    var illustrator : String = "",

    var title : String = "",
    var description : String = "",
    var coverImage : String = "",
    var tagList : MutableList<String> = mutableListOf(),

    var readMode : String = ReadMode.EDIT.name,
    var defaultEndPageId : Long = DEFAULT_END_PAGE_ID,

    var downloads : Int = 0,
    var good : Int = 0,
    var report : Int = 0
)

@Serializable
data class Page(
    var pageId : Long,
    var pageTitle : String,
    var pageImage : String = "",
    var description : String = "",
    var question : String
)

@Serializable
data class Logic(
    val bookId : Long,
    var logics : MutableList<PageLogic> = mutableListOf()
)

@Serializable
data class PageLogic(
    var pageId : Long,
    var pageTitle : String,
    var type : Int = PageType.NORMAL.ordinal,
    var choiceItems : MutableList<ChoiceItem> = mutableListOf()
)

@Serializable
data class ChoiceItem(
    var choiceId : Long,
    var title : String,
    var showConditions : MutableList<Condition> = mutableListOf(),
    var routes : MutableList<Route> = mutableListOf()
)

@Serializable
data class Route(
    var routeId : Long,
    var routePageId : Long = NOT_ASSIGN_PAGE,
    var routeConditions : MutableList<Condition> = mutableListOf()
)

@Serializable
data class Condition(
    var conditionId : Long,
    var targetId1 : Long = NOT_ASSIGN_ID,
    var targetId2 : Long = NOT_ASSIGN_ID,
    var targetCount : Int = NOT_ASSIGN_COUNT,
    var comparison : String = Comparison.EQUAL.name,
    var nextRelation : String = Relation.OR.name
)
