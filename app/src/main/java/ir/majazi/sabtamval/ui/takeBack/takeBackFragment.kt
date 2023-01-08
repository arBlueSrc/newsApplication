package ir.majazi.sabtamval.ui.takeBack

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.appbar.MaterialToolbar
import dagger.hilt.android.AndroidEntryPoint
import ir.majazi.sabtamval.R
import ir.majazi.sabtamval.databinding.FragmentTakeBackBinding

@AndroidEntryPoint
class takeBackFragment : Fragment() {

    private val viewModel: TakeBackViewModel by viewModels()
    private lateinit var binding : FragmentTakeBackBinding

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

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "پس گرفتن کالا"

    }

}