package data

import kotlinx.serialization.Serializable

@Serializable
class Project(
    var _id: Long,
    var name: String,
    var description:String?,
    var creator_id: Long
)