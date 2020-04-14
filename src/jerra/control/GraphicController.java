package jerra.control;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import jerra.core.Rect;
import jerra.core.Resources;
import jerra.core.Vector;
import jerra.effect.DamageEffect;
import jerra.entity.AmbientShooterSpawner;
import jerra.entity.AmbientSpawner;
import jerra.entity.Bullet;
import jerra.entity.Gun;
import jerra.entity.Player;
import jerra.entity.Respawner;
import jerra.entity.ShooterEntity;
import jerra.entity.Wall;
import jerra.item.Inventory;
import jerra.item.Lootbag;
import jerra.item.Medkit;
import jerra.presence.ActivePresence;
import jerra.presence.DefaultPresence;
import jerra.presence.WanderPresence;
import jerra.room.Room;
import jerra.stats.Stats;
import jerra.view.GraphicView;
import jerra.view.TextView;

/**
 * TextController
 */
public class GraphicController implements Controller {

    private Room room;
    private Canvas canvas;

    private TextView textView;
    private GraphicView view;

    private Set<String> heldKeys;
    private Set<String> lastKeys;
    private Set<String> newKeys;

    public GraphicController(Room room, Canvas canvas) {
        this.room = room;
        this.canvas = canvas;

        this.heldKeys = new HashSet<String>();
        this.lastKeys = new HashSet<String>();
        this.newKeys = new HashSet<String>();

    }

    public void start() {
        Vector zero = new Vector(0, 0);
        Vector block = new Vector(30, 30);

        String playerImage = "/resources/player.png";
        String enemyImage = "/resources/enemy.png";
        String bulletImage = "/resources/bullet.png";
        String lootbagImage = "/resources/lootbag.png";

        // Image playerImage = Resources.loadImage("/resources/player.png");
        // Image enemyImage = Resources.loadImage("/resources/enemy.png");
        // Image bulletImage = Resources.loadImage("/resources/bullet.png");

        Bullet bullet = new Bullet(new Rect(zero, new Vector(6, 6)), new Vector(15, 15), new DamageEffect(1), 100, 'T', bulletImage);
        
        this.setBoundaries();

        ShooterEntity shooter = new ShooterEntity(
            new WanderPresence(new Rect(new Vector(300, 300), block), new Vector(3, 3), 25, this.room.getGenerator()),
            new Stats(3, 3),
            new Gun(
                bullet.setTeam('E').copy(),
                40
            ),
            'E',
            enemyImage,
            this.room.getGenerator()
        );
        this.room.spawnShooter(shooter);

        this.room.spawnShooterSpawner(new AmbientShooterSpawner(
            // new DefaultEntity(new WanderPresence(new Rect(new Vector(0, 0), block), new Vector(3, 3), 25)),
            shooter.copy(),
            new Vector(285, 285), 
            255, 
            200,
            this.room.getGenerator()
        ));

        Player player = new Player(
            new ActivePresence(
                new Rect(
                    new Vector(30, 30), block
                ), 
                new Vector(5, 5), "up", "down", "left", "right"
            ),
            new Stats(10, 10),
            new Gun(
                bullet.setTeam('P').copy(),
                10
            ),
            new Inventory(),
            'P',
            new Vector(1, 0),
            playerImage
        );

        this.room.spawnPlayer(player);

        // Create basic medkit drop bag
        Lootbag medkitBag = new Lootbag(
            new DefaultPresence(
                new Rect(
                    new Vector(100, 100), block
                ), new Vector(0, 0)
            ),
            new Inventory().add(new Medkit()),
            lootbagImage
        );

        this.room.spawnLootbag(medkitBag.copy());

        this.room.spawnLootbagSpawner(new AmbientSpawner<Lootbag>(
            medkitBag.copy(),
            new Vector(285, 285),
            255,
            1000,
            this.room.getGenerator()
        ));

        Respawner respawner = new Respawner(player, 60);

        this.room.spawnInteractiveShooterSpawner(respawner);

        this.room.spawnShooter(player);
        
        // Prepare image dictionary
        String[] paths = {
            "border_horizontal", "border_vertical", "bullet", "enemy", "logo", "player",
            "wall", "lootbag", "background"
        };
        HashMap<String, Image> imageDictionary = new HashMap<String, Image>();
        for (String path : paths) {
            String fullPath = "/resources/" + path + ".png";
            imageDictionary.put(fullPath, Resources.loadImage(fullPath));
        }
    
        this.view = new GraphicView(this.room, this.canvas, imageDictionary);
        view.render();

        this.textView = new TextView(this.room);
        textView.render();

        // Handle key events
        this.canvas.getScene().setOnKeyPressed(event -> this.handleKeyPress(event));
        this.canvas.getScene().setOnKeyReleased(event -> this.handleKeyRelease(event));

        // Create game loop
        Timeline gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        KeyFrame frame = new KeyFrame(new Duration(20), (event) -> this.update());
        gameLoop.getKeyFrames().add(frame);
        gameLoop.play();

    }

    private void handleKeyPress(KeyEvent key) {     

        this.heldKeys.add(key.getCode().toString());

    }

    private void handleKeyRelease(KeyEvent key) {

        this.heldKeys.remove(key.getCode().toString());

    }

    /**
     * Calculates various keysets, such as new key presses
     */
    private void computeKeys() {

        // Find new keys by removing old ones
        this.newKeys = new HashSet<String>(this.heldKeys);
        this.newKeys.removeAll(this.lastKeys);

        // Reset last keys to current press set
        this.lastKeys = new HashSet<String>(this.heldKeys);

    }

    private void queueKeys() {

        // Check held key events; note: will include new presses
        for (String key: this.heldKeys) {
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
                default:
                    break;
            }
        }

        // Check through keys that specifically appeared this update,
        // i.e. newly pressed
        for (String key: this.newKeys) {
            switch (key) {
                case "SPACE":
                    this.room.queue("shoot");
                    break;
                case "UP":
                    this.room.queue("upSecondary");
                    this.room.queue("shoot");
                    break;
                case "DOWN":
                    this.room.queue("downSecondary");
                    this.room.queue("shoot");
                    break;
                case "LEFT":
                    this.room.queue("leftSecondary");
                    this.room.queue("shoot");
                    break;
                case "RIGHT":
                    this.room.queue("rightSecondary");
                    this.room.queue("shoot");
                    break;
                case "ENTER":
                    this.room.queue("respawn");
                    break;
                case "E":
                    this.room.queue("use");
                    break;
                default:
                    break;
            }
        }
    }

    private void checkEvents() {

        if (this.newKeys.contains("K")) {
            Resources.saveObject("save.ser", this.room);
        }
        
        if (this.newKeys.contains("L")) {
            Room room = Resources.loadRoom("save.ser");
            if (room != null) {
                this.room = room;
                this.view.setModel(room);
                this.textView.setModel(room);
            }
        }

    }

    private void update() {

        // Compute key sets
        this.computeKeys();

        // Check for control events, e.g. save/load
        this.checkEvents();

        // Queue key driven commands into the room
        this.queueKeys();

        // Update the room
        this.room.update();

        // Render the views
        // this.textView.render();

        this.view.render();

        // Clear the room command queue
        this.room.clearQueue();

    }

    private void setBoundaries() {
        Vector zero = new Vector(0, 0);
        int stroke = 30;
        Vector verticalWall = new Vector(stroke, (int) this.canvas.getHeight());
        Vector horizontalWall = new Vector((int) this.canvas.getHeight(), stroke);

        String wall = "/resources/wall.png";
        String horizontalBorder = "/resources/border_horizontal.png";
        String verticalBorder = "/resources/border_vertical.png";

        // Image wall = Resources.loadImage("/resources/wall.png");
        // Image horizontalBorder = Resources.loadImage("/resources/border_horizontal.png");
        // Image verticalBorder = Resources.loadImage("/resources/border_vertical.png");

        this.room.spawnEntity(
            new Wall(
                new DefaultPresence(
                    new Rect(
                        new Vector(300, 300),
                        new Vector(60, 60)
                    ), 
                    zero
                ),
                wall
            )
        );

        this.room.spawnEntity(
            new Wall(
                new DefaultPresence(
                    new Rect(
                        new Vector(0, 0),
                        verticalWall
                    ), 
                    zero
                ),
                verticalBorder
            )
        );

        this.room.spawnEntity(
            new Wall(
                new DefaultPresence(
                    new Rect(
                        new Vector(
                            (int) this.canvas.getWidth() - stroke, 
                            0
                        ),
                        verticalWall
                    ), 
                    zero
                ),
                verticalBorder
            )
        );

        this.room.spawnEntity(
            new Wall(
                new DefaultPresence(
                    new Rect(
                        new Vector(0, (int) this.canvas.getHeight() - stroke),
                        horizontalWall
                    ), 
                    zero
                ),
                horizontalBorder
            )
        );

        this.room.spawnEntity(
            new Wall(
                new DefaultPresence(
                    new Rect(
                        new Vector(0, 0),
                        horizontalWall
                    ), 
                    zero
                ),
                horizontalBorder
            )
        );
    }

}
