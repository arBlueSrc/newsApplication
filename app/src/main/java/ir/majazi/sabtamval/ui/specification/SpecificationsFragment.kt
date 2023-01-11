package ir.majazi.sabtamval.ui.specification

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.global.modules.app.model.Product
import com.example.global.network.resource.Resource
import com.example.global.utils.extensions.launchAndRepeatWithViewLifecycle
import com.example.global.utils.extensions.toast
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentSpecificationsBinding
import ir.majazi.sabtamval.ui.adapter.AdapterSpecification
import ir.majazi.sabtamval.ui.scanner.ScannerViewModel


@AndroidEntryPoint
class SpecificationsFragment : Fragment() {


    private lateinit var binding: FragmentSpecificationsBinding

    private val viewModel: ScannerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSpecificationsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "مشخصات"
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        val args = SpecificationsFragmentArgs.fromBundle(requireArguments())
        Log.i("TAG", "onViewCreated1: ${args.idUser.toString()}")
        if (args.idUser != null) {
            viewModel.getResponseScanner(args.idUser.toString())
            launchAndRepeatWithViewLifecycle {
                viewModel.responseScanner.collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Error -> {
                            context?.toast("مشکل در دریافت اطلاعات")
                        }
                        is Resource.Success -> {
                            binding.personSpecifications.text =
                                "${it.data?.user?.name} ${it.data?.user?.lastName}"
                            binding.storeSpecification.text = it.data?.user?.store
                            binding.partSpecification.text = it.data?.user?.part
//                                context?.toast("ok")

                            val adapter = AdapterSpecification(
                                { selectedItem: Product ->
                                    listItemClicked(selectedItem)
                                },
                                it.data?.products,
                                { selectedItem: Product ->
                                    findNavController().navigate(SpecificationsFragmentDirections.actionSpecificationsFragmentToTakeBackFragment(selectedItem.id ?: 0))
                                }
                            )
                            binding.rvSpecification.adapter = adapter
                        }
                        else -> {}
                    }
                }
            }

        } else context?.toast("خطا در خواندن بارکد")
    }

    private fun listItemClicked(product: Product) {
        val directions = SpecificationsFragmentDirections
            .actionSpecificationsFragmentToEditSpecificationsFragment(
                product,
                product.id.toString(),
                product.propertyNumber
            )
        findNavController().navigate(directions)
    }
}