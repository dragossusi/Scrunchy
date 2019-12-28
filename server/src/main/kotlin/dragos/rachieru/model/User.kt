package dragos.rachieru.model

import data.UserData
import io.ktor.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
class User(
    override val id: Long,
    override var username: String,
    override var name: String,
    override var role: String
) : UserData, Principal {

}