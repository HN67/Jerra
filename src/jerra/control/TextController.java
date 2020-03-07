package jerra.control;

import java.util.Scanner;

import jerra.core.Vector;
import jerra.core.Rect;

import jerra.presence.DefaultPresence;
import jerra.presence.ActivePresence;

import jerra.entity.Entity;
import jerra.entity.DefaultEntity;
import jerra.entity.Gun;
import jerra.entity.Player;
import jerra.entity.AmbientSpawner;
import jerra.entity.Bullet;

import jerra.room.Room;
import jerra.view.TextView;

/**
 * TextController
 */
public class TextController implements Controller {

    private Room room;
    private Scanner keyboard;

    public TextController(Room room, Scanner keyboard) {
        this.room = room;
        this.keyboard = keyboard;
    }

    public void start() {
        Vector zero = new Vector(0, 0);

        this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(4, 0), new Vector(1, 1)), zero), null));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(8, 0), new Vector(1, 1)), zero), null));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(0, 4), new Vector(1, 1)), zero), null));
		this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(0, 8), new Vector(1, 1)), zero), null));
        this.room.spawnEntity(new DefaultEntity(new DefaultPresence(new Rect(new Vector(5, 5), new Vector(1, 1)), zero), null));

        this.room.spawnSpawner(new AmbientSpawner<Entity>(
            new DefaultEntity(new DefaultPresence(new Rect(0, 0, 1, 1), zero), null),
            new Vector(4, 4), 
            1, 
            3
        ));

        this.room.spawnPlayer(new Player(new ActivePresence(new Rect(new Vector(0, 0), new Vector(1, 1)), new Vector(1, 1), "up", "down", "left", "right"),
            new Gun(new Bullet(new Rect(0, 0, 1, 1), new Vector(1, 1), 5, 'P', null), 3), 'P', new Vector(1, 0), null
        ));
        
        String command = "";
        
        TextView view = new TextView(this.room);

		while (!command.equals("quit")) {
            command = this.keyboard.nextLine();
            this.room.queue(command);
            this.room.update();
            this.room.clearQueue();
            System.out.println(this.room);
            view.render();
		}

    }

}
