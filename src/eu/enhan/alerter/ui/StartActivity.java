package eu.enhan.alerter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import eu.enhan.alerter.R;

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
}