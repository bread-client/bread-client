package fr.breadclient.breadclient.ui.panels.partials;

import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panel.Panel;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TopBar extends Panel {
    private GridPane topBar;

    @Override
    public String getName() {
        return "TopBar";
    }

    @Override
    public void Init(PanelManager panelManager) {
        super.Init(panelManager);
        this.topBar = this.layout;
        this.layout.setStyle("-fx-background-color: #2e2e2e; -fx-blur-radius: 50;");

        ImageView imageView = new ImageView();
        imageView.setImage(new Image("images/icons/bread-client-icon.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(25);
        setLeft(imageView);
        this.layout.getChildren().add(imageView);

        Label title = new Label("Bread Client - Beta version - 0.1");
        title.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.REGULAR, 16f));
        title.setStyle("-fx-text-fill: #ffffff;");
        setCenterH(title);
        this.layout.getChildren().add(title);

        GridPane topBarButtons = new GridPane();
        topBarButtons.setMinWidth(100d);
        topBarButtons.setMaxWidth(100d);
        setCanTakeAllSize(topBarButtons);
        setRight(topBarButtons);
        this.layout.getChildren().add(topBarButtons);
    }
}
