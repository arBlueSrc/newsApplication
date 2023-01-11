package ir.majazi.sabtamval.ui.takeBack

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.global.network.resource.Resource
import com.example.global.utils.extensions.dialog
import com.example.global.utils.extensions.disableButton
import com.example.global.utils.extensions.toast
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.textview.MaterialTextView
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentTakeBackBinding
import kotlinx.coroutines.launch


@AndroidEntryPoint
class TakeBackFragment : Fragment() {

    private val viewModel: TakeBackViewModel by viewModels()
    private lateinit var binding: FragmentTakeBackBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTakeBackBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setToolbar()

        handleOnClickSave()

        observeResponse()

    }

    private fun observeResponse() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.responseTakeBack.collect {
                when (it) {
                    is Resource.Loading -> {}
                    is Resource.Error -> {
                        context?.toast("خطا! دوباره تلاش کنید.")
                    }
                    is Resource.Success -> {
                        showDialog()
                    }
                    else -> {}
                }
            }
        }
    }

    private fun handleOnClickSave() {

        binding.btnOk.setOnClickListener {

            val des = binding.etDescription.text.toString().trim()
            viewModel.takeBack(
                TakeBackFragmentArgs.fromBundle(requireArguments()).productId.toString(),
                des
            )

            //show loading
            binding.btnOk.apply {
                text = "در حال بررسی ..."
                disableButton(
                    backgroundColor = com.example.global.R.color.custom_gray5,
                    textColor = ir.majazi.sabtamval.R.color.black
                )
            }
        }

    }

    private fun setToolbar() {
        val toolbar = view?.findViewById<MaterialToolbar>(ir.majazi.sabtamval.R.id.toolbar_main)
        toolbar?.title = "پس گرفتن کالا"
    }

    private fun showDialog() {
        val dialog = context?.dialog(R.layout.dialog_tale_back,binding.root,false)
            dialog?.findViewById<MaterialTextView>(R.id.btn_back_take_back)?.setOnClickListener{
            dialog.dismiss()
            findNavController().navigateUp()
        }

    }
}