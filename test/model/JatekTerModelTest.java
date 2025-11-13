package model;

public class JatekTerModelTest {

    public static void main(String[] args) {
        JatekTerModelTest teszt = new JatekTerModelTest();
        teszt.alapKezdesMindenMezoKikapcsolva();
        teszt.kapcsolKeresztetKapcsolAt();
        teszt.kapcsolSaroknalCsakLehetsegesSzomszedokValtanak();
        teszt.getMezoAllapotVisszaadottTombModositasaNemHatasos();
        teszt.nyertCsakAkkorIgazHaMindenBeVanKapcsolva();
        System.out.println("Minden teszt sikeresen lefutott.");
    }

    private final JatekTerModel model = new JatekTerModel();

    private void alapKezdesMindenMezoKikapcsolva() {
        assert model.getMezoAllapot().length == 9 : "A pálya mérete 3x3";
        for (boolean b : model.getMezoAllapot()) {
            assert !b : "Az alapállapotban minden mezőnek ki kell kapcsolva lennie";
        }
        assert model.getLepesSzam() == 0 : "Alapállapotban a lépésszám 0";
    }

    private void kapcsolKeresztetKapcsolAt() {
        model.alapKezdes();
        model.kapcsol(4);
        assert model.getMezoAllapotIndex(4) : "A középső mezőnek be kell kapcsolnia";
        assert model.getMezoAllapotIndex(1) : "A felső szomszédnak be kell kapcsolnia";
        assert model.getMezoAllapotIndex(3) : "A bal szomszédnak be kell kapcsolnia";
        assert model.getMezoAllapotIndex(5) : "A jobb szomszédnak be kell kapcsolnia";
        assert model.getMezoAllapotIndex(7) : "Az alsó szomszédnak be kell kapcsolnia";
        assert model.getLepesSzam() == 1 : "Egy kapcsolás után a lépésszám 1";
    }

    private void kapcsolSaroknalCsakLehetsegesSzomszedokValtanak() {
        model.alapKezdes();
        model.kapcsol(0);
        assert model.getMezoAllapotIndex(0);
        assert model.getMezoAllapotIndex(1);
        assert model.getMezoAllapotIndex(3);
        int[] nemValtozhat = {2, 4, 5, 6, 7, 8};
        for (int index : nemValtozhat) {
            assert !model.getMezoAllapotIndex(index) : "A saroktól távol eső mezők ne váltsanak";
        }
    }

    private void getMezoAllapotVisszaadottTombModositasaNemHatasos() {
        model.alapKezdes();
        boolean[] masolat = model.getMezoAllapot();
        masolat[0] = true;
        assert !model.getMezoAllapotIndex(0) : "A külső módosítás nem változtathatja a modell állapotát";
    }

    private void nyertCsakAkkorIgazHaMindenBeVanKapcsolva() {
        model.alapKezdes();
        assert !model.nyert() : "Kezdéskor nincs nyerés";
        for (int i = 0; i < 9; i++) {
            model.setMezoAllapotIndex(i, true);
        }
        assert model.nyert() : "Ha minden mező be van kapcsolva, nyert a játékos";
    }
}
