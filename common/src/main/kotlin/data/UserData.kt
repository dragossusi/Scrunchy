package data

interface UserData {
    val userId: Long
    val username: String
    val name: String
    val role: AppRoleData
}