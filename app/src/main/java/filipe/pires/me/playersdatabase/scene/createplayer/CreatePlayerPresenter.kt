package filipe.pires.me.playersdatabase.scene.createplayer


class CreatePlayerPresenter(private val view: CreatePlayerContract.View) : CreatePlayerContract.Presentation {

    override fun presenterMandatoryNameError() {
        view.displayMandatoryNameError()
    }
}