package code;

import processing.core.PImage;

public class BauHilfe {
	Main main;
	Spiel spiel;
	Menue menue;
	Settings settings;
	
	String name;
	int breite;
	int hoehe;
	int HBbreite;
	int HBhoehe;
	PImage image1;
	PImage image2;
	PImage image3;
	int range = 0;
	
	
	public BauHilfe(Main ma,String setName, Menue me) {
		main = ma;
		menue = me;
		settings = main.settings(setName);
		spiel = main.spiel();
		name = settings.name;
		HBbreite = settings.HBbreite;
		HBhoehe = settings.HBhoehe;
		breite = settings.breite;
		hoehe = settings.hoehe;
		range = settings.range;
		image1 = settings.base; //main.settings.EG_bild;
		image2 = settings.gun; //main.settings.BT_gun;
		image3 = settings.cover; //main.settings.BT_cover;
	}
	
	public void darstellen() {
		int red = 0;
		int green = 0;
		
		float flaechenPos1x = main.mouseX-HBbreite - 100;
		float flaechenPos1y = main.mouseY-HBhoehe; 
		float flaechenPos2x = main.mouseX+HBbreite - 100;
		float flaechenPos2y = main.mouseY+HBhoehe;
		float imgPos1x = main.mouseX-(breite/2) - 100;
		float imgPos1y = main.mouseY-(hoehe/2);
		
		if(spiel.bedeckt(flaechenPos1x, flaechenPos1y, flaechenPos2x, flaechenPos2y)) {red = 250;}
		else {green = 250;} 
				
		main.stroke(red,green,0,100);
		main.strokeWeight(2);
		main.fill(0,0,0,0);
		main.ellipse(main.mouseX-100,main.mouseY,2 * range,2 * range);
		main.fill(red,green,0,70);
		main.noStroke();
		main.rect(flaechenPos1x,flaechenPos1y,2*HBbreite,2*HBhoehe);
		main.tint(255, 180);
		main.image(image1,imgPos1x,imgPos1y); 
		if(image2 != null) {main.image(image2,imgPos1x,imgPos1y);}
		if(image3 != null) {main.image(image3,imgPos1x,imgPos1y);}
		
		main.fill(0);
		main.noTint();
	}
	
	void towerSetzen(){
		System.out.println(main.steuerung.released);
		if(main.steuerung.released){
			//System.out.println(HBbreite);
			if(spiel.bedeckt(main.mouseX - 100 - HBbreite , main.mouseY - HBhoehe, main.mouseX - 100 + HBbreite, main.mouseY + HBhoehe) == false){
				 spiel.neuerTower(name);
				 menue.building = false;
				 main.mouseButton = 0;
			}
		}
	}
	
	void machDeinDing(){
		darstellen();
		towerSetzen();
		//if(spiel.menue.building){darstellen();}
	}

}
