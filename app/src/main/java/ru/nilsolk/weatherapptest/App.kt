package ru.nilsolk.weatherapptest

import android.app.Application
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nilsolk.weatherapptest.data_source.cache_data_source.WeatherDatabase
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.Api
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.WeatherRepository
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.models.NetworkRequest
import ru.nilsolk.weatherapptest.data_source.cloud_data_source.WeatherRepositoryImpl

class App : Application() {
    private lateinit var weatherDatabase: WeatherDatabase

    companion object {
        private lateinit var retrofit: Retrofit
        private const val BASE_URL = "https://weatherapi-com.p.rapidapi.com/"
    }

    override fun onCreate() {
        super.onCreate()
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()
            val request: Request = original.newBuilder()
                .header("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com")
                .header("X-RapidAPI-Key", "7fec0813f2msh8f893d8b05d8a34p1c4932jsna0768d26404a")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        })

        val client: OkHttpClient = httpClient.build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun provideWeatherRepository(): WeatherRepository {
        val retrofit = retrofit.create(Api::class.java)
        val networkRequest = NetworkRequest()
        return WeatherRepositoryImpl(retrofit, networkRequest)
    }


}