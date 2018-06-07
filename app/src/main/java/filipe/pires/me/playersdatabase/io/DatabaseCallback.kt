package filipe.pires.me.playersdatabase.io

interface DatabaseCallback<T> {
    fun onSuccess(response: T)
    fun onFailure()
}
