package eu.enhan.alerter.ui.legacy;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.util.Log;
import eu.enhan.alerter.R;

import java.util.prefs.PreferenceChangeEvent;
import java.util.prefs.PreferenceChangeListener;

/**
 * @author Emmanuel Nhan
 */
public class LegacySettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_settings);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);


    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Log.d("DROIDALERTER", "Called");
        if(key.equals("prefs_red_sound")){
            getPreferenceScreen().findPreference("prefs_red_ringtone").setEnabled(sharedPreferences.getString(key, "1").equals("2"));
        }

        if(key.equals("prefs_yellow_sound")){
            getPreferenceScreen().findPreference("prefs_yellow_ringtone").setEnabled(sharedPreferences.getString(key, "1").equals("2"));
        }

        if(key.equals("prefs_red_sound")){
            getPreferenceScreen().findPreference("prefs_blue_ringtone").setEnabled(sharedPreferences.getString(key, "0").equals("2"));
        }
    }
}