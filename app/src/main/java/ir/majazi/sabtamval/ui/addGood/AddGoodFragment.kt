package ir.majazi.sabtamval.ui.addGood

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.global.modules.app.model.Employee
import com.example.global.modules.app.model.GoodX
import com.example.global.network.resource.Resource
import com.example.global.utils.extensions.launchAndRepeatWithViewLifecycle
import com.example.global.utils.extensions.toast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentAddGoodBinding
import ir.majazi.sabtamval.util.BottomSheetAdapter

@AndroidEntryPoint
class AddGoodFragment : Fragment() {

    private lateinit var binding: FragmentAddGoodBinding
    private val viewModel: AddGoodViewModel by viewModels()
    private  var listGood:MutableList<GoodX>? = null
    private lateinit var dialog: BottomSheetDialog
    private lateinit var recyclerView: RecyclerView
    private lateinit var itemAdapterBottomSheet: BottomSheetAdapter


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddGoodBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.autoCompletePerson.setOnClickListener{
            checkLogin()
        }

//        val arrayTypeGood: Array<String> = arrayOf("سیستم رومیزی", "میز", "دوربین")
//        dropDownAdapter(requireContext(), arrayTypeGood, binding.autoComplateGoodType)
//
//        val arrayCompany: Array<String> = arrayOf("انبار سازمانی", "انبار بسیج", "انبار فارس")
//        dropDownAdapter(requireContext(), arrayCompany, binding.autoCompleteCompany)
//
//        val arrayPart: Array<String> = arrayOf("معاونت فناوری", "معاونت اجرایی", "نگهبانی")
//        dropDownAdapter(requireContext(), arrayPart, binding.autoCompletePart)
//
//
//        val arrayPerson: Array<String> =
//            arrayOf("علیرضا مدنی", "علی فاضلی", "وحید دانشمند", "آرش میرزایی", "علی حاضری")
//        dropDownAdapter(requireContext(), arrayPerson, binding.autoCompletePerson)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "افزودن کالا"
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        binding.fabAddGood.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_addGoodFragment_to_addSpecificationsFragment)
        }

    }

    //setup recycler view  and show to bottom sheet
    private fun showBottomSheetGender(list: List<Employee>?) {
        val dialogView = layoutInflater.inflate(R.layout.bottom_sheet, LinearLayout(context))
        dialog = BottomSheetDialog(requireContext(), com.example.global.R.style.BottomSheetDialogTheme)
        dialog.setContentView(dialogView)
        recyclerView = dialogView.findViewById(R.id.recyyy)
        itemAdapterBottomSheet = BottomSheetAdapter(list) { selectedItem: Employee ->
            listItemClicked(selectedItem)
        }
        recyclerView.adapter = itemAdapterBottomSheet
        dialog.show()
    }

    //check login user
    private fun checkLogin() {

            viewModel.addProduct()
            launchAndRepeatWithViewLifecycle {
                viewModel.responseLogin.collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Error -> {
                            context?.toast(" مشکل در دریافت اطلاعات"+it.message.toString())
                        }
                        is Resource.Success -> {
                            context?.toast(it.data?.result?.goods?.get(0)?.name.toString())

                            showBottomSheetGender(it.data?.result?.employees)

                        }
                        else -> {}
                    }

            }
        }

    }

    //manage the clickable  list
    private fun listItemClicked(item: Employee) {
//        genderId = item.id.toString()
//        binding.etGender.setText(item.name)
        dialog.dismiss()
    }


}