package code;

import static java.lang.Math.PI;
import processing.core.PVector;

public class LaserTower extends Tower {
	public LaserTower(Main ma) {
		super(ma);
		
		settings = main.settings("Laser Tower");
		range = settings.range;
		energyKonsum = settings.energyKonsum;
		preis = settings.preis;
		schaden = settings.schaden;
		frequenz = settings.frequenz;
		breite = settings.breite;
		hoehe = settings.hoehe;
		HBbreite = settings.HBbreite;
		HBhoehe = settings.HBhoehe;
		type = settings.type;
		base = settings.base;
		gun = settings.gun;
	}
	
	void schuss(float zielX,float zielY, float startX, float startY){
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
				this.schuss(position.x + 40, position.y + 40, g.position.x, g.position.y);
				spiel.energy -= energyKonsum;
				g.health = g.health - schaden;
				if(g.position.x >= (position.x+40)) {ausrichtung = (float) alpha;}
				if(g.position.x <= (position.x+40)) {ausrichtung = (float) ((double)((2*PI)-alpha));}
				break;
			}
		}
	}
	
	void darstellen() {
		main.image(base, position.x, position.y);
		main.translate((position.x + (gun.height/2)) , (position.y + (gun.height/2)));
		main.rotate(ausrichtung);
		main.image(gun,-(gun.height/2), -(gun.width / 2));
		main.rotate((float)-ausrichtung);
		main.translate( -(position.x + (gun.height/2)), -(position.y + (gun.height/2)));
	}
	
	void buttonWeiter() {
		switch (button) {
		case 1: upgradeSchaden((float) 0.2, 40); break;
		case 2: upgradeRange(10, 60);
		case 3: changePriority(1); break;
		case 4: changePriority(-1); break;
		}
	}
	
	public void machDeinDing() {
		if(spiel.energy >= energyKonsum) {zielen_ausrichten();}
		darstellen();
		//main.color(255, 204, 0);
	}
	
}
