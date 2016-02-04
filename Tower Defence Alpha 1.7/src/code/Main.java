package code;

import java.awt.Cursor;
import java.awt.Image;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.event.*;

@SuppressWarnings("serial")
public class Main extends PApplet {
	static public void main(String args[]) {
		PApplet.main(new String [] { "code.Main" } );
	}
	Spiel spiel;
	HauptMenue hauptMenue;
	Steuerung steuerung;
	
	ArrayList<Settings> settingObjekte = new ArrayList<Settings>();
	PImage icon = loadImage("icon2.png");
	public String version = "Alpha 1.7";
	public int breite = 1124;
	public int hoehe = 820;
	public float mausScrolling; 
	boolean SpielActive = false;
	Cursor cursor = new Cursor(Cursor.CROSSHAIR_CURSOR);
		
	public Spiel spiel() {
		return spiel;
	}
	
	public HauptMenue hauptMenue() {
		return hauptMenue;
	}
	
	public Settings settings(String art) {
		Object returnOb = null;
		for(Settings sO: settingObjekte) {
			if(sO.name == art) {returnOb = sO;}
		}
		return (Settings) returnOb;
	}
	
	public Steuerung steuerung() {
		return steuerung;
	}
	
	public void spielStart() {
		spiel = new Spiel(this);
		spiel.neuesSpiel();
	}
	
	void settingErstellen() {
		Settings LT_settings = new Settings(this,"LaserTower"); settingObjekte.add(LT_settings);
		Settings GT1_settings = new Settings(this,"GegnerType1"); settingObjekte.add(GT1_settings);
		Settings GT2_settings = new Settings(this,"GegnerType2"); settingObjekte.add(GT2_settings);
		Settings BT_settings = new Settings(this,"BlitzTower"); settingObjekte.add(BT_settings);
		Settings EG_settings = new Settings(this,"EnergyGenerator"); settingObjekte.add(EG_settings);
		Settings ST_settings = new Settings(this,"SchussTower"); settingObjekte.add(ST_settings);
	}
	
	public void setup() {
		steuerung = new Steuerung(this);
		settingErstellen();
		hauptMenue = new HauptMenue(this);
		frame.setTitle("Tower Defence");
		frame.setIconImage((Image) icon.getNative());
		frame.setCursor(cursor);
		//frame.setResizable(true);
		size(breite, hoehe);
		noStroke();
		//frameRate(64); //nicht nötig da standart 60 FPS
		fill(255);
		background(0);
		Settings.testImage = loadImage("GegnerLv2.png");
	}

	public void keyPressed() {
		if(keyCode == PConstants.ESC) {
			if(spiel == null) {keyCode = 0; key = 0;}
			else {keyCode = 0; key = 0; 
				if(spiel.running) {spiel.running = false; spiel.hilfsWert = true; delay(200);}
				else {spiel.running = true; spiel.hilfsWert = true; delay(200);}
			}
		}
	}
	
	private void inputRest() {
		key = 0;
		keyCode = 0;
		mouseButton = 0;
		mausScrolling = 0;
	}
	
	public void mouseWheel(MouseEvent event) {
		mausScrolling = event.getCount();
	}
	
	public void mouseReleased() {
		steuerung.released = true;
		steuerung.pressed = false;
	}
	
	public void mousePressed() {
		steuerung.released = false;
		steuerung.pressed = true;
	}
	
	public void delay(int time) {
		try {
		    Thread.sleep(time);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
	}
	
	public void draw() {
		if(SpielActive) {spiel.gameLoop();}
		else {hauptMenue.mainMenue();}
		inputRest();
    }

}