package ir.majazi.sabtamval.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.global.network.resource.Resource
import com.example.global.utils.Save
import com.example.global.utils.extensions.disableButton
import com.example.global.utils.extensions.enableButton
import com.example.global.utils.extensions.launchAndRepeatWithViewLifecycle
import com.example.global.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            checkLogin()
            binding.btnLogin.apply {
                text = "در حال بررسی ..."
                disableButton(
                    backgroundColor = com.example.global.R.color.custom_gray5,
                    textColor = R.color.black
                )
            }
        }

    }

    //check login user
    private fun checkLogin() {
        if (viewModel.emptyFiledUserName(binding.edtUserName.text.toString()) ||
            viewModel.emptyFiledPassword(binding.edtPassword.text.toString())
        ) {
            context?.toast("لطفا فیلد هارا پر کنید")
        } else {

            viewModel.checkLogin(binding.edtUserName.text.toString(), binding.edtPassword.text.toString())
            launchAndRepeatWithViewLifecycle {
                viewModel.responseLogin.collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Error -> {
                            context?.toast("مشکل در دریافت اطلاعات")
                            binding.btnLogin.apply {
                                text = "ورود"
                                enableButton(
                                    backgroundColor = com.example.global.R.color.custom_green2,
                                    textColor = R.color.white
                                )
                            }
                        }
                        is Resource.Success -> {
                            if (it.data?.status.toString() == "true"){
                                withContext(Dispatchers.Main){
                                    Save.data(requireContext(),"userName",binding.edtUserName.text.toString())
                                    Save.data(requireContext(),"password",binding.edtPassword.text.toString())
                                    Navigation.findNavController(binding.root).navigate(R.id.scannerFragment)
                                }
                            }else{
                                context?.toast("نام کاربری یا رمز عبور اشتباه است")
                            }


                        }
                        else -> {}
                    }
                }
            }
        }
    }
}