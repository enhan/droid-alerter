package eu.enhan.alerter.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.fsck.k9.EmailReceivedIntent;
import eu.enhan.alerter.R;
import eu.enhan.alerter.filter.EmailFilterService;

/**
 * @author Emmanuel Nhan
 */
public class CreateFalseMailActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_false_mail);
    }


    public void sendFalseMail(View view){

        Log.d("DROIDALERTER","About to send a false email");

        EditText from = (EditText) findViewById(R.id.from);
        EditText subject = (EditText) findViewById(R.id.subject);


        Intent intent  = new Intent(this, EmailFilterService.class);
        intent.putExtra(EmailReceivedIntent.EXTRA_FROM, from.getText().toString());
        intent.putExtra(EmailReceivedIntent.EXTRA_SUBJECT, subject.getText().toString());
        startService(intent);
    }
}