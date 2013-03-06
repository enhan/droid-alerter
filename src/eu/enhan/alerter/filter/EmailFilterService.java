package eu.enhan.alerter.filter;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import com.fsck.k9.EmailReceivedIntent;
import eu.enhan.alerter.common.Email;

import java.util.Locale;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 05/03/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class EmailFilterService extends IntentService implements TextToSpeech.OnInitListener{

	private EmailFilter filter;
	private TextToSpeech tts;

	public EmailFilterService() {
		super("EmailFilterService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
        Log.d("DROIDALERTER","About to process email");
		Email email = new Email();
		email.setSubject(intent.getStringExtra(EmailReceivedIntent.EXTRA_SUBJECT));
		email.setFrom(intent.getStringExtra(EmailReceivedIntent.EXTRA_FROM));

		Set<Runnable> toRun = filter.createActions(email);
		Log.d("DROIDALERTER", toRun.size() + " actions to run");

		for(Runnable r : toRun){
			new Thread(r).start();
		}


        Log.d("DROIDALERTER", "Done with the mail");


	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.FRANCE);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			}else {
                // Compatibility with android < API 15
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1 ){
                    // Modern code
                    tts.setOnUtteranceProgressListener(new NewListener());
                }else{
                    // Old code
                    tts.setOnUtteranceCompletedListener(new OldListener());
                }
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
        Log.d("DROIDALERT","Done with the email filter service");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		tts = new TextToSpeech(getApplicationContext(), this);
		filter = new CompositeFilter(tts);
	}



    private class NewListener extends UtteranceProgressListener{
        @Override
        public void onStart(String utteranceId) {
            //To change body of implemented methods use File | Settings | File Templates.
        }

        @Override
        public void onDone(String utteranceId) {
            if ("1".equals(utteranceId)){
                tts.shutdown();
                Log.d("DROIDALERT","Done talking");
            }
        }

        @Override
        public void onError(String utteranceId) {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    private class OldListener implements TextToSpeech.OnUtteranceCompletedListener{
        @Override
        public void onUtteranceCompleted(String utteranceId) {
            if ("1".equals(utteranceId)){
               tts.shutdown();
               Log.d("DROIDALERT","Done talking");
            }
        }
    }


}
