package ir.majazi.sabtamval.ui.scanner

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.*
import com.example.global.network.resource.Resource
import com.example.global.utils.extensions.*
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentScannerBinding
import ir.majazi.sabtamval.util.ProjectConsts.Companion.CAMERA_CODE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


@AndroidEntryPoint
class ScannerFragment : Fragment() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var binding: FragmentScannerBinding
    private val viewModel: ScannerViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScannerBinding.inflate(inflater)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.fabScanner.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_scannerFragment_to_addGoodFragment)
        }

        setUpPermission()
        codeScanner()
    }

    private fun codeScanner() {


        codeScanner = CodeScanner(requireContext(), binding.scannerView)
        codeScanner.apply {
            camera = CodeScanner.CAMERA_BACK
            formats = CodeScanner.ALL_FORMATS

            autoFocusMode = AutoFocusMode.SAFE
            scanMode = ScanMode.CONTINUOUS
            isAutoFocusEnabled = true
            isFlashEnabled = false


            Log.i("TAG", "codeScanner: configured")


            decodeCallback = DecodeCallback {

                Log.i("TAG", "callback: callback")

                requireActivity().runOnUiThread{
                    codeScanner.stopPreview()
//                    Log.i("TAG", "codeScanner: ${it.text.decrypt("X9vIyNiptseccxdC")}")
//
//
//                    Toast.makeText(requireContext(), it.text.decrypt("X9vIyNiptseccxdC"), Toast.LENGTH_SHORT).show()

                    decrypt(it.text.toString())
//                   decrypt1(it.text.toString(),"X9vIyNiptseccxdC")

                    val directions =ScannerFragmentDirections
                        .actionScannerFragmentToSpecificationsFragment(
//                            it.text.decrypt("X9vIyNiptseccxdC")
                            decrypt(it.text.toString())
//                        decrypt2(requireContext(),it.text.toByteArray())
                        )
                    findNavController().navigate(directions)


                }
            }

            errorCallback = ErrorCallback {
                requireActivity().runOnUiThread {
                    Log.e("TAG", "codeScanner: ")
                }
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }




    private fun decrypt(dataToDecrypt: String): String {
        val cipher: Cipher = Cipher.getInstance("AES/CBC/NoPadding")

        val skeySpec = SecretKeySpec("X9vIyNiptseccxdC".toByteArray(), "aes-128-cbc")

        val iv = IvParameterSpec("WHG7HsS84kIGvzzI".toByteArray())

        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)

        val decrypted = cipher.doFinal(Base64.decode(dataToDecrypt, Base64.DEFAULT)) //byte[]

        val result = decrypted.toString(Charset.forName("UTF-8")).trim().replace("\u000E","")

        return result
    }



    //check login user
    private fun checkLogin(view: View) {

            viewModel.getResponseScanner("1")
            launchAndRepeatWithViewLifecycle {
                viewModel.responseScanner.collect {
                    when (it) {
                        is Resource.Loading -> {}
                        is Resource.Error -> {
                            context?.toast("مشکل در دریافت اطلاعات")
                        }
                        is Resource.Success -> {


                            withContext(Dispatchers.Main){
                                Navigation.findNavController(view)
                                    .navigate(R.id.action_scannerFragment_to_specificationsFragment)
                            }
//                                context?.toast("ok")
                        }
                        else -> {}
                    }
                }
            }
        }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }

    private fun setUpPermission() {
        val permission =
            ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(
            requireActivity(), arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_CODE
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(requireContext(), "دسترسی دوربین داده شد.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

}