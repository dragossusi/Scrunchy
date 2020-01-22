package dragos.rachieru.model

import data.AppRoleData
import data.UserData
import io.ktor.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
class User(
    override val userId: Long,
    override var username: String,
    override var name: String,
    override var role: AppRole
) : UserData, Principal {



}