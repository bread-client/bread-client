package fr.breadclient.breadclient.ui.panels.partials;

import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panel.Panel;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TopBar extends Panel {

    private GridPane topBar;

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        this.topBar = this.layout;
        this.layout.setStyle("-fx-background-color: rgb(35, 40, 40);");
        this.setCanTakeAllWidth(this.topBar);

        /*
         * TopBar separation
         */
        // TopBar: left side
        final ImageView imageView = new ImageView();
        imageView.setImage(new Image("images/icon.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(25);
        this.setLeft(imageView);
        this.layout.getChildren().add(imageView);

        // TopBar: center
        final Label title = new Label("Bread Client Launcher - Early Access");
        title.setFont(Font.font("Consolas", FontWeight.NORMAL, FontPosture.REGULAR, 18f));
        title.setStyle("-fx-text-fill: white;");
        this.setCenterH(title);
        this.layout.getChildren().add(title);

        // TopBar: right side
        final GridPane topBarButton = new GridPane();
        topBarButton.setMinWidth(100d);
        topBarButton.setMaxWidth(100d);
        this.setCanTakeAllSize(topBarButton);
        this.setRight(topBarButton);
        this.layout.getChildren().add(topBarButton);

        /*
         * TopBar buttons configuration
         */
        final FontAwesomeIconView closeBtn = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_CLOSE);
        final FontAwesomeIconView fullScreenBtn = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MAXIMIZE);
        final FontAwesomeIconView minimizeBtn = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MINIMIZE);
        this.setCanTakeAllWidth(closeBtn, fullScreenBtn, minimizeBtn);

        closeBtn.setFill(Color.WHITE);
        closeBtn.setOpacity(.7f);
        closeBtn.setSize("18px");
        closeBtn.setOnMouseEntered(e -> closeBtn.setOpacity(1.f));
        closeBtn.setOnMouseExited(e -> closeBtn.setOpacity(.7f));
        closeBtn.setOnMouseClicked(e -> System.exit(0));
        closeBtn.setTranslateX(70f);

        fullScreenBtn.setFill(Color.WHITE);
        fullScreenBtn.setOpacity(0.70f);
        fullScreenBtn.setSize("14px");
        fullScreenBtn.setOnMouseEntered(e -> fullScreenBtn.setOpacity(1.0f));
        fullScreenBtn.setOnMouseExited(e -> fullScreenBtn.setOpacity(0.7f));
        fullScreenBtn.setOnMouseClicked(e -> this.panelManager.getStage().setMaximized(!this.panelManager.getStage().isMaximized()));
        fullScreenBtn.setTranslateX(50.0d);

        minimizeBtn.setFill(Color.WHITE);
        minimizeBtn.setOpacity(0.70f);
        minimizeBtn.setSize("18px");
        minimizeBtn.setOnMouseEntered(e -> minimizeBtn.setOpacity(1.0f));
        minimizeBtn.setOnMouseExited(e -> minimizeBtn.setOpacity(0.7f));
        minimizeBtn.setOnMouseClicked(e -> this.panelManager.getStage().setIconified(true));
        minimizeBtn.setTranslateX(26.0d);

        topBarButton.getChildren().addAll(closeBtn, fullScreenBtn, minimizeBtn);
    }

    @Override
    public String getName() {
        return "TopBar";
    }

    @Override
    public String getStylesheetPath() {
        return null;
    }

    public GridPane getTopBar() {
        return topBar;
    }
}
