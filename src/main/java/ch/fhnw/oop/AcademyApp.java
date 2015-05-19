package ch.fhnw.oop;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class AcademyApp {
    public static void main(String[] args) throws IOException, URISyntaxException {
		final AcademyModel model = new AcademyModel();
		final AcademyController controller = new AcademyController(model);
		final AcademyView view = new AcademyView(model,controller);

		SwingUtilities.invokeLater(view::createAndShow);
    }
}
