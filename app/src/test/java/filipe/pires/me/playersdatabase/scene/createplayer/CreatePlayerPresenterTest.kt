package filipe.pires.me.playersdatabase.scene.createplayer

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Test

class CreatePlayerPresenterTest {

    private val view = mock<CreatePlayerContract.View>()
    private val presenter = CreatePlayerPresenter(view)

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