package fr.breadclient.breadclient.ui;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import fr.breadclient.breadclient.Launcher;
import fr.breadclient.breadclient.ui.panel.IPanel;
import fr.breadclient.breadclient.ui.panels.partials.TopBar;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Objects;

public class PanelManager {

    private final Launcher launcher;
    private final Stage stage;
    private GridPane layout;
    private final TopBar topBar = new TopBar();
    private final GridPane contentPanel = new GridPane();


    public PanelManager(Launcher launcher, Stage stage) {
        this.launcher = launcher;
        this.stage = stage;
    }

    public void init() {
        this.stage.setTitle("Bread Client");
        this.stage.setMinWidth(900);
        this.stage.setMinHeight(500);
        this.stage.setWidth(900);
        this.stage.setHeight(600);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.centerOnScreen();
        this.stage.getIcons().add(new Image("images/icons/bread-client-icon.png"));

        this.layout = new GridPane();

        BorderlessScene scene = new BorderlessScene(this.stage, StageStyle.UNDECORATED, this.layout);
        scene.setResizable(true);
        scene.setMoveControl(this.topBar.getLayout());
        scene.removeDefaultCSS();

        this.stage.setScene(scene);
        this.stage.show();

        RowConstraints topPaneConstraint = new RowConstraints();
        topPaneConstraint.setValignment(VPos.TOP);
        topPaneConstraint.setMinHeight(25);
        topPaneConstraint.setMaxHeight(25);
        this.layout.getRowConstraints().addAll(topPaneConstraint, new RowConstraints());
        this.layout.add(this.topBar.getLayout(), 0, 0);
        this.topBar.Init(this);
    }

    public void showPanel(IPanel panel) {
        this.contentPanel.getChildren().clear();
        this.contentPanel.getChildren().add(panel.getLayout());
        panel.Init(this);
        panel.onShow();
    }

    public Stage getStage() {
        return stage;
    }

    public Launcher getLauncher() {
        return launcher;
    }

}
