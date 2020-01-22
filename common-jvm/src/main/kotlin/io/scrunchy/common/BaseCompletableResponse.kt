package io.scrunchy.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import data.CompletableResponse

@JsonClass(generateAdapter = true)
open class BaseCompletableResponse(
    @Json(name="response_type")
    val responseType: ResponseType,
    @Json(name="errors")
    override val errors: List<String>?
) :CompletableResponse{

    override val isSuccess: Boolean
        get() = responseType==ResponseType.SUCCESS

    companion object {
        fun error(errors: List<String>?) =
            BaseCompletableResponse(ResponseType.ERROR, errors)
        fun success() = BaseCompletableResponse(ResponseType.SUCCESS, null)
    }

}