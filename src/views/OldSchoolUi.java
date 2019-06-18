package views;

import code.ButtonClickListener;
import code.KeyboardType;
import code.interfaces.IController;
import code.interfaces.IModel;
import code.interfaces.IView;

import javax.swing.*;

public class OldSchoolUi implements IView {
    private JPanel panelScreen;
    private JPanel panelKeyboard;
    private JButton Button1;
    private JButton Button2;
    private JButton Button3;
    private JButton Button4;
    private JButton Button5;
    private JButton Button6;
    private JButton Button7;
    private JButton Button8;
    private JButton Button9;
    private JButton ButtonStar;
    private JButton Button0;
    private JButton ButtonPound;
    private JTextArea textScreen;
    private JPanel panelCallButtons;
    private JButton ButtonPhone;
    private JButton ButtonHangup;
    private JPanel panelPhone;

    public JPanel getPanelPhone() {
        return panelPhone;
    }

    private IController theController;
    private IModel theModel;

    public OldSchoolUi(IModel theModel, IController theController)
    {
        this.theModel = theModel;
        this.theModel.RegisterView(this);
        this.theController = theController;
        initialize();
    }

    private void initialize() {
        Button0.addActionListener(new ButtonClickListener(theController, "0"));
        Button1.addActionListener(new ButtonClickListener(theController, "1"));
        Button2.addActionListener(new ButtonClickListener(theController, "2"));
        Button3.addActionListener(new ButtonClickListener(theController, "3"));
        Button4.addActionListener(new ButtonClickListener(theController, "4"));
        Button5.addActionListener(new ButtonClickListener(theController, "5"));
        Button6.addActionListener(new ButtonClickListener(theController, "6"));
        Button7.addActionListener(new ButtonClickListener(theController, "7"));
        Button8.addActionListener(new ButtonClickListener(theController, "8"));
        Button9.addActionListener(new ButtonClickListener(theController, "9"));
        ButtonStar.addActionListener(new ButtonClickListener(theController, "*"));
        ButtonPound.addActionListener(new ButtonClickListener(theController, "#"));
        ButtonPhone.addActionListener(new ButtonClickListener(theController, "phone"));
        ButtonHangup.addActionListener(new ButtonClickListener(theController, "hangup"));
    }


    @Override
    public void UpdateText(String newText) {
        textScreen.setText(newText);
    }

    @Override
    public void UpdateKeyboard(KeyboardType type) {
        // does not do anything for this phone type
    }

}
