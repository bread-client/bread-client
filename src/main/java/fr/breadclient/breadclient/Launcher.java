package fr.breadclient.breadclient;

import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panels.pages.Login;
import fr.breadclient.breadclient.utils.Helpers;
import fr.breadclient.breadclientapi.files.KeyValueConfiguration;
import fr.breadclient.breadclientapi.files.KeyValueDocument;
import fr.breadclient.breadclientapi.utils.KeyValueSeparator;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

public class Launcher extends Application {

    private static final Launcher INSTANCE = new Launcher();

    private PanelManager panelManager;
    private final ILogger logger;
    private final File launcherDirectory = Helpers.generateGamePath("bread-client");
    private final KeyValueDocument keyValueDocument = new KeyValueDocument("path", "name");
    private final KeyValueConfiguration keyValueConfiguration = KeyValueConfiguration.load(keyValueDocument, KeyValueSeparator.EQUAL);

    public Launcher() {
        this.logger = new Logger("[BREAD-CLIENT]", new File(this.launcherDirectory, "bread-client.logs").toPath());
        if(!launcherDirectory.exists())
            this.launcherDirectory.mkdir();
    }

    public static Launcher getInstance() {
        return INSTANCE;
    }

    public PanelManager getPanelManager() {
        return panelManager;
    }

    public ILogger getLogger() {
        return logger;
    }

    public File getLauncherDirectory() {
        return launcherDirectory;
    }

    public KeyValueDocument getKeyValueDocument() {
        return keyValueDocument;
    }

    public KeyValueConfiguration getKeyValueConfiguration() {
        return keyValueConfiguration;
    }

    public void start(Stage stage) throws Exception {
        this.logger.info("Starting Bread Client");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();

        this.panelManager.showPanel(new Login());
    }
}
