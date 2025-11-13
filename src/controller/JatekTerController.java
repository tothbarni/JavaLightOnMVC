package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import model.JatekTerModel;
import view.Gui;

public class JatekTerController {
    private final JatekTerModel model;
    private final Gui nezet;
    private final JButton[] gombok = new JButton[9];

    public JatekTerController(JatekTerModel model, Gui nezet) {
        this.model = model;
        this.nezet = nezet;
        hozzarendeles();
        frissitTeljesNezet();
        nezet.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                kilepes();
            }
        });
    }

    private void hozzarendeles() {

    }

    private void frissitTeljesNezet() {

    }

    private void mentes() {

    }

    private void betoltes() {

    }

    private void kilepes() {
        nezet.kilepes();
    }

}
