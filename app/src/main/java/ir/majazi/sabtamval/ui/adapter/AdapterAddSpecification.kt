package ir.majazi.sabtamval.ui.adapter

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.AddProduct2
import com.example.global.modules.app.model.Product
import com.example.global.modules.app.model.ResultX
import com.example.global.modules.app.model.ResultXX
import com.google.android.material.textfield.TextInputEditText
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.ItemAddGoodBinding
import ir.majazi.sabtamval.databinding.ItemSpecificationsBinding
import ir.majazi.sabtamval.util.Test

class AdapterAddSpecification (private val list: List<ResultX>?,private val clickListener:(TextInputEditText)->Unit) :
    RecyclerView.Adapter<AdapterAddSpecification.MyViewHolder>() {
    var array:MutableList<ResultXX> = mutableListOf()

    init {
        list?.forEach {

            array.add(ResultXX(0,"",""))
        }
    }




   inner class MyViewHolder(private val binding: ItemAddGoodBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(detailScanner: ResultX , clickListener: (TextInputEditText) -> Unit,position: Int) {
            binding.edtAddGood.hint = detailScanner.name


            clickListener(binding.edtAddGood)



            binding.edtAddGood.addTextChangedListener(object :TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    val resultX= ResultXX(detailScanner.id,detailScanner.name,p0.toString())
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