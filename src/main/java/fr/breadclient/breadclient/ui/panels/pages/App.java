package fr.breadclient.breadclient.ui.panels.pages;

import fr.breadclient.breadclient.Launcher;
import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panel.Panel;
import fr.breadclient.breadclient.ui.panels.pages.content.ContentPanel;
import fr.breadclient.breadclient.ui.panels.pages.content.Home;
import fr.breadclient.breadclient.ui.panels.pages.content.Settings;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class App extends Panel {

    private final GridPane sideMenu = new GridPane();
    private final GridPane navContent = new GridPane();
    private Node activeLink;
    private ContentPanel currentPage;
    private Button homeBtn, settingsBtn;
    final Saver saver = Launcher.getInstance().getSaver();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStylesheetPath() {
        return "css/app.css";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        // Background
        this.layout.getStyleClass().add("app-layout");
        this.setCanTakeAllSize(this.layout);

        final ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.LEFT);
        columnConstraints.setMinWidth(350);
        columnConstraints.setMaxWidth(350);
        this.layout.getColumnConstraints().addAll(columnConstraints, new ColumnConstraints());

        // Side menu
        this.layout.add(this.sideMenu, 0, 0);
        this.sideMenu.getStyleClass().add("sidemenu");
        this.setLeft(this.sideMenu);
        this.setCenterH(this.sideMenu);
        this.setCenterV(this.sideMenu);

        // Background Image
        final GridPane bgImage = new GridPane();
        this.setCanTakeAllSize(bgImage);
        bgImage.getStyleClass().add("bg-image");
        this.layout.add(bgImage, 1, 0);

        // Nav content
        this.layout.add(navContent, 1, 0);
        this.navContent.getStyleClass().add("nav-content");
        setLeft(this.navContent);
        setCenterH(this.navContent);
        setCenterV(this.navContent);

        /*
         * Side menu
         */
        // Titre
        final Label title = new Label("JavaFX Launcher");
        title.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 30f));
        title.getStyleClass().add("home-title");
        setCenterH(title);
        setCanTakeAllSize(title);
        setTop(title);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setTranslateY(30d);
        this.sideMenu.getChildren().add(title);

        // Navigation
        this.homeBtn = new Button("Accueil");
        this.homeBtn.getStyleClass().add("sidemenu-nav-btn");
        this.homeBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.HOME));
        setCanTakeAllSize(this.homeBtn);
        setTop(this.homeBtn);
        this.homeBtn.setTranslateY(90d);
        this.homeBtn.setOnMouseClicked(e -> setPage(new Home(), this.homeBtn));

        this.settingsBtn = new Button("Paramètres");
        this.settingsBtn.getStyleClass().add("sidemenu-nav-btn");
        this.settingsBtn.setGraphic(new FontAwesomeIconView(FontAwesomeIcon.GEARS));
        setCanTakeAllSize(this.settingsBtn);
        setTop(this.settingsBtn);
        this.settingsBtn.setTranslateY(130d);
        this.settingsBtn.setOnMouseClicked(e -> setPage(new Settings(), this.settingsBtn));

        this.sideMenu.getChildren().addAll(this.homeBtn, this.settingsBtn);

        if(Launcher.getInstance().getAuthInfos() != null) {
            // Pseudo + avatar
            final GridPane userPane = new GridPane();
            this.setCanTakeAllWidth(userPane);
            userPane.setMaxHeight(80);
            userPane.setMinWidth(80);
            userPane.getStyleClass().add("user-pane");
            this.setBottom(userPane);

            final String avatarUrl = "https://minotar.net/avatar/" + (
                    this.saver.get("offline-username") != null ?
                            "MHF_Steve.png" :
                            Launcher.getInstance().getAuthInfos().getUuid() + ".png"
            );
            final ImageView avatarView = new ImageView();
            final Image avatarImg = new Image(avatarUrl);
            avatarView.setImage(avatarImg);
            avatarView.setPreserveRatio(true);
            avatarView.setFitHeight(50d);
            this.setCenterV(avatarView);
            this.setCanTakeAllSize(avatarView);
            this.setLeft(avatarView);
            avatarView.setTranslateX(15d);
            userPane.getChildren().add(avatarView);

            final Label usernameLabel = new Label(Launcher.getInstance().getAuthInfos().getUsername());
            usernameLabel.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 25f));
            this.setCanTakeAllSize(usernameLabel);
            this.setCenterV(usernameLabel);
            this.setLeft(usernameLabel);
            usernameLabel.getStyleClass().add("username-label");
            usernameLabel.setTranslateX(75d);
            setCanTakeAllWidth(usernameLabel);
            userPane.getChildren().add(usernameLabel);

            final Button logoutBtn = new Button();
            final FontAwesomeIconView logoutIcon = new FontAwesomeIconView(FontAwesomeIcon.SIGN_OUT);
            logoutIcon.getStyleClass().add("logout-icon");
            setCanTakeAllSize(logoutBtn);
            setCenterV(logoutBtn);
            setRight(logoutBtn);
            logoutBtn.getStyleClass().add("logout-btn");
            logoutBtn.setGraphic(logoutIcon);
            logoutBtn.setOnMouseClicked(e -> {
                if(this.currentPage instanceof Home && ((Home) this.currentPage).isDownloading()) {
                    return;
                }
                this.saver.remove("accessToken");
                this.saver.remove("clientToken");
                this.saver.remove("offline-username");
                this.saver.remove("msAccessToken");
                this.saver.remove("msRefreshToken");
                this.saver.save();
                Launcher.getInstance().setAuthInfos(null);
                this.panelManager.showPanel(new Login());
            });
            userPane.getChildren().add(logoutBtn);
            this.sideMenu.getChildren().add(userPane);
        }
    }

    @Override
    public void onShow() {
        super.onShow();
        setPage(new Home(), this.homeBtn);
    }

    public void setPage(ContentPanel panel, Node navButton) {
        if(this.currentPage instanceof Home && ((Home) this.currentPage).isDownloading()) {
            return;
        }
        if(this.activeLink != null)
            this.activeLink.getStyleClass().remove("active");
        this.activeLink = navButton;
        this.activeLink.getStyleClass().add("active");

        this.navContent.getChildren().clear();
        if(panel != null) {
            this.navContent.getChildren().add(panel.getLayout());
            this.currentPage = panel;
            if(panel.getStylesheetPath() != null) {
                this.panelManager.getStage().getScene().getStylesheets().clear();
                this.panelManager.getStage().getScene().getStylesheets().addAll(
                        this.getStylesheetPath(),
                        panel.getStylesheetPath()
                );
            }
            panel.init(this.panelManager);
            panel.onShow();
        }
    }

    public GridPane getSideMenu() {
        return sideMenu;
    }

    public GridPane getNavContent() {
        return navContent;
    }

    public Node getActiveLink() {
        return activeLink;
    }

    public ContentPanel getCurrentPage() {
        return currentPage;
    }

    public Button getHomeBtn() {
        return homeBtn;
    }

    public Button getSettingsBtn() {
        return settingsBtn;
    }

    public Saver getSaver() {
        return saver;
    }
}
