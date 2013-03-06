package eu.enhan.alerter.filter;

import com.google.common.collect.ImmutableSet;
import eu.enhan.alerter.action.ActionFactory;
import eu.enhan.alerter.common.Email;

import java.util.Set;

/**
 * @author Emmanuel Nhan
 */
public class ActionTemplate {

    public static Builder newBuilder(){
        return new Builder();
    }

    private final boolean speak;
    private final boolean vibrate;
    private final boolean visual;
	private final String ttsPattern;

    private ActionTemplate(boolean speak, boolean vibrate, boolean visual, String ttsPattern) {
        this.speak = speak;
        this.vibrate = vibrate;
        this.visual = visual;
	    this.ttsPattern = ttsPattern;
    }

    public boolean isSpeak() {
        return speak;
    }

    public boolean isVibrate() {
        return vibrate;
    }

    public boolean isVisual() {
        return visual;
    }

	public Set<Runnable> instantiateActions(ActionFactory factory, Email email){
		ImmutableSet.Builder<Runnable> builder = ImmutableSet.builder();
		if (isSpeak()){
			builder.add(factory.speakActionBuilder().withText(ttsPattern).build());
		}


		return builder.build();
	}


    public static class Builder{
        private boolean speak = false;
        private boolean vibrate = false;
        private boolean visual = false;
	    private String textToSpeakPattern = "";

        public Builder withSpeak(boolean speak){
            this.speak=speak;
            return this;
        }

        public Builder withSpeak(){
            return withSpeak(true);
        }

        public Builder withVibrate(boolean vibrate){
            this.vibrate = vibrate;
            return this;
        }

        public Builder withVibrate(){
            return withVibrate(true);
        }

        public Builder withVisual(boolean speak){
            this.visual=speak;
            return this;
        }

        public Builder withVisual(){
            return withVisual(true);
        }

	    public Builder withTextToSpeakPattern(String ttsPattern){
		    this.textToSpeakPattern = ttsPattern;
		    return this;
	    }

        public ActionTemplate build(){
            return new ActionTemplate(speak,vibrate,visual, textToSpeakPattern);
        }



    }

}
