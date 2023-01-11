package ir.majazi.sabtamval.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.Product
import com.google.android.material.textfield.TextInputEditText
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.ItemAddGoodBinding

class AdapterEditGood(private val list: Product?, private val clickListener:(TextInputEditText)->Unit) :
    RecyclerView.Adapter<AdapterEditGood.MyViewHolder>() {



    inner class MyViewHolder(private val binding: ItemAddGoodBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(detailScanner: Product , clickListener: (TextInputEditText) -> Unit,position: Int) {
            binding.edtAddGood.setText(detailScanner.products!![position].value)





//            binding.imvTrust.setOnClickListener {
//                clickListener(detailScanner)
//
//            }
//            binding.imvEditInformation.setOnClickListener {
//                Navigation.findNavController(it)
//                    .navigate(R.id.action_specificationsFragment_to_editSpecificationsFragment)
//            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_add_good,
                parent, false
            )
        )


    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list!![position],clickListener,position)

    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
}