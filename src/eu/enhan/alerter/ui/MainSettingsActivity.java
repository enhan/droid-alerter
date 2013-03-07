package eu.enhan.alerter.ui;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import eu.enhan.alerter.ui.fragments.MainSettingsFragment;

/**
 * @author Emmanuel Nhan
 */
public class MainSettingsActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(R.id.content, new MainSettingsFragment()).commit();

    }
}