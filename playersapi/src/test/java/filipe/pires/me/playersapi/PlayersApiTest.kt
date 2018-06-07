package filipe.pires.me.playersapi

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class PlayersApiTest {

    private val publicApi = PlayersApi()

    @Test
    fun `when receives list with player without id, filter them out`() {
        val regularPlayer = DataTransferPlayer()
        regularPlayer.id = "some id"
        regularPlayer.playerName = "some name"
        val filteredPlayer = DataTransferPlayer()
        assertEquals(3, publicApi.extractPlayersResponse(Arrays.asList(
                regularPlayer,
                regularPlayer,
                filteredPlayer,
                regularPlayer
        )).size)
    }

    @Test
    fun `when receives a null player, callback failure`() {
        val callback = mock<PlayersCallback<DatabasePlayer>>()
        publicApi.extractPlayer(null, callback)
        verify(callback).onFailure()
    }

    @Test
    fun `when receives a data transfer object, callback with player`() {
        val player = mock<DataTransferPlayer>()
        whenever(player.id).thenReturn("some id")
        whenever(player.playerName).thenReturn("some name")
        val callback = mock<PlayersCallback<DatabasePlayer>>()
        publicApi.extractPlayer(player, callback)
        verify(callback).onSuccess(any())
    }

    @Test
    fun `when player doesn't have a description, send empty string`() {
        val dataPlayer = DataTransferPlayer()
        dataPlayer.id = "some id"
        dataPlayer.playerName = "player name"
        val player = publicApi.toDatabasePlayer(dataPlayer)
        assertEquals("", player.playerDescription)
    }

}