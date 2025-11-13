package model;

public class JatekTerModel {
    private final boolean[] mezoAllapot = new boolean[9];
    private int lepesSzam = 0;

    public JatekTerModel() {
        alapKezdes();
    }

    public boolean[] getMezoAllapot() {
        return mezoAllapot.clone();
    }

    public boolean getMezoAllapotIndex(int index) {
        return mezoAllapot[index];
    }

    public void setMezoAllapotIndex(int index, boolean beKapcsolt) {
        mezoAllapot[index] = beKapcsolt;
    }

    public int getLepesSzam() {
        return lepesSzam;
    }

    public void setLepesSzam(int lepesSzam) {
        this.lepesSzam = lepesSzam;
    }

    public void kapcsol(int index) {

    }

    private void valt(int index) {
        
    }

    public void alapKezdes() {
        
    }

    public void randomKezdes() {
        
    }

    public boolean nyert() {
        return true;
    }
}