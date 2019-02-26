package uk.co.jakelee.preferencesexample

import android.content.Context
import android.preference.PreferenceManager

class PreferenceHelper(val context: Context) {
    val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    enum class BooleanPref(val prefId: Int, val defaultId: Int) {
        setting1(R.string.pref_boolean1, R.bool.pref_boolean1_default),
        setting2(R.string.pref_boolean2, R.bool.pref_boolean2_default)
    }

    fun getBooleanPref(pref: BooleanPref) =
        prefs.getBoolean(context.getString(pref.prefId), context.resources.getBoolean(pref.defaultId))

    fun setBooleanPref(pref: BooleanPref, value: Boolean) =
        prefs.edit().putBoolean(context.getString(pref.prefId), value).commit()

    enum class StringPref(val prefId: Int, val defaultId: Int) {
        setting1(R.string.pref_string1, R.string.pref_string1_default),
        setting2(R.string.pref_string2, R.string.pref_string2_default)
    }

    fun getStringPref(pref: StringPref) =
        prefs.getString(context.getString(pref.prefId), context.getString(pref.defaultId))!!

    fun setStringPref(pref: StringPref, value: String) =
        prefs.edit().putString(context.getString(pref.prefId), value).commit()

    enum class IntPref(val prefId: Int, val defaultId: Int) {
        setting1(R.string.pref_int1, R.integer.pref_int1_default),
        setting2(R.string.pref_int2, R.integer.pref_int2_default)
    }

    fun getIntPref(pref: IntPref) =
        prefs.getInt(context.getString(pref.prefId), context.resources.getInteger(pref.defaultId).toInt())

    fun setIntPref(pref: IntPref, value: Int) = prefs.edit().putInt(context.getString(pref.prefId), value).commit()
}