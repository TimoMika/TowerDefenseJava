package code;
import processing.core.PImage;
import processing.core.PVector;

public class Building {
	Main main;
	Menue menue;
	Spiel spiel;
	Settings setting;
	
	PVector position;
    int breite;
	int hoehe;
	int HBbreite;
	int HBhoehe;
	int preis;
	PImage bild;
	
	public Building(Main ma) {
		main = ma;
		spiel = main.spiel();		
	}
	
	public void setzePosition(PVector startPos) {
		position = startPos;
		
	}
	

	void darstellen() {
		main.fill(250,150,150);
		main.rect(position.x, position.y, breite, hoehe);
	}

	public void machDeinDing() {
		darstellen();
	}


	
}
