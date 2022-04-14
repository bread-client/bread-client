package fr.breadclient.breadclient;

import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panels.pages.Login;
import fr.breadclient.breadclient.utils.Helpers;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class Launcher extends Application {
    private PanelManager panelManager;
    private static Launcher INSTANCE;
    private final ILogger logger;
    private final File launcherDir = Helpers.generateGamePath("bread-client");
    private final Saver saver = new Saver(new File(launcherDir, "config.properties"));

    public Launcher() {
        INSTANCE = this;
        this.logger = new Logger("[BREADCLIENT]", new File(this.launcherDir, "bclient.logs").toPath());
        this.launcherDir.mkdirs();
    }

    public void start(Stage stage) throws Exception {
        this.logger.info("Starting Bread Client");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();

        this.panelManager.showPanel(new Login());
    }

    public ILogger getLogger() {
        return logger;
    }

    public static Launcher getInstance() {
        return INSTANCE;
    }

    public Saver getSaver() {
        return saver;
    }
}
