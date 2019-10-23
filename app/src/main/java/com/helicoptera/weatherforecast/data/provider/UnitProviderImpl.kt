package com.helicoptera.weatherforecast.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.helicoptera.weatherforecast.internal.UnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"

class UnitProviderImpl(context: Context) : PreferenceProvider(context), UnitProvider {


    override fun getUnitSystem(): String {
        val selectedName = preferences.getString(UNIT_SYSTEM, UnitSystem.METRIC)
        return selectedName!!
    }
}