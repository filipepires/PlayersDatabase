package filipe.pires.me.playersdatabase.io

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import filipe.pires.me.playersapi.PlayersApi
import filipe.pires.me.playersapi.PlayersCallback
import org.junit.Test

class DeletePlayerWorkerTest {

    private val api = mock<PlayersApi>()
    private val worker = DeletePlayerWorker(api)

    @Test
    fun `when is able to delete a player, return success`() {
        val callback = mock<DatabaseCallback<Any>>()
        whenever(api.deletePlayer(any(), any())).thenAnswer {
            val playersCallback = it.arguments[1] as PlayersCallback<Any>
            playersCallback.onSuccess(mock())
        }
        worker.deletePlayer("some id", callback)
        verify(callback).onSuccess(any())
    }

    @Test
    fun `when an error occurs, callback on failure`() {
        val callback = mock<DatabaseCallback<Any>>()
        whenever(api.deletePlayer(any(), any())).thenAnswer {
            val playersCallback = it.arguments[1] as PlayersCallback<Any>
            playersCallback.onFailure()
        }
        worker.deletePlayer("some id", callback)
        verify(callback).onFailure()
    }
}