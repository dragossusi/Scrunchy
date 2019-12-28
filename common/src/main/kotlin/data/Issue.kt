package data

import io.ktor.util.date.GMTDate

class Issue(
    val id:String,
    val userId:String,
    val status: Status,
    val createdAt: GMTDate,
    var updatedAt:GMTDate
) {

    enum class Status{
        OPEN,
        CLOSED
    }

}