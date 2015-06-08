package ch.fhnw.oop;

import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class AcademyApp {
    public static void main(String[] args) throws IOException, URISyntaxException {

        try {
            // Set System L&F
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }

        final AcademyModel model = new AcademyModel();
        final AcademyController controller = new AcademyController(model);
        final AcademyView view = new AcademyView(model, controller);

        SwingUtilities.invokeLater(view::createAndShow);
    }
}
