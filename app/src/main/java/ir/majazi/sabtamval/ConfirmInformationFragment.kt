package ir.majazi.sabtamval

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.appbar.MaterialToolbar
import ir.majazi.sabtamval.databinding.FragmentConfirmInformationBinding

class ConfirmInformationFragment : Fragment() {


    private lateinit var binding: FragmentConfirmInformationBinding
    private lateinit var viewModel: ConfirmInformationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding = FragmentConfirmInformationBinding.inflate(inflater)
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
    }
}