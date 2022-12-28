package ir.majazi.sabtamval.ui.specification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentSpecificationsBinding

@AndroidEntryPoint
class SpecificationsFragment : Fragment() {


    private lateinit var binding: FragmentSpecificationsBinding
    private lateinit var viewModel: SpecificationsViewModel

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

    }
}