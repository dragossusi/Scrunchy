package io.scrunchy.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
open class BaseDataResponse<T>(
    @Json(name="response_type")
    responseType: ResponseType,
    @Json(name="data")
    val data: T?,
    @Json(name="errors")
    errors: List<String>?
) : BaseCompletableResponse(responseType, errors) {

    companion object {
        fun <T> error(errors: List<String>?) =
            BaseDataResponse<T>(ResponseType.ERROR, null, errors)
        fun <T> success(data: T) =
            BaseDataResponse(ResponseType.SUCCESS, data, null)
    }
}