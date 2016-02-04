package code;


public class PauseMenue {
	Main main;
	Steuerung steuerung;
	
	int eintrag;
	
	public PauseMenue(Main ma) {
		main = ma;
		steuerung = main.steuerung();
	}
	
	void pauseMenue() {
		if(steuerung.mausbedeckt(445, 260, 240, 45)) {eintrag = 1;}
		else if(steuerung.mausbedeckt(445, 360, 285, 45)) {eintrag = 2;}
		else {eintrag = 0;}
		
		if(steuerung.click("links")) {mainMenueWeiter(); main.delay(200);}
		
		main.fill(0);
		main.rect(420, 120, 340, 300);
		main.textSize(45);
		
		main.fill(255);
		if(eintrag == 1) {main.fill(0,220,255);}
		main.text("Fortsetzen",450, 300);

		main.fill(255);
		if(eintrag == 2) {main.fill(0,220,255);}
		main.text("Hauptmenue",450, 400);
				
		main.textSize(100);
		main.fill(255,20,20);
		main.text("Pause",450,200);
	}		

	void mainMenueWeiter() {
		switch (eintrag) {
			case 1: main.spiel.running = true; break;
			case 2: main.SpielActive = false; break;
		}
	}
}
