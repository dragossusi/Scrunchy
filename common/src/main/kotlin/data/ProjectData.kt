package data

interface ProjectData {
    val projectId:Long
    val name:String
    val description:String?
    val creator:UserData
}