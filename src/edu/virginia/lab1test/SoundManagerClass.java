
package edu.virginia.lab1test;


import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JApplet;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class SoundManagerClass  {
	boolean play = false;
	 ContinuousAudioDataStream loop = null;
	 
	 private AudioClip song; // Sound player
	 
	     private URL songPath; // Sound path

	 
	
	public void playSoundEffect(String file){
		try{
		 AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(file).getAbsoluteFile());
	        Clip clip1 = AudioSystem.getClip();
	        clip1.open(audioInputStream);
	        clip1.start();
	        
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void playMusic(String filename){
		
		    new javafx.embed.swing.JFXPanel();
		    //String uriString = new File(file).toURI().toString();
		    //new MediaPlayer(new Media(uriString)).play();
		    File file = new File(filename);
			Media player = new Media(file.toURI().toString());
			MediaPlayer play = new MediaPlayer(player);
			play.play();
			
			
		
		
		
	}
	
	
	
}
