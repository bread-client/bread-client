package fr.breadclient.breadclient.ui.panels.pages;

import fr.breadclient.breadclient.Launcher;
import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panel.Panel;
import fr.breadclient.breadclientapi.files.KeyValueConfiguration;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class Login extends Panel {

    private final GridPane loginCard = new GridPane();

    //private final KeyValueConfiguration config = Launcher.getInstance().getKeyValueConfiguration();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStyleSheetPath() {
        return "css/login.css";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        this.layout.getStyleClass().add("login-layout");

        final ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.RIGHT);
        columnConstraints.setMinWidth(564);
        columnConstraints.setMaxWidth(564);
        this.layout.getColumnConstraints().addAll(columnConstraints, new ColumnConstraints());
        this.layout.add(this.loginCard, 0, 0);

        final GridPane backImage = new GridPane();
        this.setCanTakeAllSize(backImage);
        backImage.getStyleClass().add("login-back-image");
        this.layout.add(backImage, 1, 0);
        this.setCanTakeAllSize(this.layout);
        this.loginCard.getStyleClass().add("login-card");
        this.setRight(this.loginCard);
        this.setCenterH(this.loginCard);
        this.setCenterV(this.loginCard);
    }
}
