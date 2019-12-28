package data

import kotlinx.serialization.Serializable

@Serializable
class Project(
    val id: Long,
    val name: String,
    val description:String?,
    val creator_id: Long
) {
}