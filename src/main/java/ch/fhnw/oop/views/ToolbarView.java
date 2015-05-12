package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;

import javax.swing.*;
import java.awt.*;


public class ToolbarView extends JToolBar {
    private final AcademyModel model;
    private final AcademyController controller;

    private JButton btnSave;

    public ToolbarView(AcademyModel model, AcademyController controller) {
        this.model = model;
        this.controller = controller;

        this.createAndShow();
    }

    public void createAndShow() {
        initializeComponents();
        addEvents();

        //first button
        this.add(btnSave);

        this.setFloatable(false);
        this.setRollover(true);
        this.setBackground(Color.darkGray);
        this.setVisible(true);
    }


    private void initializeComponents() {
        btnSave = new JButton("Save");
    }

//    private JPanel layoutComponents() {
//
//        FlowLayout experimentLayout = new FlowLayout();
//
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        panel.setBackground(Color.gray);
//
//
//        /*
//        PAGE_START
//        PAGE_END
//        LINE_START
//        LINE_END
//        CENTER
//        */
//
//        return panel;
//    }




    private void addEvents() {
        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;
        });
    }

}
