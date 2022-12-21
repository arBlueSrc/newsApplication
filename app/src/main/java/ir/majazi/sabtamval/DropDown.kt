package ir.majazi.sabtamval

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView

fun dropDownAdapter(
    context: Context,
    array: Array<String>,
    autoCompleteTextView: AutoCompleteTextView
) {
    val arrayAdapter = ArrayAdapter(context, R.layout.drop_down_item, array)
    autoCompleteTextView.setAdapter(arrayAdapter)
}