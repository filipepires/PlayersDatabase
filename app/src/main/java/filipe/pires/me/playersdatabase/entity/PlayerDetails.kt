package filipe.pires.me.playersdatabase.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlayerDetails(val id: String,
                         val name: String,
                         val description: String) : Parcelable