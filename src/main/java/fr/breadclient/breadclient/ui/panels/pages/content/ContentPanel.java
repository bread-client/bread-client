package fr.breadclient.breadclient.ui.panels.pages.content;

import fr.breadclient.breadclient.ui.panel.Panel;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

public abstract class ContentPanel extends Panel {

    @Override
    public void onShow() {
        final FadeTransition transition = new FadeTransition(Duration.millis(250), this.layout);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }
}
