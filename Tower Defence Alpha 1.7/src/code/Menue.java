package code;
import java.awt.Point;

public class Menue {
	Main main;
	Steuerung steuerung;
	Spiel spiel;
	Settings settings;
	Wave waves;
	SeitenLeiste seitenLeiste;
	BauHilfe bauHelp;
	InfoLeiste infoLeiste;
	
	public int tap;
	boolean flaechen_zeigen;
	boolean building;
	int balkenLaenge;
	int BuildingType; // 0=Nichts, 1=LaserTower, 2=EnergyGenerator, 3=Blitz Tower
	int activeBuilding;
	int mouseButton;
	Tower upgradeTower;
	Tower infoObjekt;
	
	public Menue(Main ma) {
		main = ma;
		spiel = main.spiel();
		//settings = main.settings();
		tap = 1;
		steuerung = main.steuerung();
		neueSeitenLeiste();
		neueInfoLeiste();
	}
	
	void neueSeitenLeiste() {
		seitenLeiste  = new SeitenLeiste(main, this); 
		seitenLeiste.bauEintraegeAdden();
	}
	
	void neueInfoLeiste() {
		infoLeiste = new InfoLeiste(main, this);
	}
	
	/*void mouseControl() {
		main.mousePressed();
		if(main.mousePressed) {
			
			if(main.mouseButton == PConstants.LEFT) {
				//if(BuildingType > 0 || activeBuilding > 0){
						spiel.neuerTower(BuildingType, main.mouseX-100, main.mouseY);
					//}					
				}
				else {mouseOverButton();mouseOverBuilding();}
			}
			
			/*if(main.mouseButton == PConstants.RIGHT) {
				BuildingType = 0;
				activeBuilding = 0;
			}
		
	}*/
	
	void keyControl() {
		if(spiel.hilfsWert) {main.keyCode = 0; main.key = 0; spiel.hilfsWert = false;}
		if(steuerung.key('w')) {spiel.wave += 1;}
		if(steuerung.key('g')) { spiel.geld += 10; }
		if(steuerung.key('c')) { for (Gegner g : spiel.gegners) {spiel.zuLoeschen.add(g);} }
		//if(steuerung.key('v')) { for (Tower t : spiel.towers) {spiel.zuLoeschen.add(t); spiel.removeBedeckteFlaechen(t.position.x+((main.settings(t.name).breite/2)-main.settings(t.name).HBbreite), t.position.y+((main.settings(t.name).hoehe/2)-main.settings(t.name).HBhoehe));} }
		if(steuerung.key('x')) { for (Building b : spiel.buildings) {spiel.zuLoeschen.add(b); spiel.removeBedeckteFlaechen(b.position.x, b.position.y);} }
		if(steuerung.key('b')) { if(flaechen_zeigen) {flaechen_zeigen = false;} else{flaechen_zeigen = true;} }
		if(steuerung.key('l')) { spiel.leben += 10; spiel.maxLeben += 10;}
		if(steuerung.key('e')) { spiel.energy += 10; spiel.maxEnergy += 10;}
		if(steuerung.key('n')) { seitenLeiste.offset += 4;}
		if(steuerung.key('m')) { seitenLeiste.offset -= 4;}
		if(steuerung.key('s')) { System.out.println(spiel.towers);}
	}
	
	void mouseOverBuilding() {
		if(steuerung.click("rechts")) {upgradeTower = null; infoObjekt = null;}
		for(Tower t : spiel.towers) {
			if(steuerung.clickOn("links", (int)t.position.x+100, (int)t.position.y, t.breite, t.hoehe)) {
				//upgradeTower = t;
				infoObjekt = t;				
			}
		}
	}
	
	void bedeckteFlaechen() {
		main.fill(255,0,0,75);
		for(Point[] p : spiel.bedeckteFlaechen) {
			int Xpos = p[0].x;
			int Ypos = p[0].y;
			int Xlaenge = p[1].x - Xpos;
			int Ylaenge = p[1].y - Ypos;
			main.rect(Xpos, Ypos, Xlaenge, Ylaenge);
		}
	}

	void upgradeHilfe() {
		main.textSize(20);
		main.text("upgraden", main.mouseX-100, main.mouseY);
	}
	
	void tabsZeichnen(int groeseX, int groeseY, int rectY, int posX1, int posX2, int posX3){
		int textY = 740;
		
		if(tap == 1){main.stroke(20,200,255);}
		
		main.fill(20,200,255);
		main.text("Tower", 20,textY);
		main.fill(100,100,100,100);
		main.rect(posX1, rectY , groeseX, groeseY);
		main.stroke(0);
		
		if(tap == 2){main.stroke(20,200,255);}
		main.fill(20,200,255);
		main.text("Buildings",130,textY);
		main.fill(100,100,100,100);
		main.rect(posX2, rectY , groeseX, groeseY);
		main.stroke(0);
		
		if(tap == 3){main.stroke(20,200,255);}
		main.fill(20,200,255);
		main.text("Robotter",240,textY);
		main.fill(100,100,100,100);
		main.rect(posX3, rectY , groeseX, groeseY);
		main.stroke(0);
	}
	
	void tabs(){
		int groeseX = 100;
		int groeseY = 20;
		int posY1 = 10;
		int posY2 = 120;
		int posY3 = 230;
		int rectX = 725;
		if(steuerung.click("links")){
				if(steuerung.mausbedeckt(posY1 + 100, 730, groeseX, groeseY)){tap = 1;}
				if(steuerung.mausbedeckt(posY2 + 100, 730, groeseX, groeseY)){tap = 2;}
				if(steuerung.mausbedeckt(posY3 + 100, 730, groeseX, groeseY)){tap = 3;}
				//seitenLeiste.offset = 0;
		}
		tabsZeichnen(groeseX, groeseY, rectX, posY1,posY2, posY3);
	}
	
	void energyAnzeige() {
		int balkenLaenge_max = 400;
		float faktor = spiel.energy/spiel.maxEnergy;
		int faktorGruen = (int) (faktor*250);
		int faktorRot = (int) ((1-faktor)*250);
		balkenLaenge = (int) (balkenLaenge_max * faktor);
		main.fill(faktorRot, faktorGruen, 250);
		main.stroke(120,120,250);
		main.rect(530, 22, balkenLaenge, 18);
		main.textSize(25);
		main.text("Energy:",430,40);
		main.textSize(10);
		main.text((int) spiel.energy,430,50);
		main.noStroke();
	}
	
	void lebensAnzeige() {
		int balkenLaenge_max = 300;
		float faktor = (float) spiel.leben/spiel.maxLeben;
		int faktorGruen = (int) (faktor*250);
		int faktorRot = (int) ((1-faktor)*250);
		balkenLaenge = (int) (balkenLaenge_max * faktor);
		main.fill(faktorRot, faktorGruen, 0);
		main.stroke(120,120,250);
		main.rect(110, 22, balkenLaenge, 18);
		main.textSize(25);
		main.text("Leben:",20,40);
		main.textSize(10);
		main.text(spiel.leben,20,50);
		main.noStroke();
	}
	
	void anzeigen() {
		main.fill(200,200,250);
		main.stroke(200,200,250);
		main.textSize(15);
		main.text("Geld:",890,740);
		main.text(spiel.geld, 930,740);
		main.text("FrameRate:", 890, 760);
		main.text((int) main.frameRate, 980, 760);
		main.text("Wave:",890,780);
		main.text(spiel.wave, 940,780);
		main.noStroke();
	}
	void buildingEnd(){
		//System.out.println(building);
		if(steuerung.click("rechts")){
			building = false;
		}
	}
	
	void hintergrundAnzeigen() {
		main.fill(0);
		main.noStroke();
		main.rect(-100, 0, 100, main.hoehe);
		main.rect(-100, main.hoehe-100, main.breite, 100);
	}
	
	Menue getMenue(){
		return this;
	}

	public void machMenue() {
		hintergrundAnzeigen();
		infoLeiste.machDeinDing();	
		seitenLeiste.machDeinDing(tap);
		if(flaechen_zeigen) {bedeckteFlaechen();}
		if(building) {bauHelp.machDeinDing();}
		lebensAnzeige();
		energyAnzeige();
		anzeigen();
		keyControl();
		mouseOverBuilding();
		buildingEnd();
		tabs();
	}

}
