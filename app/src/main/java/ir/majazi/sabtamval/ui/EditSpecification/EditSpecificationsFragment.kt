package ir.majazi.sabtamval.ui.EditSpecification

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.global.network.resource.Resource
import com.example.global.utils.extensions.launchAndRepeatWithViewLifecycle
import com.example.global.utils.extensions.toast
import com.google.android.material.appbar.MaterialToolbar
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentEditSpecificationsBinding
import ir.majazi.sabtamval.ui.adapter.AdapterEditGood
import ir.majazi.sabtamval.ui.confirmInfo.ConfirmInformationViewModel
import ir.majazi.sabtamval.util.Test


@AndroidEntryPoint
class EditSpecificationsFragment : Fragment() {

    private lateinit var args: EditSpecificationsFragmentArgs
    private lateinit var adapter: AdapterEditGood
    private lateinit var binding: FragmentEditSpecificationsBinding
    private val viewModel: EditSpecificationsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEditSpecificationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "ویرایش کالا"
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        args = EditSpecificationsFragmentArgs.fromBundle(requireArguments())

         adapter=AdapterEditGood(args.product?.products){

        }
        binding.recyEditGood.adapter =adapter


        binding.btnSaveEdit.setOnClickListener {

            editProduct()

        }
    }

   private fun editProduct(){

        val gson= Gson()
        val json = gson.toJson(Test.array)

        viewModel.editProduct(args.productId.toString(),args.goodProperty.toString(),json)
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
                        context?.toast("ok")
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



}