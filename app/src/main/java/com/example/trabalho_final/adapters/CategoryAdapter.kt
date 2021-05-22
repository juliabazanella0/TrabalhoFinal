package com.example.trabalho_final.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho_final.R
import com.example.trabalho_final.models.Category
import com.example.trabalhqo_final.dao.CategoryDAO
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    private var dao: CategoryDAO = CategoryDAO()
    private var categories: List<Category> = listOf()
    private var selectedCategory: Category? = null

    init {
        dao.getAll { response ->
            this.categories = response.data.categories
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = categories.size                   //retorna quant categorias

    override fun getItemViewType(position: Int): Int {
        val category = categories[position]
        return if(category.equals(selectedCategory)) R.layout.item_category_selected else R.layout.item_category
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =         //montaviewHolder
        ViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(viewType, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {         //vincula categoria ao ViewHolder
        val category = categories[position]
        holder.fillView(category)
    }

    fun getSelectedCategory(): Category? {
        return this.selectedCategory
    }

    fun setSelectedCategory(category: Category?) {
        this.selectedCategory = category
        if(category != null) {
            notifyItemChanged(categories.indexOf(category))
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun fillView(category: Category){
            itemView.lbCategory.text = category.name

            itemView.setOnClickListener {
                var lastSelected = selectedCategory
                if(lastSelected != null) {
                    selectedCategory = null
                    notifyItemChanged(categories.indexOf(lastSelected))      //notificar mudança no item
                }

                if(!category.equals(lastSelected)) {
                    selectedCategory = category
                }

                notifyItemChanged(categories.indexOf(category))
            }
        }
    }
}