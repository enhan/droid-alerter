package eu.enhan.alerter.ui.legacy;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import eu.enhan.alerter.R;

/**
 * @author Emmanuel Nhan
 */
public class LegacySettingsActivity extends PreferenceActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.main_settings);
    }
}