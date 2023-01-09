package ir.majazi.sabtamval.ui.addGood

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.Employee
import com.example.global.modules.app.model.GoodX
import com.example.global.modules.app.model.Part
import com.example.global.modules.app.model.Store
import com.example.global.network.resource.Resource
import com.example.global.utils.extensions.dialog
import com.example.global.utils.extensions.launchAndRepeatWithViewLifecycle
import com.example.global.utils.extensions.toast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentAddGoodBinding
import ir.majazi.sabtamval.ui.adapter.BottomSheetAdapterEmployee
import ir.majazi.sabtamval.ui.adapter.BottomSheetAdapterParts
import ir.majazi.sabtamval.ui.adapter.BottomSheetGoods
import ir.majazi.sabtamval.ui.adapter.BottomSheetStores

@AndroidEntryPoint
class AddGoodFragment : Fragment() {

    private lateinit var binding: FragmentAddGoodBinding
    private val viewModel: AddGoodViewModel by viewModels()
    private  var listEmployee:List<Employee>? = null
    private  var listStore:List<Store>? = null
    private  var listGood:List<GoodX>? = null
    private  var listPart:List<Part>? = null
    private lateinit var dialog: BottomSheetDialog
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapterBottomSheetEmployee: BottomSheetAdapterEmployee
    private lateinit var itemAdapterBottomSheetGoods: BottomSheetGoods
    private lateinit var itemAdapterBottomSheetStore: BottomSheetStores
    private lateinit var itemAdapterBottomSheetParts: BottomSheetAdapterParts
    private lateinit var dialog1:Dialog

    private lateinit var goodId:String


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddGoodBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getAddProduct()
        binding.autoCompletePerson.setOnClickListener{
            showBottomSheetEmployee(listEmployee)
        }
        binding.autoComplateGoodType.setOnClickListener {
            showBottomSheetGoods(listGood)
        }
        binding.autoCompleteCompany.setOnClickListener {
            showBottomSheetStores(listStore)
        }
        binding.autoCompletePart.setOnClickListener {
            showBottomSheetParts(listPart)
        }




        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "افزودن کالا"
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        binding.fabAddGood.setOnClickListener {

            val directions = AddGoodFragmentDirections
                .actionAddGoodFragmentToAddSpecificationsFragment(goodId)
            findNavController().navigate(directions)

//            Navigation.findNavController(view)
//                .navigate(R.id.action_addGoodFragment_to_addSpecificationsFragment)

        }

    }

    //setup recycler view  and show to bottom sheet
    private fun showBottomSheetEmployee(list: List<Employee>?) {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet, LinearLayout(context))
        dialog = BottomSheetDialog(requireContext(), com.example.global.R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        recyclerView = dialogView.findViewById(R.id.recyyy)
        itemAdapterBottomSheetEmployee = BottomSheetAdapterEmployee(list) { selectedItem: Employee ->
            listItemClicked(selectedItem)
        }
        recyclerView.adapter = itemAdapterBottomSheetEmployee
        dialog.show()
    }


    //setup recycler view  and show to bottom sheet
    private fun showBottomSheetGoods(list: List<GoodX>?) {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet, LinearLayout(context))
        dialog = BottomSheetDialog(requireContext(), com.example.global.R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        recyclerView = dialogView.findViewById(R.id.recyyy)
        itemAdapterBottomSheetGoods = BottomSheetGoods(list) { selectedItem: GoodX ->
            listItemClicked(selectedItem)
        }
        recyclerView.adapter = itemAdapterBottomSheetGoods
        dialog.show()
    }


    //setup recycler view  and show to bottom sheet
    private fun showBottomSheetStores(list: List<Store>?) {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet, LinearLayout(context))
        dialog = BottomSheetDialog(requireContext(), com.example.global.R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        recyclerView = dialogView.findViewById(R.id.recyyy)
        itemAdapterBottomSheetStore = BottomSheetStores(list) { selectedItem: Store ->
            listItemClicked(selectedItem)
        }
        recyclerView.adapter = itemAdapterBottomSheetStore
        dialog.show()
    }

    //setup recycler view  and show to bottom sheet
    private fun showBottomSheetParts(list: List<Part>?) {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet, LinearLayout(context))
        dialog = BottomSheetDialog(requireContext(), com.example.global.R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        recyclerView = dialogView.findViewById(R.id.recyyy)
        itemAdapterBottomSheetParts = BottomSheetAdapterParts(list) { selectedItem: Part ->
            listItemClicked(selectedItem)
        }
        recyclerView.adapter = itemAdapterBottomSheetParts
        dialog.show()
    }


    //check login user
    private fun getAddProduct() {

//        binding.progressBar2.visibility = View.VISIBLE
//        requireActivity().window.setFlags(
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//        context?.dialog(R.layout.dialog_progress,binding.root,false)
            viewModel.addProduct()
            launchAndRepeatWithViewLifecycle {
                viewModel.responseLogin.collect {
                    when (it) {
                        is Resource.Loading -> {
////                            binding.progressBar.visibility=View.VISIBLE
//                            binding.progressBar2.visibility = View.VISIBLE
//                            requireActivity().window.setFlags(
//                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)


                        }
                        is Resource.Error -> {
                            context?.toast(" مشکل در دریافت اطلاعات"+it.message.toString())

                        }
                        is Resource.Success -> {
                            listEmployee = it.data?.result?.employees
                            listGood = it.data?.result?.goods
                            listStore = it.data?.result?.stores
                            listPart = it.data?.result?.parts
//                            binding.progressBar.visibility = View.VISIBLE
//                            showBottomSheetGoods(it.data?.result?.goods)
//                            showBottomSheetStores(it.data?.result?.stores)
//                            showBottomSheetParts(it.data?.result?.parts)

                        }
                        else -> {}
                    }

            }
        }

    }

    //manage the clickable  list
    private fun listItemClicked(item: Employee) {
        Log.i("TAG", "listItemClicked: "+item.id)
        goodId = item.id.toString()
        dialog.dismiss()
    }

    //manage the clickable  list
    private fun listItemClicked(item: GoodX) {
//        genderId = item.id.toString()
//        binding.etGender.setText(item.name)
        dialog.dismiss()
    }

    //manage the clickable  list
    private fun listItemClicked(item: Store) {
//        genderId = item.id.toString()
//        binding.etGender.setText(item.name)
        dialog.dismiss()
    }


    //manage the clickable  list
    private fun listItemClicked(item: Part) {
//        genderId = item.id.toString()
//        binding.etGender.setText(item.name)
        dialog.dismiss()
    }
}