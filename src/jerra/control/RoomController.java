package jerra.control;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import jerra.core.Rect;
import jerra.core.Vector;
import jerra.entity.AmbientSpawner;
import jerra.entity.DefaultEntity;
import jerra.entity.Player;
import jerra.presence.ActivePresence;
import jerra.presence.DefaultPresence;
import jerra.room.Room;
import jerra.view.RoomView;
import jerra.view.TextView;

/**
 * TextController
 */
public class RoomController implements Controller {

    private Room room;
    private Canvas canvas;

    private TextView textView;
    private RoomView view;

    public RoomController(Room room, Canvas canvas) {
        this.room = room;
        this.canvas = canvas;
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
            50 // 10 seconds at 10 FPS
        ));

        this.room.spawnPlayer(
            new Player(
                new ActivePresence(
                    new Rect(
                        new Vector(0, 0), new Vector(1, 1)
                    ), 
                    new Vector(1, 1), "up", "down", "left", "right"
                ),
                "RIGHT"
            )
        );
    
        this.view = new RoomView(this.room, this.canvas);
        view.render();

        this.textView = new TextView(this.room);
        textView.render();

        // Handle key events
        this.canvas.getScene().setOnKeyPressed(event -> this.handle(event));

        // Create game loop
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(new Duration(100), (event) -> this.update(event));
        gameLoop.getKeyFrames().add(frame);
        gameLoop.play();

    }

    private void handle(KeyEvent keyCode) {     

        String key = keyCode.getCode().toString();

        switch(key) {
            case "W":
                this.room.queue("up");
                break;
            case "S":
                this.room.queue("down");
                break;

            case "A":
                this.room.queue("left");
                break;
            
            case "D":
                this.room.queue("right");
                break;

            case "SPACE":
                this.room.queue("shoot");
                break;    

            default:
                this.room.queue("");
        }

        //this.update(keyCode);
    }

    private void update(Event event) {

        this.room.update();

        this.textView.render();

        this.view.render();

        this.room.clearQueue();

    }

}
