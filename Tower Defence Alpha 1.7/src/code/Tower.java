package code;
import static java.lang.Math.PI;
import static java.lang.Math.sqrt;
import processing.core.PImage;
import processing.core.PVector;

public class Tower {
	Main main;
	Menue menue;
	Spiel spiel;
	Settings settings;
	Roboter robo;
	
	double energyKonsum;
	float schaden;
	int breite;
	int hoehe;
	int HBbreite;
	int HBhoehe;
	int range;
	int frequenz;
	int preis;
	int type;
	int energyPriority;
	String name;
	PImage base;
	PImage gun;
	PImage cover;
	float ausrichtung;
	int button;
	PVector position;
	
	public Tower(Main ma) {
		main = ma;
		spiel = main.spiel();
		energyPriority = 1;
	}
	
	public Integer getEnergyPriority() {
		return energyPriority;
	}
	

	public void changePriority(int w) {
		 if(energyPriority > 0-w && energyPriority <= 10-w) {energyPriority += w;}
		 spiel.sortTowers();		
	}
	
	public void upgradeSchaden(float plusSchaden, int preis) {
		if(spiel.geld >= preis) {schaden += plusSchaden; spiel.geld -= preis;}
	}
	
	public void upgradeRange(float plusRange, int preis) {
		if(spiel.geld >= preis) {range += plusRange; spiel.geld -= preis;}
	}
	
	public void setzePosition(PVector startPos) {
		position = startPos;
	}
	
	double dist(PVector t, PVector g){
		float distX = t.x - g.x;
		float distY = t.y - g.y;
		double dist_t_g = sqrt((distX * distX) + (distY * distY));
		return dist_t_g;
	}
	
	boolean pos(float z){return z>0;}
	boolean neg(float z){return z<0;}
	
	void schuss(float zielX,float zielY, float startX, float startY){
		main.strokeCap(1);
		main.strokeWeight(6);
		main.stroke(200, 0, 100, 80);
		main.line(startX, startY, zielX, zielY);
		main.strokeWeight(1);
		main.stroke(0, 178, 255);
		main.line(startX, startY, zielX, zielY);
	}
	
	void zielen_ausrichten() {
		for (Gegner g : spiel.gegners) {
				if(angreifbar(g)){
					PVector positionGun = new PVector(position.x+(breite/2), position.y+(hoehe/2));
					double dist = dist(positionGun, g.position);
					double alpha = (double) (Math.acos(-(g.position.y-(position.y+40))/dist));
					main.stroke(126);
					schuss(position.x + 40, position.y + 40, g.position.x, g.position.y);
					spiel.energy -= (energyKonsum);
					g.health = g.health - schaden;
					if(g.position.x >= (position.x+40)) {ausrichtung = (float) alpha;}
					if(g.position.x <= (position.x+40)) {ausrichtung = (float) ((double)((2*PI)-alpha));}
					break;
			}
		}
	}
	
	public void infoDisplay() {
		main.fill(0, 80);		
		main.strokeWeight(2);
		main.stroke(120,120,250);
		//main.rect(555, 776, 30, 18);
		main.ellipse(position.x+(breite/2), position.y+(hoehe/2), 2 * range, 2 * range);
		main.fill(255);
		
		main.text(String.valueOf((float)(((int)(schaden*10))/10.0)), 470, 740);
		main.text("Schaden:", 400, 740);
		main.text(range, 470, 765);
		main.text("Range:", 400, 765);
		main.text(energyPriority, 470, 790);
		main.text("Priorität:", 400, 790);
		main.text(this.toString(), 100 , 765);
		
				
		if(main.steuerung.mausbedeckt(515+100, 726, 135, 18)) {button = 1;}
		else if(main.steuerung.mausbedeckt(515+100, 751, 135, 18)) {button = 2;}
		else if(main.steuerung.mausbedeckt(515+100, 776, 30, 18)) {button = 3;}
		else if(main.steuerung.mausbedeckt(555+100, 776, 30, 18)) {button = 4;}
		else{button = 0;}
		
		if(main.steuerung.click("links")) {buttonWeiter();}
		
		if(button == 1) {main.fill(50,220,255);}
		main.text("Upgraden für: " + 90, 520, 740);
		main.fill(255);
		if(button == 2) {main.fill(50,220,255);}
		main.text("Upgraden für: " + 80, 520, 765);
		main.fill(255);
		if(button == 3) {main.fill(50,220,255);}
		main.text("[+]", 520, 790);
		main.fill(255);
		if(button == 4) {main.fill(50,220,255);}
		main.text("[-]", 560, 790);
		main.fill(255);
	}
	
	boolean angreifbar(Gegner a){
		PVector positionGun = new PVector(position.x+(breite/2), position.y+(hoehe/2));
		double dist = dist(positionGun, a.position);
		if(a.position.y < 720 && dist <= range){
			return true;
		}
		return false;
	}
	
	void buttonWeiter() {
		switch (button) {
		case 1: upgradeSchaden((float) 0.1, 50); break;
		case 2: upgradeRange(10, 50); break;
		case 3: changePriority(1); break;
		case 4: changePriority(-1); break;
		}
	}

	public void machDeinDing() {
		
	}


	
}
