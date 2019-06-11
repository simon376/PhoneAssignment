package code;

import code.interfaces.IController;
import code.interfaces.IModel;
import views.ModernUi;
import views.OldSchoolUi;
import views.QwertyKeyboard;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        IModel theModel = new PhoneModel();
        IController theController = new PhoneController(new StateDialing(), theModel);
        OldSchoolUi oldSchoolUi = new OldSchoolUi(theModel,theController);
        ModernUi modernUi = new ModernUi(theModel,theController);

        new QwertyKeyboard(theController).setVisible(true);

        JFrame frame = new JFrame("Phone");
        frame.setContentPane(oldSchoolUi.getPanelPhone());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


        JFrame frame2 = new JFrame("Phone");
        frame2.setContentPane(modernUi.getPanelPhone());
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.pack();
        frame2.setVisible(true);

    }
}
