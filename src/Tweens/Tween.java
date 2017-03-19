package Tweens;

import edu.virginia.engine.display.DisplayObject;
import edu.virginia.engine.display.Sprite;

import java.util.ArrayList;

public class Tween {
    ArrayList<TweenParam> tweenParam = new ArrayList<>();
    DisplayObject sprite;
    TweenTransitions tweenTransitions;
    double percentDone = 0;
    double startTime = -1;

    public Tween(DisplayObject object) {

    }

    public Tween(DisplayObject object, TweenTransitions transition) {
        sprite = object;
        tweenTransitions = transition;
    }

    public void animate(TweenableParams fieldToAnimate, double startVal, double endVal, double time) {
        tweenParam.add(new TweenParam(fieldToAnimate, startVal, endVal, time));
    }

    public void update() {
        for(int i=0; i < tweenParam.size(); i++){
            TweenParam tmp = tweenParam.get(i);
            if (tmp.getParem() == TweenableParams.Y) {
                if (startTime == -1) {
                    startTime = (double) System.currentTimeMillis();
                }
                percentDone = ((double) System.currentTimeMillis() - startTime) / (tmp.getTweenTime() * 1000);
                sprite.setPositionY(tmp.getStartVal() - (tmp.startVal - tmp.endVal) * tweenTransitions.applyTransition(percentDone));

                System.out.println((double) System.currentTimeMillis());
            }
            if (tmp.getParem() == TweenableParams.X) {
                if (startTime == -1) {
                    startTime = (double) System.currentTimeMillis();
                }
                percentDone = ((double) System.currentTimeMillis() - startTime) / (tmp.time * 1000);
                sprite.setPositionX(tmp.startVal - (tmp.startVal - tmp.endVal) * tweenTransitions.applyTransition(percentDone));
                System.out.println((double) System.currentTimeMillis());
            }
            if (tmp.getParem() == TweenableParams.SCALE_X) {
                if (startTime == -1) {
                    startTime = (double) System.currentTimeMillis();
                }
                percentDone = ((double) System.currentTimeMillis() - startTime) / (tmp.time * 1000);
                sprite.setScaleX(tmp.startVal - (tmp.startVal - tmp.endVal) * tweenTransitions.applyTransition(percentDone));
                System.out.println((double) System.currentTimeMillis());
            }
            if (tmp.getParem() == TweenableParams.SCALE_Y) {
                if (startTime == -1) {
                    startTime = (double) System.currentTimeMillis();
                }
                percentDone = ((double) System.currentTimeMillis() - startTime) / (tmp.time * 1000);
                sprite.setScaleY(tmp.startVal - (tmp.startVal - tmp.endVal) * tweenTransitions.applyTransition(percentDone));
                System.out.println((double) System.currentTimeMillis());
            }
            if (tmp.getParem() == TweenableParams.ALPHA) {
                if (startTime == -1) {
                    startTime = (double) System.currentTimeMillis();
                }
                percentDone = ((double) System.currentTimeMillis() - startTime) / (tmp.time * 1000);
                sprite.setTransparency((float)(tmp.startVal - (tmp.startVal - tmp.endVal) * tweenTransitions.applyTransition(percentDone)));
                System.out.println((double) System.currentTimeMillis());
            }
        }

    }


    public boolean isComplete() {
        if (percentDone >= 1) {
            startTime = -1;
            return true;
        } else {
            return false;
        }
    }

    public void setValue(TweenableParams param, double value) {

    }


}
