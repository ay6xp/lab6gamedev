package Tweens;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;

public class Tween {
    TweenParam tweenParam;
    DisplayObject sprite;
    TweenTransitions tweenTransitions;
    double percentDone = 0;
    double startTime = -1;

    Tween(DisplayObject object) {

    }

    public Tween(DisplayObject object, TweenTransitions transition) {
        sprite = object;
        tweenTransitions = transition;
    }

    public void animate(TweenableParams fieldToAnimate, double startVal, double endVal, double time) {
        tweenParam = new TweenParam(fieldToAnimate, startVal, endVal, time);
    }

    public void update() {
        if (tweenParam.getParem() == TweenableParams.Y) {
            if (startTime == -1){
                startTime = (double)System.currentTimeMillis();
            }
            percentDone = ((double)System.currentTimeMillis() - startTime)/(tweenParam.time*1000);
            sprite.setPositionY(tweenParam.startVal - (tweenParam.startVal- tweenParam.endVal)*tweenTransitions.applyTransition(percentDone));

            System.out.println((double)System.currentTimeMillis());
        }
    }


    public boolean isComplete() {
        if (percentDone >= 1){
            startTime = -1;
            return true;
        }
        else{
            return false;
        }
    }

    public void setValue(TweenableParams param, double value) {

    }


}
