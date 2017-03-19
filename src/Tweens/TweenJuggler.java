package Tweens;

import java.util.ArrayList;

public class TweenJuggler {
	private static TweenJuggler instance = new TweenJuggler();
	ArrayList<Tween> jugglerList = new ArrayList<>();
	public TweenJuggler(){
			instance = this;
	}
	public void add(Tween tween){
		jugglerList.add(tween);
	}
	public void nextFrame(){
		for (int i=0; i< jugglerList.size(); i++){
			jugglerList.get(i).update();
			if(jugglerList.get(i).isComplete()){
				jugglerList.remove(i);

			}
		}
	}
	public static TweenJuggler getInstance(){
		return instance;
	}

	
	
}