import com.example.quickquiz.data.QuizApiService
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit;


object RetrofitClient {
    private const val BASE_URL = "https://moc.pythonanywhere.com/"

    private val cookieJar = object : CookieJar {
        private var cookies: List<Cookie> = listOf()

        override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
            this.cookies = cookies
        }

        override fun loadForRequest(url: HttpUrl): List<Cookie> = cookies
    }

    private val client = OkHttpClient.Builder()
        .cookieJar(cookieJar)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(90, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()


    val apiService: QuizApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuizApiService::class.java)
}

