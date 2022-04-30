package connection;

import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class Alarm {
	private InputStream in;
	private AudioStream as;

	public Alarm(String fileName) throws Exception {
		this.in = new FileInputStream(fileName);
		this.as = new AudioStream(in);
	}

	public void play() {
		new Thread() {
			public void run() {
				AudioPlayer.player.start(as);
			}
		}.start();
	}

	public void stop() {
		new Thread() {
			public void run() {
				AudioPlayer.player.stop(as);
			}
		}.start();
	}

}
