package filipe.pires.me.playersapi

import retrofit2.Call
import retrofit2.http.*


internal interface PlayerRetrofitApi {

    @GET("players")
    fun getPlayers(): Call<List<Player>>

    @GET("player/{id}")
    fun getPlayer(@Path("id") id: String): Call<Player>

    @PUT("player/{id}")
    fun updatePlayer(@Path("id") id: String, @Body player: Player): Call<Player>

    @POST("player")
    fun addPlayer(@Body player: Player): Call<Player>

    @DELETE("player/{id}")
    fun deletePlayer(@Path("id") id: String): Call<Player>

}