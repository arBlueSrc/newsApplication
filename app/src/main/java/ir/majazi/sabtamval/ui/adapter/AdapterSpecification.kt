package ir.majazi.sabtamval.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.Product
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.ItemSpecificationsBinding

class AdapterSpecification(private val clickListener:(Product)->Unit,private val list: List<Product>?) :
    RecyclerView.Adapter<AdapterSpecification.MyViewHolder>() {

    class MyViewHolder(private val binding: ItemSpecificationsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailScanner: Product , clickListener: (Product) -> Unit) {

            binding.mtvTypeGood.text = detailScanner.good?.name
            binding.mtvPropertyNumber.text = detailScanner.propertyNumber
            binding.btnLend.setOnClickListener {
                clickListener(detailScanner)

            }
            binding.btnEdit.setOnClickListener {
                Navigation.findNavController(it)
                    .navigate(R.id.action_specificationsFragment_to_editSpecificationsFragment)
            }

            //set buttons view
            if (detailScanner.is_loaned == 1){
                binding.apply {
                    btnTakeBack.visibility = View.VISIBLE
                    btnLend.visibility = View.GONE
                }
            }else{
                binding.apply {
                    btnTakeBack.visibility = View.GONE
                    btnLend.visibility = View.VISIBLE
                }
            }

            val adapterGoodProperty = AdapterGoodProperty(detailScanner.products)
            binding.rvGoodProperty.adapter = adapterGoodProperty
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_specifications,
                parent, false
            )
        )


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list!![position],clickListener)

    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
}


