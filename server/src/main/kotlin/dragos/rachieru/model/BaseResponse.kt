package dragos.rachieru.model

import kotlinx.serialization.ContextualSerialization
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class BaseResponse<T>(
    @SerialName("responseType")
    val responseType: ResponseType,
    @SerialName("data")
    @ContextualSerialization
    val data: T?,
    @SerialName("errors")
    val errors: List<String>?
) {

    companion object {
        fun <T> error(errors: List<String>?) = BaseResponse<T>(ResponseType.ERROR, null, errors)
        fun <T> success(data: T) = BaseResponse(ResponseType.SUCCESS, data, null)
    }
}