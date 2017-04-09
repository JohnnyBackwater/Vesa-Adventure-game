package com.backwatersoftware.asd;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequencer;
import java.io.IOException;

public class MusaSoitin implements Runnable {
	public boolean running;
	public Thread thread;
	public String kappale;
	public Sequencer soitto;

	public MusaSoitin() {

	}

	public MusaSoitin(String kappale) {
		this.kappale = kappale;
	}

	public synchronized void soitaKappale(String kappalee) {
		this.kappale = kappalee;
		this.running = true;
		this.thread = new Thread(this);
		this.thread.start();
	}

	public synchronized void start() {
		this.thread = new Thread(this);
		this.thread.start();

	}

	public synchronized void stop() {
		this.running = false;
		this.soitto.close();
	}


	public void grassField() {

		this.kappale = "music/1.mid";
		start();
	}

	public void bossMusa() {

		try {
			this.soitto = MidiSystem.getSequencer();
			this.soitto.open();
			this.soitto.setSequence(MusaSoitin.class.getResourceAsStream("/music/2.mid"));
			this.soitto.start();
		} catch (MidiUnavailableException | IOException | InvalidMidiDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void endingMusa() {
		this.kappale = "music/3.mid";
		start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
