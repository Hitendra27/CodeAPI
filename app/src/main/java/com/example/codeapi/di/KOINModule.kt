package com.example.codeapi.di

import com.example.codeapi.network.CodeAPI
import com.example.codeapi.network.CodeListRepository
import com.example.codeapi.network.CodeListRepositoryImpl
import com.example.codeapi.viewmodel.CodeListViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

val networkModule = module {

    // provides the code repository implementation
    fun provideCodeRepo(codeAPI: CodeAPI): CodeListRepository = CodeListRepositoryImpl(codeAPI)

    // provide Gson object
    fun provideGson() = GsonBuilder().create()

    // provide logging interceptor
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }


    // provide okhttp client
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()

    // providing the retrofit builder
    fun provideCodeListApi(okHttpClient: OkHttpClient,gson: Gson): CodeAPI =
        Retrofit.Builder()
            .baseUrl(CodeAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
            .create(CodeAPI::class.java)

    single { provideGson() }
    single { provideLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideCodeListApi(get(), get()) }
    single { provideCodeRepo(get()) }
}

val viewModelModule = module {
    viewModel {
        CodeListViewModel(get())
    }
}