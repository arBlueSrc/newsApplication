package ir.majazi.sabtamval.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.Part
import com.example.global.modules.app.model.Store
import com.google.android.material.textview.MaterialTextView

class BottomSheetAdapterParts (
    private var mList: List<Part>?,
    private val name: (Part) -> Unit
) : RecyclerView.Adapter<BottomSheetAdapterParts.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(ir.majazi.sabtamval.R.layout.item_bottom_sheet, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList?.get(position)
        holder.item.text = item?.name
        holder.item.setOnClickListener {
            name(item!!)
        }
    }

    override fun getItemCount(): Int {
        return mList?.size!!
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val item: MaterialTextView = itemView.findViewById(ir.majazi.sabtamval.R.id.txt)
    }

}