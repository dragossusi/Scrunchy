package data

import io.ktor.util.date.GMTDate

class Comment(
    val targetId:String,
    val userId:String,
    var content:String,
    val createdAt: GMTDate,
    var updatedAt:GMTDate
) {
}