package eu.enhan.alerter.filter;

import android.app.IntentService;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import com.fsck.k9.EmailReceivedIntent;
import eu.enhan.alerter.common.AlertMessage;
import eu.enhan.alerter.common.Email;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 05/03/13
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class EmailFilterService extends IntentService implements TextToSpeech.OnInitListener, TextToSpeech.OnUtteranceCompletedListener{

	private final EmailFilter filter;
	private TextToSpeech tts;

	public EmailFilterService() {
		super("EmailFilterService");
		filter = new BasicOnSubjectFilter();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Email email = new Email();
		email.setSubject(intent.getStringExtra(EmailReceivedIntent.EXTRA_SUBJECT));
		email.setFrom(intent.getStringExtra(EmailReceivedIntent.EXTRA_FROM));
		AlertMessage msg = filter.filter(email);

		// TODO filtering alerts and react according to the alert
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "1");
		tts.speak(msg.getAlertLevel(), TextToSpeech.QUEUE_FLUSH, null);
		long endTime = System.currentTimeMillis() + 15*1000;
		while (System.currentTimeMillis() < endTime){
			synchronized (this){
				try{
					wait(endTime - System.currentTimeMillis());
				} catch (InterruptedException e) {

				}
			}
		}

	}

	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.FRANCE);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			}else {
				//tts.setOnUtteranceProgressListener(new TTSCleaner(tts));
				//tts.setOnUtteranceCompletedListener(this);
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}
	}

	@Override
	public void onDestroy() {
		if (tts != null){
			tts.shutdown();
		}
		super.onDestroy();
	}

	@Override
	public void onCreate() {
		super.onCreate();
		tts = new TextToSpeech(getApplicationContext(), this);
	}

	@Override
	public void onUtteranceCompleted(String utteranceId) {

		if ("1".equals(utteranceId)){
			tts.shutdown();
		}
	}
}
