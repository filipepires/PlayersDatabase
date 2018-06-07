package filipe.pires.me.playersdatabase.io

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.playersapi.DatabasePlayer
import filipe.pires.me.playersapi.PlayersApi
import filipe.pires.me.playersapi.PlayersCallback
import filipe.pires.me.playersdatabase.entity.Player
import org.junit.Test
import java.util.*

class DatabaseWorkerTest {

    private val api = mock<PlayersApi>()
    private val worker = DatabaseWorker(api)

    @Test
    fun `when receives a list of database player, return a list of players`() {
        val callback = mock<DatabaseCallback<List<Player>>>()
        val databasePlayer = DatabasePlayer("some id", "some name", "")
        val response = Arrays.asList(databasePlayer)
        whenever(api.getPlayers(any())).thenAnswer {
            val playersCallback = it.arguments[0] as PlayersCallback<List<DatabasePlayer>>
            playersCallback.onSuccess(response)
        }
        worker.fetchPlayers(callback)
        verify(callback).onSuccess(any())
    }

    @Test
    fun `when an error occurs, callback on failure`() {
        val callback = mock<DatabaseCallback<List<Player>>>()
        whenever(api.getPlayers(any())).thenAnswer {
            val playersCallback = it.arguments[0] as PlayersCallback<List<DatabasePlayer>>
            playersCallback.onFailure()
        }
        worker.fetchPlayers(callback)
        verify(callback).onFailure()
    }
}