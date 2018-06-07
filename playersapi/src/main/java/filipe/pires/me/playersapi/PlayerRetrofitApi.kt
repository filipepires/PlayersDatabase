package filipe.pires.me.playersapi

import retrofit2.Call
import retrofit2.http.*


internal interface PlayerRetrofitApi {

    @GET("players")
    fun getPlayers(): Call<List<DataTransferPlayer>>

    @GET("player/{id}")
    fun getPlayer(@Path("id") id: String): Call<DataTransferPlayer>

    @PUT("player/{id}")
    fun updatePlayer(@Path("id") id: String, @Body player: DataTransferPlayer): Call<DataTransferPlayer>

    @POST("player")
    fun addPlayer(@Body player: DataTransferPlayer): Call<DataTransferPlayer>

    @DELETE("player/{id}")
    fun deletePlayer(@Path("id") id: String): Call<DataTransferPlayer>

}