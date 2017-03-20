package Tweens;

import java.lang.Math;

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

       // System.out.println((percentDone));
        //if(percentDone+Math.log(percentDone)>0){
          //  return percentDone+Math.log(percentDone);
        //}
        return Math.log(percentDone+1)/Math.log(2);
    }

    public double linearTransition(double percentDone) {
        return percentDone;
    }

    private void introTransition(double startVal, double endVal, double time, double percentDone) {

    }

    public String getTransitionType() {
        return transitionType;
    }
}