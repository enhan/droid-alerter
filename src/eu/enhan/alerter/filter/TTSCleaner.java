package eu.enhan.alerter.filter;

import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 05/03/13
 * Time: 22:24
 * To change this template use File | Settings | File Templates.
 */
public class TTSCleaner extends UtteranceProgressListener {

	private final TextToSpeech tts;

	public TTSCleaner(TextToSpeech tts) {
		this.tts = tts;
	}

	@Override
	public void onStart(String utteranceId) {
		//To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public void onDone(String utteranceId) {
		if ("1".equals(utteranceId)){
			tts.shutdown();
		}
	}

	@Override
	public void onError(String utteranceId) {
		//To change body of implemented methods use File | Settings | File Templates.
	}
}
