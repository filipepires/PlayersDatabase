package filipe.pires.me.playersdatabase.scene.editplayer

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import org.junit.Test

class EditPlayerInteractorTest {

    private val presenter = mock<EditPlayerContract.Presentation>()
    private val interactor = EditPlayerInteractor(presenter)

    @Test
    fun `when entering the view, present player details`() {
        val playerDetails = mock<PlayerDetails>()
        interactor.onCreate(playerDetails)
        verify(presenter).presentReceivedPlayer(playerDetails)
    }
}