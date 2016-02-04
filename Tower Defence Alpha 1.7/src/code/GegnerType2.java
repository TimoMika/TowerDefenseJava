package code;

public class GegnerType2 extends Gegner {
			
	public GegnerType2(Main ma) {
		super(ma);
		setting = main.settings("Gegner Type2");

		health = setting.health_max;
		health_max = setting.health_max;
		DMG = setting.DMG;
		loot = setting.loot;
		speed_Max = setting.speed_Max;
		speed = setting.speed;
		drehGesch = setting.drehGesch;
		bild = setting.bild;

	}
	
	public void machDeinDing() {
		durchkommen();
		sterben();
		bewegen();
		darstellen();
		lebensleiste();
		speedReset();
	}
	
}
