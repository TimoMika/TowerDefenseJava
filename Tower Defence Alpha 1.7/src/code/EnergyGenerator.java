package code;

public class EnergyGenerator extends Building {
	double energyProduce;
	
	public EnergyGenerator(Main ma){
		super(ma);
		setting = main.settings("Energy Generator");
		
		energyProduce = setting.energyProduce;
		preis = setting.preis;
		breite = setting.breite;
		hoehe = setting.hoehe;
		HBbreite = setting.HBbreite;
		HBhoehe = setting.HBhoehe;
		bild = setting.icon;
	}
	
	void darstellen() {
		bild = main.loadImage("Generator.png");
		main.image(bild,position.x,position.y);
		main.fill(50,50,250);
		main.fill(250);
	}
	
	public void machDeinDing() {
		if(spiel.energy<spiel.maxEnergy) {spiel.energy += energyProduce;}
		darstellen();
	}
	
}
