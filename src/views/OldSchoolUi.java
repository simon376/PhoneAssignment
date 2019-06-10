package views;

import code.interfaces.IController;
import code.interfaces.IModel;
import code.interfaces.IView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OldSchoolUi implements IView {
    private JPanel panelScreen;
    private JPanel panelKeyboard;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton aStarButton;
    private JButton a0Button;
    private JButton aPoundButton;
    private JTextArea text_screen;
    private JPanel panelCallButtons;
    private JButton acceptButton;
    private JButton declineButton;

    public JPanel getPanelPhone() {
        return panelPhone;
    }

    private JPanel panelPhone;


    private IController theController;
    private IModel theModel;

    public OldSchoolUi(IModel theModel, IController theController)
    {
        this.theModel = theModel;
        this.theModel.RegisterView(this);
        this.theController = theController;
      //  initialize();
        //this.theModel.NotifyViews();
    }


    @Override
    public void Update(String newText) {
        text_screen.setText(newText);
    }
}
