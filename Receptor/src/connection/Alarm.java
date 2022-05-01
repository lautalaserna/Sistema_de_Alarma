package connection;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;


public class Alarm {
	private final static String PATH = "media/alarm_tone.wav";
	private Clip audioClip;
	private AudioInputStream audioStream;

	public Alarm() {
		File audioFile = new File(PATH);
		try {
			audioStream = AudioSystem.getAudioInputStream(audioFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		AudioFormat format = audioStream.getFormat();
		new DataLine.Info(Alarm.class, format);

		try {
			audioClip = AudioSystem.getClip();
			audioClip.open(audioStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loop() {
		new Thread(() -> {
			audioClip.loop(Clip.LOOP_CONTINUOUSLY);
		}) {
		}.start();
	}

	public void stop() {
		audioClip.stop();
	}

}