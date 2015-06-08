package ch.fhnw.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import ch.fhnw.oop.views.*;


public class AcademyView extends JFrame {
    private JTable table;
    private JScrollPane tableScrollPane;
    private JPanel detail;
    private JToolBar toolbar;
    private JSplitPane splitPane;


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

        setPreferredSize(new Dimension(1400, 800));

        // Add Header Toolbar
        contents.add(toolbar, BorderLayout.PAGE_START);
        contents.add(splitPane, BorderLayout.CENTER);


        add(contents);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        toolbar = new ToolbarView(this.model, this.controller);
        detail = new DetailView(this.model, this.controller);

        table = new TableView(this.model, this.controller);

        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(0).setMaxWidth(25);
        table.getColumnModel().getColumn(1).setMaxWidth(50);


        tableScrollPane = new JScrollPane(table);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, tableScrollPane, detail);
        splitPane.setDividerLocation(700);


        //set minimum Size of the Components
        Dimension minimumSize = new Dimension(500, 500);
        tableScrollPane.setMinimumSize(minimumSize);
        detail.setMinimumSize(minimumSize);

    }

    private JPanel layoutComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.gray);
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
