package ch.fhnw.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import ch.fhnw.oop.table.TableView;
import ch.fhnw.oop.detail.DetailView;


public class AcademyView extends JFrame {
    private JTable table;
    private JPanel detail;



    private final AcademyModel model;
    private final AcademyController controller;

    public AcademyView(AcademyModel model, AcademyController controller) {
        super("Action!");
        this.model = model;
        this.controller = controller;
    }

    public void createAndShow() {
        initializeComponents();
        JPanel contents = layoutComponents();
        addEvents();

        add(contents);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }


    private void initializeComponents() {
        table = new TableView(this.model, this.controller);
        detail = new DetailView(this.model, this.controller);
        JSplitPane pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, table, detail );

    }

    private JPanel layoutComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        return panel;
    }

    private void addEvents() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int answer = JOptionPane.showConfirmDialog(
                        AcademyView.this,
                        "Programm wirklich beenden?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        model.addObserver(m -> {
            AcademyModel counterModel = (AcademyModel) m;


        });
    }

}
