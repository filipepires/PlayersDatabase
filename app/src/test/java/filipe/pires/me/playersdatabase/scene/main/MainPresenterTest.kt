package filipe.pires.me.playersdatabase.scene.main

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import filipe.pires.me.playersdatabase.entity.Player
import org.junit.Test
import java.util.*

class MainPresenterTest {

    private val view = mock<MainContract.View>()
    private val presenter = MainPresenter(view)

    @Test
    fun `when player list is empty, display empty list message`() {
        presenter.presentPlayers(emptyList())
        verify(view).displayEmptyListMessage()
    }

    @Test
    fun `when has a player list, display player list`() {
        presenter.presentPlayers(Arrays.asList(Player("id","name")))
        verify(view).displayPlayers(any())
    }

    @Test
    fun `when an error occur, show error message`() {
        presenter.presentGeneralError()
        verify(view).displayGeneralError()
    }
}