package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyApp;
import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.stream.Collectors;


public class ToolbarView extends JToolBar {

    static final private String ICON_PATH = "resources/icons/";
    static final private String PNG_EXTENSION = ".png";

    private final AcademyModel model;
    private final AcademyController controller;

    private JButton btnSave;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnUndo;
    private JButton btnRedo;
    private JToolBar buttons;
    private JTextField searchField;

    public ToolbarView(AcademyModel model, AcademyController controller) {
        this.model = model;
        this.controller = controller;
        this.createAndShow();
    }

    public void createAndShow() {
        initializeComponents();
        addEvents();

        // Main toolbar
        this.setLayout(new BorderLayout());
        this.setFloatable(false);
        this.setRollover(true);
        this.setBackground(Color.darkGray);
        this.setVisible(true);

        // Buttons
        buttons.setFloatable(false);
        buttons.setRollover(true);
        buttons.setBackground(Color.darkGray);
        buttons.setVisible(true);

        buttons.add(btnSave);
        buttons.addSeparator();
        buttons.add(btnAdd);
        buttons.add(btnRemove);
        buttons.addSeparator();
        buttons.add(btnRedo);
        buttons.add(btnUndo);
        this.add(buttons, BorderLayout.WEST);

        // Search field
        this.add(searchField, BorderLayout.EAST);

    }


    private void initializeComponents() {
        btnSave = makeNavigationButton("Save", "save", "Save changes", "Save");
        btnAdd = makeNavigationButton("Plus", "add", "Add changes", "Add");
        btnRemove = makeNavigationButton("Minus", "remove", "Remove changes", "Remove");
        btnRedo = makeNavigationButton("Redo", "redo", "Redo changes", "Redo");
        btnUndo = makeNavigationButton("Undo", "undo", "Undo changes", "Undo");
        buttons = new JToolBar();
        searchField = new JTextField(30);

        btnSave.setEnabled(false);
        btnRedo.setEnabled(false);
        btnUndo.setEnabled(false);
    }

    private void addEvents() {
        btnAdd.addActionListener(e -> controller.addNewMovie());
        btnRemove.addActionListener(e -> controller.removeMovie());

        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;

            btnSave.setEnabled(
                    academyModel.hasModelBeenChanged()
            );

        });
    }


    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        //Look for the image.
        String imgLocation = ICON_PATH
                + imageName
                + PNG_EXTENSION;

        String imgPressedLocation = ICON_PATH
                + imageName
                + "_Pressed" + PNG_EXTENSION;

        URL imageURL = AcademyApp.class.getResource(imgLocation);
        URL imagePressedURL = AcademyApp.class.getResource(imgPressedLocation);

        //Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
//        button.addActionListener(this);

        if (imageURL != null) {                      //image found
            button.setIcon(new ImageIcon(imageURL, altText));
            button.setPressedIcon(new ImageIcon(imagePressedURL, altText));
        } else {                                     //no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                    + imgLocation);
        }


        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        return button;
    }

}
