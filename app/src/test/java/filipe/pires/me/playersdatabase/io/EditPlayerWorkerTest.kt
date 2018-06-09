package filipe.pires.me.playersdatabase.io

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.playersapi.PlayersApi
import filipe.pires.me.playersapi.PlayersCallback
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import org.junit.Test

class EditPlayerWorkerTest {

    private val api = mock<PlayersApi>()
    private val worker = EditPlayerWorker(api)

    @Test
    fun `when receives a database player, return player details`() {
        val callback = mock<DatabaseCallback<Any>>()
        whenever(api.updatePlayer(any(), any(), any())).thenAnswer {
            val playersCallback = it.arguments[2] as PlayersCallback<Any>
            playersCallback.onSuccess(mock())
        }
        worker.updatePlayerDetails(PlayerDetails("some id", "some name", "some description"), callback)
        verify(callback).onSuccess(any())
    }

    @Test
    fun `when an error occurs, callback on failure`() {
        val callback = mock<DatabaseCallback<Any>>()
        whenever(api.updatePlayer(any(), any(), any())).thenAnswer {
            val playersCallback = it.arguments[2] as PlayersCallback<Any>
            playersCallback.onFailure()
        }
        worker.updatePlayerDetails(PlayerDetails("some id", "some name", "some description"), callback)
        verify(callback).onFailure()
    }
}