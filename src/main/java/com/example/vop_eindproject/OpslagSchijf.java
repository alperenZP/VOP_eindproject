import java.util.ArrayList;

public class OpslagSchijf {
    private String modelNaam;
    private int maxOpslag;
    private int gebruikteOpslag;
    private ArrayList<String> gegevens = new ArrayList<>();

    public String getModelNaam() {
        return modelNaam;
    }

    public void setModelNaam(String modelNaam) {
        this.modelNaam = modelNaam;
    }

    public int getMaxOpslag() {
        return maxOpslag;
    }

    @Override
    public String toString() {
        return "OpslagSchijf{" +
                "modelNaam='" + modelNaam + '\'' +
                ", maxOpslag=" + maxOpslag +
                ", gebruikteOpslag=" + gebruikteOpslag +
                ", gegevens=" + gegevens +
                '}';
    }

    public OpslagSchijf(String modelNaam, int maxOpslag, int gebruikteOpslag) {
        this.modelNaam = modelNaam;
        this.maxOpslag = maxOpslag;
        this.gebruikteOpslag = gebruikteOpslag;
    }

    public void setMaxOpslag(int maxOpslag) {
        this.maxOpslag = maxOpslag;
    }

    public int getGebruikteOpslag() {
        return gebruikteOpslag;
    }

    public void setGebruikteOpslag(int gebruikteOpslag) {
        this.gebruikteOpslag = gebruikteOpslag;
    }
    public void slaDataOp(String data){
        if (this.gebruikteOpslag + 1 <= maxOpslag){
            this.gebruikteOpslag = this.gebruikteOpslag + 1;
            gegevens.add(data);
        }
    }
}
