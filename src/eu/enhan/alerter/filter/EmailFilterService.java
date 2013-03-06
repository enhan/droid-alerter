package eu.enhan.alerter.filter;

import android.app.IntentService;
import android.content.Intent;
import android.os.Build;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import com.fsck.k9.EmailReceivedIntent;
import eu.enhan.alerter.common.AlertMessage;
import eu.enhan.alerter.common.Email;

import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.Semaphore;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 05/03/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class EmailFilterService extends IntentService implements TextToSpeech.OnInitListener{

	private final EmailFilter filter;
	private TextToSpeech tts;
    private final Semaphore ttsSemaphore;

	public EmailFilterService() {
		super("EmailFilterService");
		filter = new BasicOnSubjectFilter();
        ttsSemaphore = new Semaphore(0);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
        Log.d("DROIDALERTER","About to process email");
		Email email = new Email();
		email.setSubject(intent.getStringExtra(EmailReceivedIntent.EXTRA_SUBJECT));
		email.setFrom(intent.getStringExtra(EmailReceivedIntent.EXTRA_FROM));
		AlertMessage msg = filter.filter(email);

		// TODO filtering alerts and react according to the alert
        new Thread(new SpeakRunner(msg)).start();
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        if (vibrator != null){
            Log.d("DROIDALERTER","Vivrator found");
            vibrator.vibrate(2000);
        }else{
            Log.d("DROIDALERTER","Vivrator not found");
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


    private class SpeakRunner implements Runnable{

        private final AlertMessage message;

        public SpeakRunner(AlertMessage message) {
            this.message = message;
        }

        @Override
        public void run() {
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "1");
            tts.speak(message.getAlertLevel(), TextToSpeech.QUEUE_ADD, params);

        }
    }
}
