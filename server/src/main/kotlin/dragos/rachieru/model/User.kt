package dragos.rachieru.model

import data.UserData
import kotlinx.serialization.Serializable

@Serializable
class User(
    override val id: Long,
    override var username: String,
    override var name: String,
    override var role: String
) : UserData {
}