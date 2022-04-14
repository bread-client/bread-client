package fr.breadclient.breadclient.ui.panel;

import fr.breadclient.breadclient.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {

    void init(PanelManager panelManager);

    GridPane getLayout();

    void onShow();

    String getName();

    String getStyleSheetPath();
}
