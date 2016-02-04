package code;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import processing.core.PImage;
import processing.core.PVector;

public class Spiel {
	Main main;
	Menue menue;
	HauptMenue hauptMenue;
	PauseMenue pauseMenue;
	Maps map;
	Wave aktiveWave;	
	PImage GameOver;
	int timer;
	int wellenTimer;
	int geld;
	//int h = 0;
	int wave;
	boolean hilfsWert;
	float maxEnergy = 100;
	float energy = maxEnergy;
	int maxLeben = 100;
	int leben = maxLeben;
	public int waveOffsetTime = 0;
		
	public ArrayList<Wave> waves = new ArrayList<>();
	public ArrayList<Point[]> bedeckteFlaechen;
	public ArrayList<PVector> wendepunkte_pos;
	public ArrayList<PVector> wendepunkte_dir;
	public ArrayList<Gegner> gegners = new ArrayList<Gegner>();
	public ArrayList<Tower> towers = new ArrayList<Tower>();
	public ArrayList<Roboter> roboters = new ArrayList<Roboter>();
	public ArrayList<Building> buildings = new ArrayList<Building>();
	public ArrayList<Object> zuLoeschen = new ArrayList<Object>();
	public boolean running;
	Comparator<? super Tower> tcomp;


	
	public Spiel(Main ma) {
		main = ma;
		hauptMenue = main.hauptMenue();
		tcomp = new TowerComp();
	}
	
	public Menue menue() {
		return menue;
	}
	
	void neuesSpiel() {
		neueMap(1);
		neueWaves();
		neuesMenue();
		neuesPauseMenue();
		neueRoboter();
		//comp = new MeinComparator();
		//neueMenueLeisten();
		geld = 500;
		wellenTimer = 550;
		timer = 100;
		running = true;
	}

	void neuesMenue() {
		menue = new Menue(main);
		//menue bereich bedecken
			Point[] flaeche = new Point[2];
			flaeche[0] = new Point(-100,0);
			flaeche[1] = new Point(0, 720);
			bedeckteFlaechen.add(flaeche);
	}

	void neuesPauseMenue() {
		pauseMenue = new PauseMenue(main);
	}
	
	void neuerTower(String name){		
		boolean bu = false;
		boolean to = false;
		Tower t = new Tower(main);
		Building b = new Building(main);
		//Settings setting = main.settings(name);
		if(name == "Schuss Tower"){t = new SchussTower(main); to = true;}
		if(name == "Laser Tower"){t = new LaserTower(main); to = true;}
		if(name == "Blitz Tower"){t = new BlitzTower(main); to = true;}
		if(name == "Energy Generator"){b = new EnergyGenerator(main); bu = true;}
		int breite = 0;
		int hoehe = 0;
		if(to){breite = t.breite;}else{breite = b.breite;}
		if(to){hoehe = t.hoehe;}else{hoehe = b.hoehe;}
		PVector startPosition = new PVector(main.mouseX - (breite/2) - 100, main.mouseY - (hoehe/2));		
		//PVector endPosition = new PVector(startPosition.x+breite,startPosition.y+hoehe);
		if(geld < t.preis) {}
		else{
		Point[] flaeche = new Point[2];
		t.setzePosition(startPosition);
		b.setzePosition(startPosition);

		if(name == "Energy Generator") {
			flaeche[0] = new Point((int) main.mouseX-b.HBbreite - 100, main.mouseY-b.HBhoehe);
			flaeche[1] = new Point((int) main.mouseX+b.HBbreite - 100, main.mouseY+b.HBhoehe);
			bedeckteFlaechen.add(flaeche);
		}
		else {
			flaeche[0] = new Point((int) main.mouseX-t.HBbreite - 100, main.mouseY-t.HBhoehe);
			flaeche[1] = new Point((int) main.mouseX+t.HBbreite - 100, main.mouseY+t.HBhoehe);
			bedeckteFlaechen.add(flaeche);
		}
		menue.BuildingType = 0;
		if(to) {geld -= t.preis;}
		if(bu) {geld -= b.preis;}
		if(to){towers.add(t);}
		if(bu){buildings.add(b);}
		}
	}
	
	void neueWaves(){ 
		//{type, laenge/anzahl, abstand, offsetTime}
		
		ArrayList<int[]> gruppen = new ArrayList<>();
		int[] gruppe1 = {1, 20, 70, 0};			gruppen.add(gruppe1);
		int[] gruppe2 = {1, 20, 50, 1000};		gruppen.add(gruppe2);
		Wave w1 = new Wave(main, gruppen, 4000);
		
		ArrayList<int[]> gruppen2 = new ArrayList<>();
		int[] gruppe12 = {2, 5, 60, 1000};		gruppen2.add(gruppe12);
		int[] gruppe22 = {1, 10, 60, 600};		gruppen2.add(gruppe22);
		int[] gruppe32 = {2, 20, 50, 800};		gruppen2.add(gruppe32);	
		Wave w2 = new Wave(main, gruppen2, 100);
		
		
		waves.add(w1);
		waves.add(w2);
		aktiveWave = waves.get(0);
	}
	
	void neueRoboter(){
		Roboter Hamlet = new Roboter(main,"Hamlet"); roboters.add(Hamlet);
		Roboter Steve = new Roboter(main,"Steve"); roboters.add(Steve);
		Roboter Ghost = new Roboter(main,"Ghost"); roboters.add(Ghost);
		Roboter Randy = new Roboter(main,"Randy"); roboters.add(Randy);
		Roboter Sam = new Roboter(main,"Sam"); roboters.add(Sam);
	}
	
	/*
	void neuesBuilding(int type, int mausX, int mausY) {		
		Building b = new Building(main);
		if(type == 1){b = new EnergyGenerator(main);}
		PVector startPosition = new PVector(mausX - (b.breite/2), mausY - (b.hoehe/2));		
		//PVector endPosition = new PVector(startPosition.x+breite,startPosition.y+hoehe);
		if(geld < b.preis || bedeckt(mausX-b.HBbreite, mausY-b.HBhoehe, mausX+b.HBbreite, mausY+b.HBhoehe)) {}
		else {
			Point[] flaeche = new Point[2];
			b.setzePosition(startPosition);
			flaeche[0] = new Point((int) mausX-b.HBbreite, mausY-b.HBhoehe);
			flaeche[1] = new Point((int) mausX+b.HBbreite, mausY+b.HBhoehe);
			bedeckteFlaechen.add(flaeche);
			menue.BuildingType = 0;
			geld -= b.preis;
			
		}
	}*/

	void neuerGegner(int offsetPos, int type) {
		Gegner g = new Gegner(main);
		if(type == 1){g = new GegnerType1(main);}
		if(type == 2){g = new GegnerType2(main);}
		
		g.bewegungsRichtung = wendepunkte_dir.get(0);
		PVector spawnPosition = new PVector();
		spawnPosition.x = wendepunkte_pos.get(0).x + (wendepunkte_dir.get(0).x * -offsetPos);
		spawnPosition.y = wendepunkte_pos.get(0).y + (wendepunkte_dir.get(0).y * -offsetPos);
		g.setzePosition(spawnPosition);
		gegners.add(g);
		g.init_ausrichtung();
	}
	
	void neueMap(int Lv){
		map = new Maps(main,this,Lv);
		wendepunkte_pos = map.getWendepunkte_pos();
		wendepunkte_dir = map.getWendepunkte_dir();
		bedeckteFlaechen =  map.getWegFlaechen();
	}
	
	void neueWelle(int start, int laenge,int rate, int type) {
		for(int i = 1; i <= laenge; i++) {
			neuerGegner(i*rate + start, type);
		}
	}
	
	class TowerComp implements Comparator<Tower> {
		@Override
		public int compare(Tower t1, Tower t2) {
			return -(t1.getEnergyPriority().compareTo(t2.getEnergyPriority()));
		}
	}
	
	public void sortTowers() {
		Collections.sort(towers, tcomp);
	}
	
	
	void checkStatus() {
		if(leben<=0) {
			running = false;
			leben = 0;
		}
		if(energy<0) {energy = 0;}
	}
	
	void clean() {
		for (Object o : zuLoeschen) {
			if(o instanceof Gegner) {gegners.remove(o);}
			if(o instanceof Tower) {towers.remove(o);}
			if(o instanceof Building) {buildings.remove(o);}
			if(o instanceof Point[]) {bedeckteFlaechen.remove(o);} 
		}
		zuLoeschen.clear();
	}
	
	void pause() {
		if(hilfsWert) {main.keyCode = 0; main.key = 0; hilfsWert = false;}
		if(leben<=0) {hauptMenue.gameOverScreen();}		
		else {
			pauseMenue.pauseMenue();
		}
	}
	
	void removeBedeckteFlaechen(float objektPosX, float objektPosY) {
		for(Point[] p : bedeckteFlaechen){
			if(objektPosX == p[0].x && objektPosY == p[0].y){
				zuLoeschen.add(p);
			}
		}
	}
	
	void waveManager() {
		if(aktiveWave == null) {
			if(gegners.size() == 0) {
				main.textSize(64);
				main.fill(20, 20, 20, 220);
				main.rect(285, 325, 320, 90);
				main.fill(10, 255, 10);
				main.text("You Win !", 300, 400);
			}
		} else {
			aktiveWave.machDeinDing();
		}
	}
	
	public boolean bedeckt(float pos1X, float pos1Y, float pos2X, float pos2Y) {
		boolean beidesBedeckt = false;
		for(int i = 0; i < bedeckteFlaechen.size(); i++) {
			boolean xBedeckt = false;
			boolean yBedeckt = false;
			if(pos1X <= bedeckteFlaechen.get(i)[0].x && bedeckteFlaechen.get(i)[0].x <= pos2X) {xBedeckt = true;}
			if(pos1X >= bedeckteFlaechen.get(i)[0].x && bedeckteFlaechen.get(i)[1].x >= pos1X) {xBedeckt = true;}
			if(pos1Y <= bedeckteFlaechen.get(i)[0].y && bedeckteFlaechen.get(i)[0].y <= pos2Y) {yBedeckt = true;}
			if(pos1Y >= bedeckteFlaechen.get(i)[0].y && bedeckteFlaechen.get(i)[1].y >= pos1Y) {yBedeckt = true;}
			if(xBedeckt && yBedeckt) {beidesBedeckt = true;}
		}
		return beidesBedeckt;	
	}
	 		
	public static int myRandom(int low, int high) {
		high++;
		return (int) (Math.random() * (high - low) + low);
	}
	
	int spielStufe() {
		if(wave <= 30){return 1;}
		if(wave <= 200 && wave > 30) {return 2;}
		else {return myRandom(1, 2);}
	}
	
	void gameLoop() {
		if(running) {
		main.background(0);
		main.translate(100, 0);
		map.drawMap();
		for (Building b : buildings) {b.machDeinDing();}
		for (Gegner g : gegners) {g.machDeinDing();}
		for (Tower t : towers) {t.machDeinDing();}
		map.drawDetails();
		waveManager();
		menue.machMenue();
		checkStatus();
		clean();
		}
		else {pause();}
	}
}
