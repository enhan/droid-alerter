package eu.enhan.alerter.action;

import android.speech.tts.TextToSpeech;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: manu
 * Date: 06/03/13
 * Time: 19:29
 * To change this template use File | Settings | File Templates.
 */
public class SpeakRunner implements Runnable {



	private final TextToSpeech tts;
	private final String text;


	private SpeakRunner(TextToSpeech tts, String text) {
		this.tts = tts;
		this.text = text;
	}

	@Override
	public void run() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, "1");
		tts.speak(text, TextToSpeech.QUEUE_ADD, params);
	}


	public static class Builder{
		private TextToSpeech tts;
		private String text;

		public Builder(TextToSpeech tts) {
			this.tts = tts;
		}

		public Builder withText(String text){
			this.text = text;
			return this;
		}

		public SpeakRunner build(){
			return new SpeakRunner(tts, text);
		}
	}
}
