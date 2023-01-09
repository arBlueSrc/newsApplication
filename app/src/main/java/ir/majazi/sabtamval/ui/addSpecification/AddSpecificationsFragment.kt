package ir.majazi.sabtamval.ui.addSpecification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.global.utils.extensions.toast
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentAddSpecificationsBinding

@AndroidEntryPoint
class AddSpecificationsFragment : Fragment() {


    private lateinit var binding: FragmentAddSpecificationsBinding
    private lateinit var viewModel: AddSpecificationsViewModel

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
        val args = AddSpecificationsFragmentArgs.fromBundle(requireArguments())
        context?.toast(args.goodId)


        binding.fabAddSpecifications.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_addSpecificationsFragment_to_confirmInformationFragment)
        }
    }

}