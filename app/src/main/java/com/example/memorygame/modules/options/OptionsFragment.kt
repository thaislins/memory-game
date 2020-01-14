package com.example.memorygame.modules.options

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.memorygame.R
import kotlinx.android.synthetic.main.fragment_options.*


class OptionsFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val paths = arrayOf("10", "14", "18")

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

        val adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, paths)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setOnItemSelectedListener(this)
        setSpinnerValue(adapter)
    }

    private fun setSpinnerValue(adapter: ArrayAdapter<String>) {
        val value = PreferenceManager.getDefaultSharedPreferences(context).getInt(
            resources.getString(R.string.app_preferences_amount_pairs), 10
        );
        val spinnerPosition = adapter.getPosition(value.toString())
        spinner.setSelection(spinnerPosition)
    }

    override fun onItemSelected(
        parent: AdapterView<*>?,
        v: View?,
        position: Int,
        id: Long
    ) {
        when (position) {
            0 -> {
                PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putInt(resources.getString(R.string.app_preferences_amount_pairs), 10).apply()
            }
            1 -> {
                PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putInt(resources.getString(R.string.app_preferences_amount_pairs), 14).apply()
            }
            2 -> {
                PreferenceManager.getDefaultSharedPreferences(context).edit()
                    .putInt(resources.getString(R.string.app_preferences_amount_pairs), 18).apply()
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun pressBackButton() {
        btnReturn.setOnClickListener {
            view?.findNavController()?.navigateUp()
        }
    }
}
