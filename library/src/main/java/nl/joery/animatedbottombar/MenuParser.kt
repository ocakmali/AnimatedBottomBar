package nl.joery.animatedbottombar

import android.content.Context
import android.view.MenuInflater
import android.widget.PopupMenu
import androidx.core.view.iterator


internal object MenuParser {
    fun parse(context: Context, resId: Int, exception: Boolean): ArrayList<AnimatedBottomBar.Tab> {
        val p = PopupMenu(context, null)
        MenuInflater(context).inflate(resId, p.menu)

        val tabs = ArrayList<AnimatedBottomBar.Tab>()
        for (item in p.menu.iterator()) {
            if (exception) {
                if (item.title == null) {
                    throw Exception("Menu item attribute 'title' is missing")
                }

                if (item.icon == null) {
                    throw Exception("Menu item attribute 'icon' for tab named '${item.title}' is missing")
                }
            }

            tabs.add(
                AnimatedBottomBar.Tab(
                    title = item.title.toString(),
                    icon = item.icon,
                    id = item.itemId
                )
            )
        }
        return tabs
    }
}