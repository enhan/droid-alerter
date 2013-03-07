package eu.enhan.alerter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import eu.enhan.alerter.R;
import eu.enhan.alerter.ui.legacy.LegacySettingsActivity;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 05/03/13
 * Time: 20:22
 * To change this template use File | Settings | File Templates.
 */
public class StartActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
	}


    public void createFalseMail(View view){
        Intent intent = new Intent(this, CreateFalseMailActivity.class);
        startActivity(intent);
    }


    public void showConfiguration(View view){

        Class<?> c = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ? MainSettingsActivity.class : LegacySettingsActivity.class;

        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

}