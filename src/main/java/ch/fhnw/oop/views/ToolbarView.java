package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyApp;
import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;


public class ToolbarView extends JToolBar {

    static final private String ICON_PATH = "resources/icons/";
    static final private String PNG_EXTENSION = ".png";

    private final AcademyModel model;
    private final AcademyController controller;

    private JButton saveButton;
    private JButton addButton;
    private JButton removeButton;
    private JButton undoButton;
    private JButton redoButton;
    private JToolBar buttonsToolbar;
    private JTextField searchTextField;

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
        buttonsToolbar.setFloatable(false);
        buttonsToolbar.setRollover(true);
        buttonsToolbar.setBackground(Color.darkGray);
        buttonsToolbar.setVisible(true);

        buttonsToolbar.add(saveButton);
        buttonsToolbar.addSeparator();
        buttonsToolbar.add(addButton);
        buttonsToolbar.add(removeButton);
        buttonsToolbar.addSeparator();
        buttonsToolbar.add(undoButton);
        buttonsToolbar.add(redoButton);
        this.add(buttonsToolbar, BorderLayout.WEST);

        // Search field
        this.add(searchTextField, BorderLayout.EAST);

    }


    private void initializeComponents() {
        saveButton = makeNavigationButton("Save", "save", "Save changes", "Save");
        addButton = makeNavigationButton("Plus", "add", "Add changes", "Add");
        removeButton = makeNavigationButton("Minus", "remove", "Remove changes", "Remove");
        undoButton = makeNavigationButton("Undo", "undo", "Undo changes", "Undo");
        redoButton = makeNavigationButton("Redo", "redo", "Redo changes", "Redo");
        buttonsToolbar = new JToolBar();
        searchTextField = new JTextField(30);

        saveButton.setEnabled(false);
        redoButton.setEnabled(false);
        undoButton.setEnabled(false);
    }

    private void addEvents() {
        addButton.addActionListener(e -> controller.addNewMovie());
        saveButton.addActionListener(e -> controller.save());
        removeButton.addActionListener(e -> controller.removeMovie());

        searchTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                controller.onChangeSearch(searchTextField.getText());
            }
        });

        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;

            saveButton.setEnabled(
                    academyModel.hasModelBeenChanged()
                            && academyModel.editorIsValid()
                            && academyModel.areAllMoviesValid()
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
