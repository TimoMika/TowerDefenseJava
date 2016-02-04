package code;


public class BuildingEintrag extends SeitenLeiste{
	Main main;
	int preis;
	
	public BuildingEintrag(Main ma, Menue me, String setName, int num) {
		super(ma, me);
		main = ma;
		setting = main.settings(setName);
		eintrag = num;
		name = setting.name;
		preis = setting.preis;
		icon = setting.icon;
	}

	public void machDeinDing(int o){
		positionieren(o);
		darstellen_Icons_info(setting.preis, setting.name);
		bauen(name, preis);
		
	}
}
