package filipe.pires.me.playersapi


interface PlayersCallback<T> {
    fun onSuccess(response: T)
    fun onFailure()
}