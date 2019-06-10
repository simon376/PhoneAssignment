package code;

import code.interfaces.IController;
import code.interfaces.IModel;
import views.OldSchoolUi;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        IModel theModel = new PhoneModel();
        IController theController = new PhoneController(theModel);
        OldSchoolUi oldSchoolUi = new OldSchoolUi(theModel,theController);

        JFrame frame = new JFrame("Phone");
        frame.setContentPane(oldSchoolUi.getPanelPhone());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
