package fr.breadclient.breadclient.ui;

import fr.breadclient.breadclient.Launcher;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class PanelManager {

    private final Launcher launcher;
    private final Stage stage;
    private GridPane layout;


    public PanelManager(Launcher launcher, Stage stage) {
        this.launcher = launcher;
        this.stage = stage;
    }

    public void init() {
        this.stage.setTitle("Bread Client");
        this.stage.setMinWidth(900);
        this.stage.setMinHeight(500);
        this.stage.setWidth(1280);
        this.stage.setHeight(720);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.centerOnScreen();
        // this.stage.getIcons().add(new Image("src/main/resources/images/icons/bread-client-icon.png"));

        this.layout = new GridPane();

        Scene scene = new Scene(this.layout);
        this.stage.setScene(scene);
        this.stage.show();
    }

}
