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


    // Attributes of preview_panel
    private JLabel ds_year;
    private JLabel ds_title;
    private JLabel ds_regisseur;
    private JLabel ds_actors;
    private JLabel flag;
    private JLabel oscars;
    private JLabel poster;

    // Attributes of show_panel
    private JLabel sp_Year;
    private JTextField sp_YearText;
    private JLabel sp_Title;
    private JTextField sp_TitleText;
    private JLabel sp_Regisseur;
    private JTextField sp_RegisseurText;
    private JLabel sp_Actor;
    private JTextField sp_ActorText;
    private JLabel sp_TitleEng;
    private JTextField sp_TitleEngText;
    private JLabel sp_Genre;
    private JTextField sp_GenreText;
    private JLabel sp_ProductionYear;
    private JTextField sp_ProductionYearText;
    private JLabel sp_actor;
    private JTextField sp_actorText;
    private JLabel sp_Country;
    private JTextField sp_CountryText;
    private JLabel sp_Duration;
    private JTextField sp_DurationText;
    private JLabel sp_Fsk;
    private JTextField sp_FskText;
    private JLabel sp_ReleaseDate;
    private JTextField sp_ReleaseDateText;
    private JLabel sp_Oscars;
    private JTextField sp_OscarsText;



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
        panel.setLayout(new MigLayout());

        sp_Year = new JLabel("Jahr:");
        panel.add(sp_Year, "width :100:");
        sp_YearText = new JTextField();
        panel.add(sp_YearText, "width :100:,wrap");

        sp_Title = new JLabel("Titel:");
        panel.add(sp_Title,"width :100:" );
        sp_TitleText = new JTextField();
        panel.add(sp_TitleText, "width :500:,wrap");




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
