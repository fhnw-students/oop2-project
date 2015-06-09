package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;
import ch.fhnw.oop.Movie;
import ch.fhnw.oop.MovieValidator;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DetailView extends JPanel {

    public static String PATH_POSTERS = "../resources/poster/";
    public static String IMAGE_NO_POSTERS = "../resources/poster/no_poster.gif";
    public static String PATH_FLAGS = "../resources/flags_iso/24/";

    public static Color VALID_BACKGROUND = new Color(255, 255, 255);
    public static Color INVALID_BACKGROUND = new Color(132, 76, 76);
    public static Color VALID_COLOR = new Color(0, 0, 0);
    public static Color INVALID_COLOR = new Color(220, 220, 220);

    private final AcademyModel model;
    private final AcademyController controller;

    // Attributes of preview panel
    private JLabel previewYearOfAwardLabel;
    private JTextArea previewTitleTextArea;
    private JTextArea previewDirectorTextArea;
    private JTextArea previewMainActorTextArea;
    private JPanel previewCountryFlagsPanel;
    private JPanel previewNumberOfOscarsPanel;
    private JLabel previewPosterLabel;

    // Attributes of editor panel
    private JTextField editorYearOfAwardTextField;
    private JTextField editorTitleTextField;
    private JTextField editorDirectorTextField;
    private JTextField editorMainActorsTextField;
    private JTextField editorTitleEnglishTextField;
    private JTextField editorGenreTextField;
    private JTextField editorProductionYearTextField;
    private JTextField editorCountryTextField;
    private JTextField editorReleaseDateTextField;
    private SpinnerModel editorFskSpinnerModel;
    private SpinnerModel editorDurationSpinnerModel;
    private SpinnerModel editorNumberOfOscarsSpinnerModel;


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
        JPanel editor = initializeEditorPanel();
        this.addEvents();
        this.addObservers();
        this.add(preview, BorderLayout.NORTH);
        this.add(editor, BorderLayout.CENTER);
        this.setVisible(true);
        this.showData();
    }

    /**
     * @return JPanel
     * @description View with the images, flags...
     */
    private JPanel initializePreviewPanel() {
        JPanel previewPanel = new JPanel();
        previewPanel.setLayout(new MigLayout(
                "fillx,gap 10!", //--> Layout Constraints
                "10[][grow][]", //--> Column Constraints
                "10[shrink 0]10[shrink 0]10[shrink 0]10[shrink 0]10[shrink 0]"//--> Row Constraints
        ));
        previewPanel.setBackground(Color.white);

        previewNumberOfOscarsPanel = new JPanel();
        previewNumberOfOscarsPanel.setLayout(new MigLayout());
        previewNumberOfOscarsPanel.setBackground(Color.white);
        previewPanel.add(previewNumberOfOscarsPanel, "dock south");

        previewPosterLabel = new JLabel();
        previewPanel.add(previewPosterLabel, "growx,dock east");

        previewYearOfAwardLabel = new JLabel("");
        previewYearOfAwardLabel.setFont(new Font("", Font.BOLD, 20));
        previewPanel.add(previewYearOfAwardLabel, "");

        previewCountryFlagsPanel = new JPanel();
        previewCountryFlagsPanel.setLayout(new MigLayout());
        previewCountryFlagsPanel.setBackground(Color.white);
        previewPanel.add(previewCountryFlagsPanel, "wrap,right");

        previewTitleTextArea = new JTextArea("");
        previewTitleTextArea.setSize(300, 300);
        previewTitleTextArea.getLineWrap();
        previewTitleTextArea.setLineWrap(true); //set new Line if title length > Text Area Size
        previewTitleTextArea.setWrapStyleWord(true);

        previewTitleTextArea.setFont(new Font("", Font.BOLD, 25));
        previewPanel.add(previewTitleTextArea, "span 3,wrap");

        JLabel previewFromLabel = new JLabel("von");
        previewFromLabel.setFont(new Font("", Font.BOLD, 18));
        previewPanel.add(previewFromLabel);

        previewDirectorTextArea = new JTextArea("");
        previewDirectorTextArea.setSize(200, 200);
        previewDirectorTextArea.getLineWrap();
        previewDirectorTextArea.setWrapStyleWord(true);
        previewDirectorTextArea.setFont(new Font("", Font.LAYOUT_RIGHT_TO_LEFT, 18));
        previewPanel.add(previewDirectorTextArea, "wrap");

        JLabel previewWithLabel = new JLabel("mit");
        previewWithLabel.setFont(new Font("", Font.BOLD, 18));
        previewPanel.add(previewWithLabel);

        previewMainActorTextArea = new JTextArea("");
        previewMainActorTextArea.setSize(200, 200);
        previewMainActorTextArea.setLineWrap(true); //set new Line automatically
        previewMainActorTextArea.setWrapStyleWord(true);
        previewMainActorTextArea.setFont(new Font("", Font.LAYOUT_RIGHT_TO_LEFT, 18));
        previewPanel.add(previewMainActorTextArea, "wrap");

        return previewPanel;
    }


    /**
     * @return JPanel
     * @description View with formular...
     */
    private JPanel initializeEditorPanel() {
        JPanel editorPanel = new JPanel();
        editorPanel.setBackground(Color.LIGHT_GRAY);
        editorPanel.setLayout(new MigLayout("", //--> Layout Constraints
                "10[]0[grow]50[]0[grow]", //--> Column Constraints
                "[][][][][][][][][]"//--> Row Constraints
        ));
        final String LABEL_WIDTH = "width :100:";
        final String TEXT_BOTTOM = "growx, sg";
        final String TEXT_UP = "span, wrap, growx";
        final String TEXT_BOTTOM_WRAP = "growx, sg, wrap";

        JLabel editorYearOfAwardLabel = new JLabel("Jahr:");
        editorPanel.add(editorYearOfAwardLabel, LABEL_WIDTH);
        editorYearOfAwardTextField = new JTextField();
        editorPanel.add(editorYearOfAwardTextField, TEXT_UP);

        JLabel editorTitleLabel = new JLabel("Titel:");
        editorPanel.add(editorTitleLabel, LABEL_WIDTH);
        editorTitleTextField = new JTextField();
        editorPanel.add(editorTitleTextField, TEXT_UP);

        JLabel editorDirectorLabel = new JLabel("Regisseur:");
        editorPanel.add(editorDirectorLabel, LABEL_WIDTH);
        editorDirectorTextField = new JTextField();
        editorPanel.add(editorDirectorTextField, TEXT_UP);

        JLabel editorMainActorLabel = new JLabel("Hauptdarsteller:");
        editorPanel.add(editorMainActorLabel, LABEL_WIDTH);
        editorMainActorsTextField = new JTextField();
        editorPanel.add(editorMainActorsTextField, TEXT_UP);

        JLabel editorTitleEnglishLabel = new JLabel("Titel (eng):");
        editorPanel.add(editorTitleEnglishLabel, LABEL_WIDTH);
        editorTitleEnglishTextField = new JTextField();
        editorPanel.add(editorTitleEnglishTextField, TEXT_UP);

        JLabel editorGenreLabel = new JLabel("Genre:");
        editorPanel.add(editorGenreLabel, LABEL_WIDTH);
        editorGenreTextField = new JTextField();
        editorPanel.add(editorGenreTextField, TEXT_BOTTOM);

        JLabel editorProductionYearLabel = new JLabel("Produktionsjahr:");
        editorPanel.add(editorProductionYearLabel, LABEL_WIDTH);
        editorProductionYearTextField = new JTextField();
        editorPanel.add(editorProductionYearTextField, TEXT_BOTTOM_WRAP);

        JLabel editorCountryLabel = new JLabel("Land:");
        editorPanel.add(editorCountryLabel, LABEL_WIDTH);
        editorCountryTextField = new JTextField();
        editorPanel.add(editorCountryTextField, TEXT_BOTTOM);

        JLabel editorDurationLabel = new JLabel("Spieldauer:");
        editorPanel.add(editorDurationLabel, LABEL_WIDTH);
        editorDurationSpinnerModel = new SpinnerNumberModel(1, 1, 1000, 1);
        JSpinner editorDurationSpinner = new JSpinner(editorDurationSpinnerModel);
        editorPanel.add(editorDurationSpinner, TEXT_BOTTOM_WRAP);

        JLabel editorFskLabel = new JLabel("FSK:");
        editorPanel.add(editorFskLabel, LABEL_WIDTH);
        editorFskSpinnerModel = new SpinnerNumberModel(0, 0, 21, 1);
        JSpinner editorFskSpinner = new JSpinner(editorFskSpinnerModel);
        editorPanel.add(editorFskSpinner, TEXT_BOTTOM);

        JLabel editorReleaseDateLabel = new JLabel("Releasedatum:");
        editorPanel.add(editorReleaseDateLabel, LABEL_WIDTH);
        editorReleaseDateTextField = new JTextField();
        editorPanel.add(editorReleaseDateTextField, TEXT_BOTTOM_WRAP);

        JLabel editorNumberOfOscarsLabel = new JLabel("Oscars:");
        editorPanel.add(editorNumberOfOscarsLabel, LABEL_WIDTH);
        editorNumberOfOscarsSpinnerModel = new SpinnerNumberModel(1, 1, 20, 1);
        JSpinner editorNumberOfOscarsSpinner = new JSpinner(editorNumberOfOscarsSpinnerModel);
        editorPanel.add(editorNumberOfOscarsSpinner, TEXT_BOTTOM);

        return editorPanel;
    }

    /**
     * EVENTS
     */
    private void addEvents() {

        editorTitleTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(editorTitleTextField.getText(), TableView.TableModel.COL_TITLE);
                previewTitleTextArea.setText(model.getMovieById(model.getSelectedMovieId()).getTitle());
            }
        });

        editorYearOfAwardTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(editorYearOfAwardTextField.getText(), TableView.TableModel.COL_YEAR);
            }
        });

        editorDirectorTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(editorDirectorTextField.getText(), TableView.TableModel.COL_DIRECTOR);
                previewDirectorTextArea.setText(model.getMovieById(model.getSelectedMovieId()).getDirector());
            }
        });

        editorMainActorsTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.setValueAtSelectedRow(editorMainActorsTextField.getText(), TableView.TableModel.COL_MAIN_ACTOR);
                previewMainActorTextArea.setText(model.getMovieById(model.getSelectedMovieId()).getMainActor());
            }
        });

        editorNumberOfOscarsSpinnerModel.addChangeListener(e -> controller.onChangeNumberOfOscars((int) editorNumberOfOscarsSpinnerModel.getValue()));
        editorDurationSpinnerModel.addChangeListener(e -> controller.onChangeDuration((int) editorDurationSpinnerModel.getValue()));
        editorFskSpinnerModel.addChangeListener(e -> controller.onChangeFsk((int) editorFskSpinnerModel.getValue()));

        editorCountryTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.onChangeCountry(editorCountryTextField.getText());
            }
        });

        editorGenreTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.onChangeGenre(editorGenreTextField.getText());
            }
        });

        editorReleaseDateTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.onChangeStartDate(editorReleaseDateTextField.getText());
            }
        });

        editorTitleEnglishTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.onChangeTitleEnglish(editorTitleEnglishTextField.getText());
            }
        });

        editorProductionYearTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                controller.onChangeYearOfProduction(editorProductionYearTextField.getText());
            }
        });

    }

    public void addObservers() {
        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;
            Movie movie = academyModel.getMovieById(academyModel.getSelectedMovieId());

            validateTextField(MovieValidator.isValidYear(movie.getYearOfAward()), editorYearOfAwardTextField);
            validateTextField(MovieValidator.isRequired(movie.getTitle()), editorTitleTextField);
            validateTextField(MovieValidator.isRequired(movie.getDirector()), editorDirectorTextField);
            validateTextField(MovieValidator.isRequired(movie.getMainActor()), editorMainActorsTextField);

            showData();
        });
    }

    public void validateTextField(boolean ok, JTextField field ){
        if(ok){
            setTextFieldStyle(field, VALID_BACKGROUND, VALID_COLOR);
        }else{
            setTextFieldStyle(field, INVALID_BACKGROUND, INVALID_COLOR);
        }
    }

    public void setTextFieldStyle(JTextField field, Color bg, Color color) {
        field.setBackground(bg);
        field.setForeground(color);
    }

    public ImageIcon getPoster() {
        String targetPoster = PATH_POSTERS + model.getSelectedMovieId() + ".jpg";
        ImageIcon poster;
        if (getClass().getResource(targetPoster) != null) {
            poster = new ImageIcon(getClass().getResource(targetPoster).getFile());
        } else {
            poster = new ImageIcon(getClass().getResource(IMAGE_NO_POSTERS));
        }
        poster.setImage(poster.getImage().getScaledInstance(210, 340, Image.SCALE_DEFAULT));
        return poster;
    }

    public void showData() {
        Movie movie = model.getMovieById(model.getSelectedMovieId());

        previewYearOfAwardLabel.setText(movie.getYearOfAward());
        previewTitleTextArea.setText(movie.getTitle());
        previewDirectorTextArea.setText(movie.getDirector());
        previewMainActorTextArea.setText(movie.getMainActor());

        ImageIcon poster = getPoster();
        previewPosterLabel.setIcon(poster);
        previewPosterLabel.updateUI();

        editorYearOfAwardTextField.setText(movie.getYearOfAward());
        editorTitleTextField.setText(movie.getTitle());
        editorDirectorTextField.setText(movie.getDirector());
        editorMainActorsTextField.setText(movie.getMainActor());
        editorTitleEnglishTextField.setText(movie.getTitleEnglish());
        editorGenreTextField.setText(movie.getGenre());
        editorProductionYearTextField.setText(movie.getYearOfProduction());
        editorCountryTextField.setText(movie.getCountry());
        editorReleaseDateTextField.setText(movie.getStartDate());
        editorFskSpinnerModel.setValue(movie.getFsk());
        editorNumberOfOscarsSpinnerModel.setValue(movie.getNumberOfOscars());
        editorDurationSpinnerModel.setValue(movie.getDuration());

        generateFlagIconsSet(movie);
        generateOscarIconsSet(movie);

    }

    public void generateOscarIconsSet(Movie movie) {
        previewNumberOfOscarsPanel.removeAll();
        for (int i = 0; i < movie.getNumberOfOscars(); ++i) {
            JLabel oscarLabel = new JLabel();
            ImageIcon oscar = new ImageIcon(getClass().getResource("../resources/Oscar-logo.jpg"));
            oscar.setImage(oscar.getImage().getScaledInstance(25, 50, Image.SCALE_DEFAULT));
            oscarLabel.setIcon(oscar);
            previewNumberOfOscarsPanel.add(oscarLabel);
        }
        previewNumberOfOscarsPanel.updateUI();
    }

    //FLAG
    public void generateFlagIconsSet(Movie movie) {
        previewCountryFlagsPanel.removeAll();
        String country = movie.getCountry();

        if (country != null) {
            country = country.toLowerCase();
            country = country.trim();

            String[] countries = country.split("/");
            for (String country1 : countries) {
                String targetFlag = PATH_FLAGS + country1.trim() + ".png";
                JLabel flagLabel = new JLabel();
                if (getClass().getResource(targetFlag) != null) {
                    ImageIcon flag = new ImageIcon(getClass().getResource(targetFlag).getFile());
                    flagLabel.setIcon(flag);
                }
                previewCountryFlagsPanel.add(flagLabel);
            }
        }
        previewCountryFlagsPanel.updateUI();

    }

}
