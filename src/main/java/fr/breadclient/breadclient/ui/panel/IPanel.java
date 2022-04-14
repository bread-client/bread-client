package fr.breadclient.breadclient.ui.panel;

import fr.breadclient.breadclient.ui.PanelManager;
import javafx.scene.layout.GridPane;

public interface IPanel {
    void Init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
    String getName();
    String getStyleSheetPath();

}
