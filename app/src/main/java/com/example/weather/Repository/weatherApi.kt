import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

// Base URL
private val BASE_URL = "https://api.openweathermap.org/data/3.0/"

// Retrofit Builder
private val retrofit = Retrofit.Builder()
//    .addConverterFactory(GsonConverterFactory.create()) // Use Gson for JSON
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

// Weather API Service Interface
interface WeatherApiService {
    @GET("onecall")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("exclude") exclude: String,
        @Query("appid") apiKey: String
    ): ResponseBody // Replace with your data model
}

// Singleton Object for API
object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}