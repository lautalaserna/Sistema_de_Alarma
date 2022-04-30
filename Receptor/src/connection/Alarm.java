package connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
/*
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
*/
public class Alarm {
	private Clip audioClip;
	private AudioInputStream audioStream;
	
	public Alarm(String path) {
		File audioFile = new File(path);
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		AudioFormat format = audioStream.getFormat();
		DataLine.Info info = new DataLine.Info(Alarm.class, format);
		
		try {
			audioClip = AudioSystem.getClip();
			audioClip.open(audioStream);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void loop() {
		new Thread (() -> {
			audioClip.setFramePosition(0);
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		}) {}.start();
	}
	
	public void stop() {
		audioClip.stop();
	}
	
}
//	private InputStream in;
//	private AudioStream as;
//
//	public Alarm(String fileName) throws Exception {
//		this.in = new FileInputStream(fileName);
//		this.as = new AudioStream(in);
//	}
//
//	public void play() {
//		new Thread() {
//			public void run() {
//				AudioPlayer.player.start(as);
//			}
//		}.start();
//	}
//
//	public void stop() {
//		new Thread() {
//			public void run() {
//				AudioPlayer.player.stop(as);
//			}
//		}.start();
//	}

