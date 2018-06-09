package filipe.pires.me.playersdatabase.scene.editplayer

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import org.junit.Test

class EditPlayerPresenterTest {

    private val view = mock<EditPlayerContract.View>()
    private val presenter = EditPlayerPresenter(view)

    @Test
    fun `when entering the view, present player details`() {
        val playerDetails = mock<PlayerDetails>()
        presenter.presentReceivedPlayer(playerDetails)
        verify(view).displayReceivedPlayer(playerDetails)
    }

    @Test
    fun `when user tries to save player without name, show error message`() {
        presenter.presenterMandatoryNameError()
        verify(view).displayMandatoryNameError()
    }

    @Test
    fun `when an error occur, show error message`() {
        presenter.presentGeneralError()
        verify(view).displayGeneralError()
    }
}