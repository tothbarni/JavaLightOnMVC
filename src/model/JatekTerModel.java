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
        int sor = index / 3;
        int oszlop = index % 3;
        valt(index);
        if (oszlop > 0) valt(index - 1);
        if (oszlop < 2) valt(index + 1);
        if (sor > 0) valt(index - 3);
        if (sor < 2) valt(index + 3);
        lepesSzam++;
    }

    private void valt(int index) {
        mezoAllapot[index] = !mezoAllapot[index];
    }

    public void alapKezdes() {
        for (int i = 0; i < mezoAllapot.length; i++) {
            mezoAllapot[i] = false;
        }
        lepesSzam = 0;
    }

    public void randomKezdes(java.util.Random r) {
        for (int i = 0; i < mezoAllapot.length; i++) {
            mezoAllapot[i] = r.nextBoolean();
        }
        lepesSzam = 0;
    }

    public boolean nyert() {
        for (boolean b : mezoAllapot) {
            if (!b) return false;
        }
        return true;
    }
}