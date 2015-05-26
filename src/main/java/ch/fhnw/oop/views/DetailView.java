package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;
import net.miginfocom.swing.MigLayout;


import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static javax.swing.SwingConstants.CENTER;


public class DetailView extends JPanel {
    private final AcademyModel model;
    private final AcademyController controller;


    // Attributes of detail_show
    private JLabel ds_year;
    private JLabel ds_title;
    private JLabel ds_regisseur;
    private JLabel ds_actors;
    private JLabel flag;
    private JLabel oscars;
    private JLabel poster;

    // Attributes of detail_editor
    private JLabel titleYear;
    private JLabel titleTitle;
    private JLabel titleRegisseur;

    /**
     * CONSTRUCTOR
     */
    public DetailView(AcademyModel model, AcademyController controller) {
        this.model = model;
        this.controller = controller;
        this.createAndShow();

        this.setBackground(Color.black);
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

    }

    /**
     * API
     * ------------------------------------------
     */
    public void createAndShow() {
        JPanel preview = initializePreviewPanel();
        JPanel form = initializeFormPanel();
        addEvents();


        this.add(preview);
        this.add(form);

        this.setVisible(true);
    }

    /**
     * @description
     * View with the images, flags...
     *
     * @return JPanel
     */
    private JPanel initializePreviewPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        return panel;
    }

    /**
     * @description
     * View with formular...
     *
     * @return JPanel
     */
    private JPanel initializeFormPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        return panel;
    }

    /**
     * EVENTS
     */
    private void addEvents() {

        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;


        });
    }


    //    private void initializeComponents() {
//
//        ds_year = new JLabel();
//        ds_year.setFont(new Font("Arial", 1, 24));
//        ds_year.setText("bla");
//        ds_year.setHorizontalAlignment(CENTER);
//
//
//    }

//    private JPanel layoutComponents() {
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        panel.setBackground(Color.black);
//
//
////        JPanel detail_show = new JPanel(new MigLayout());
////        detail_show.add(ds_year);
////        JPanel detail_editor = new JPanel(new MigLayout());
////        panel.add(detail_show, BorderLayout.NORTH);
////        panel.add(detail_editor, BorderLayout.SOUTH);
//
//
//        return panel;
//    }

}
