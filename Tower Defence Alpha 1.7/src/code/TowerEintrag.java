package code;

public class TowerEintrag extends SeitenLeiste{
	
	int preis;
	
	public TowerEintrag(Main ma, Menue me, String setName, int num) {
		super(ma, me);
		setting = main.settings(setName);
		eintrag = num;
		icon = setting.icon;
		name = setting.name;
		preis = setting.preis;
	}

	public void machDeinDing(int o){
		positionieren(o);
		darstellen_Icons_info(preis, name);
		bauen(name, preis);
	}
	
}
