import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.gravardados.R

data class MenuItem(val data: String, val periodo: String, val categoria: String, val nome: String, val selo: String?)

class MenuItemsAdapter : RecyclerView.Adapter<MenuItemsAdapter.MenuItemViewHolder>() {

    private var menuItems: List<MenuItem> = emptyList()

    fun submitList(items: List<MenuItem>) {
        menuItems = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        holder.bind(menuItems[position])
    }

    override fun getItemCount(): Int = menuItems.size

    class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewName: TextView = itemView.findViewById(R.id.text_view_name)
        private val textViewCategory: TextView = itemView.findViewById(R.id.text_view_category)

        fun bind(menuItem: MenuItem) {
            textViewName.text = menuItem.nome
            textViewCategory.text = menuItem.categoria
        }
    }
}
