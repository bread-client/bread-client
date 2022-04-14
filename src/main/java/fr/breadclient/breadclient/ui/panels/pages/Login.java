package fr.breadclient.breadclient.ui.panels.pages;

import fr.breadclient.breadclient.Launcher;
import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panel.Panel;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class Login extends Panel {
    GridPane loginCard = new GridPane();

    Saver saver = Launcher.getInstance().getSaver();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStyleSheetPath() {
        return "css/login.css";
    }

    @Override
    public void Init(PanelManager panelManager) {
        super.Init(panelManager);

        this.layout.getStyleClass().add("login-layout");

        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.RIGHT);
        columnConstraints.setMinWidth(564);
        columnConstraints.setMaxWidth(564);
        this.layout.getColumnConstraints().addAll(columnConstraints, new ColumnConstraints());
        this.layout.add(loginCard, 0, 0);

        GridPane backImage = new GridPane();
        setCanTakeAllSize(backImage);
        backImage.getStyleClass().add("login-back-image");
        this.layout.add(backImage, 1, 0);

        setCanTakeAllSize(this.layout);
        loginCard.getStyleClass().add("login-card");
        setRight(loginCard);
        setCenterH(loginCard);
        setCenterV(loginCard);

    }
}
