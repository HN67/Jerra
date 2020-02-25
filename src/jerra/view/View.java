package jerra.view;

import jerra.room.Room;

abstract public class View {
	
	protected Room model;
	
	public View(Room model) {
		this.setModel(model);
	}
	
	
	public void setModel(Room model) {
		// Potential privacy leaks.
		this.model = model;
	}
	
	public Room getModel() {
		return this.model;
	}
	
    abstract public void render();

}
