package jerra.view;

import jerra.core.Vector;
import jerra.entity.Entity;
import jerra.room.Room;

public class TextView extends View<Room> {
	private Room model;
	
	public TextView(Room model) {
		super(model);
	}
	
    public void render() {
        StringBuilder output = new StringBuilder(220);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                boolean found = false;
                for (Entity entity: this.model.getEntities()) {
                    if (entity.getPosition().getOrigin().equals(new Vector(col, row))) {
                        output.append(entity.symbol() + " ");
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    output.append("  ");
                }
            }
            output.append("\n");
        }

        System.out.println(output);
        
    }
}
