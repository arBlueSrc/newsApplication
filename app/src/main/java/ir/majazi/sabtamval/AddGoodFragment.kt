package ir.majazi.sabtamval

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.appbar.MaterialToolbar
import ir.majazi.sabtamval.databinding.FragmentAddGoodBinding

class AddGoodFragment : Fragment() {

    private lateinit var binding: FragmentAddGoodBinding
    private lateinit var viewModel: AddGoodViewModel

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddGoodBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arrayTypeGood: Array<String> = arrayOf("سیستم رومیزی", "میز", "دوربین")
        dropDownAdapter(requireContext(), arrayTypeGood, binding.autoComplateGoodType)

        val arrayCompany: Array<String> = arrayOf("انبار سازمانی", "انبار بسیج", "انبار فارس")
        dropDownAdapter(requireContext(), arrayCompany, binding.autoCompleteCompany)

        val arrayPart: Array<String> = arrayOf("معاونت فناوری", "معاونت اجرایی", "نگهبانی")
        dropDownAdapter(requireContext(), arrayPart, binding.autoCompletePart)


        val arrayPerson: Array<String> =
            arrayOf("علیرضا مدنی", "علی فاضلی", "وحید دانشمند", "آرش میرزایی", "علی حاضری")
        dropDownAdapter(requireContext(), arrayPerson, binding.autoCompletePerson)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar_main)
        toolbar.title = "افزودن کالا"
        toolbar.setNavigationOnClickListener {
            Navigation.findNavController(view).popBackStack()
        }

        binding.fabAddGood.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_addGoodFragment_to_addSpecificationsFragment)
        }

    }
}