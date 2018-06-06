package filipe.pires.me.playersapi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Player : Serializable {

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var playerName: String? = null

    @SerializedName("bio")
    @Expose
    var playerDescription: String? = null
}