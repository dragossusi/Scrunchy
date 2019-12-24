package dragos.rachieru.model
import kotlinx.serialization.*

@Serializable
class BaseResponse<T>(
    @SerialName("responseType")
    val responseType: ResponseType,
    @SerialName("data")
    val data: T?,
    @SerialName("errors")
    val errors: List<String>?
) {

    @Serializable
    enum class ResponseType {
        @SerialName("success")
        SUCCESS,
        @SerialName("error")
        ERROR
    }
}