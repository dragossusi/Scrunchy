package dragos.rachieru.model

import data.UserData
import kotlinx.serialization.Serializable

@Serializable
class User(
    override var email: String,
    override var name: String,
    override var role: String
) : UserData {
}