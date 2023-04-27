package com.cheesejuice.fancymansion.domain

import com.cheesejuice.fancymansion.Comparison
import com.cheesejuice.fancymansion.DEFAULT_END_PAGE_ID
import com.cheesejuice.fancymansion.NOT_ASSIGN_COUNT
import com.cheesejuice.fancymansion.NOT_ASSIGN_ID
import com.cheesejuice.fancymansion.ReadMode
import com.cheesejuice.fancymansion.Relation
import com.cheesejuice.fancymansion.data.repository.BookRepository
import com.cheesejuice.fancymansion.data.source.local.database.model.*
import com.cheesejuice.fancymansion.data.source.local.storage.model.*
import javax.inject.Inject

class ReadBookUseCase @Inject constructor(
    private val bookRepository : BookRepository
) {

    /**
     * Make Sampe
     */
    suspend fun makeSample(userId : String, readMode: ReadMode, bookId : String) {
        bookRepository.makeSample(userId, readMode, bookId)
    }

    /**
     * Get BookObject From File
     */
    suspend fun getConfig(userId : String, readMode: ReadMode, bookId : String) : Config? {
        return bookRepository.getConfigFromFile(userId = userId, readMode = readMode, bookId = bookId)
    }

    suspend fun getLogic(userId : String, readMode: ReadMode, bookId : String) : Logic? {
        return bookRepository.getLogicFromFile(userId = userId, readMode = readMode, bookId = bookId)
    }

    suspend fun getPage(userId: String, readMode: ReadMode, bookId: String, pageId: Long, logic : Logic) : Page? {
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
        val pageLogic = findPageLogic?.copy(choiceItems = mutableListOf())
        findPageLogic?.let {
            for(choice in it.choiceItems){
                if(checkConditions(userId, readMode.name, bookId, choice.showConditions)){
                    pageLogic!!.choiceItems.add(choice)
                }
            }
        }
        return if(pageContent != null && pageLogic != null && pageImage != null) Page(pageContent, pageLogic, pageImage) else null
    }

    /**
     * ReadData From Room
     */
    suspend fun getReadData(userId : String, config: Config, initBook: Boolean): ReadData {
        if(!bookRepository.isUserDataExist(userId)){
            bookRepository.insertUserData(UserData(userId = userId))
        }

        if(initBook){
            bookRepository.deleteReadDataFromId(userId, config.readMode, config.bookId)
        }

        val readData = bookRepository.getReadData(userId = userId, readMode = config.readMode, bookId = config.bookId)?:let{
            val newReadData = ReadData(
                userId = userId, readMode = config.readMode, bookId = config.bookId,
                savePage = config.defaultStartPageId
            )
            bookRepository.deleteReadCountFromBookId(userId = userId, readMode = config.readMode, bookId = config.bookId)
            bookRepository.insertReadData(readData = newReadData)
            newReadData
        }
        return readData
    }

    suspend fun visitReadElement(userId : String, readMode: String, bookId : String, elementId : Long, isStartPage : Boolean = false) {
        if(bookRepository.isReadCountExist(userId, readMode, bookId, elementId)){
            if(!isStartPage){
                bookRepository.incrementReadCount(userId, readMode, bookId, elementId)
            }
        } else {
            bookRepository.insertReadCount(ReadCount(userId, readMode, bookId, elementId, 1))
        }
    }

    /**
     * Decide Route
     */
    suspend fun decideRoute(userId : String, readMode: String, bookId : String, choice : ChoiceItem) : Long {
        var decideRouteId = DEFAULT_END_PAGE_ID
        for(route in choice.routes){
            if(checkConditions(userId, readMode, bookId, route.routeConditions)){
                decideRouteId = route.routePageId
                break
            }
        }
        return decideRouteId
    }

    private suspend fun checkConditions(userId: String, readMode : String, bookId: String, conditions: List<Condition>): Boolean{
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

    private suspend fun checkCondition(userId: String, readMode : String, bookId: String, condition: Condition): Boolean =
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