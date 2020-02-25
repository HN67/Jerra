package jerra.control;

import java.util.Scanner;

import javafx.scene.canvas.GraphicsContext;
import jerra.core.Vector;
import jerra.core.Rect;

import jerra.presence.DefaultPresence;
import jerra.presence.ActivePresence;

import jerra.entity.DefaultEntity;
import jerra.entity.Player;
import jerra.entity.AmbientSpawner;

import jerra.room.Room;
import jerra.view.FxView;
import jerra.view.TextView;

/**
 * TextController
 */
public class GameController implements Controller {

    private Room room;
    private String input;
    private GraphicsContext context;

    public GameController(Room room, String input, GraphicsContext context) {
        this.room = room;
        this.input = input;
        this.context = context;
    }

    public void start() {
        Vector zero = new Vector(0, 0);

        this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(4, 0), new Vector(1, 1)), zero)));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(8, 0), new Vector(1, 1)), zero)));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(0, 4), new Vector(1, 1)), zero)));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(0, 8), new Vector(1, 1)), zero)));
        this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(5, 5), new Vector(1, 1)), zero)));

        this.room.spawnSpawner(new AmbientSpawner(
            new DefaultEntity(new DefaultPresence(new Rect(0, 0, 1, 1), zero)),
            new Vector(4, 4), 
            1, 
            3
        ));

        this.room.spawnPlayer(new Player(new ActivePresence(new Rect(new Vector(0, 0), new Vector(1, 1)), new Vector(1, 1), "up", "down", "left", "right")));
        
        FxView fxView = new FxView(this.room, this.context);
        
        fxView.render();
    }

}
