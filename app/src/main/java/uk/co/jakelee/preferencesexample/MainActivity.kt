package uk.co.jakelee.preferencesexample

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.preference.EditTextPreference
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import android.support.v7.preference.SeekBarPreference
import android.text.format.Formatter
import android.view.*
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

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
        (activity as AppCompatActivity).supportActionBar!!.title = getString(R.string.settings_title)
        addPreferencesFromResource(R.xml.preferences_ui)
        setupListeners()
        setupSeekbars()
    }

    private fun setupSeekbars() {
        setupSeekbar(
            R.string.pref_automatic_check_time,
            R.integer.automatic_check_time_step,
            R.integer.automatic_check_time_min,
            R.integer.automatic_check_time_max
        )
        setupSeekbar(
            R.string.pref_automatic_check_variation,
            R.integer.automatic_check_variation_step,
            R.integer.automatic_check_variation_min,
            R.integer.automatic_check_variation_max
        )
        setupSeekbar(
            R.string.pref_filtering_width,
            R.integer.filtering_width_step,
            R.integer.filtering_width_min,
            R.integer.filtering_width_max
        )
        setupSeekbar(
            R.string.pref_filtering_height,
            R.integer.filtering_height_step,
            R.integer.filtering_height_min,
            R.integer.filtering_height_max
        )
    }

    private fun setupListeners() {
        findPreference(getString(R.string.pref_view_status)).onPreferenceClickListener = viewStatusListener
    }

    fun setupSeekbar(id: Int, step: Int, min: Int, max: Int) {
        val seekbar = findPreference(getString(id)) as SeekBarPreference
        seekbar.seekBarIncrement = resources.getInteger(step)
        seekbar.min = resources.getInteger(min)
        seekbar.max = resources.getInteger(max)
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
        val pref = findPreference(key)
        when {
            key == getString(R.string.pref_custom_key) && pref is EditTextPreference -> {
                Toast.makeText(activity!!, "Set a string", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val viewStatusListener = Preference.OnPreferenceClickListener { _ ->
        AlertDialog.Builder(activity!!)
            .setTitle("test")
            .setPositiveButton("ok") { _, _ -> }
            .show()
        true
    }
}
