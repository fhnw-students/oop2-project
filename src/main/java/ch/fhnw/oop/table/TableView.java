package ch.fhnw.oop.table;

import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TableView extends JTable {
    private final AcademyModel model;
    private final AcademyController controller;

    public TableView(AcademyModel model, AcademyController controller) {
        this.model = model;
        this.controller = controller;
    }

    public void createAndShow() {
        initializeComponents();
        JPanel contents = layoutComponents();
        addEvents();

        add(contents);
        setVisible(true);
    }


    private void initializeComponents() {

    }

    private JPanel layoutComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        return panel;
    }

    private void addEvents() {


        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;


        });
    }

}
