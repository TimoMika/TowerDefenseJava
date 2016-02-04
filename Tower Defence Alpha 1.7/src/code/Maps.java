package code;
import java.awt.Point;
import java.util.ArrayList;

import processing.core.PImage;
import processing.core.PVector;


public class Maps {
	Main main;
	Spiel spiel;
	
	int posX;
	int posY;
	int posXalt;
	int posYalt;
	public ArrayList<PVector> wendepunkte_pos = new ArrayList<PVector>();
	public ArrayList<PVector> wendepunkte_dir = new ArrayList<PVector>();
	public ArrayList<Point[]> wegFlaechen = new ArrayList<Point[]>();
	public ArrayList<Point[]> wegFlaechen2 = new ArrayList<Point[]>();
	public PImage back;
	PImage ziel;
	int maxWendePunkte;
	PVector	p0_pos;	PVector p1_pos;	PVector p2_pos;	PVector p3_pos;	PVector p4_pos;	PVector p5_pos;	PVector p6_pos;	PVector p7_pos; PVector p8_pos;
	PVector p0_dir;	PVector p1_dir;	PVector p2_dir;	PVector p3_dir;	PVector p4_dir;	PVector p5_dir;	PVector p6_dir;	PVector p7_dir;	PVector p8_dir;
	Point[] p0_area = new Point[2]; Point[] p1_area = new Point[2]; Point[] p2_area = new Point[2]; Point[] p3_area = new Point[2]; Point[] p4_area = new Point[2];
	Point[] p5_area = new Point[2]; Point[] p6_area = new Point[2]; Point[] p7_area = new Point[2]; Point[] p8_area = new Point[2]; Point[] rand1_area = new Point[2];
	Point[] rand2_area = new Point[2]; Point[] rand3_area = new Point[2]; Point[] rand4_area = new Point[2];
		
	public Maps(Main ma,Spiel sp, int Lv) {
		main = ma;
		spiel = sp;
		back = main.loadImage("BackLv1.png");
		ziel = main.loadImage("Ziel.png");
		
		rand1_area[0] = new Point(-200, 0); 	rand1_area[1] = new Point(0, 920);
		rand2_area[0] = new Point(0, 720); 		rand2_area[1] = new Point(1224, 920);
		rand3_area[0] = new Point(1024, -200); 	rand3_area[1] = new Point(1224, 720);
		rand4_area[0] = new Point(-200, -200); 	rand4_area[1] = new Point(1024, 0);
	
		if(Lv == 1) {
			maxWendePunkte = 9;
			p0_pos = new PVector(696, 720);
			p1_pos = new PVector(696, 380);
			p2_pos = new PVector(532, 380);
			p3_pos = new PVector(532, 580);
			p4_pos = new PVector(184, 580);
			p5_pos = new PVector(184, 133);
			p6_pos = new PVector(838, 133);
			p7_pos = new PVector(838, 476);
			p8_pos = new PVector(1030, 476);
			
			p0_dir = new PVector(0, -1);
			p1_dir = new PVector(-1, 0);
			p2_dir = new PVector(0, 1);
			p3_dir = new PVector(-1, 0);
			p4_dir = new PVector(0, -1);
			p5_dir = new PVector(1, 0);
			p6_dir = new PVector(0, 1);
			p7_dir = new PVector(1, 0);
			p8_dir = new PVector(1, 0);

			p0_area[0] = new Point(676, 400); p0_area[1] = new Point(716, 720);
			p1_area[0] = new Point(512, 360); p1_area[1] = new Point(716, 400);
			p2_area[0] = new Point(512, 400); p2_area[1] = new Point(552, 600);
			p3_area[0] = new Point(164, 560); p3_area[1] = new Point(512, 600);
			p4_area[0] = new Point(164, 113); p4_area[1] = new Point(204, 560);
			p5_area[0] = new Point(204, 113); p5_area[1] = new Point(818, 153);
			p6_area[0] = new Point(818, 113); p6_area[1] = new Point(860, 456);
			p7_area[0] = new Point(818, 456); p7_area[1] = new Point(1024,496);
			p8_area[0] = new Point(920, 423); p8_area[1] = new Point(1024,531);		
		}
		
		wegFlaechen.add(rand1_area); wegFlaechen.add(rand2_area); wegFlaechen.add(rand3_area); wegFlaechen.add(rand4_area);		
		
		for(int n = 0; n < maxWendePunkte; n++) {
			switch(n) {
				case 0: wendepunkte_pos.add(p0_pos); wendepunkte_dir.add(p0_dir); wegFlaechen.add(p0_area); break;
				case 1: wendepunkte_pos.add(p1_pos); wendepunkte_dir.add(p1_dir); wegFlaechen.add(p1_area); break;
				case 2: wendepunkte_pos.add(p2_pos); wendepunkte_dir.add(p2_dir); wegFlaechen.add(p2_area); break;
				case 3: wendepunkte_pos.add(p3_pos); wendepunkte_dir.add(p3_dir); wegFlaechen.add(p3_area); break;
				case 4: wendepunkte_pos.add(p4_pos); wendepunkte_dir.add(p4_dir); wegFlaechen.add(p4_area); break;
				case 5: wendepunkte_pos.add(p5_pos); wendepunkte_dir.add(p5_dir); wegFlaechen.add(p5_area); break;
				case 6: wendepunkte_pos.add(p6_pos); wendepunkte_dir.add(p6_dir); wegFlaechen.add(p6_area); break;
				case 7: wendepunkte_pos.add(p7_pos); wendepunkte_dir.add(p7_dir); wegFlaechen.add(p7_area); break;
				case 8: wendepunkte_pos.add(p8_pos); wendepunkte_dir.add(p8_dir); wegFlaechen.add(p8_area); break;			
			}
		}
	}
	
	public ArrayList<PVector> getWendepunkte_pos() {
		return wendepunkte_pos;
	}
	
	public ArrayList<PVector> getWendepunkte_dir() {
		return wendepunkte_dir;
	}
	
	public ArrayList<Point[]> getWegFlaechen() {
		return wegFlaechen;
	}
	
	void drawMap() {
		main.image(back, 0, 0);
	}
	
	void drawDetails(){
		main.image(ziel,920, 423);
	}
}
	

