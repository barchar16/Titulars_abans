package com.example.hobbit.titulars_abans.model.business;

/**
 * Created by Cristian on 2/15/2016.
 */
public class Titular {
    private int codi;
    private String titol;
    private String subtitol;

    public Titular(int codi, String titol, String subtitol) {
        this.setCodi(codi);
        this.setTitol(titol);
        this.setSubtitol(subtitol);
    }

    public Titular(){

    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public String getSubtitol() {
        return subtitol;
    }

    public void setSubtitol(String subtitol) {
        this.subtitol = subtitol;
    }
}
