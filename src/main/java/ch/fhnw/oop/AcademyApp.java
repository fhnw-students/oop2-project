package ch.fhnw.oop;

import javax.swing.*;

public class AcademyApp {
    public static void main(String[] args) {
		final AcademyModel model = new AcademyModel();
		final AcademyController controller = new AcademyController(model);
		final AcademyView view = new AcademyView(model,controller);

		SwingUtilities.invokeLater(view::createAndShow);
    }
}
