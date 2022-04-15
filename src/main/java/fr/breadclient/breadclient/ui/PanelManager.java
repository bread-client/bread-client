package fr.breadclient.breadclient.ui;

import fr.breadclient.breadclient.Launcher;
import fr.breadclient.breadclient.ui.panel.IPanel;
import fr.breadclient.breadclient.ui.panels.partials.TopBar;
import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import fr.flowarg.flowcompat.Platform;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager {

    private final Launcher launcher;
    private final Stage stage;
    private GridPane layout;
    private final GridPane contentPane = new GridPane();

    public PanelManager(Launcher launcher, Stage stage) {
        this.launcher = launcher;
        this.stage = stage;
    }

    public void init() {
        this.stage.setTitle("Launcher");
        this.stage.setMinWidth(854);
        this.stage.setMinHeight(480);
        this.stage.setWidth(1280);
        this.stage.setHeight(720);
        this.stage.centerOnScreen();
        this.stage.getIcons().add(new Image("images/icon.png"));

        this.layout = new GridPane();

        if(Platform.isOnLinux()) {
            final Scene scene = new Scene(this.layout);
            this.stage.setScene(scene);
        } else {
            this.stage.initStyle(StageStyle.UNDECORATED);

            final TopBar topBar = new TopBar();
            final BorderlessScene scene = new BorderlessScene(this.stage, StageStyle.UNDECORATED, this.layout);
            scene.setResizable(true);
            scene.setMoveControl(topBar.getLayout());
            scene.removeDefaultCSS();
            this.stage.setScene(scene);
            final RowConstraints topPaneConstraints = new RowConstraints();
            topPaneConstraints.setValignment(VPos.TOP);
            topPaneConstraints.setMinHeight(25);
            topPaneConstraints.setMaxHeight(25);
            this.layout.getRowConstraints().addAll(topPaneConstraints, new RowConstraints());
            this.layout.add(topBar.getLayout(), 0, 0);
            topBar.init(this);
        }
        this.layout.add(this.contentPane, 0, 1);
        GridPane.setVgrow(this.contentPane, Priority.ALWAYS);
        GridPane.setHgrow(this.contentPane, Priority.ALWAYS);
        this.stage.show();
    }

    public void showPanel(IPanel panel) {
        this.contentPane.getChildren().clear();
        this.contentPane.getChildren().add(panel.getLayout());
        if(panel.getStylesheetPath() != null) {
            this.stage.getScene().getStylesheets().clear();
            this.stage.getScene().getStylesheets().add(panel.getStylesheetPath());
        }
        panel.init(this);
        panel.onShow();
    }

    public Launcher getLauncher() {
        return launcher;
    }

    public Stage getStage() {
        return stage;
    }

    public GridPane getLayout() {
        return layout;
    }

    public GridPane getContentPane() {
        return contentPane;
    }
}
