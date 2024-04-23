package ir.majazi.sabtamval.ui.confirmInfo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.global.network.resource.Resource
import com.example.global.utils.extensions.dialog
import com.example.global.utils.extensions.launchAndRepeatWithViewLifecycle
import com.example.global.utils.extensions.toast
import com.example.tarashehospitai.data.utils.onBackPressed
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textview.MaterialTextView
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentConfirmInformationBinding
import ir.majazi.sabtamval.ui.adapter.AdapterConfirmInfo
import ir.majazi.sabtamval.util.Test

@AndroidEntryPoint
class ConfirmInformationFragment : Fragment() {


    private lateinit var binding: FragmentConfirmInformationBinding
    private val viewModel: ConfirmInformationViewModel by viewModels()
    private lateinit var args: ConfirmInformationFragmentArgs
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentConfirmInformationBinding.inflate(inflater)

        requireActivity().onBackPressed {
            Test.array.clear()
            Navigation.findNavController(requireView()).popBackStack()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabConfirmInfo.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_confirmInformationFragment_to_scannerFragment)
        }

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "تایید اطلاعات"
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        args = ConfirmInformationFragmentArgs.fromBundle(requireArguments())

        binding.tvGoodTypeInfo.text = args.goodType
        binding.tvPartInfo.text = args.part
        binding.tvPersonInfo.text = args.person
        binding.txtPropertyNumber.text = args.propertyNumber


        Log.i("TAG", "onViewCreated: ${Test.array[0].name} ")

        val adapter = AdapterConfirmInfo(Test.array)
        binding.recyConfirmInfo.adapter = adapter

        binding.fabConfirmInfo.setOnClickListener {
            sendInfo()
            Test.array.clear()
        }

    }

   private fun sendInfo() {

        val gson = Gson()
        val json = gson.toJson(Test.array)

        viewModel.addProduct(
            args.goodId.toString(),
            args.storeId.toString(),
            args.partId.toString(),
            args.employeeId.toString(),
            args.propertyNumber.toString(),
            json
        )
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
                        context?.toast(" مشکل در دریافت اطلاعات" + it.message.toString())

                    }
                    is Resource.Success -> {
                        val dialog = context?.dialog(R.layout.dialog_registranion,binding.root,false)
                        dialog?.findViewById<MaterialTextView>(R.id.tv_back_registranion)?.setOnClickListener {
                            dialog.dismiss()
                            Navigation.findNavController(binding.root).navigate(R.id.scannerFragment)
                        }
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