package fr.breadclient.breadclient.ui.panels.partials;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panel.Panel;
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
    public String getStyleSheetPath() {
        return null;
    }

    @Override
    public String getName() {
        return "TopBar";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        this.topBar = this.layout;
        this.layout.setStyle("-fx-background-color: #2e2e2e; -fx-blur-radius: 50;");

        final ImageView imageView = new ImageView();
        imageView.setImage(new Image("images/icons/bread-client-icon.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(25);
        this.setLeft(imageView);
        this.layout.getChildren().add(imageView);

        final Label title = new Label("Bread Client - Beta version - 0.1");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16f));
        title.setStyle("-fx-text-fill: #ffffff;");
        this.setCenterH(title);
        this.layout.getChildren().add(title);

        final GridPane topBarButtons = new GridPane();
        topBarButtons.setMinWidth(100d);
        topBarButtons.setMaxWidth(100d);
        this.setCanTakeAllSize(topBarButtons);
        this.setRight(topBarButtons);
        this.layout.getChildren().add(topBarButtons);

        final FontAwesomeIconView closeButton = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_CLOSE);
        final FontAwesomeIconView fullScreenButton = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MAXIMIZE);
        final FontAwesomeIconView minimizeButton = new FontAwesomeIconView(FontAwesomeIcon.WINDOW_MINIMIZE);
        this.setCanTakeAllWidth(closeButton, fullScreenButton, minimizeButton);

        closeButton.setFill(Color.WHITE);
        closeButton.setOpacity(0.7f);
        closeButton.setSize("16px");
        closeButton.setOnMouseEntered(event -> closeButton.setOpacity(1f));
        closeButton.setOnMouseExited(event -> closeButton.setOpacity(0.7f));
        closeButton.setOnMouseClicked(event -> System.exit(0));
        closeButton.setTranslateX(70f);

        fullScreenButton.setFill(Color.WHITE);
        fullScreenButton.setOpacity(0.70f);
        fullScreenButton.setSize("16px");
        fullScreenButton.setOnMouseEntered(event -> fullScreenButton.setOpacity(1f));
        fullScreenButton.setOnMouseExited(event -> fullScreenButton.setOpacity(0.7f));
        fullScreenButton.setOnMouseClicked(event -> {
            this.panelManager.getStage().setMaximized(!this.panelManager.getStage().isMaximized());
        });
        fullScreenButton.setTranslateX(50d);

        minimizeButton.setFill(Color.WHITE);
        minimizeButton.setOpacity(0.70f);
        minimizeButton.setSize("16px");
        minimizeButton.setOnMouseEntered(event -> minimizeButton.setOpacity(1f));
        minimizeButton.setOnMouseExited(event -> minimizeButton.setOpacity(0.7f));
        minimizeButton.setOnMouseClicked(event -> this.panelManager.getStage().setIconified(true));
        minimizeButton.setTranslateX(26d);

        topBarButtons.getChildren().addAll(closeButton, fullScreenButton, minimizeButton);
    }
}
