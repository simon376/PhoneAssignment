package views;

import code.ButtonClickListener;
import code.interfaces.IController;
import code.interfaces.IModel;
import code.interfaces.IView;

import javax.swing.*;
import java.awt.*;

public class ModernUi implements IView {
    private JPanel panelPhone;
    private JPanel panelKeyboard;
    private JPanel panelKeyBoardQwerty;
    private JPanel panelKeyboardNumerical;
    private JPanel panelScreen;
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
    private JTextArea text_screen;
    private JButton ButtonPhone;
    private JButton ButtonAction;
    private JButton qButton;
    private JButton wButton;
    private JButton eButton;
    private JButton rButton;
    private JButton tButton;
    private JButton yButton;
    private JButton uButton;
    private JButton iButton;
    private JButton oButton;
    private JButton pButton;
    private JButton aButton;
    private JButton dButton;
    private JButton fButton;
    private JButton lButton;
    private JButton kButton;
    private JButton sButton;
    private JButton gButton;
    private JButton hButton;
    private JButton jButton;
    private JButton vButton;
    private JButton cButton;
    private JButton bButton;
    private JButton SHIFTButton;
    private JButton mButton;
    private JButton zButton1;
    private JButton xButton;
    private JButton nButton;
    private JButton SPACEButton;
    private JButton DRAFTButton;
    private JButton SENDButton;

    public JPanel getPanelPhone() {
        return panelPhone;
    }

    private IController theController;
    private IModel theModel;
    final static String NUMPANEL = "Card with Numbers";
    final static String QWERTYPANEL = "Card with Qwerty";
    boolean isNumPad = true;

    public ModernUi(IModel theModel, IController theController)
    {
        this.theModel = theModel;
        this.theModel.RegisterView(this);
        this.theController = theController;
        initializeNumericalKeyboard();

        panelKeyboard.add(panelKeyboardNumerical,NUMPANEL);
        panelKeyboard.add(panelKeyBoardQwerty,QWERTYPANEL);
    }


    private void initializeNumericalKeyboard() {
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
        ButtonAction.addActionListener(new ButtonClickListener(theController, "action"));
    }

    public void switchKeyboard()
    {
        CardLayout layout = (CardLayout) panelKeyboard.getLayout();
        if(isNumPad)
            layout.show(panelKeyboard,QWERTYPANEL);
        else
            layout.show(panelKeyboard,NUMPANEL);
    }

    @Override
    public void Update(String newText) {
        text_screen.setText(newText);
    }
}
