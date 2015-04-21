package ch.fhnw.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class AcademyView extends JFrame {
//	private JButton button;
//	private JTextField textField;
//	private JLabel label;

    private final AcademyModel model;
    private final AcademyController controller;

    public AcademyView(AcademyModel model, AcademyController controller) {
        super("Academy App");
        this.model = model;
        this.controller = controller;
    }

    public void createAndShow() {
        initializeComponents();
        JPanel contents = layoutComponents();
        addEvents();

        add(contents);
        pack();
        setLocationRelativeTo(null);
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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int answer = JOptionPane.showConfirmDialog(
                        AcademyView.this,
                        "Programm wirklich beenden?",
                        "Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );
                if (answer == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        model.addObserver(m -> {
            AcademyModel counterModel = (AcademyModel) m;


        });
    }

}
