package com.example.vop_eindproject;

import java.math.BigDecimal;

public class Processor {
    private String modelNaam;
    private int aantalCores;
    private BigDecimal kloksnelheid;
    private int cacheOpslag;
    private String brandmerk;

    public Processor(String modelNaam, int aantalCores, BigDecimal kloksnelheid, int cacheOpslag, String brandmerk) {
        this.modelNaam = modelNaam;
        this.aantalCores = aantalCores;
        this.kloksnelheid = kloksnelheid;
        this.cacheOpslag = cacheOpslag;
        this.brandmerk = brandmerk;
    }

    @Override
    public String toString() {
        return "Processor{" +
                "modelNaam='" + modelNaam + '\'' +
                ", aantalCores=" + aantalCores +
                ", kloksnelheid=" + kloksnelheid +
                ", cacheOpslag=" + cacheOpslag +
                ", brandmerk='" + brandmerk + '\'' +
                '}';
    }

    public String getModelNaam() {
        return modelNaam;
    }

    public void setModelNaam(String modelNaam) {
        this.modelNaam = modelNaam;
    }

    public int getAantalCores() {
        return aantalCores;
    }

    public void setAantalCores(int aantalCores) {
        this.aantalCores = aantalCores;
    }

    public BigDecimal getKloksnelheid() {
        return kloksnelheid;
    }

    public void setKloksnelheid(BigDecimal kloksnelheid) {
        this.kloksnelheid = kloksnelheid;
    }

    public int getCacheOpslag() {
        return cacheOpslag;
    }

    public void setCacheOpslag(int cacheOpslag) {
        this.cacheOpslag = cacheOpslag;
    }


    public String getBrandmerk() {
        return brandmerk;
    }

    public void setBrandmerk(String brandmerk) {
        this.brandmerk = brandmerk;
    }

    public Integer bereken(int getal1, int getal2, Teken toestandsteken){
        switch (toestandsteken){
            case PLUS -> {
                return getal1 + getal2;
            }
            case MINUS -> {
                return getal1 - getal2;
            }
            case VERMENIGVULDIG -> {
                return getal1 * getal2;
            }
            case VERDEEL -> {
                if (getal2 != 0){
                    return getal1 / getal2;
                } else {
                    return null;
                }
            }
            default -> {
                return null;
            }
        }
    }
}
