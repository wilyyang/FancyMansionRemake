package com.cheesejuice.fancymansion.domain.usecase.library.bookFile.sample

import com.cheesejuice.core.common.Comparison
import com.cheesejuice.core.common.LOCAL_USER_ID
import com.cheesejuice.core.common.PageType
import com.cheesejuice.core.common.Relation
import com.cheesejuice.core.common.SAMPLE_BOOK_ID
import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.data.mapper.book.*
import com.cheesejuice.fancymansion.domain.entity.BookEntity

object Sample {
    private const val bookId = SAMPLE_BOOK_ID

    private val config = ConfigMapper(
        bookId = bookId,

        version = 101001,
        updateTime = 234256544566,

        userId = LOCAL_USER_ID,

        writer = "팀 치즈쥬스",
        illustrator = "Ekaterina Rogova",

        title = "나의 고양이 찾기",
        description = "나의 고양이 존 크리스탈을 찾아주세요.. \n누군가 데려간걸까요? \n크리스탈의 친구들을 유심히 살펴보면 단서를 얻을지도 모르죠..\nimage : Purr\nlink : https://icons8.kr/",
        coverImage = "image_1.gif",
        tagList = mutableListOf("귀여운", "고양이"),

        defaultStartPageId = 200000000,
        defaultEndPageId = 100000000
    )

    private val logics = mutableListOf(
        PageLogicMapper(
            pageId = 200000000, pageTitle = "대체 어딨는거야!", type = PageType.START.ordinal,
            choiceItems = mutableListOf(
                ChoiceItemMapper(
                    choiceId = 201000000,
                    title = "하얀 고양이 모야에게로",
                    showConditions = mutableListOf(
                        ConditionMapper(
                            conditionId = 201010000,
                            targetId1 = 400000000,
                            targetCount = 1,
                            comparison = Comparison.NOT.name
                        )
                    ),
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 201000100,
                            routePageId = 300000000
                        )
                    )
                ),

                ChoiceItemMapper(
                    choiceId = 202000000,
                    title = "얼룩 고양이 웬디에게로",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 202000100,
                            routePageId = 600000000,
                            routeConditions = mutableListOf(
                                ConditionMapper(
                                    conditionId = 202000101,
                                    targetId1 = 600000000,
                                    targetCount = 0,
                                    comparison = Comparison.OVER.name
                                )
                            )
                        ),
                        RouteMapper(
                            routeId = 202000200,
                            routePageId = 700000000,
                            routeConditions = mutableListOf(
                                ConditionMapper(
                                    conditionId = 202000201,
                                    targetId1 = 400000000,
                                    targetCount = 0,
                                    comparison = Comparison.OVER.name
                                )
                            )
                        ),
                        RouteMapper(
                            routeId = 202000300,
                            routePageId = 500000000
                        )
                    )
                )
            )
        ),

        PageLogicMapper(pageId = 100000000, pageTitle = "더 이상 존 크리스탈을 찾을 수 없습니다...", type = PageType.END.ordinal),
        PageLogicMapper(
            pageId = 300000000, pageTitle = "하얀 고양이 모야!",
            choiceItems = mutableListOf(
                ChoiceItemMapper(
                    choiceId = 301000000,
                    title = "모야의 머리에서 어항을 빼버린다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 301000100,
                            routePageId = 400000000,
                            routeConditions = mutableListOf(
                                ConditionMapper(
                                    conditionId = 301000101,
                                    targetId1 = 302000000,
                                    targetCount = 3,
                                    comparison = Comparison.EQUAL.name
                                )
                            )
                        ),
                        RouteMapper(
                            routeId = 301000200,
                            routePageId = 100000000
                        )
                    )
                ),
                ChoiceItemMapper(
                    choiceId = 302000000,
                    title = "모야와 함께 물고기를 바라본다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 302000100,
                            routePageId = 100000000,
                            routeConditions = mutableListOf(
                                ConditionMapper(
                                    conditionId = 302000101,
                                    targetId1 = 302000000,
                                    targetCount = 5,
                                    comparison = Comparison.OVER.name
                                )
                            )
                        ),
                        RouteMapper(
                            routeId = 302000200,
                            routePageId = 300000000
                        )
                    )
                ),
                ChoiceItemMapper(
                    choiceId = 303000000,
                    title = "다시 존을 잃어버린 자리로 돌아간다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 303000300,
                            routePageId = 200000000
                        )
                    )
                )
            )
        ),

        PageLogicMapper(
            pageId = 400000000, pageTitle = "모야가 가져다준 물고기!",
            choiceItems = mutableListOf(
                ChoiceItemMapper(
                    choiceId = 401000000,
                    title = "다시 존을 잃어버린 자리로 돌아간다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 401000100,
                            routePageId = 200000000
                        )
                    )
                )
            )
        ),
        PageLogicMapper(
            pageId = 500000000, pageTitle = "응꼬를 핥으려는 고양이 웬디!",
            choiceItems = mutableListOf(
                ChoiceItemMapper(
                    choiceId = 501000000,
                    title = "박스를 가져다 준다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 501000100,
                            routePageId = 600000000
                        )
                    )
                ),
                ChoiceItemMapper(
                    choiceId = 502000000,
                    title = "다시 존을 잃어버린 자리로 돌아간다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 502000100,
                            routePageId = 200000000
                        )
                    )
                )
            )
        ),
        PageLogicMapper(
            pageId = 600000000, pageTitle = "빙글빙글 상자속에서 돌고도는 너와 나",
            choiceItems = mutableListOf(
                ChoiceItemMapper(
                    choiceId = 601000000,
                    title = "빙글빙글 웬디와 함께 돈다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 601000100,
                            routePageId = 600000000
                        )
                    )
                ),
                ChoiceItemMapper(
                    choiceId = 602000000,
                    title = "기분이 좋아진 웬디에게 물고기를 주고 존의 행방을 묻는다",
                    showConditions = mutableListOf(
                        ConditionMapper(
                            conditionId = 602010000,
                            targetId1 = 602000000,
                            targetCount = 1,
                            comparison = Comparison.UNDER.name,
                            nextRelation = Relation.AND.name
                        ),
                        ConditionMapper(
                            conditionId = 602020000,
                            targetId1 = 400000000,
                            targetCount = 0,
                            comparison = Comparison.OVER.name
                        )
                    ),
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 602000100,
                            routePageId = 800000000
                        )
                    )
                ),
                ChoiceItemMapper(
                    choiceId = 603000000,
                    title = "다시 존을 잃어버린 자리로 돌아간다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 603000100,
                            routePageId = 200000000
                        )
                    )
                )
            )
        ),
        PageLogicMapper(
            pageId = 700000000, pageTitle = "응꼬를 핥으려는 고양이 웬디!",
            choiceItems = mutableListOf(
                ChoiceItemMapper(
                    choiceId = 701000000,
                    title = "다시 존을 잃어버린 자리로 돌아간다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 701000100,
                            routePageId = 200000000
                        )
                    )
                )
            )
        ),
        PageLogicMapper(
            pageId = 800000000, pageTitle = "그 녀석은 용서할 수 없는 녀석이야..",
            choiceItems = mutableListOf(
                ChoiceItemMapper(
                    choiceId = 801000000,
                    title = "빙글빙글 돌아준다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 801000100,
                            routePageId = 600000000
                        )
                    )
                ),
                ChoiceItemMapper(
                    choiceId = 802000000,
                    title = "빙글빙글 안돌아준다",
                    routes = mutableListOf(
                        RouteMapper(
                            routeId = 802000100,
                            routePageId = 900000000
                        )
                    )
                )
            )
        ),
        PageLogicMapper(pageId = 900000000, pageTitle = "아니 이 녀석 집에 있었잖아?", type = PageType.END.ordinal)
    )

    private val logic = LogicMapper(
        bookId = bookId,
        logics = logics
    )

    private val pageContents = mutableListOf(
        PageContentMapper(
            pageId = 200000000,
            pageTitle = "대체 어딨는거야!",
            pageImage = "image_1.gif",
            description = "대체 어딨는거야... 나의 고양이 존 크리스탈...\n사라져버린 존을 누군가 데려간걸까요?\n평소 존과 친했던 고양이 모야와 웬디에게로 가봅시다.",
            question = "어떤 고양이를 찾아갈까요?"
        ),
        PageContentMapper(
            pageId = 100000000,
            pageTitle = "더 이상 존 크리스탈을 찾을 수 없습니다...",
            pageImage = "game_end.jpg",
            description = "대체 무슨 일을 해버린거죠?\n존 크리스탈은 이제 찾을 수 없습니다..\n처음부터 다시 시작하세요...\n",
            question = ""
        ),
        PageContentMapper(
            pageId = 300000000,
            pageTitle = "하얀 고양이 모야!",
            pageImage = "image_6.gif",
            description = "\"모야? 모야? 모야아?\"\n하얀 고양이 모야가 물고기를 바라보며 궁금해하네요?\n존의 행방을 물어보기가 곤란하군요?\n하지만 원하는걸 얻기위해선 공손하게 행동해야 합니다.",
            question = "무엇을 할까요?"
        ),
        PageContentMapper(
            pageId = 400000000,
            pageTitle = "모야가 가져다준 물고기!",
            pageImage = "fish_cat.jpg",
            description = "모야가 당신에게 물고기를 가져다주는군요?\n\"이 물고기가 필요하다구용?\n확실히.. 배가 고픈 고양이에겐 필요할지도?\n그럼 난 이만 ~\"\n당신은 들고있는 모든 물건을 내려놓고 공손히 물고기를 받았습니다.",
            question = "무엇을 할까요?"
        ),
        PageContentMapper(
            pageId = 500000000,
            pageTitle = "응꼬를 핥으려는 고양이 웬디!",
            pageImage = "image_3.gif",
            description = "너무 배가 고파서... 응꼬를 핥고 있어요...\n\"캬! 아냐! 난 단지 그루밍을 하고 있을 뿐이라고!\"\n웬디가 화가 났군요?\n마침 당신은 박스를 들고 있습니다.\n일설에 고양이는 박스를 아주 좋아한다고 하지요?\n",
            question = "무엇을 할까요?"
        ),
        PageContentMapper(
            pageId = 600000000,
            pageTitle = "빙글빙글 상자속에서 돌고도는 너와 나",
            pageImage = "image_5.gif",
            description = "\"우리 만남은 빙글빙글 돌고\"\n웬디와 함께 빙글빙글 돌아볼까요?\n\"상자도 좋은데 빙글빙글 도는 상자라니!\"",
            question = "무엇을 할까요?"
        ),
        PageContentMapper(
            pageId = 700000000,
            pageTitle = "응꼬를 핥으려는 고양이 웬디!",
            pageImage = "image_3.gif",
            description = "너무 배가 고파서... 응꼬를 핥고 있어요...\n\"캬! 아냐! 난 단지 그루밍을 하고 있을 뿐이라고!\"\n웬디가 화가 났군요?\n",
            question = "무엇을 할까요?"
        ),
        PageContentMapper(
            pageId = 800000000,
            pageTitle = "그 녀석은 용서할 수 없는 녀석이야..",
            pageImage = "image_2.gif",
            description = "\"존... 그녀석은 내 먹이를 다 먹어 버렸지.. 괘씸한 녀석\"\n이런.. 존이 웬디의 밥을 모두 먹어버렸군요?\n\"물고기도 다 먹었으니 다시 빙글빙글 돌아볼까?\"\n웬디의 표정이 어쩐지 슬퍼보이는군요...",
            question = "돌아줄래요?"
        ),
        PageContentMapper(
            pageId = 900000000,
            pageTitle = "아니 이 녀석 집에 있었잖아?",
            pageImage = "image_4.gif",
            description = "이럴수가! 그렇게 찾았던 존이 언제 돌아와 집에서 자고 있었을까요?\n후.. 걱정도 많이 했지만 귀여우니까 봐준다.\n존 크리스탈.. 넌 내꺼야!..",
            question = ""
        )
    )

    val book = BookEntity(
        config = config,
        pageContents = pageContents,
        logic = logic
    )

    fun getSampleImageId(image : String) : Int = when (image) {
        "image_1.gif" -> R.raw.image_1
        "image_2.gif" -> R.raw.image_2
        "image_3.gif" -> R.raw.image_3
        "image_4.gif" -> R.raw.image_4
        "image_5.gif" -> R.raw.image_5
        "image_6.gif" -> R.raw.image_6
        "fish_cat.jpg" -> R.raw.fish_cat
        "game_end.jpg" -> R.raw.game_end
        else -> R.raw.game_end
    }
}