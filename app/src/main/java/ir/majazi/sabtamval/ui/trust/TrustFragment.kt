package ir.majazi.sabtamval.ui.trust

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.Employee
import com.example.global.modules.app.model.Part
import com.example.global.modules.app.model.Person
import com.example.global.modules.app.model.ResultXXX
import com.example.global.network.resource.Resource
import com.example.global.utils.extensions.dialog
import com.example.global.utils.extensions.launchAndRepeatWithViewLifecycle
import com.example.global.utils.extensions.toast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentTrustBinding
import ir.majazi.sabtamval.ui.adapter.AdapterTrust
import ir.majazi.sabtamval.ui.adapter.BottomSheetAdapterEmployee
import ir.majazi.sabtamval.ui.adapter.BottomSheetAdapterParts
import ir.majazi.sabtamval.ui.adapter.BottomSheetAdapterPerson

@AndroidEntryPoint
class TrustFragment : Fragment() {

    private lateinit var binding: FragmentTrustBinding
    private var  args:TrustFragmentArgs?=null
    private lateinit var dialog: BottomSheetDialog
    private lateinit var itemAdapterBottomSheetPerson: BottomSheetAdapterPerson
    private lateinit var recyclerView: RecyclerView
    private  var listPart:List<ResultXXX>? = null
    private  val viewModel:TrustViewModel by viewModels()
    private lateinit var personId:String



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrustBinding.inflate(inflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "امانات"
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        getAddProduct()

        args = TrustFragmentArgs.fromBundle(requireArguments())
        Log.i("TAG", "onViewCreated: ${args?.product?.products?.get(0)?.value.toString()}")
        binding.tvPersonTrust.text = args?.name
        binding.tvStoreTrust.text = args?.store
        binding.tvPartTrust.text = args?.part
        binding.tvTypeTrust.text = args?.typeGood
        binding.tvProprtyNumberTrust.text = args?.propertyNumber

        val adapter = AdapterTrust(args?.product?.products)
        binding.rvTrust.adapter = adapter


        binding.autoCompleteTrust.setOnClickListener {
            showBottomSheetPerson(listPart)
        }

        binding.btnSaveLend.setOnClickListener {
            lend()
        }
    }


    //setup recycler view  and show to bottom sheet
    private fun showBottomSheetPerson(list: List<ResultXXX>?) {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet, LinearLayout(context))
        dialog = BottomSheetDialog(requireContext(), com.example.global.R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        recyclerView = dialogView.findViewById(R.id.recyyy)
        itemAdapterBottomSheetPerson = BottomSheetAdapterPerson(list) { selectedItem: ResultXXX ->
            listItemClicked(selectedItem)
        }
        recyclerView.adapter = itemAdapterBottomSheetPerson
        dialog.show()
    }

    //manage the clickable  list
    private fun listItemClicked(item: ResultXXX) {
        personId = item.id.toString()
        binding.autoCompleteTrust.setText(item.name)
        dialog.dismiss()
    }

    private fun getAddProduct() {
        viewModel.getPerson()
        launchAndRepeatWithViewLifecycle {
            viewModel.responseLogin.collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Error -> {
                        context?.toast(" مشکل در دریافت اطلاعات"+it.message.toString())
                    }
                    is Resource.Success -> {
                        listPart = it.data?.result
                    }
                    else -> {}
                }
            }
        }
    }

    private fun lend() {
        viewModel.lend(args?.product?.id.toString(),personId)
        launchAndRepeatWithViewLifecycle {
            viewModel.responseLogin.collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Error -> {
                        context?.toast(" مشکل در دریافت اطلاعات"+it.message.toString())
                    }
                    is Resource.Success -> {
                        val dialog = context?.dialog(R.layout.dialog_trust,binding.root,false)
                        dialog?.findViewById<MaterialTextView>(R.id.back_trust)?.setOnClickListener {
                            dialog.dismiss()
                            Navigation.findNavController(binding.root).navigate(R.id.scannerFragment)
                        }
                    }
                    else -> {}
                }
            }
        }
    }
}