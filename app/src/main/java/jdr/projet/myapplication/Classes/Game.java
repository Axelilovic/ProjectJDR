package jdr.projet.myapplication.Classes;

/**
 * Created by Aubry on 13/02/2018.
 */

public class Game {

    private Integer Id;
    private String Name;
    private String Edition;
    private String Extension;

    public Game(Integer id, String name, String edition, String extension) {

        Id = id;
        Name = name;
        Edition = edition;
        Extension = extension;

    }

    public Game(String name, String edition, String extension) {

        Name = name;
        Edition = edition;
        Extension = extension;

    }

    public String getName() {
        return Name;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEdition() {
        return Edition;
    }

    public void setEdition(String edition) {
        Edition = edition;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }
}
