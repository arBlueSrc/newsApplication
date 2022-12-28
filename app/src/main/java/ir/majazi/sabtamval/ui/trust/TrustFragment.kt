package ir.majazi.sabtamval.ui.trust

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
import ir.majazi.sabtamval.databinding.FragmentTrustBinding
import ir.majazi.sabtamval.util.dropDownAdapter

@AndroidEntryPoint
class TrustFragment : Fragment() {

    private lateinit var binding: FragmentTrustBinding
    private lateinit var viewModel: TrustViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrustBinding.inflate(inflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "امانات"
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }


        val arrayTrust: Array<String> =
            arrayOf("علیرضا مدنی", "علی فاضلی", "وحید دانشمند", "آرش میرزایی", "علی حاضری")
        dropDownAdapter(requireContext(),arrayTrust,binding.autoCompleteTrust)
    }
}