package Tweens;

import java.util.ArrayList;

public class TweenJuggler {
	ArrayList<Tween> jugglerList = new ArrayList<>();
	public TweenJuggler(){

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
	
	
}