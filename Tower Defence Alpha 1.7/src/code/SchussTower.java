package code;


import static java.lang.Math.PI;
import java.lang.Math;
import java.util.ArrayList;

import processing.core.PVector;

public class SchussTower extends Tower {
	ArrayList<Schuss> schuesse = new ArrayList<Schuss>();
	ArrayList<Schuss> loeschSchuesse = new ArrayList<Schuss>();
	int i = 0;
	double flugZeit;
	int schussLaenge;
	int schussSpeed;
	
	
	public SchussTower(Main ma) {
		super(ma);		
		settings = main.settings("Schuss Tower");
		
		schussSpeed = settings.schussSpeed;
		schussLaenge = settings.schussLaenge;
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
	
	void schiessen_ausrichten() {
		PVector positionGun = new PVector(position.x+(breite/2), position.y+(hoehe/2));
		boolean schussImRohr;
		try {
			if(dist(schuesse.get(schuesse.size()-1).schussPos, positionGun) < 80 ) {
				schussImRohr = true;
			}
			else {
				schussImRohr = false;
			}
		} catch (Exception e) {
			schussImRohr = false;
		}
		System.out.println(schussImRohr);
		for (Gegner g : spiel.gegners) {
			if(angreifbar(g)){
				PVector ziel = zielBerechnen(positionGun, g);
				double dist = dist(positionGun, ziel);
				if(i > frequenz) {
					spiel.energy -= energyKonsum;
					Schuss s = new Schuss(main, g, positionGun, ziel, 0);
					schuesse.add(s);
					i = 0;
				}
				//float AlteAusrichtung = ausrichtung;
				if(!schussImRohr) {
					double alpha = (double) (Math.acos(-(ziel.y-(position.y+40))/dist));
					if(ziel.x >= (position.x+40)) {ausrichtung = (float) alpha;}
					if(ziel.x <= (position.x+40)) {ausrichtung = (float) ((double)((2*PI)-alpha));}
				}
				/*System.out.println(ausrichtung);
				if(Math.abs(ausrichtung - AlteAusrichtung) >= 0.4){
					if(ausrichtung < AlteAusrichtung){ausrichtung = AlteAusrichtung - 0.3f;}
					else{ausrichtung = AlteAusrichtung + 0.3f;}
					
				}*/
				i++;				
				break;
			}
		}
					
	}
	
	PVector zielBerechnen(PVector start, Gegner g) {
		PVector ziel = new PVector(0,0);
		flugZeit = (dist(start, g.position)/schussSpeed);
		
		ziel.x = (float) (g.position.x - (g.bild.height/2) + flugZeit*(g.bewegungsRichtung.x * g.speed) );
		ziel.y = (float) (g.position.y - (g.bild.height/2) + flugZeit*(g.bewegungsRichtung.y * g.speed) );

		return ziel;
	}
		
	void darstellen() {
		main.image(base, position.x, position.y);
		main.translate((position.x + (gun.height/2)) , (position.y + (gun.height/2)));
		main.rotate(ausrichtung);
		main.image(gun,-(gun.height/2),-(gun.height/2) /*- (gun.width / 2)*/);
		main.rotate((float)-ausrichtung);
		main.translate( -(position.x + (gun.height/2)), -(position.y + (gun.height/2)));
	}
	
	void buttonWeiter() {
		switch (button) {
		case 1: upgradeSchaden((float) 1, 80); break;
		case 2: upgradeRange(10, 60); break;
		case 3: changePriority(1); break;
		case 4: changePriority(-1); break;
		}
	}
	
	public void machDeinDing() {
		if(spiel.energy >= energyKonsum) {schiessen_ausrichten();}	
		for(Schuss s: schuesse) {s.SchussMache();}
		for(Schuss o: loeschSchuesse) {schuesse.remove(o);}
		darstellen();
	}
	
	class Schuss{
		Main main;
		Gegner geg;
		int i;
		double lifeTime;
		double flugTime;
		PVector start;
		PVector ziel;
		PVector schussPos = new PVector(0,0);
		PVector schussRichtung = new PVector(0,0);
		
		public Schuss(Main ma, Gegner g, PVector startPos, PVector zielPos, double fZ) {
			main = ma;
			geg = g;
			i = 0;
			flugTime = fZ;
			schussPos = startPos;
			start = startPos;
			ziel = zielPos;
			schussRichtung.x = (float) ((ziel.x - start.x)/dist(start, ziel));
			schussRichtung.y = (float) ((ziel.y - start.y)/dist(start, ziel));
		}
				
		void treffen() {
			//if(geg == null) {loeschSchuesse.add(this);}
			/*else if(lifeTime >= flugTime) {
				geg.health = geg.health - schaden;
				loeschSchuesse.add(this);	
			}*/
			//else {lifeTime++;}
			
			/*for(Gegner zg : spiel.gegners) {
				if(dist(schussPos, zg.position) < 10){
					geg.health = geg.health - schaden;
					loeschSchuesse.add(this);				
				}				
			}*/
			
			if(schussPos.x < 100 || schussPos.y < 0 || schussPos.x > main.breite+100 || schussPos.y > main.hoehe-100) {
				loeschSchuesse.add(this);	
			}
			
			for(Gegner ge : spiel.gegners){
			if(dist(schussPos, ge.position) < ge.bild.height){
				geg.health = ge.health - schaden;
				loeschSchuesse.add(this);	
				}			
			}
		}
		
		void schuss(){
			schussPos.x += schussRichtung.x * schussSpeed;
			schussPos.y += schussRichtung.y * schussSpeed;	
		}
		
		void schussMalen(){
			//main.image(geg.bild, ziel.x, ziel.y);
			main.strokeWeight(7);
			main.stroke(200, 0, 100, 80);
			main.line(schussPos.x - (schussRichtung.x * schussLaenge), schussPos.y - (schussRichtung.y * schussLaenge) ,schussPos.x ,schussPos.y);	
			main.strokeWeight(2);
			main.stroke(0, 178, 255);
			main.line(schussPos.x - (schussRichtung.x * schussLaenge), schussPos.y - (schussRichtung.y * schussLaenge) ,schussPos.x ,schussPos.y);	
		}
		
		void SchussMache() {
			treffen();
			schuss();
			schussMalen();
			
		}
		
	}

}
