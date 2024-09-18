import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

data class UserPreferences(
    val theme: Theme,
    val color: String,
    val category: Category
)

enum class Theme {
    LIGHT, DARK
}

enum class Category(val value: String) {
    PESSOAL("pessoal"),
    TRABALHO("trabalho"),
    SOCIAL("social"),
    PROMOCAO("promocao");

    companion object {
        fun fromString(value: String): Category {
            return values().find { it.value.equals(value, ignoreCase = true) }
                ?: throw IllegalArgumentException("Unknown category: $value")
        }
    }
}

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
