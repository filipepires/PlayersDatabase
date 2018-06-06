package filipe.pires.me.playersapi

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class PlayersApiTest {

    private val publicApi = PlayersApi()

    @Test
    fun `when receives list with player without id, filter them out`() {
        val regularPlayer = Player()
        regularPlayer.id = "some id"
        val filteredPlayer = Player()
        assertEquals(3, publicApi.extractPlayersResponse(Arrays.asList(
                regularPlayer,
                regularPlayer,
                filteredPlayer,
                regularPlayer
        )).size)
    }

    @Test
    fun `when receives a null player, callback failure`() {
        val callback = mock<PlayersCallback<Player>>()
        publicApi.extractPlayer(null, callback)
        verify(callback).onFailure()
    }

    @Test
    fun `when receives a player, callback with player`() {
        val player = mock<Player>()
        val callback = mock<PlayersCallback<Player>>()
        publicApi.extractPlayer(player, callback)
        verify(callback).onSuccess(player)
    }

    @Test
    fun `when user doesn't sent name and description, callback failure`() {
        val callback = mock<PlayersCallback<Player>>()
        publicApi.updatePlayer("some id", Player(), callback)
        verify(callback).onFailure()
    }

    @Test
    fun `when new player doesn't contains name and description, callback failure`() {
        val callback = mock<PlayersCallback<Player>>()
        publicApi.addPlayer(Player(), callback)
        verify(callback).onFailure()
    }

}