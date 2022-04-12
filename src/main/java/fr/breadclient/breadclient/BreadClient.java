package fr.breadclient.breadclient;

public class BreadClient {

    private static final BreadClient INSTANCE = new BreadClient();

    public static BreadClient getInstance() {
        return INSTANCE;
    }
}
