package jerra.view;

abstract public class View<Model>{
	
	protected Model model;
	
	public View(Model model) {
		this.setModel(model);
	}
	
	
	public void setModel(Model model) {
		// Potential privacy leaks.
		// Might create interface CopyConstructor or
		// CopiesItself
		this.model = model;
	}
	
	public Model getModel() {
		return this.model;
	}
	
    abstract public void render();

}
