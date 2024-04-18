package com.example.vop_eindproject;

import javafx.scene.paint.Color;

public class Lichaam {
    private String modelNaam;
    private Color verfkleur;
    private int positieX;
    private int positieY;

    public Lichaam(String modelNaam, Color verfkleur, int positieX, int positieY) {
        this.modelNaam = modelNaam;
        this.verfkleur = verfkleur;
        this.positieX = positieX;
        this.positieY = positieY;
    }

    public String getModelNaam() {
        return modelNaam;
    }

    public Color getVerfkleur() {
        return verfkleur;
    }

    public void setVerfkleur(Color verfkleur) {
        this.verfkleur = verfkleur;
    }

    @Override
    public String toString() {
        return "Lichaam{" +
                "modelNaam='" + modelNaam + '\'' +
                ", verfkleur=" + verfkleur +
                ", positieX=" + positieX +
                ", positieY=" + positieY +
                '}';
    }

    public int getPositieX() {
        return positieX;
    }

    public void setPositieX(int positieX) {
        this.positieX = positieX;
    }

    public int getPositieY() {
        return positieY;
    }

    public void setPositieY(int positieY) {
        this.positieY = positieY;
    }
}
