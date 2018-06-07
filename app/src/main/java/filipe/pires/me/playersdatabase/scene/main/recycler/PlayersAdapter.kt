package filipe.pires.me.playersdatabase.scene.main.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import filipe.pires.me.playersdatabase.R
import kotlinx.android.synthetic.main.recycler_item_player.view.*


class PlayersAdapter(
        private val players: List<String>,
        private val playersClickListeners: List<View.OnClickListener>
) : RecyclerView.Adapter<PlayersAdapter.PlayersHolder>() {

    override fun getItemCount(): Int = players.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersHolder = PlayersHolder(
            LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.recycler_item_player, parent, false))

    override fun onBindViewHolder(holder: PlayersHolder, position: Int) {
        holder.itemView.setOnClickListener(playersClickListeners[position])
        holder.itemView.player_name.text = players[position]
    }

    class PlayersHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}