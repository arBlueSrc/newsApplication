package ir.majazi.sabtamval.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.Product
import com.example.global.modules.app.model.ProductX
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.ItemGoodPropertyBinding

class AdapterTrust(private val list: List<ProductX>?) : RecyclerView.Adapter<AdapterTrust.MyViewHolder>() {


    class MyViewHolder(private val binding: ItemGoodPropertyBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(detailScanner: ProductX) {
            binding.mtvNameGoodPeroperty.text = detailScanner.goodProperty?.name
            binding.mtvValueGoodProperty.text = detailScanner.value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_good_property,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list!![position])
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
}