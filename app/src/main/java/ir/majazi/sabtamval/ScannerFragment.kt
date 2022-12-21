package ir.majazi.sabtamval

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.budiyev.android.codescanner.*
import ir.majazi.sabtamval.databinding.FragmentScannerBinding

private const val CAMERA_CODE = 101

class ScannerFragment : Fragment() {

    private lateinit var viewModel: ScannerViewModel
    private lateinit var codeScanner: CodeScanner
    private lateinit var nationalCode: String
    private lateinit var binding: FragmentScannerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScannerBinding.inflate(inflater)
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
//                requireActivity().runOnUiThread{
//                    Log.i("TAG", "codeScanner: ${it.text}")
//                    if (it.text.isNotEmpty()){
//                        Log.i("TAG", "codeScanner: ${it.text}")
//                        val dialog= Dialog(requireContext())
//                        dialog.setContentView(R.layout.dialog_status)
//                        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//                        dialog.window?.setLayout(_binding.root.width,_binding.root.height)
//                        dialog.show()
//                        codeScanner.stopPreview()
//                        val textResultDialog=dialog.findViewById<TextView>(R.id.textViewDialog)
//                        dialog.findViewById<Button>(R.id.buttonBacktoScanner)
//                            .setOnClickListener(){
//
//                                dialog.cancel()
//                                dialog.dismiss()
//                                codeScanner.startPreview()
//                            }
//
//                        nationalCode=it.text.toString().substring(48,it.text.lastIndexOf("'"))
//                        Log.i("TAG", "codeScanner: $nationalCode")
//
//
//                        val apiService: ApiService by lazy {
//                            ApiClient.getRetrofit().create()
//                        }
//
//                        lifecycleScope.launchWhenCreated {
//                            textResultDialog.text=apiService.responseUser(nationalCode,getDataUser())
//                                .body()?.result
//                        }
//                    }
//                }
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
                    Toast.makeText(requireContext(), "permission", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}