package ir.majazi.sabtamval.ui.scanner

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.*
import com.example.global.utils.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentScannerBinding
import ir.majazi.sabtamval.util.ProjectConsts.Companion.CAMERA_CODE
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec


@AndroidEntryPoint
class ScannerFragment : Fragment() {

    private lateinit var codeScanner: CodeScanner
    private lateinit var binding: FragmentScannerBinding

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
            decodeCallback = DecodeCallback {
                requireActivity().runOnUiThread {
                    codeScanner.stopPreview()
                    if (decrypt(it.text.toString()) != "") {
                        val directions = ScannerFragmentDirections
                            .actionScannerFragmentToSpecificationsFragment(
                                decrypt(it.text.toString())
                            )
                        findNavController().navigate(directions)
                    }
                }
            }

            errorCallback = ErrorCallback {
                requireActivity().runOnUiThread {}
            }
        }

        binding.scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }


    private fun decrypt(dataToDecrypt: String): String {
        return try {
            val cipher: Cipher = Cipher.getInstance("AES/CBC/NoPadding")
            val skeySpec = SecretKeySpec("X9vIyNiptseccxdC".toByteArray(), "aes-128-cbc")
            val iv = IvParameterSpec("WHG7HsS84kIGvzzI".toByteArray())
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv)
            val decrypted = cipher.doFinal(Base64.decode(dataToDecrypt, Base64.DEFAULT)) //byte[]
            decrypted.toString(Charset.forName("UTF-8")).trim().replace("\u000E", "")
        } catch (e: Exception) {
            context?.toast("خطا در اسکن ")
            ""
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
                    Toast.makeText(requireContext(), "دسترسی دوربین داده شد.", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }
}