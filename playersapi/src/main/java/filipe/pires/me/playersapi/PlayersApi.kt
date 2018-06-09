package filipe.pires.me.playersapi

import android.support.annotation.VisibleForTesting
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PlayersApi {

    companion object {
        private const val BASE_URL = "http://10.0.2.2:3000/"
    }

    private val restClient: Retrofit by lazy {
        initRestClient()
    }

    private fun initRestClient(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build();
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private val playersService = restClient.create(PlayerRetrofitApi::class.java)

    fun getPlayers(callback: PlayersCallback<List<DatabasePlayer>>) {
        val call = playersService.getPlayers()
        call.enqueue(object : Callback<List<DataTransferPlayer>> {
            override fun onFailure(call: Call<List<DataTransferPlayer>>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<DataTransferPlayer>>, response: Response<List<DataTransferPlayer>>) {
                callback.onSuccess(extractPlayersResponse(response.body()))
            }

        })
    }

    @VisibleForTesting
    internal fun extractPlayersResponse(players: List<DataTransferPlayer>?): List<DatabasePlayer> =
            players?.filter { it.id != null && it.playerName != null }
                    ?.map { toDatabasePlayer(it) }
                    ?: emptyList()


    fun getPlayer(id: String, callback: PlayersCallback<DatabasePlayer>) {
        val call = playersService.getPlayer(id)
        call.enqueue(object : Callback<DataTransferPlayer> {
            override fun onFailure(call: Call<DataTransferPlayer>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<DataTransferPlayer>, response: Response<DataTransferPlayer>) {
                response.body()?.let { extractPlayer(it, callback) } ?: callback.onFailure()
            }

        })
    }

    @VisibleForTesting
    internal fun extractPlayer(player: DataTransferPlayer?, callback: PlayersCallback<DatabasePlayer>) {
        if (player?.id != null && player.playerName != null)
            callback.onSuccess(toDatabasePlayer(player))
        else
            callback.onFailure()
    }

    @VisibleForTesting
    internal fun toDatabasePlayer(player: DataTransferPlayer): DatabasePlayer {
        return DatabasePlayer(player.id!!, player.playerName!!, player.playerDescription ?: "")
    }

    fun updatePlayer(id: String, player: DatabasePlayer, callback: PlayersCallback<Any>) {
        val call = playersService.updatePlayer(id, toDataTransferObject(player))
        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback.onSuccess(Any())
            }

        })
    }

    private fun toDataTransferObject(player: DatabasePlayer): DataTransferPlayer {
        val dataTransferPlayer = DataTransferPlayer()
        dataTransferPlayer.id = player.id
        dataTransferPlayer.playerName = player.playerName
        dataTransferPlayer.playerDescription = player.playerDescription
        return dataTransferPlayer
    }

    fun addPlayer(player: PlayerInformation, callback: PlayersCallback<Any>) {
        val databasePlayer = DataTransferPlayer()
        databasePlayer.playerName = player.playerName
        databasePlayer.playerDescription = player.playerDescription
        val call = playersService.addPlayer(databasePlayer)
        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback.onSuccess(Any())
            }

        })
    }

    fun deletePlayer(id: String, callback: PlayersCallback<Any>) {
        val call = playersService.deletePlayer(id)
        call.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback.onSuccess(Any())
            }
        })
    }
}