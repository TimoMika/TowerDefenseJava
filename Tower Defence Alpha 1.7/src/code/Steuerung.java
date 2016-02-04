package code;

import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;

import processing.core.PConstants;

public class Steuerung /*implements MouseListener, KeyListener, MouseWheelListener*/{
	Main main;
	boolean released = false;
	boolean pressed = false;
	MouseWheelEvent event;
	public int MouseWheel;
	
	public ArrayList<Object> aktiveFlaechen = new ArrayList<Object>();
	
	public Steuerung(Main ma) {
		main = ma;
	}
	
	//mouse
	public boolean mausbedeckt(int posX, int  posY, int groeseX, int groeseY) {
		if(main.mouseX > posX && main.mouseX < (posX+groeseX) && main.mouseY > posY && main.mouseY < (posY+groeseY)) {
			return true;
		}
		return false;
	}
	
	public boolean key(char taste) {
		main.keyPressed();
		if(main.keyPressed) {
			if(main.key == taste) {
				return true;
			}
		}
		return false;
	}
	
	public boolean key(int taste) {
		main.keyPressed();
		if(main.keyPressed) {
			if(main.keyCode == taste) {
				return true;
			}
		}
		return false;
	}
	
	public boolean wheel(String richtung) {
		main.mouseWheel();
		if(main.mausScrolling > 0 && richtung == "runter") {return true;}
		if(main.mausScrolling < 0 && richtung == "hoch") {return true;}
		return false;
	}
	
	public boolean mouseDown(String taste) {
		if(released){
			return true;
		}
		return false;
	}
	
	public boolean click(String taste){
		if(pressed){
			if(main.mouseButton == PConstants.LEFT && taste == "links") {
				return true;
			}
			if(main.mouseButton == PConstants.RIGHT && taste == "rechts") {
				return true;
			}
		}
		return false;
	}
	public boolean clickOn(String taste,int posX, int  posY, int groeseX, int groeseY){
		if(click(taste) && mausbedeckt(posX,  posY, groeseX, groeseY)){
			return true;
		}
		return false;
	}
}
