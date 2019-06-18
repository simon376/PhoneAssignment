package code;

import code.interfaces.IController;
import code.interfaces.IModel;
import code.model.PhoneModel;
import views.ModernUi;
import views.OldSchoolUi;

import javax.swing.*;
import java.awt.*;

// Main Application entry point
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        IModel theModel = new PhoneModel();

        IController oldschoolPhoneController = new OldschoolPhoneController(new StateDialingOld(), theModel);
        OldSchoolUi oldSchoolUi = new OldSchoolUi(theModel,oldschoolPhoneController);

        IController modernPhoneController = new ModernPhoneController(new StateStartModern(), theModel);
        ModernUi modernUi = new ModernUi(theModel,modernPhoneController);

        // Application window showing Old School UI
        JFrame frame = new JFrame("Phone");
        frame.setContentPane(oldSchoolUi.getPanelPhone());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // Application window showing Modern UI
        JFrame frame2 = new JFrame("Phone");
        frame2.setLayout(new BorderLayout());
        frame2.setContentPane(modernUi.getPhonePanel());
        frame2.setPreferredSize(new Dimension(300, 500));
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);


    }
}
