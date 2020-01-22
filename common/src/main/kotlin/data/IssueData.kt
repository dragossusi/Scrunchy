package data

import io.ktor.util.date.GMTDate

interface IssueData {
    val issueId: Long
    val project: ProjectData
    val creator: UserData
    val status: String
    val createdAt: Long
    var updatedAt: Long?
    var title: String
    var content: String

    enum class Status {
        OPEN,
        CLOSED
    }

}