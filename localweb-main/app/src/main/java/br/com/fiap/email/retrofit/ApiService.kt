import br.com.fiap.email.retrofit.Email
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @GET("email")
    suspend fun getEmails(): List<Email>

    @GET("email/id/{id}")
    suspend fun getEmail(@Path("id") id: Long): Email

    @POST("email")
    suspend fun sendEmail(@Body email: Email)

    @DELETE("email/id/{id}")
    suspend fun deleteEmail(@Path("id") id: Long): Response<Unit>
}


