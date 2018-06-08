package filipe.pires.me.playersdatabase.utils

import android.support.annotation.StringRes

interface StringProvider {
    fun getString(@StringRes stringId: Int): String
}
