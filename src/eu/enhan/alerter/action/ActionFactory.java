package eu.enhan.alerter.action;

import android.speech.tts.TextToSpeech;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 06/03/13
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
public class ActionFactory {

	private final TextToSpeech tts;

	public ActionFactory(TextToSpeech tts) {
		this.tts = tts;
	}


	public SpeakRunner.Builder speakActionBuilder(){
		return new SpeakRunner.Builder(tts);
	}

}
