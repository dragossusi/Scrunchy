package dragos.rachieru.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
open class CompletableResponse(
    @SerialName("responseType")
    val responseType: ResponseType,
    @SerialName("errors")
    val errors: List<String>?
) {

    companion object {
        fun error(errors: List<String>?) = CompletableResponse(ResponseType.ERROR, errors)
        fun success() = CompletableResponse(ResponseType.SUCCESS, null)
    }

}