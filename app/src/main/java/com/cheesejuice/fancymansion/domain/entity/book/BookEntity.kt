package com.cheesejuice.fancymansion.domain.entity.book

import com.cheesejuice.fancymansion.data.mapper.book.ConfigMapper
import com.cheesejuice.fancymansion.data.mapper.book.LogicMapper
import com.cheesejuice.fancymansion.data.mapper.book.PageContentMapper

/**
 * # [BookEntity]
 * ## 1. [ConfigMapper]
 * ```
 * 책 관련 정보 { 출시, 계정, 작가, 커버, 평가 }
 * ```
 * ## 2. Page List { [PageContentMapper] }
 * ```
 * 내용을 구성하는 페이지 목록
 * ```
 * ## 3. [LogicMapper]
 * ```
 * 페이지 간 이동을 제어하는 로직
 * ㄴ 3.1 PageLogic List
 * ```
 * [PageLogicMapper] : 하나의 페이지가 가지는 선택지와 로직
 *
 * [ChoiceItemMapper] : 유저가 선택하는 선택지
 *
 * [RouteMapper] : 선택지를 통해 이동하는 다음 페이지 루트
 *
 * [ConditionMapper] : 선택지가 보여지는 여부나 루트를 결정하는 조건
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
    val config : ConfigMapper,
    val pageContents : List<PageContentMapper>,
    val logic : LogicMapper
)