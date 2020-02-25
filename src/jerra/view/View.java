package jerra.view;

abstract public class View<T>{
	
	protected T model;
	
	public View(T model) {
		this.setModel(model);
	}
	
	
	public void setModel(T model) {
		// Potential privacy leaks.
		this.model = model;
	}
	
	public T getModel() {
		return this.model;
	}
	
    abstract public void render();

}
