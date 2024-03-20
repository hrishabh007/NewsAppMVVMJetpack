package com.app.newsappmvvmjetpack.data.remote

import com.app.newsappmvvmjetpack.common.Constants
import com.app.newsappmvvmjetpack.data.remote.dto.getCategory.GetCategoryDTO
import com.app.newsappmvvmjetpack.data.remote.dto.getNewsDetail.GetNewsDetailDTO
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.GetRecentPostDTO
import com.app.newsappmvvmjetpack.data.remote.dto.getsettings.GetSettingsDTO
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RetrofitService {


    @GET(Constants.GETCALLSETTINGS)
    suspend fun getSettings(): GetSettingsDTO

    @GET(Constants.GETCALLCATEGORY)
    suspend fun getCategory(): GetCategoryDTO


    @GET(Constants.GETRECENTPOST)
    suspend fun getRecentPost(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): GetRecentPostDTO

    @GET(Constants.GETVIDEOPOST)
    suspend fun getVideoPost(
        @Query("page") page: Int,
        @Query("count") count: Int
    ): GetRecentPostDTO

    @GET(Constants.GETNEWSDETAIL)
    suspend fun getNewsDetail(
        @Query("id") id: Int
    ): GetNewsDetailDTO

    companion object {

        private val interceptor = run {

            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            }
        }
        val okHttpClient =
            OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .addInterceptor(RequestInterceptor(object :
                    RequestInterceptor.OnRequestInterceptor {
                    override fun provideBodyMap(): HashMap<String, String> {
                        val hashMap = HashMap<String, String>()
                        hashMap["api_key"] = Constants.API_KEY
                        return hashMap
                    }

                    override fun provideHeaderMap(): HashMap<String, String> {
                        val hashMap = HashMap<String, String>()
                        /* if (MainApplication.sharedPreference.getPref(USER_ACCESS_TOKEN, "").isNotEmpty()) {
                             hashMap["Authorization"] = "Bearer " + MainApplication.sharedPreference.getPref(USER_ACCESS_TOKEN, "")
                         }*/
                        return hashMap
                    }

                    override fun removeFromBody(): ArrayList<String> {
                        return arrayListOf()
                    }

                }))
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build()
        var retrofitService: RetrofitService? = null
        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }

    }
}