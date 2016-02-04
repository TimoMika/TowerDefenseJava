package code;

import processing.core.PImage;

public final class Settings {
	
	static PImage testImage = null;
	Main main;
	
	public Settings(Main ma, String art) {
		main = ma;		
		icon = main.loadImage("GegnerLv2.png");
		setupSetting(art);
	}

	double health_max;
	float speed;
	float speed_Max;
	int DMG;
	int loot;
	int drehGesch;
	float schaden;
	double energyKonsum;
	int frequenz;
	int range;
	int schussSpeed;
	int schussLaenge;
	int preis;
	PImage base;
	PImage gun;
	PImage cover;
	double energyProduce;
	int breite;
	int hoehe;
	int HBhoehe;
	int HBbreite;
	int type;
	String name;
	PImage bild;
	PImage icon;
	int gegnerMenge;
	double rotSpeed;
	double slowFactor;
	
	void setupSetting(String art) {

	if(art == "GegnerType1") {
	name = "Gegner Type1";
	health_max = 100;
	speed = 2;
	speed_Max = 2;
	DMG = 1;
	loot = 2;
	drehGesch = 90;//nur Teiler von 90 d.h: 1,2,3,5,6,9,10,15,18,30,45,90
	bild = main.loadImage("GegnerLv1.png");;
	}
	
	else if(art == "GegnerType2") {
	name = "Gegner Type2";
	health_max = 90;
	speed = 3;
	speed_Max = 3;
	DMG = 3;
	loot = 4;
	drehGesch = 10;//nur Teiler von 90 d.h: 1,2,3,5,6,9,10,15,18,30,45,90
	bild = main.loadImage("GegnerLv2.png");
	}
	
	else if(art == "SchussTower") {
	schaden = (float) 40;
	energyKonsum = 0.2;
	breite = 80;
	hoehe = 80;
	HBhoehe = 26;
	HBbreite = 26;
	frequenz = 20;
	range = 200;
	schussSpeed = 10;
	schussLaenge = 25;
	preis = 50;
	type = 11;
	name = "Schuss Tower";
	base = main.loadImage("BlitzTower.png");
	gun = main.loadImage("LaserTowerGun.png");
	cover = main.loadImage("BlitzTowerCovering.png");
	icon = main.loadImage("LaserTowerMenue.png");
	}
	
	else if(art == "LaserTower") {
	schaden = (float) 2;
	energyKonsum = 0.05;
	breite = 80;
	hoehe = 80;
	HBhoehe = 26;
	HBbreite = 26;
	frequenz = 1;
	range = 150;
	preis = 120;
	type = 12;
	name = "Laser Tower";
	base = main.loadImage("LaserTowerBase.png");
	gun = main.loadImage("LaserTowerGun.png");
	icon = main.loadImage("LaserTowerMenue.png");
	}
	
	else if(art == "BlitzTower") {
	schaden = (float) 0.1;
	gegnerMenge = 5;
	slowFactor = 0.6;
	energyKonsum = 0.01;
	breite = 80;
	hoehe = 80;
	HBhoehe = 26;
	HBbreite = 26;
	frequenz = 1;
	range = 120;
	preis = 70;
	rotSpeed = 0.2;
	type = 13;
	name = "Blitz Tower";
	base = main.loadImage("BlitzTower.png");
	gun = main.loadImage("BlitzTowerBlitze.png");
	cover = main.loadImage("BlitzTowerCovering.png");
	icon = main.loadImage("BlitzTowerMenue.png");
	}
	
	else if(art == "EnergyGenerator") {
	energyProduce = 0.06;
    breite = 80;
	hoehe = 40;
	HBbreite = 40;
	HBhoehe = 20;
	preis = 100;
	type = 21;
	name = "Energy Generator";
	base = main.loadImage("Generator.png");
	icon= main.loadImage("GeneratorMenue.png");
	}

	else {
		health_max		= 0;
		speed			= 0;
		speed_Max 		= 0;
		DMG				= 0;
		loot			= 0;
		drehGesch		= 0;
		schaden			= 0;
		energyKonsum	= 0;
		frequenz		= 0;
		range			= 0;
		schussSpeed		= 0;
		schussLaenge	= 0;
		preis			= 0;
		base 			= null;
		gun 			= null;
		cover 			= null;
		energyProduce 	= 0;
		breite			= 0;
		hoehe			= 0;
		HBhoehe			= 0;
		HBbreite		= 0;
		type			= 0;
		name 			= "0";
		bild 			= null;
		icon 			= null;
		gegnerMenge		= 0;
		rotSpeed		= 0;
		slowFactor		= 0;
	}	
	}
	public static String GegnerType1_name = "Gegner Type1";
	public static float GegnerType1_health_max = 100;
	public static float GegnerType1_speed = 2;
	public static float GegnerType1_speed_Max = 2;
	public static int GegnerType1_DMG = 1;
	public static int GegnerType1_loot = 2;
	public static int GegnerType1_drehGesch = 90;//nur Teiler von 90 d.h: 1,2,3,5,6,9,10,15,18,30,45,90
	public static PImage GegnerType1_bild = null; //loadTexture("GegnerLv1");;
	
	public static String GegnerType2_name = "Gegner Type2";
	public static float GegnerType2_health_max = 90;
	public static float GegnerType2_speed = 3;
	public static float GegnerType2_speed_Max = 3;
	public static int GegnerType2_DMG = 3;
	public static int GegnerType2_loot = 4;
	public static int GegnerType2_drehGesch = 10;//nur Teiler von 90 d.h: 1,2,3,5,6,9,10,15,18,30,45,90
	public static PImage GegnerType2_bild = null; //loadTexture("GegnerLv2");
	
	
	public static float SchussTower_schaden = (float) 40;
	public static double SchussTower_energyKonsum = 0.2;
	public static int SchussTower_breite = 80;
	public static int SchussTower_hoehe = 80;
	public static int SchussTower_HBhoehe = 26;
	public static int SchussTower_HBbreite = 26;
	public static int SchussTower_frequenz = 15;
	public static int SchussTower_range = 150;
	public static int SchussTower_schussSpeed = 12;
	public static int SchussTower_schussLaenge = 25;
	public static int SchussTower_preis = 50;
	public static int SchussTower_type = 11;
	public static String SchussTower_name = "Schuss Tower";
	public static PImage SchussTower_base = null; //loadTexture("BlitzTower");
	public static PImage SchussTower_gun = null; //loadTexture("LaserTowerGun");
	public static PImage SchussTower_cover = null; //loadTexture("BlitzTowerCovering");
	public static PImage SchussTower_icon = null; //loadTexture("LaserTowerMenue");
	
	public static float LaserTower_schaden = (float) 2;
	public static double LaserTower_energyKonsum = 0.05;
	public static int LaserTower_breite = 80;
	public static int LaserTower_hoehe = 80;
	public static int LaserTower_HBhoehe = 26;
	public static int LaserTower_HBbreite = 26;
	public static int LaserTower_frequenz = 1;
	public static int LaserTower_range = 150;
	public static int LaserTower_preis = 120;
	public static int LaserTower_type = 12;
	public static String LaserTower_name = "Laser Tower";
	public static PImage LaserTower_base = null; //loadTexture("LaserTowerBase");
	public static PImage LaserTower_gun = null; //loadTexture("LaserTowerGun");
	public static PImage LaserTower_icon = null; //loadTexture("LaserTowerMenue");
	
	public static float BlitzTower_schaden = (float) 01;
	public static int BlitzTower_gegnerMenge = 5;
	public static double BlitzTower_slowFactor = 0.6;
	public static double BlitzTower_energyKonsum = 0.01;
	public static double BlitzTower_rotSpeed = 0.2; 
	public static int BlitzTower_breite = 80;
	public static int BlitzTower_hoehe = 80;
	public static int BlitzTower_HBhoehe = 26;
	public static int BlitzTower_HBbreite = 26;
	public static int BlitzTower_frequenz = 1;
	public static int BlitzTower_range = 120;
	public static int BlitzTower_preis = 70;
	public static int BlitzTower_type = 13;
	public static String BlitzTower_name = "Blitz Tower";
	public static PImage BlitzTower_base = null; //loadTexture("BlitzTower");
	public static PImage BlitzTower_gun = null; //loadTexture("BlitzTowerBlitze");
	public static PImage BlitzTower_cover = null; //loadTexture("BlitzTowerCovering");
	public static PImage BlitzTower_icon = null; //loadTexture("BlitzTowerMenue");
	

	public static double EnergyGenerator_energyProduce = 0.06;
	public static int EnergyGenerator_breite = 80;
    public static int EnergyGenerator_hoehe = 40;
	public static int EnergyGenerator_HBbreite = 40;
	public static int EnergyGenerator_HBhoehe = 20;
	public static int EnergyGenerator_preis = 100;
	public static int EnergyGenerator_type = 21;
	public static String EnergyGenerator_name = "Energy Generator";
	public static PImage EnergyGenerator_base = null; //loadTexture("Generator");
	public static PImage EnergyGenerator_icon= null; //loadTexture("GeneratorMenue");
}