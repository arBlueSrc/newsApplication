package ir.majazi.sabtamval.ui.addSpecification

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

import com.example.global.modules.app.model.ResultX
import com.example.global.modules.app.model.ResultXX
import com.example.global.network.resource.Resource
import com.example.global.utils.extensions.launchAndRepeatWithViewLifecycle
import com.example.global.utils.extensions.toast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentAddSpecificationsBinding
import ir.majazi.sabtamval.ui.adapter.AdapterAddSpecification
import ir.majazi.sabtamval.ui.addGood.AddGoodViewModel
import ir.majazi.sabtamval.util.Test

@AndroidEntryPoint
class AddSpecificationsFragment : Fragment() {


    private lateinit var adapter: AdapterAddSpecification
    private lateinit var binding: FragmentAddSpecificationsBinding
    private val viewModel: AddSpecificationsViewModel by viewModels()
    private lateinit var args: AddSpecificationsFragmentArgs



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddSpecificationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "افزودن مشخصات کالا"
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }
         args = AddSpecificationsFragmentArgs.fromBundle(requireArguments())
        context?.toast(args.goodId)

        getAddProduct()

        binding.fabAddSpecifications.setOnClickListener {

            adapter.array.forEach {
                Test.array.add(it)
                Log.i("TAG", "onViewCreated: ${it.name} ${it.id}${it.value}")
            }


            val directions =AddSpecificationsFragmentDirections
                .actionAddSpecificationsFragmentToConfirmInformationFragment(
                    args.goodId,
                    args.typeGood,
                    args.part,
                    args.person,
                    binding.edtPropertyNumber.text.toString(),
                    args.partId,
                    args.employeeId,
                    args.storeId
                )

            findNavController()
                .navigate(directions)
        }




    }

    //check login user
    private fun getAddProduct() {

//        binding.progressBar2.visibility = View.VISIBLE
//        requireActivity().window.setFlags(
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
//        context?.dialog(R.layout.dialog_progress,binding.root,false)
        viewModel.addProduct2(args.goodId)
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

                        adapter=AdapterAddSpecification(it.data?.result){ selectedItem: TextInputEditText ->
                            listItemClicked(selectedItem)
                        }

                        binding.recyAddGood.adapter = adapter
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
    private fun listItemClicked(item: TextInputEditText) {
    }





}