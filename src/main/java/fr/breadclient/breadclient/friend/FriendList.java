package fr.breadclient.breadclient.friend;

import java.util.ArrayList;

// L'ArrayList est une solution temporaire, elle sera remplacée par le fichier yml ou autre

public class FriendList {

    // Ajouter le status de l'amis --> Voir si il est connecté ou pas ?

    public ArrayList<String> friends = new ArrayList<String>();

    public ArrayList<String> getFriends(){
        return friends;
    }

    public void addFriends(String name){
        friends.add(name);
    }

    public void loadFriends(){
        // Charger les amis enregistrés dans un fichier yml ou autre
        // Si le fichier n'existe pas, créer le fichier
        // A faire avec la lib properties-data.jar
    }

}