package eu.enhan.alerter.ui.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import eu.enhan.alerter.R;

/**
 * @author Emmanuel Nhan
 */
public class MainSettingsFragment extends PreferenceFragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_settings);
    }
}
