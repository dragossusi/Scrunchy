package data

import kotlinx.serialization.Serializable

@Serializable
open class User(
    override val email: String,
    override val name: String,
    override val role: String
):UserData