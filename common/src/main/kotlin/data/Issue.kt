package data

import io.ktor.util.date.GMTDate

interface IssueData {
    val id: Long
    val projectId:Long
    val userId: Long
    val status: Status
    val createdAt: GMTDate
    var updatedAt: GMTDate
    var title: String
    var content: String

    enum class Status {
        OPEN,
        CLOSED
    }

}