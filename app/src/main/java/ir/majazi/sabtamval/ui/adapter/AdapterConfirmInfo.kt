package ir.majazi.sabtamval.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.AddProduct2
import com.example.global.modules.app.model.Product
import com.example.global.modules.app.model.ResultX
import com.example.global.modules.app.model.ResultXX
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.ItemAddGoodBinding
import ir.majazi.sabtamval.databinding.ItemBottomSheetBinding
import ir.majazi.sabtamval.databinding.ItemGoodPropertyBinding
import ir.majazi.sabtamval.databinding.ItemSpecificationsBinding

class AdapterConfirmInfo (private val list: List<ResultXX>?) :
    RecyclerView.Adapter<AdapterConfirmInfo.MyViewHolder>() {



    class MyViewHolder(private val binding: ItemGoodPropertyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(detailScanner: ResultXX ) {
            binding.mtvNameGoodPeroperty.text = detailScanner.name
            binding.mtvValueGoodProperty.text = detailScanner.value
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
                R.layout.item_good_property,
                parent, false
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