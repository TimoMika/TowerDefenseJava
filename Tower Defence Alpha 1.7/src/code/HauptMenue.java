package code;

import processing.core.PConstants;
import processing.core.PImage;

public class HauptMenue {
	Main main;
	Steuerung steuerung;

	PImage startBild;
	PImage gameOverBild;
	int eintrag = 0;
	
	public HauptMenue(Main ma) {
		main = ma;
		steuerung = main.steuerung();
		
		startBild = main.loadImage("Background.png");
		gameOverBild = main.loadImage("GameOver.png");
	}
	
	void mainMenue() {
		main.background(0);
		main.image(startBild,70,0);
		main.fill(255);
		main.textSize(45);
		
		if(steuerung.mausbedeckt(220, 140, 300, 50)) {eintrag = 1;}
		else if(steuerung.mausbedeckt(220, 200, 340, 50)) {eintrag = 2;}
		else if(steuerung.mausbedeckt(220, 260, 340, 50)) {eintrag = 3;}
		else {eintrag = 0;}
		
		if(steuerung.click("links")) {mainMenueWeiter(); main.delay(200);}
				
		if(eintrag == 1) {main.fill(0,220,255);}
		main.text("Spiel Starten",220, 180);
		main.fill(255);
		//main.textSize(50);
		
		if(eintrag == 2) {main.fill(0,220,255);}
		main.text("Spiel Beenden",220, 240);
		main.fill(255);
		
		//if(eintrag == 3) {main.fill(0,220,255);}
		//main.text("Credits",220, 300);
		//main.fill(255);
		
		main.textSize(60);
		//main.fill(255,10,10);
		//main.text("Achtung WIP", 500, 650);
		main.fill(255);
		main.text("Tower Defense",130,60);
		main.textSize(30);
		main.text("by Timo und Mika",600, 60);

		main.text("Version:", 200, 800);
		main.text(main.version, 325, 800);
	}		

	void mainMenueWeiter() {
		switch (eintrag) {
			case 1: main.spielStart(); main.SpielActive = true; main.keyCode = 0; break;
			case 2: main.exit(); break;
			case 3: System.out.println("von Timo und Mika"); break;
		}
	}

	void gameOverScreen() {
		main.image(gameOverBild, 0, 0);
		main.keyPressed();
		main.mouseClicked();
		if(main.keyPressed || main.mousePressed); {
			if(main.keyCode == PConstants.ENTER || main.mouseButton == PConstants.LEFT) {
				main.SpielActive = false;
				eintrag = 1;
				main.delay(600);
			}
		}
		main.mouseButton = 0;
		main.keyCode = 0;
	}
}
