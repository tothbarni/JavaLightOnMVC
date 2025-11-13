package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
        gombok[0] = nezet.getBtn1();
        gombok[1] = nezet.getBtn2();
        gombok[2] = nezet.getBtn3();
        gombok[3] = nezet.getBtn4();
        gombok[4] = nezet.getBtn5();
        gombok[5] = nezet.getBtn6();
        gombok[6] = nezet.getBtn7();
        gombok[7] = nezet.getBtn8();
        gombok[8] = nezet.getBtn9();

        for (int i = 0; i < gombok.length; i++) {
            final int index = i;
            gombok[i].addActionListener(e -> {
                model.kapcsol(index);
                frissitTeljesNezet();
                if (model.nyert()) {
                    JOptionPane.showMessageDialog(nezet, "Nyertél! Lépések száma: " + model.getLepesSzam(), "Gratulálok", JOptionPane.INFORMATION_MESSAGE);
                }
            });
        }

        nezet.getBtnAlap().addActionListener(e -> {
            model.alapKezdes();
            frissitTeljesNezet();
        });
        nezet.getBtnRandom().addActionListener(e -> {
            model.randomKezdes(new java.util.Random());
            frissitTeljesNezet();
        });
        nezet.getBtnUjJatek().addActionListener(e -> {
            model.randomKezdes(new java.util.Random());
            frissitTeljesNezet();
        });
        nezet.getMnuMentes().addActionListener(e -> mentes());
        nezet.getMnuBetoltes().addActionListener(e -> betoltes());
        nezet.getMnuKilepes().addActionListener(e -> kilepes());
    }

    private void frissitTeljesNezet() {
        for (int i = 0; i < gombok.length; i++) {
            boolean be = model.getMezoAllapotIndex(i);
            gombok[i].setBackground(be ? Gui.SZIN_BE : Gui.SZIN_KI);
        }
        nezet.getTxtLepes().setText(Integer.toString(model.getLepesSzam()));
    }

    private void mentes() {
        JFileChooser valaszt = new JFileChooser();
        if (valaszt.showSaveDialog(nezet) == JFileChooser.APPROVE_OPTION) {
            try (PrintWriter iro = new PrintWriter(valaszt.getSelectedFile())) {
                boolean[] allapot = model.getMezoAllapot();
                for (int i = 0; i < allapot.length; i++) {
                    if (i > 0) iro.print(' ');
                    iro.print(allapot[i] ? 1 : 0);
                }
                iro.println();
                iro.println(model.getLepesSzam());
                JOptionPane.showMessageDialog(nezet, "Mentés kész.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(nezet, "Mentés sikertelen: " + ex.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void betoltes() {
        JFileChooser valaszt = new JFileChooser();
        if (valaszt.showOpenDialog(nezet) == JFileChooser.APPROVE_OPTION) {
            try (Scanner sc = new Scanner(valaszt.getSelectedFile())) {
                for (int i = 0; i < gombok.length; i++) {
                    if (!sc.hasNextInt()) throw new IllegalArgumentException("Hiányzó mező érték");
                    model.setMezoAllapotIndex(i, sc.nextInt() == 1);
                }
                model.setLepesSzam(sc.hasNextInt() ? sc.nextInt() : 0);
                frissitTeljesNezet();
                JOptionPane.showMessageDialog(nezet, "Betöltés kész.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(nezet, "Betöltés sikertelen: " + ex.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void kilepes() {
        nezet.kilepes();
    }

}
