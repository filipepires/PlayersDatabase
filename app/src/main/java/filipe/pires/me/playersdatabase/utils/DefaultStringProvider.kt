package filipe.pires.me.playersdatabase.utils

import android.content.Context

class DefaultStringProvider(private val context: Context) : StringProvider {
    override fun getString(stringId: Int): String = context.getString(stringId)


}