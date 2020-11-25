package ui.abstract_Factory;

import java.util.ArrayList;

import ui.HanbatPhotoShop_SERVER;

public class Rect_Factory implements AbstractFactory {
	boolean x;
	HanbatPhotoShop_SERVER server;
	
	public Rect_Factory(boolean x, HanbatPhotoShop_SERVER server) {
		this.x = x;
		this.server = server;
	}

	@Override
	public Displays createMethod() {
		// TODO Auto-generated method stub
		return new Rect(x, server);
	}

}
