package filipe.pires.me.playersdatabase.scene.main.recycler

import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View


class DefaultItemDecorator(
        private val padding: Int,
        private val divider: Drawable?
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        divider?.intrinsicHeight?.let { outRect.bottom = it }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft + padding
        val right = parent.width - parent.paddingRight - padding

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = divider?.intrinsicHeight?.let { top + it } ?: 0

            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
        }
    }
}