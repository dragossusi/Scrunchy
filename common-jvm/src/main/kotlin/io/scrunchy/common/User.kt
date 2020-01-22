package io.scrunchy.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import data.UserData

@JsonClass(generateAdapter = true)
open class User(

    @Json(name = "user_id")
    override val userId: Long,

    @Json(name = "username")
    override var username: String,

    @Json(name = "name")
    override var name: String,

    @Json(name = "role")
    override var role: AppRole

) : UserData {


}