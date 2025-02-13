package com.loveinshayari.quize;

public class CategoriModel {

    private String name;
    private int sets;
    private String url;

    public CategoriModel() {
        // for fireabase
    }

    public CategoriModel(String name, int sets, String url) {
        this.name = name;
        this.sets = sets;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
