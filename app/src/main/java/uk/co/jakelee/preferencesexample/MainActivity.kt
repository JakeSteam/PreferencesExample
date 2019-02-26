package uk.co.jakelee.preferencesexample

import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.preference.EditTextPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.SeekBarPreference
import android.view.*
import android.widget.Toast

class MainActivity : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        view!!.setBackgroundColor(Color.WHITE)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_ui)
        findPreference(getString(R.string.pref_show_values)).onPreferenceClickListener = showValuesListener
        setupSeekbars()
    }

    private fun setupSeekbars() {
        (findPreference(getString(R.string.pref_int1)) as SeekBarPreference).apply { 
            seekBarIncrement = resources.getInteger(R.integer.pref_int1_step)
            min = resources.getInteger(R.integer.pref_int1_min)
            max = resources.getInteger(R.integer.pref_int1_max)
        }

        (findPreference(getString(R.string.pref_int2)) as SeekBarPreference).apply {
            seekBarIncrement = resources.getInteger(R.integer.pref_int2_step)
            min = resources.getInteger(R.integer.pref_int2_min)
            max = resources.getInteger(R.integer.pref_int2_max)
        }
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        when (key) {
            getString(R.string.pref_string1) -> {
                val pref = findPreference(key) as EditTextPreference
                Toast.makeText(activity!!, "String changed to ${pref.text}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val showValuesListener = Preference.OnPreferenceClickListener { _ ->
        AlertDialog.Builder(activity!!)
            .setTitle("test")
            .setPositiveButton("ok") { _, _ -> }
            .show()
        true
    }
}
