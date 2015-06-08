package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;
import ch.fhnw.oop.Movie;
import net.miginfocom.swing.MigLayout;
import sun.jvm.hotspot.types.JIntField;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

import static javax.swing.SwingConstants.CENTER;


public class DetailView extends JPanel {

    public static String PATH_POSTERS = "../resources/poster/";
    public static String IMAGE_NO_POSTERS = "../resources/poster/no_poster.gif";
    public static String PATH_FLAGS = "../resources/flags_iso/24/";

    private final AcademyModel model;
    private final AcademyController controller;


    // Attributes of preview_panel
    private JLabel pp_year;
    private JLabel pp_title;
    private JLabel pp_von;
    private JLabel pp_mit;
    private JLabel pp_director;
    private JLabel pp_actors;
    private JPanel pp_flag;
    private JPanel pp_oscars;
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
    private JLabel sp_ReleaseDate;
    private JTextField sp_ReleaseDateText;

    private JLabel sp_Fsk;
    private SpinnerModel sp_FskModel;
    private JSpinner sp_FskText;
    private JLabel sp_Duration;
    private SpinnerModel sp_DurationModel;
    private JSpinner sp_DurationText;
    private JLabel sp_Oscars;
    private SpinnerModel sp_OscarsModel;
    private JSpinner sp_OscarsText;


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
        showData();
    }

    /**
     * @return JPanel
     * @description View with the images, flags...
     */
    private JPanel initializePreviewPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout(
                "",
                "[][][]",
                "[][][][][]"
        ));
        panel.setBackground(Color.white);

        pp_poster = new JLabel();
        panel.add(pp_poster, "dock east");

        pp_oscars = new JPanel();
        pp_oscars.setLayout(new MigLayout());
        pp_oscars.setBackground(Color.white);
        panel.add(pp_oscars, "dock south");

        pp_year = new JLabel("");
        panel.add(pp_year, "");

        pp_flag = new JPanel();
        pp_flag.setLayout(new MigLayout());
        pp_flag.setBackground(Color.white);
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
        sp_DurationModel = new SpinnerNumberModel(1, 1, 1000, 1);
        sp_DurationText = new JSpinner(sp_DurationModel);
        panel.add(sp_DurationText, "width :100:,wrap,right");

        sp_Fsk = new JLabel("FSK:");
        panel.add(sp_Fsk, "width :100:");
        sp_FskModel = new SpinnerNumberModel(0, 0, 21, 1);
        sp_FskText = new JSpinner(sp_FskModel);
        panel.add(sp_FskText, "width :100:");

        sp_ReleaseDate = new JLabel("Releasedatum:");
        panel.add(sp_ReleaseDate, "width :100:,gapleft 100");
        sp_ReleaseDateText = new JTextField();
        panel.add(sp_ReleaseDateText, "width :100:,wrap,right");

        sp_Oscars = new JLabel("Oscars:");
        panel.add(sp_Oscars, "width :100:");
        sp_OscarsModel = new SpinnerNumberModel(1, 1, 30, 1);
        sp_OscarsText = new JSpinner(sp_OscarsModel);
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
                pp_title.setText(model.getModel(model.getSelectedMovieId()).getTitle());
            }
        });

        sp_YearText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(sp_YearText.getText(), TableView.TableModel.COL_YEAR);
                pp_year.setText(model.getModel(model.getSelectedMovieId()).getYearOfAward());
            }
        });

        sp_directorText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(sp_directorText.getText(), TableView.TableModel.COL_DIRECTOR);
                pp_director.setText(model.getModel(model.getSelectedMovieId()).getDirector());
            }
        });

        sp_ActorText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(sp_ActorText.getText(), TableView.TableModel.COL_MAIN_ACTOR);
                pp_actors.setText(model.getModel(model.getSelectedMovieId()).getMainActor());
            }
        });


        sp_OscarsModel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                controller.setNumberOfOscarsAtSelectedMovie((int)sp_OscarsModel.getValue());
            }
        });


        sp_CountryText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.onChangeCountry(sp_CountryText.getText());
            }
        });


    }

    public void addObservers() {
        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;
            showData();

        });
    }

    public ImageIcon getPoster(){
        String targetPoster = PATH_POSTERS + model.getSelectedMovieId() + ".jpg";
        ImageIcon poster;
        if(getClass().getResource(targetPoster) != null){
            poster = new ImageIcon(getClass().getResource(targetPoster).getFile());
        }else{
            poster = new ImageIcon(getClass().getResource(IMAGE_NO_POSTERS));
        }
        return poster;
    }

    public void showData() {
        Movie movie = model.getMovieById(model.getSelectedMovieId());

        pp_year.setText(movie.getYearOfAward());
        pp_title.setText(movie.getTitle());
        pp_director.setText(movie.getDirector());
        pp_actors.setText(movie.getMainActor());

        ImageIcon poster = getPoster();
        pp_poster.setIcon(poster);
        pp_poster.updateUI();

        sp_YearText.setText(movie.getYearOfAward());
        sp_TitleText.setText(movie.getTitle());
        sp_directorText.setText(movie.getDirector());
        sp_ActorText.setText(movie.getMainActor());
        sp_TitleEngText.setText(movie.getTitleEnglish());
        sp_GenreText.setText(movie.getGenre());
        sp_ProductionYearText.setText(movie.getYearOfProduction());
        sp_CountryText.setText(movie.getCountry());
        sp_ReleaseDateText.setText(movie.getYearOfProduction());

        sp_FskModel.setValue(movie.getFsk());
        sp_OscarsModel.setValue(movie.getNumberOfOscars());
        sp_DurationModel.setValue(movie.getDuration());

        generateFlagIconsSet(movie);
        generateOscarIconsSet(movie);


    }

    public void generateOscarIconsSet(Movie movie) {
        pp_oscars.removeAll();
        for (int i = 0; i < movie.getNumberOfOscars(); ++i) {
            JLabel oscarLabel = new JLabel();
            ImageIcon oscar = new ImageIcon(getClass().getResource("../resources/Oscar-logo.jpg"));
            oscar.setImage(oscar.getImage().getScaledInstance(25, 50, Image.SCALE_DEFAULT));
            oscarLabel.setIcon(oscar);
            pp_oscars.add(oscarLabel);
        }
        pp_oscars.updateUI();
    }

    //FLAG
    public void generateFlagIconsSet(Movie movie) {
        pp_flag.removeAll();
        String country = movie.getCountry();

        if(country != null){
            country = country.toLowerCase();
            country = country.trim();

            String[] countries = country.split("/");
            for (int i=0; i<countries.length; i++){
                String targetFlag = PATH_FLAGS + countries[i].trim() + ".png";
                JLabel flagLabel = new JLabel();
                if(getClass().getResource(targetFlag) != null){
                    ImageIcon flag = new ImageIcon(getClass().getResource(targetFlag).getFile());
                    flagLabel.setIcon(flag);
                }
                pp_flag.add(flagLabel);
            }
        }
        pp_flag.updateUI();

    }

    public static boolean isNumeric(String value) {
        try {
            int number = Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //FLAG
//    public String setFlag(String allFlags) {
//        StringBuilder stringFlag = new StringBuilder(allFlags);
//        String help, targetFlag;
//        for (int i = 0; i < stringFlag.length(); ++i) {
//            if (stringFlag.charAt(i) == ' ' || stringFlag.charAt(i) == ',' || stringFlag.charAt(i) == ';') {
//                help = stringFlag.substring(i - 2, i);
//                targetFlag = "../resources/flags_iso/24/" + help + ".png";
//                return targetFlag;
//            }
//        }
//        help = (stringFlag.substring(stringFlag.length() - 2, stringFlag.length()));
//        targetFlag = "../resources/flags_iso/24/" + help + ".png";
//        return targetFlag;
//    }

}
