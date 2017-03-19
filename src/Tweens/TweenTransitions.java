package Tweens;

public class TweenTransitions {

    private String transitionType;

    public TweenTransitions(String string) {
        transitionType = string;
    }

    public double applyTransition(double percentDone) {
        if (transitionType == "linearTransition") {
            return linearTransition(percentDone);
        } else if (transitionType == "easeInOut") {
            return easeInOut(percentDone);
        }
        throw new RuntimeException("no valid transition Type");
    }

    private double easeInOut(double percentDone) {
            return Math.sin(percentDone);
    }

    public double linearTransition(double percentDone) {
        return percentDone;
    }

    private void intoTransition(double startVal, double endVal, double time, double percentDone) {

    }

    public String getTransitionType() {
        return transitionType;
    }
}