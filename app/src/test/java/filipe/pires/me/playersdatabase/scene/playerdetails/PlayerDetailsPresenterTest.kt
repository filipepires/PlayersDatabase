package filipe.pires.me.playersdatabase.scene.playerdetails

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import filipe.pires.me.playersdatabase.entity.PlayerDetails
import filipe.pires.me.playersdatabase.utils.StringProvider
import org.junit.Test

class PlayerDetailsPresenterTest {

    private val view = mock<PlayerDetailsContract.View>()
    private val stringProvider = mock<StringProvider>()
    private val presenter = PlayerDetailsPresenter(view, stringProvider)

    @Test
    fun `when player details contains description, display description`(){
        presenter.presentPlayerDetails(PlayerDetails("id","name","description"))
        verify(view).displayName("name")
        verify(view).displayDescription("description")
    }

    @Test
    fun `when player details doesn't contain description, display default description`(){
        doReturn("default description").`when`(stringProvider).getString(any())
        presenter.presentPlayerDetails(PlayerDetails("id","name",""))
        verify(view).displayName("name")
        verify(view).displayDescription("default description")
    }

}