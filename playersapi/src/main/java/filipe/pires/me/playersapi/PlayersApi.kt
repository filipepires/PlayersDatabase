package filipe.pires.me.playersapi

import android.support.annotation.VisibleForTesting
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PlayersApi {

    companion object {
        private const val BASE_URL = "http://localhost:3000/"
    }

    private val restClient: Retrofit by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private val playersService = restClient.create(PlayerRetrofitApi::class.java)

    fun getPlayers(callback: PlayersCallback<List<Player>>) {
        val call = playersService.getPlayers()
        call.enqueue(object : Callback<List<Player>> {
            override fun onFailure(call: Call<List<Player>>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<List<Player>>, response: Response<List<Player>>) {
                callback.onSuccess(extractPlayersResponse(response.body()))
            }

        })
    }

    @VisibleForTesting
    fun extractPlayersResponse(players: List<Player>?): List<Player> =
            players?.filter { it.id != null } ?: emptyList()


    fun getPlayer(id: String, callback: PlayersCallback<Player>) {
        val call = playersService.getPlayer(id)
        call.enqueue(object : Callback<Player> {
            override fun onFailure(call: Call<Player>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<Player>, response: Response<Player>) {
                extractPlayer(response.body(), callback)
            }

        })
    }

    @VisibleForTesting
    fun extractPlayer(player: Player?, callback: PlayersCallback<Player>) {
        if (player != null)
            callback.onSuccess(player)
        else
            callback.onFailure()
    }

    fun updatePlayer(id: String, player: Player, callback: PlayersCallback<Player>) {
        if (player.playerName.isNullOrEmpty() && player.playerDescription.isNullOrEmpty())
            callback.onFailure()

        val call = playersService.updatePlayer(id, player)
        call.enqueue(object : Callback<Player> {
            override fun onFailure(call: Call<Player>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<Player>, response: Response<Player>) {
                extractPlayer(response.body(), callback)
            }

        })
    }

    fun addPlayer(player: Player, callback: PlayersCallback<Player>) {
        if (player.playerName.isNullOrEmpty() && player.playerDescription.isNullOrEmpty())
            callback.onFailure()

        val call = playersService.addPlayer(player)
        call.enqueue(object : Callback<Player> {
            override fun onFailure(call: Call<Player>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<Player>, response: Response<Player>) {
                extractPlayer(response.body(), callback)
            }

        })
    }

    fun addPlayer(id: String, callback: PlayersCallback<Player>) {
        val call = playersService.deletePlayer(id)
        call.enqueue(object : Callback<Player> {
            override fun onFailure(call: Call<Player>, t: Throwable?) {
                callback.onFailure()
            }

            override fun onResponse(call: Call<Player>, response: Response<Player>) {
                extractPlayer(response.body(), callback)
            }

        })
    }

}