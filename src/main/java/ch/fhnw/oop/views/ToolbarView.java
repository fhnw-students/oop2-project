package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyApp;
import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;


public class ToolbarView extends JToolBar {
    private final AcademyModel model;
    private final AcademyController controller;

    private JButton btnSave;
    private JButton btnAdd;
    private JButton btnRemove;
    private JButton btnUndo;
    private JButton btnRedo;

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
        this.addSeparator();

        this.setFloatable(false);
        this.setRollover(true);
        this.setBackground(Color.darkGray);
        this.setVisible(true);
    }


    private void initializeComponents() {
        btnSave = makeNavigationButton("Save", "save", "Save changes", "Save");
        btnSave = makeNavigationButton("Redo", "save", "Redo changes", "Redo");
        btnSave = makeNavigationButton("Undo", "save", "Undo changes", "Undo");
        btnSave = makeNavigationButton("Save", "save", "Save changes", "Save");

    }

    private void addEvents() {
        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;
        });
    }


    protected JButton makeNavigationButton(String imageName,
                                           String actionCommand,
                                           String toolTipText,
                                           String altText) {
        //Look for the image.
        String imgLocation = "resources/icons/"
                + imageName
                + ".png";

        String imgPressedLocation = "resources/icons/"
                + imageName
                + "_Pressed.png";

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
