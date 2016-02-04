package code;


public class InfoLeiste {

	Main main;
	Menue menue;
	
	public InfoLeiste(Main ma, Menue me) {
		main = ma;
		menue = me;
	}
	
	public void machDeinDing() {
		if(menue.infoObjekt != null) {((Tower) menue.infoObjekt).infoDisplay();}
	}	
}
