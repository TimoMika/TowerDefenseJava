package code;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;

public class Gegner {
	
	Main main;
	Maps map;
	Spiel spiel;
	Settings setting;
	
	double speed;
	double speed_Max;
	int DMG;
	double health;
	double health_max;
	int loot;
	int drehGesch;
	int ausrichtung;
	int aktuellerWendepunkt = 0;
	PVector bewegungsRichtung = new PVector (0, 0);
	PVector position;
	PImage bild;	
	
	public Gegner(Main ma) {
		main = ma;
		spiel = main.spiel();
		
	 /* health = settings.Gegener_health_max;
		health_max = settings.Gegener_health_max;
		DMG = settings.Gegener_DMG;
		loot = settings.Gegener_loot;
		speed = settings.Gegener_speed;
		drehGesch = settings.Gegener_drehGesch;
		bild = settings.Gegner_bild; */
	}
	
	void setzePosition(PVector startPos) {
		position = startPos;
	}
	
	boolean amZiel(){
		boolean b = (aktuellerWendepunkt == spiel.map.wendepunkte_pos.size());
			return b; 
	}
	
	void sterben() {
		if(health <= 0) {
			health = 0;
			spiel.zuLoeschen.add(this);
			spiel.geld = spiel.geld + loot;
		}
	}
	
	void durchkommen(){
		if(amZiel()){
			speed = 0;
			spiel.zuLoeschen.add(this);
			spiel.leben -= DMG;
		}
	}

	public boolean bedeckt(float x, float y, float x2, float y2) {
		boolean b = ( (bewegungsRichtung.x == -1 && x <= x2)||(bewegungsRichtung.x == 1 && x >= x2)||(bewegungsRichtung.y == -1 && y <= y2)||(bewegungsRichtung.y == 1 && y >= y2) );
		return b;
	}
	
	void richtungAendern() {
		float wePuX = spiel.wendepunkte_pos.get(aktuellerWendepunkt).x;
		float wePuY = spiel.wendepunkte_pos.get(aktuellerWendepunkt).y;
		
		if(bedeckt(position.x, position.y, wePuX, wePuY)) { 	
			bewegungsRichtung = spiel.wendepunkte_dir.get(aktuellerWendepunkt);
			position.x = (spiel.wendepunkte_pos.get(aktuellerWendepunkt).x + bewegungsRichtung.x);
			position.y = (spiel.wendepunkte_pos.get(aktuellerWendepunkt).y + bewegungsRichtung.y);
			aktuellerWendepunkt = aktuellerWendepunkt + 1;
		}
				//System.out.println(aktuellerWendepunkt);
}
	
	void bewegen() {
		double by = bewegungsRichtung.y * speed;
		double bx = bewegungsRichtung.x * speed;
		position.y = (float) (position.y + by);
		position.x = (float) (position.x + bx); 
		if(speed != 0){
			richtungAendern();
		}
		
	}
	
	void lebensleiste(){
		int balkenLaenge_max = 20;
		float faktor = (float) (health/health_max);
		int faktorGruen = (int) (faktor*250);
		int faktorRot = (int) ((1-faktor)*250);
		main.fill(faktorRot, faktorGruen, 0);
		main.stroke(20, 20, 200);
		int balkenLaenge = (int)(balkenLaenge_max * faktor);
		main.rect(position.x - balkenLaenge_max/2, position.y - bild.height/2 ,balkenLaenge, 2);	
	}
	
	void init_ausrichtung(){
		if (bewegungsRichtung.x == 1){ausrichtung = 90;}
		if (bewegungsRichtung.x == -1){ausrichtung = 270;}
		if (bewegungsRichtung.y == 1){ausrichtung = 180;}
		if (bewegungsRichtung.y == -1){ausrichtung = 0;}
	}
	
	void drehen(){
		if (ausrichtung == 360){ausrichtung = 0;}
		if (bewegungsRichtung.x == 1 && ausrichtung != 90){
			if(ausrichtung < 90){ausrichtung = ausrichtung + drehGesch;}
			if(ausrichtung > 90){ausrichtung = ausrichtung - drehGesch;}
		}
		if (bewegungsRichtung.x == -1 && ausrichtung != 270){
			if(ausrichtung >= 180 && ausrichtung < 270){ausrichtung = ausrichtung + drehGesch;}
			if(ausrichtung == 0){ausrichtung = 360 - drehGesch;}
			if(ausrichtung > 270){ausrichtung = ausrichtung - drehGesch;}
		}
		if (bewegungsRichtung.y == 1 && ausrichtung != 180){
			if(ausrichtung < 180){ausrichtung = ausrichtung + drehGesch;}
			if(ausrichtung > 180){ausrichtung = ausrichtung - drehGesch;}
		}
		if (bewegungsRichtung.y == -1 && ausrichtung != 0){
			if(ausrichtung <= 90){ausrichtung = ausrichtung - drehGesch;}
			if(ausrichtung >= 270){ausrichtung = ausrichtung + drehGesch;}
		}
	}
	void darstellen() {		
		drehen();
		main.translate((int)position.x , (int)position.y);
		main.rotate(ausrichtung*PConstants.TWO_PI/360);
		main.image(bild, - (bild.height / 2), - (bild.width / 2));
		main.rotate(-(ausrichtung*PConstants.TWO_PI/360));
		main.translate((int)-position.x ,(int) - position.y);
		
		//main.image(bild,position.x,position.y);
	}
	void speedReset(){
		speed = speed_Max;
	}
	
	
	void machDeinDing() { }
}