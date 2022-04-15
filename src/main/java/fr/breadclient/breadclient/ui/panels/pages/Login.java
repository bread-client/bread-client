package fr.breadclient.breadclient.ui.panels.pages;

import fr.breadclient.breadclient.Launcher;
import fr.breadclient.breadclient.ui.PanelManager;
import fr.breadclient.breadclient.ui.panel.Panel;
import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.response.AuthResponse;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login extends Panel {

    private final GridPane loginCard = new GridPane();
    private final Saver saver = Launcher.getInstance().getSaver();
    private final AtomicBoolean offlineAuth = new AtomicBoolean(false);
    private final TextField userField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Label userErrorLabel = new Label();
    private final Label passwordErrorLabel = new Label();
    private final Button btnLogin = new Button("Connexion");
    private final CheckBox authModeChk = new CheckBox("Mode crack");
    private final Button msLoginBtn = new Button();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStylesheetPath() {
        return "css/login.css";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        // Background
        this.layout.getStyleClass().add("login-layout");

        final ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setHalignment(HPos.LEFT);
        columnConstraints.setMinWidth(350);
        columnConstraints.setMaxWidth(350);
        this.layout.getColumnConstraints().addAll(columnConstraints, new ColumnConstraints());
        this.layout.add(this.loginCard, 0, 0);

        // Background image
        final GridPane bgImage = new GridPane();
        this.setCanTakeAllSize(bgImage);
        bgImage.getStyleClass().add("bg-image");
        this.layout.add(bgImage, 1, 0);

        // Login card
<<<<<<< HEAD
        setCanTakeAllSize(this.layout);
        loginCard.getStyleClass().add("login-card");
        setRight(loginCard);
        setCenterH(loginCard);
        setCenterV(loginCard);
=======
        this.setCanTakeAllSize(this.layout);
        this.loginCard.getStyleClass().add("login-card");
        this.setLeft(this.loginCard);
        this.setCenterH(this.loginCard);
        this.setCenterV(this.loginCard);
>>>>>>> edb11bbaba2e6da815600d419a2ae4c854fcb625

        /*
         * Login sidebar
         */
<<<<<<< HEAD
        Label title = new Label("Bread Client");
=======
        final Label title = new Label("JavaFX Launcher");
>>>>>>> edb11bbaba2e6da815600d419a2ae4c854fcb625
        title.setFont(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 30f));
        title.getStyleClass().add("login-title");
        this.setCenterH(title);
        this.setCanTakeAllSize(title);
        this.setTop(title);
        title.setTextAlignment(TextAlignment.CENTER);
        title.setTranslateY(30d);
        this.loginCard.getChildren().add(title);

        // Username/E-Mail
        this.setCanTakeAllSize(this.userField);
        this.setCenterV(this.userField);
        this.setCenterH(this.userField);
        this.userField.setPromptText("Adresse E-Mail");
        this.userField.setMaxWidth(300);
        this.userField.setTranslateY(-70d);
        this.userField.getStyleClass().add("login-input");
        this.userField.textProperty().addListener((_a, oldValue, newValue) -> this.updateLoginBtnState(this.userField, this.userErrorLabel));

        // User error
        this.setCanTakeAllSize(userErrorLabel);
        this.setCenterV(userErrorLabel);
        this.setCenterH(userErrorLabel);
        this.userErrorLabel.getStyleClass().add("login-error");
        this.userErrorLabel.setTranslateY(-45d);
        this.userErrorLabel.setMaxWidth(280);
        this.userErrorLabel.setTextAlignment(TextAlignment.LEFT);

        // Password
        this.setCanTakeAllSize(this.passwordField);
        this.setCenterV(this.passwordField);
        this.setCenterH(this.passwordField);
        this.passwordField.setPromptText("Mot de passe");
        this.passwordField.setMaxWidth(300);
        this.passwordField.setTranslateY(-15d);
        this.passwordField.getStyleClass().add("login-input");
        this.passwordField.textProperty().addListener((_a, oldValue, newValue) -> this.updateLoginBtnState(this.passwordField, this.passwordErrorLabel));

        // User error
        this.setCanTakeAllSize(this.passwordErrorLabel);
        this.setCenterV(this.passwordErrorLabel);
        this.setCenterH(this.passwordErrorLabel);
        this.passwordErrorLabel.getStyleClass().add("login-error");
        this.passwordErrorLabel.setTranslateY(10d);
        this.passwordErrorLabel.setMaxWidth(280);
        this.passwordErrorLabel.setTextAlignment(TextAlignment.LEFT);

        // Login button
        this.setCanTakeAllSize(this.btnLogin);
        this.setCenterV(this.btnLogin);
        this.setCenterH(this.btnLogin);
        this.btnLogin.setDisable(true);
        this.btnLogin.setMaxWidth(300);
        this.btnLogin.setTranslateY(40d);
        this.btnLogin.getStyleClass().add("login-log-btn");
        this.btnLogin.setOnMouseClicked(e -> this.authenticate(this.userField.getText(), this.passwordField.getText()));

        this.setCanTakeAllSize(this.authModeChk);
        this.setCenterV(this.authModeChk);
        this.setCenterH(this.authModeChk);
        this.authModeChk.getStyleClass().add("login-mode-chk");
        this.authModeChk.setMaxWidth(300);
        this.authModeChk.setTranslateY(85d);
        this.authModeChk.selectedProperty().addListener((e, old, newValue) -> {
            this.offlineAuth.set(newValue);
            this.passwordField.setDisable(newValue);
            if(newValue) {
                this.userField.setPromptText("Pseudo");
                this.passwordField.clear();
            } else {
                this.userField.setPromptText("Adresse E-Mail");
            }
            this.btnLogin.setDisable(!(this.userField.getText().length() > 0 && (this.offlineAuth.get() || this.passwordField.getText().length() > 0)));
        });

        final Separator separator = new Separator();
        this.setCanTakeAllSize(separator);
        this.setCenterH(separator);
        this.setCenterV(separator);
        separator.getStyleClass().add("login-separator");
        separator.setMaxWidth(300);
        separator.setTranslateY(110d);

        // Login with label
        final Label loginWithLabel = new Label("Ou se connecter avec:".toUpperCase());
        this.setCanTakeAllSize(loginWithLabel);
        this.setCenterV(loginWithLabel);
        this.setCenterH(loginWithLabel);
        loginWithLabel.setFont(Font.font(loginWithLabel.getFont().getFamily(), FontWeight.BOLD, FontPosture.REGULAR, 14d));
        loginWithLabel.getStyleClass().add("login-with-label");
        loginWithLabel.setTranslateY(130d);
        loginWithLabel.setMaxWidth(280d);

        // Microsoft login button
        final ImageView view = new ImageView(new Image("images/microsoft.png"));
        view.setPreserveRatio(true);
        view.setFitHeight(30d);
        this.setCanTakeAllSize(this.msLoginBtn);
        this.setCenterH(this.msLoginBtn);
        this.setCenterV(this.msLoginBtn);
        this.msLoginBtn.getStyleClass().add("ms-login-btn");
        this.msLoginBtn.setMaxWidth(300);
        this.msLoginBtn.setTranslateY(165d);
        this.msLoginBtn.setGraphic(view);
        this.msLoginBtn.setOnMouseClicked(e -> this.authenticateMS());

        this.loginCard.getChildren().addAll(this.userField, this.userErrorLabel, this.passwordField, this.passwordErrorLabel, this.authModeChk, this.btnLogin, separator, loginWithLabel, this.msLoginBtn);
    }

    public void updateLoginBtnState(TextField textField, Label errorLabel) {
        if(this.offlineAuth.get() && textField == this.passwordField) return;

        if(textField.getText().length() == 0) {
            errorLabel.setText("Le champ ne peut être vide");
        } else {
            errorLabel.setText("");
        }
        this.btnLogin.setDisable(!(this.userField.getText().length() > 0 && (this.offlineAuth.get() || this.passwordField.getText().length() > 0)));
    }

    public void authenticate(String user, String password) {
        if(!offlineAuth.get()) {
            final Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

            try {
                final AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, user, password, null);

                this.saver.set("accessToken", response.getAccessToken());
                this.saver.set("clientToken", response.getClientToken());
                this.saver.save();

                final AuthInfos infos = new AuthInfos(
                        response.getSelectedProfile().getName(),
                        response.getAccessToken(),
                        response.getClientToken(),
                        response.getSelectedProfile().getId()
                );

                Launcher.getInstance().setAuthInfos(infos);

                this.logger.info("Hello " + infos.getUsername());

                this.panelManager.showPanel(new App());
            } catch(AuthenticationException e) {
                final Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Une erreur est survenu lors de la connexion");
                alert.setContentText(e.getMessage());
                alert.show();
            }
        } else {
            final AuthInfos infos = new AuthInfos(
                    this.userField.getText(),
                    UUID.randomUUID().toString(),
                    UUID.randomUUID().toString()
            );
            this.saver.set("offline-username", infos.getUsername());
            this.saver.save();
            Launcher.getInstance().setAuthInfos(infos);
            this.logger.info("Hello " + infos.getUsername());
            this.panelManager.showPanel(new App());
        }
    }

    public void authenticateMS() {
        final MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();

        authenticator.loginWithAsyncWebview().whenComplete((response, error) -> {
            if(error != null) {
                Launcher.getInstance().getLogger().err(error.toString());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(error.getMessage());
                alert.show();
                return;
            }

            this.saver.set("msAccessToken", response.getAccessToken());
            this.saver.set("msRefreshToken", response.getRefreshToken());
            this.saver.save();
            Launcher.getInstance().setAuthInfos(new AuthInfos(
                    response.getProfile().getName(),
                    response.getAccessToken(),
                    response.getProfile().getId()
            ));
            this.logger.info("Hello " + response.getProfile().getName());

            Platform.runLater(() -> {
                this.panelManager.showPanel(new App());
            });
        });
    }
}
