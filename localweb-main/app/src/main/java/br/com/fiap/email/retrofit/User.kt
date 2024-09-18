import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class UserPreferences(
    val preference1: String,
    val preference2: Boolean
)
data class UserAccountRegisterDto(
    val id: Long? = null,
    val name: String,
    val email: String,
    val password: String,
    val preferences: UserPreferences? = null
)

data class UserAccountExhibitDto(
    val id: Long,
    val username: String,
    val email: String
)

interface UserAccountApi {
    @POST("/account")
    suspend fun registerUser(@Body user: UserAccountRegisterDto): UserAccountExhibitDto

    @GET("/account/id/{id}")
    suspend fun getUserById(@Path("id") id: Long): UserAccountExhibitDto
}
