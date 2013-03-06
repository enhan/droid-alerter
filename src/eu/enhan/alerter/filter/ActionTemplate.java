package eu.enhan.alerter.filter;

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

    private ActionTemplate(boolean speak, boolean vibrate, boolean visual) {
        this.speak = speak;
        this.vibrate = vibrate;
        this.visual = visual;
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



    public static class Builder{
        private boolean speak = false;
        private boolean vibrate = false;
        private boolean visual = false;

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

        public ActionTemplate build(){
            return new ActionTemplate(speak,vibrate,visual);
        }

    }

}
