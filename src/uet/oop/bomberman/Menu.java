package uet.oop.bomberman;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.MenuItem;

public class Menu {
    private final Scene scene;
    private int currentItem = 0;
    MenuItem[] menuItems;

    public Menu() {
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(new BackgroundFill(Color.BLACK,
                CornerRadii.EMPTY,
                Insets.EMPTY)));

        menuItems = new MenuItem[4];

        menuItems[0] = new MenuItem("START");
        menuItems[0].setOnActivate(Main::switchPlayingStatus);

        menuItems[1] = new MenuItem("LEADERBOARD");

        menuItems[2] = new MenuItem("ABOUT");

        menuItems[3] = new MenuItem("QUIT");
        menuItems[3].setOnActivate(() -> System.exit(0));

        currentItem = 0;
        menuItems[currentItem].setActive(true);

        root.getChildren().addAll(menuItems);
        scene = new Scene(root, Main.initialSceneWidth, Main.initialSceneHeight);
    }

    public Scene getScene() {
        return this.scene;
    }

    public void reset() {
        menuItems[currentItem].setActive(false);
        currentItem = 0;
        menuItems[currentItem].setActive(true);
    }

    public void update() {
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    if (currentItem > 0) {
                        menuItems[currentItem].setActive(false);
                        menuItems[--currentItem].setActive(true);
                    }
                    break;
                case DOWN:
                    if (currentItem < menuItems.length - 1) {
                        menuItems[currentItem].setActive(false);
                        menuItems[++currentItem].setActive(true);
                    }
                    break;
                case ENTER:
                    menuItems[currentItem].activate();
                    break;
            }
        });
    }
}
