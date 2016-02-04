package code;

import java.util.ArrayList;

import processing.core.PImage;

public class SeitenLeiste {
	Settings setting;
	Main main;
	Spiel spiel;
	Menue menue;
	
	int type;
	int eintrag;
	public int offset;
	int tapSize;
	PImage icon;
	PImage image1;
	PImage image2;
	PImage image3;
	String name;
	int position;
	public ArrayList<TowerEintrag> tap1 = new ArrayList<TowerEintrag>();
	public ArrayList<BuildingEintrag> tap2 = new ArrayList<BuildingEintrag>();
	
	
	public SeitenLeiste(Main ma, Menue me) {
		main = ma;
		menue = me;
		spiel = main.spiel();
	}
	
	public void bauEintraegeAdden() {
		
		//tap 1
		tap1.clear();
		int num = 0;
	 	TowerEintrag t1 = new TowerEintrag(main, menue, "Laser Tower", num); tap1.add(t1); num++;
	 	TowerEintrag t2 = new TowerEintrag(main, menue, "Laser Tower", num); tap1.add(t2); num++;
	 	TowerEintrag t3 = new TowerEintrag(main, menue, "Blitz Tower", num); tap1.add(t3); num++;
	 	TowerEintrag t4 = new TowerEintrag(main, menue, "Schuss Tower", num); tap1.add(t4); num++;
	 	TowerEintrag t5 = new TowerEintrag(main, menue, "Laser Tower", num); tap1.add(t5); num++;
	 	TowerEintrag t6 = new TowerEintrag(main, menue, "Laser Tower", num); tap1.add(t6); num++;
	 	TowerEintrag t7 = new TowerEintrag(main, menue, "Blitz Tower", num); tap1.add(t7); num++;
	 	TowerEintrag t8 = new TowerEintrag(main, menue, "Schuss Tower", num); tap1.add(t8); num++;
	 	TowerEintrag t11 = new TowerEintrag(main, menue, "Laser Tower", num); tap1.add(t11); num++;
	 	TowerEintrag t21 = new TowerEintrag(main, menue, "Laser Tower", num); tap1.add(t21); num++;
	 	TowerEintrag t31 = new TowerEintrag(main, menue, "Blitz Tower", num); tap1.add(t31); num++;
	 	TowerEintrag t41 = new TowerEintrag(main, menue, "Schuss Tower", num); tap1.add(t41); num++;
	 	TowerEintrag t51 = new TowerEintrag(main, menue, "Laser Tower", num); tap1.add(t51); num++;
	 	TowerEintrag t61 = new TowerEintrag(main, menue, "Laser Tower", num); tap1.add(t61); num++;
	 	TowerEintrag t71 = new TowerEintrag(main, menue, "Blitz Tower", num); tap1.add(t71); num++;
	 	TowerEintrag t81 = new TowerEintrag(main, menue, "Schuss Tower", num); tap1.add(t81); num++;
	 	
		//tap 2
	 	tap2.clear();
	 	//System.out.println(tap2.size());
		num = 0;
		BuildingEintrag b1 = new BuildingEintrag(main, menue, "Energy Generator", num); tap2.add(b1);num++;
		BuildingEintrag b2 = new BuildingEintrag(main, menue, "Energy Generator", num); tap2.add(b2);num++;
		//tap3
		spiel.roboters.clear();
		num = 0;
	}
	
	void bauen(String namedeszubauenden, int preis){
		if(main.steuerung.clickOn("links", 0, position, 100, 100) && !locked(preis)){
			main.fill(30,180,250);
			main.rect(-100 , position, 100, 100);
			menue.bauHelp = new BauHilfe(main,namedeszubauenden, menue);
			menue.building = true;
		}
			//System.out.println("bauen wird ausgef�rt");
	}
	
	void Robosetzen(){
		if(main.steuerung.clickOn("links", 0, position, 100, 100)){
			
		}
	}
	void scrollen() {
		int spied = 30;
		if(main.steuerung.wheel("hoch")) {offset += spied;}
		if(main.steuerung.wheel("runter")) {offset -= spied;}
	}

	void darstellen(int tap) {
		for(int i=0; i < tapSize; i++) {
			main.fill(100,100,200,50);
			main.strokeWeight(2);
			main.stroke(120,120,250);
			main.rect(-100,(i*100) + offset, 100, 100);
			main.strokeWeight(1);
		}
	}
	void offsetMax_Min(int tap){
		if(tap == 1){tapSize = tap1.size();}
		if(tap == 2){tapSize = tap2.size();}
		if(tap == 3){tapSize = spiel.roboters.size();}
		if(offset < -tapSize * 100 + main.hoehe){
			offset = -tapSize * 100 + main.hoehe;
		}
		if(offset > 0){offset = 0;}
	}
	
	boolean locked(int preis) {
		if(preis > main.spiel.geld) {return true;}
		return false;
	}	

	void positionieren(int o) {
		//System.out.println(o);
		position = (eintrag * 100) + o;
		
	}
	
	void darstellen_Icons_info(int preis, String name) {
		if(locked(preis)) {main.tint(255, 100);}
		main.image(icon, -100,  position);
		main.noTint();
		if(main.steuerung.mausbedeckt(0, position, 100, 100)) {
			int mX = main.mouseX-100; int mY = main.mouseY;
			main.fill(0,120); 
			main.noStroke();
			main.rect(mX+15, mY-15, 130, 40);
			main.fill(255);
			main.text(name, mX+20, mY);
			main.text("Preis:", mX+20, mY+20);
			main.text(preis, mX+70, mY+20);
		}
	}
	
	 public void machDeinDing(int tap) {
		 scrollen();
		 offsetMax_Min(tap);
		 darstellen(tap);
		 if(tap == 1){for(TowerEintrag e : tap1) {e.machDeinDing(offset);}}
		 if(tap == 2){for(BuildingEintrag e : tap2) {e.machDeinDing(offset);}}
	 }
	
}
