package ch.fhnw.oop.views;

import ch.fhnw.oop.AcademyController;
import ch.fhnw.oop.AcademyModel;
import ch.fhnw.oop.Movie;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;


public class TableView extends JTable {


    private final AcademyModel model;
    private final AcademyController controller;
    private TableModel tableModel;


    public TableView(AcademyModel model, AcademyController controller) {
        this.model = model;
        this.controller = controller;
        tableModel = new TableModel(model);
        setModel(tableModel);

        addEvents();
        addObservers();
    }


    private void addEvents() {
        this.getSelectionModel().addListSelectionListener(event -> {
            if (this.getSelectedRow() > -1) {
                Movie selectedMovie = this.model.getList().get(this.getSelectedRow());
                this.model.setSelectedMovieId(selectedMovie.getId());
                tableModel.fireTableRowsUpdated(0, model.getList().size() - 1);
            }
        });

    }

    public void refreshSelectedMovie(int index){
        tableModel.fireTableRowsUpdated(index, index);
    }

    public void refreshTable(int index){
        tableModel.fireTableRowsUpdated(0, index);
    }

    public void addObservers() {
        model.addObserver(m -> {
            AcademyModel academyModel = (AcademyModel) m;

            switch (academyModel.observerAction){
                case AcademyModel.ACTION_INSERT:
                    tableModel.fireTableRowsInserted(academyModel.observerIndex, academyModel.observerIndex);
                    break;

                case AcademyModel.ACTION_DELETE:
                    tableModel.fireTableRowsDeleted(academyModel.observerIndex, academyModel.observerIndex);
                    break;

                case AcademyModel.ACTION_PRISTINE:
                    refreshTable(academyModel.getList().size()-1);
                    break;
            }

            refreshSelectedMovie(academyModel.getIndexById(academyModel.getSelectedMovieId()));
            academyModel.observerAction = AcademyModel.ACTION_NONE;
            academyModel.observerIndex = academyModel.getIndexById(academyModel.getSelectedMovieId());

        });
    }


    class TableModel extends AbstractTableModel {
        public final static String MARK_EMPTY = "../resources/marks/Mark_Empty.png";
        public final static String MARK_BLUE = "../resources/marks/Mark_Blue.png";
        public final static String MARK_GREEN = "../resources/marks/Mark_Green.png";
        public final static String MARK_ORANGE = "../resources/marks/Mark_Orange.png";
        public final static String MARK_RED = "../resources/marks/Mark_Red.png";
        public final static String MARK_YELLOW = "../resources/marks/Mark_Yellow.png";

        public final static int COL_YEAR = 1;
        public final static int COL_TITLE = 2;
        public final static int COL_DIRECTOR = 3;
        public final static int COL_MAIN_ACTOR = 4;

        private String[] columnNames = {
                "",
                "Jahr",
                "Titel",
                "Regisseur",
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
            return model.getList().size();
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            Object value = model.getValueAt(row, col);

            if (col == 0) {

                String path = TableModel.MARK_EMPTY;
                if (model.getRow(row).getId() == model.getSelectedMovieId()) {
                    path = TableModel.MARK_BLUE;
                } else if (model.getRow(row).isHasModified()) {
                    path = TableModel.MARK_ORANGE;
                }

                URL imageURL = TableView.class.getResource(path);
                ImageIcon imageIcon = new ImageIcon(imageURL);
                return imageIcon;
            }

            return value;


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
            model.setValueAt(value.toString(), row, col);
            fireTableCellUpdated(row, col);
        }

    }

}

