package com.example.memorygame.modules.options

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.memorygame.R
import kotlinx.android.synthetic.main.fragment_options.*

class OptionsFragment : Fragment() {

    private val amountSets = arrayOf("10", "11", "12")
    private val amountEqualCards = arrayOf("2", "3", "4")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pressBackButton()
        (activity as AppCompatActivity).supportActionBar!!.hide()
        setSpinner(spinnerSets, R.string.preferences_amount_sets, amountSets, 10)
        setSpinner(spinnerEqualCards, R.string.preferences_amount_equal_cards, amountEqualCards, 2)
    }

    /**
     * Generic function that sets a spinner's adapter and its values
     *
     */
    private fun setSpinner(spinner: Spinner, key: Int, list: Array<String>, default: Int) {
        val adapter = ArrayAdapter(context!!, android.R.layout.simple_spinner_item, list)
        setAdapter(adapter, spinner)
        setSpinnerClick(spinner, key, list)

        val value = PreferenceManager.getDefaultSharedPreferences(context).getInt(
            resources.getString(key), default
        );

        val spinnerPosition = adapter.getPosition(value.toString())
        spinner.setSelection(spinnerPosition)
    }

    private fun setAdapter(adapter: ArrayAdapter<String>, spinner: Spinner) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    private fun setSpinnerClick(spinner: Spinner, key: Int, list: Array<String>) {
        spinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View, pos: Int, id: Long) {
                when (pos) {
                    0, 1, 2 -> PreferenceManager.getDefaultSharedPreferences(context).edit()
                        .putInt(
                            resources.getString(key),
                            list[pos].toInt()
                        ).apply()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
    }

    fun pressBackButton() {
        btnReturn.setOnClickListener {
            view?.findNavController()?.navigateUp()
        }
    }
}
