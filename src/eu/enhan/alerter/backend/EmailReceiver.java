package eu.enhan.alerter.backend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.fsck.k9.EmailReceivedIntent;
import eu.enhan.alerter.filter.EmailFilterService;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 05/03/13
 * Time: 20:03
 * To change this template use File | Settings | File Templates.
 */
public class EmailReceiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		Log.d("DROIDALERTER", "Email received : " + intent.getStringExtra(EmailReceivedIntent.EXTRA_SUBJECT));
		Intent transferedIntent = new Intent(context, EmailFilterService.class);
		transferedIntent.setAction(intent.getAction());
		transferedIntent.putExtras(intent);
		context.startService(transferedIntent);
	}
}
