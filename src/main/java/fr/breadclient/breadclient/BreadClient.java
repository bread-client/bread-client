package fr.breadclient.breadclient;

import javax.swing.*;

public class BreadClient {

    private static final BreadClient INSTANCE = new BreadClient();

    public static BreadClient getInstance() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        try {
            Class.forName("javafx.application.Application");
        }
        catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "JavaFX is not installed on this computer \n " + e.getMessage(), "JavaFX not found", JOptionPane.ERROR_MESSAGE);
        }
    }
}
