package Tweens;

public class TweenParam {
	TweenableParams param;
	double startVal;
	double endVal;
	double time;
	public TweenParam(TweenableParams paramToTween,double startVal, double endVal,double time){
		this.param = paramToTween;
		this.startVal = startVal;
		this.endVal = endVal;
		this.time = time;
		
	}
	public TweenableParams getParem(){
		
		
		return param;
	}
	public double getStartVal(){
		return startVal;
		
	}
	public double getEndVal(){
		return endVal;
	}
	public double getTweenTime(){
		return time;
	}
	
	
}
