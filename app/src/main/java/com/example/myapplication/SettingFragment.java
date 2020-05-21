package com.example.myapplication;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;


public class SettingFragment extends PreferenceFragment {
    static private final String keyy = "color";
    SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener;
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferenes);
        preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//                System.out.println("share = "+sharedPreferences);
//                System.out.println("key = "+key);
                if(key.equals(keyy)){

                }
            }
        };
    }
}
