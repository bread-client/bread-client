package fr.breadclient.breadclient;

import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panels.pages.App;
import fr.breadclient.breadclient.ui.panels.pages.Login;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.model.response.RefreshResponse;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.nio.file.Path;
import java.util.UUID;

public class Launcher extends Application {

    private static Launcher INSTANCE;
    private final ILogger logger;
    private final Path launcherDir = GameDirGenerator.createGameDir("bread-client", true);
    private final Saver saver;
    private PanelManager panelManager;
    private AuthInfos authInfos;
    private FriendList friend = new FriendList()

    public Launcher() {
        INSTANCE = this;
        this.logger = new Logger("[bread-client]", this.launcherDir.resolve("launcher.log"));
        if(!this.launcherDir.toFile().exists())
            if(!this.launcherDir.toFile().mkdir())
                this.logger.err("Unable to create launcher folder");

        this.saver = new Saver(this.launcherDir.resolve("config.properties"));
        this.saver.load();
    }

    @Override
    public void start(Stage stage) {
        this.logger.info("Starting launcher");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();

        if(this.isUserAlreadyLoggedIn()) {
            this.logger.info("Hello " + this.authInfos.getUsername());
            this.panelManager.showPanel(new App());
        } else {
            this.panelManager.showPanel(new Login());
        }
    }

    public boolean isUserAlreadyLoggedIn() {
        if(this.saver.get("accessToken") != null && this.saver.get("clientToken") != null) {
            final Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

            try {
                final RefreshResponse response = authenticator.refresh(this.saver.get("accessToken"), this.saver.get("clientToken"));
                this.saver.set("accessToken", response.getAccessToken());
                this.saver.set("clientToken", response.getClientToken());
                this.saver.save();
                this.setAuthInfos(new AuthInfos(
                        response.getSelectedProfile().getName(),
                        response.getAccessToken(),
                        response.getClientToken(),
                        response.getSelectedProfile().getId()
                ));
                return true;
            } catch(AuthenticationException ignored) {
                this.saver.remove("accessToken");
                this.saver.remove("clientToken");
                this.saver.save();
            }
        } else if(this.saver.get("msAccessToken") != null && this.saver.get("msRefreshToken") != null) {
            try {
                final MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
                final MicrosoftAuthResult response = authenticator.loginWithRefreshToken(this.saver.get("msRefreshToken"));

                this.saver.set("msAccessToken", response.getAccessToken());
                this.saver.set("msRefreshToken", response.getRefreshToken());
                this.saver.save();
                this.setAuthInfos(new AuthInfos(
                        response.getProfile().getName(),
                        response.getAccessToken(),
                        response.getProfile().getId()
                ));
                return true;
            } catch(MicrosoftAuthenticationException e) {
                this.saver.remove("msAccessToken");
                this.saver.remove("msRefreshToken");
                this.saver.save();
            }
        } else if(this.saver.get("offline-username") != null) {
            this.authInfos = new AuthInfos(this.saver.get("offline-username"), UUID.randomUUID().toString(), UUID.randomUUID().toString());
            return true;
        }
        return false;
    }

    public static Launcher getInstance() {
        return INSTANCE;
    }

    public void setAuthInfos(AuthInfos authInfos) {
        this.authInfos = authInfos;
    }

    public ILogger getLogger() {
        return logger;
    }

    public Path getLauncherDir() {
        return launcherDir;
    }

    public Saver getSaver() {
        return saver;
    }

    public PanelManager getPanelManager() {
        return panelManager;
    }

    public AuthInfos getAuthInfos() {
        return authInfos;
    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    public void hideWindow() {
        this.panelManager.getStage().hide();
    }
}
