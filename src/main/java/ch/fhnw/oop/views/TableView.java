package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;


public class TableView extends JTable {
    private final AcademyModel model;
    private final AcademyController controller;

    private final TableModel tableModel;


    public TableView(AcademyModel model, AcademyController controller) {
        this.model = model;
        this.controller = controller;
        tableModel = new TableModel(model);
        setModel(tableModel);

    }

    public void createAndShow() {
        initializeComponents();
        JPanel contents = layoutComponents();
        addEvents();

        setBackground(Color.yellow);

        add(contents);
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


        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;


        });
    }


    class TableModel extends AbstractTableModel {

        private String[] columnNames = {
                "",
                "Jahr",
                "Titel",
                "Reggiseur",
                "Hauptdarsteller"
        };
        private final AcademyModel model;

        public TableModel(AcademyModel model) {

            this.model = model;
        }


        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return model.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return model[row][col];
        }

        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 2) {
                return false;
            } else {
                return true;
            }
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            model[row][col] = value;
            fireTableCellUpdated(row, col);
        }

    }

}
