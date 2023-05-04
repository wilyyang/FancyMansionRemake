package com.cheesejuice.fancymansion.core.domain

import com.cheesejuice.fancymansion.R
import com.cheesejuice.fancymansion.core.common.*
import com.cheesejuice.fancymansion.core.common.sample.Sample
import com.cheesejuice.fancymansion.core.data.repository.BookRepository
import com.cheesejuice.fancymansion.core.entity.book.*
import javax.inject.Inject

class ReadBookUseCase @Inject constructor(
    private val bookRepository : BookRepository
) {

    /**
     * Make Sample
     */
    // make Sample
    suspend fun makeSample(userId : String, readMode: ReadMode, bookId : String) {
        bookRepository.run {
            initRootDir()
            initUserDir(userId)
            initBookDir(userId, readMode, bookId)

            makeConfigFile(Sample.book.config)
            makeLogicFile(Sample.book.logic, userId, readMode, bookId)

            for(pageContent in Sample.book.pageContents){
                makePageFile(pageContent, userId, readMode, bookId)
            }
            val array = arrayOf("image_1.gif", "image_2.gif", "image_3.gif", "image_4.gif", "image_5.gif", "image_6.gif", "fish_cat.jpg", "game_end.jpg")
            val arrayId = arrayOf(R.raw.image_1, R.raw.image_2, R.raw.image_3, R.raw.image_4, R.raw.image_5, R.raw.image_6, R.raw.fish_cat, R.raw.game_end)
            array.forEachIndexed { index, imageName ->
                bookRepository.makeImageFromResource(userId, readMode, bookId, imageName, arrayId[index])
            }
        }
    }

    /**
     * Get BookObject From File
     */
    suspend fun getConfig(userId : String, readMode: ReadMode, bookId : String) : ConfigEntity? {
        return bookRepository.getConfigFromFile(userId = userId, readMode = readMode, bookId = bookId)
    }

    suspend fun getLogic(userId : String, readMode: ReadMode, bookId : String) : LogicEntity? {
        return bookRepository.getLogicFromFile(userId = userId, readMode = readMode, bookId = bookId)
    }

    suspend fun getPage(userId: String, readMode: ReadMode, bookId: String, pageId: Long, logic : LogicEntity) : PageEntity? {
        val pageContent = bookRepository.getPageFromFile(userId = userId, readMode = readMode, bookId = bookId, pageId = pageId)
        val pageImage = pageContent?.let {
            bookRepository.getImageFromFile(
                userId = userId,
                readMode = readMode,
                bookId = bookId,
                image = it.pageImage
            )
        }

        val findPageLogic = logic.logics.find { it.pageId == pageId }
        val checkedChoiceList : MutableList<ChoiceItemEntity> = mutableListOf()
        findPageLogic?.let {
            for(choice in it.choiceItems){
                if(checkConditions(userId, readMode.name, bookId, choice.showConditions)){
                    checkedChoiceList.add(choice)
                }
            }
        }
        val pageLogic = findPageLogic?.copy(choiceItems = checkedChoiceList)
        return if(pageContent != null && pageLogic != null && pageImage != null) PageEntity(pageContent, pageLogic, pageImage) else null
    }

    /**
     * ReadData From Room
     */
    suspend fun getReadData(userId : String, config: ConfigEntity, initBook: Boolean): ReadEntity {
        if(!bookRepository.isUserEntityExist(userId)){
            bookRepository.insertUserEntity(UserEntity(userId = userId))
        }

        if(initBook){
            bookRepository.deleteReadEntityFromId(userId, config.readMode, config.bookId)
        }

        val readData = bookRepository.getReadEntity(userId = userId, readMode = config.readMode, bookId = config.bookId)?:let{
            val newReadEntity = ReadEntity(
                userId = userId, readMode = config.readMode, bookId = config.bookId,
                savePage = config.defaultStartPageId
            )
            bookRepository.deleteCountEntityFromBookId(userId = userId, readMode = config.readMode, bookId = config.bookId)
            bookRepository.insertReadEntity(readEntity = newReadEntity)
            newReadEntity
        }
        return readData
    }

    suspend fun visitReadElement(userId : String, readMode: String, bookId : String, elementId : Long, isStartPage : Boolean = false) {
        if(bookRepository.isCountEntityExist(userId, readMode, bookId, elementId)){
            if(!isStartPage){
                bookRepository.incrementCountEntity(userId, readMode, bookId, elementId)
            }
        } else {
            bookRepository.insertCountEntity(CountEntity(userId, readMode, bookId, elementId, 1))
        }
    }

    /**
     * Decide Route
     */
    suspend fun decideRoute(userId : String, readMode: String, bookId : String, choice : ChoiceItemEntity) : Long {
        var decideRouteId = DEFAULT_END_PAGE_ID
        for(route in choice.routes){
            if(checkConditions(userId, readMode, bookId, route.routeConditions)){
                decideRouteId = route.routePageId
                break
            }
        }
        return decideRouteId
    }

    private suspend fun checkConditions(userId: String, readMode : String, bookId: String, conditions: List<ConditionEntity>): Boolean{
        var relationResult = true
        var nextRelation = Relation.AND
        for(condition in conditions){
            // 현재 조건을 검사 한다
            val conditionResult = checkCondition(userId, readMode, bookId, condition)

            // 현재 조건 결과와 이전 결과의 관계 결과를 도출 한다
            relationResult = nextRelation.check(relationResult, conditionResult)

            // 현재 조건으로부터 관계 연산자를 가져 온다
            nextRelation = Relation.from(condition.nextRelation)

            // 관계 결과가 true 이고 다음 관계가 OR인 경우 최종 반환 한다
            if(relationResult && nextRelation == Relation.OR) break
        }
        return relationResult
    }

    private suspend fun checkCondition(userId: String, readMode : String, bookId: String, condition: ConditionEntity): Boolean =
        condition.run{
            val targetCount1 = bookRepository.getElementCount(userId, readMode, bookId, targetId1)?:0
            val targetCount2 = if (targetId2 == NOT_ASSIGN_ID) {
                targetCount
            } else {
                bookRepository.getElementCount(userId, readMode, bookId, this.targetId2)?:0
            }

            if(targetCount2 != NOT_ASSIGN_COUNT){
                Comparison.from(comparison).compare(targetCount1, targetCount2)
            }else{
                false
            }
        }
}