package filipe.pires.me.playersdatabase.io

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.playersapi.DatabasePlayer
import filipe.pires.me.playersapi.PlayersApi
import filipe.pires.me.playersapi.PlayersCallback
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import org.junit.Test

class PlayerDetailsWorkerTest {

    private val api = mock<PlayersApi>()
    private val worker = PlayerDetailsWorker(api)

    @Test
    fun `when receives a database player, return player details`() {
        val callback = mock<DatabaseCallback<PlayerDetails>>()
        val response = DatabasePlayer("some id", "some name", "")
        whenever(api.getPlayer(any(), any())).thenAnswer {
            val playersCallback = it.arguments[1] as PlayersCallback<DatabasePlayer>
            playersCallback.onSuccess(response)
        }
        worker.fetchPlayerWithId("some id", callback)
        verify(callback).onSuccess(any())
    }

    @Test
    fun `when an error occurs, callback on failure`() {
        val callback = mock<DatabaseCallback<PlayerDetails>>()
        whenever(api.getPlayer(any(),any())).thenAnswer {
            val playersCallback = it.arguments[1] as PlayersCallback<DatabasePlayer>
            playersCallback.onFailure()
        }
        worker.fetchPlayerWithId("some id", callback)
        verify(callback).onFailure()
    }

}