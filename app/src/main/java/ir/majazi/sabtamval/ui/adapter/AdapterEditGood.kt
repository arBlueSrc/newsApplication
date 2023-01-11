package ir.majazi.sabtamval.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.ProductX
import com.example.global.modules.app.model.ProductXX
import com.example.global.modules.app.model.ResultXX
import com.google.android.material.textfield.TextInputEditText
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.ItemAddGoodBinding

class AdapterEditGood(private val list: List<ProductX>?, private val clickListener:(TextInputEditText)->Unit) :
    RecyclerView.Adapter<AdapterEditGood.MyViewHolder>() {
    var array:MutableList<ResultXX> = mutableListOf()


    init {
        list?.forEach {

            array.add(ResultXX(0,"",""))
        }
    }

    inner class MyViewHolder(private val binding: ItemAddGoodBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(detailScanner: ProductX, clickListener: (TextInputEditText) -> Unit, position: Int) {
            binding.edtAddGood.setText(detailScanner.value)



            clickListener(binding.edtAddGood)

            binding.edtAddGood.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    val resultX= ResultXX(detailScanner.id,detailScanner.value,p0.toString())
                    array[position] = resultX
                }

            })

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