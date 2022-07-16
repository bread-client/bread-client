package fr.breadclient.breadclient.friend;

import fr.fusion569.propertiesdata.files.PropertiesFile;
import fr.fusion569.propertiesdata.utils.KeyValueSeparator;
import fr.fusion569.propertiesdata.utils.StandardDirectoryCreationType;
import fr.fusion569.propertiesdata.utils.StandardFileCreationType;

import java.util.ArrayList;

// L'ArrayList est une solution temporaire, elle sera remplacée par le fichier yml ou autre

public class FriendList {

    // Ajouter le status de l'amis --> Voir si il est connecté ou pas ?

    public PropertiesFile friendsFile;
    public PropertiesFile nofFile;

    public void addFriends(String name) {
        this.friendsFile = new PropertiesFile("", "friendsList", StandardFileCreationType.ONLY_WANTED_FILE, StandardDirectoryCreationType.IGNORE, KeyValueSeparator.DOUBLE_POINTS_AND_SPACE);
        this.friendsFile.create();
        this.friendsFile.setDefaultInteger("nof", 0);

        int nof = this.friendsFile.getInteger("nof");
        this.friendsFile.setString("friend" + nof, name);
        this.friendsFile.setInteger("nof", nof + 1);
    }

    public void loadFriends(){
        //TODO by Fusion569
    }

}