package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;
import ch.fhnw.oop.Movie;
import net.miginfocom.swing.MigLayout;
import sun.jvm.hotspot.types.JIntField;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

import static javax.swing.SwingConstants.CENTER;


public class DetailView extends JPanel {
    private final AcademyModel model;
    private final AcademyController controller;


    // Attributes of preview_panel
    private JLabel pp_year;
    private JLabel pp_title;
    private JLabel pp_von;
    private JLabel pp_mit;
    private JLabel pp_director;
    private JLabel pp_actors;
    private JLabel pp_flag;
    private JLabel pp_oscars;
    private JLabel pp_poster;

    // Attributes of show_panel
    private JLabel sp_Year;
    private JTextField sp_YearText;
    private JLabel sp_Title;
    private JTextField sp_TitleText;
    private JLabel sp_director;
    private JTextField sp_directorText;
    private JLabel sp_Actor;
    private JTextField sp_ActorText;
    private JLabel sp_TitleEng;
    private JTextField sp_TitleEngText;
    private JLabel sp_Genre;
    private JTextField sp_GenreText;
    private JLabel sp_ProductionYear;
    private JTextField sp_ProductionYearText;
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
        this.setLayout(new BorderLayout());
        JPanel preview = initializePreviewPanel();
        JPanel form = initializeFormPanel();
        addEvents();
        addObservers();


        this.add(preview, BorderLayout.NORTH);
        this.add(form, BorderLayout.CENTER);

        this.setVisible(true);
        showData(model.getList(), model.getSelectedMovieId());
    }

    /**
     * @return JPanel
     * @description View with the images, flags...
     */
    private JPanel initializePreviewPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout(
                "",
                "[][grow][grow]",
                "[grow][grow][grow][grow][grow]"
        ));
        panel.setBackground(Color.white);

        pp_poster = new JLabel("Hier kommt das Poster hin");
        panel.add(pp_poster, "dock east");

        pp_oscars = new JLabel("Anzahl Oscars");
        panel.add(pp_oscars, "dock south");

        pp_year = new JLabel("");
        panel.add(pp_year, "");

        pp_flag = new JLabel("Country Flags");
        panel.add(pp_flag, "wrap,right");

        pp_title = new JLabel("");
        panel.add(pp_title, "wrap");

        pp_von = new JLabel("von");
        panel.add(pp_von);

        pp_director = new JLabel("");
        panel.add(pp_director, "wrap");

        pp_mit = new JLabel("mit");
        panel.add(pp_mit);

        pp_actors = new JLabel("");
        panel.add(pp_actors, "wrap");

        return panel;
    }


    /**
     * @return JPanel
     * @description View with formular...
     */
    private JPanel initializeFormPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.gray);
        panel.setLayout(new MigLayout());

        sp_Year = new JLabel("Jahr:");
        panel.add(sp_Year, "width :100:");
        sp_YearText = new JTextField();
        panel.add(sp_YearText, "width :100:,wrap,span 3");

        sp_Title = new JLabel("Titel:");
        panel.add(sp_Title, "width :100:");
        sp_TitleText = new JTextField();
        panel.add(sp_TitleText, "width :500:,wrap,span 3");

        sp_director = new JLabel("Regisseur:");
        panel.add(sp_director, "width :100:");
        sp_directorText = new JTextField();
        panel.add(sp_directorText, "width :500:,wrap,span 3");

        sp_Actor = new JLabel("Hauptdarsteller:");
        panel.add(sp_Actor, "width :100:");
        sp_ActorText = new JTextField();
        panel.add(sp_ActorText, "width :500:,wrap,span 3");

        sp_TitleEng = new JLabel("Titel (eng):");
        panel.add(sp_TitleEng, "width :100:");
        sp_TitleEngText = new JTextField();
        panel.add(sp_TitleEngText, "width :500:,wrap,span 3");

        sp_Genre = new JLabel("Genre:");
        panel.add(sp_Genre, "width :100:");
        sp_GenreText = new JTextField();
        panel.add(sp_GenreText, "width :100:");

        sp_ProductionYear = new JLabel("Produktionsjahr:");
        panel.add(sp_ProductionYear, "width :100:,gapleft 100");
        sp_ProductionYearText = new JTextField();
        panel.add(sp_ProductionYearText, "width :100:,wrap,right");

        sp_Country = new JLabel("Land:");
        panel.add(sp_Country, "width :100:");
        sp_CountryText = new JTextField();
        panel.add(sp_CountryText, "width :100:");

        sp_Duration = new JLabel("Spieldauer:");
        panel.add(sp_Duration, "width :100:,gapleft 100");
        sp_DurationText = new JTextField();
        panel.add(sp_DurationText, "width :100:,wrap,right");

        sp_Fsk = new JLabel("FSK:");
        panel.add(sp_Fsk, "width :100:");
        sp_FskText = new JTextField();
        panel.add(sp_FskText, "width :100:");

        sp_ReleaseDate = new JLabel("Releasedatum:");
        panel.add(sp_ReleaseDate, "width :100:,gapleft 100");
        sp_ReleaseDateText = new JTextField();
        panel.add(sp_ReleaseDateText, "width :100:,wrap,right");

        sp_Oscars = new JLabel("Oscars:");
        panel.add(sp_Oscars, "width :100:");
        sp_OscarsText = new JTextField();
        panel.add(sp_OscarsText, "width :100:");

        return panel;
    }

    /**
     * EVENTS
     */
    private void addEvents() {

        sp_TitleText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(sp_TitleText.getText(), TableView.TableModel.COL_TITLE);
            }
        });

        sp_YearText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(sp_YearText.getText(), TableView.TableModel.COL_YEAR);
            }
        });

        sp_directorText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(sp_directorText.getText(), TableView.TableModel.COL_DIRECTOR);
            }
        });

        sp_ActorText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(sp_ActorText.getText(), TableView.TableModel.COL_MAIN_ACTOR);
            }
        });


    }

    public void addObservers(){
        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;




        });
    }


    public void showData(List<Movie> list, int index) {
        Movie movie = list.get(index);

        pp_year.setText(movie.getYearOfAward());
        pp_title.setText(movie.getTitle());
        pp_director.setText(movie.getDirector());
        pp_actors.setText(movie.getMainActor());

        sp_YearText.setText(movie.getYearOfAward());
        sp_TitleText.setText(movie.getTitle());
        sp_directorText.setText(movie.getDirector());
        sp_ActorText.setText(movie.getMainActor());
        sp_TitleEngText.setText(movie.getTitleEnglish());
        sp_GenreText.setText(movie.getGenre());
        sp_ProductionYearText.setText(movie.getYearOfProduction());
        sp_CountryText.setText(movie.getCountry());
        sp_DurationText.setText(movie.getDuration().toString());
        sp_FskText.setText(movie.getFsk());
        sp_ReleaseDateText.setText(movie.getYearOfProduction());
        sp_OscarsText.setText(movie.getNumberOfOscars().toString());


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
