import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeMap;

public class Robot {
    private String code;
    private String naam;
    private BigDecimal accuPercentage;
    private boolean isActief;
    private Lichaam lichaam;
    private ArrayList<OpslagSchijf> opslagSchijven = new ArrayList<>();
    private Processor processor;

    public Robot(String code, String naam, BigDecimal accuPercentage, boolean isActief, Lichaam lichaam, OpslagSchijf opslagSchijf, Processor processor) {
        this.code = code;
        this.naam = naam;
        this.accuPercentage = accuPercentage;
        this.isActief = isActief;
        this.lichaam = lichaam;
        this.opslagSchijven.add(opslagSchijf);
        this.processor = processor;
    }

    public String getCode() {
        return code;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public BigDecimal getAccuPercentage() {
        return accuPercentage;
    }

    public void setAccuPercentage(BigDecimal accuPercentage) {
        this.accuPercentage = accuPercentage;
    }

    public boolean isActief() {
        return isActief;
    }

    public void setActief(boolean actief) {
        isActief = actief;
    }

    public void loopVooruit(){
        if (isActief && (accuPercentage.compareTo(BigDecimal.ZERO) > 0)){
            lichaam.setPositieY(lichaam.getPositieY() + 1);
            accuPercentage = accuPercentage.subtract(BigDecimal.valueOf(0.01));
        }
    }

    public void loopTerug(){
        if (isActief && (accuPercentage.compareTo(BigDecimal.ZERO) > 0)){
            lichaam.setPositieY(lichaam.getPositieY() - 1);
            accuPercentage = accuPercentage.subtract(BigDecimal.valueOf(0.01));
        }
    }

    public void loopRechts(){
        if (isActief && (accuPercentage.compareTo(BigDecimal.ZERO) > 0)){
            lichaam.setPositieX(lichaam.getPositieX() + 1);
            accuPercentage = accuPercentage.subtract(BigDecimal.valueOf(0.01));
        }
    }

    @Override
    public String toString() {
        return "Robot{" +
                "code='" + code + '\'' +
                ", naam='" + naam + '\'' +
                ", accuPercentage=" + accuPercentage +
                ", isActief=" + isActief +
                ", lichaam=" + lichaam +
                '}';
    }

    public void loopLinks(){
        if (isActief && (accuPercentage.compareTo(BigDecimal.ZERO) > 0)){
            lichaam.setPositieX(lichaam.getPositieX() - 1);
            accuPercentage = accuPercentage.subtract(BigDecimal.valueOf(0.01));
        }
    }

    public String geefHuidigeLocatie(){
        return "X positie: " + lichaam.getPositieX() + "\nY positie: " + lichaam.getPositieY();
    }

    public Integer maakBerekening(int getal1, int getal2, Teken teken){
        if (isActief && (accuPercentage.compareTo(BigDecimal.ZERO) > 0)){
            accuPercentage = accuPercentage.subtract(BigDecimal.valueOf(0.01));
            return processor.bereken(getal1, getal2, teken);
        } else {
            return null;
        }
    }

    public void slaDataOp(String data){
        boolean dataHasntBeenSaved = (isActief && (accuPercentage.compareTo(BigDecimal.ZERO) > 0));
        int i = 0;
        while (dataHasntBeenSaved){
            if (opslagSchijven.get(i).getGebruikteOpslag() < opslagSchijven.get(i).getMaxOpslag()){
                opslagSchijven.get(i).slaDataOp(data);
                accuPercentage = accuPercentage.subtract(BigDecimal.valueOf(0.01));
                dataHasntBeenSaved = false;
            }

            if (i == opslagSchijven.size()){
                dataHasntBeenSaved = false;
            }
        }
    }
}
