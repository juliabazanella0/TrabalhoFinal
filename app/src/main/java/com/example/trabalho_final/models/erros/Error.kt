package com.example.trabalho_final.models.erros
import okhttp3.ResponseBody
import retrofit2.Retrofit

open class Error() {
    companion object {
        fun <T> toPOJO(retrofitInstance: Retrofit, errorClass: Class<T>, body: ResponseBody): T {
            val convertedObject = retrofitInstance.responseBodyConverter<T>(errorClass, Annotation::class.java.annotations)
                    .convert(body)

            return convertedObject!!
        }
    }
}