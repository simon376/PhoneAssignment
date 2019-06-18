package views;

import code.ButtonClickListener;
import code.KeyboardType;
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
    private JTextArea textScreen;
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
    private JButton zButton;
    private JButton xButton;
    private JButton nButton;
    private JButton SPACEButton;
    private JButton DRAFTButton;
    private JButton SENDButton;
    private JPanel panelAction;
    private JPanel panelKeyboardHidden;

    public JPanel getPhonePanel() {
        return panelPhone;
    }

    private IController theController;
    private IModel theModel;
    private final static String NUM_PANEL = "Card with Numbers";
    private final static String QWERTY_PANEL = "Card with Qwerty";
    private final static String HIDDEN_PANEL = "Card with nothing";

    public ModernUi(IModel theModel, IController theController)
    {
        this.theModel = theModel;
        this.theModel.RegisterView(this);
        this.theController = theController;
        initializeNumericalKeyboard();
        initializeQwertyKeyboard();
        panelKeyboard.add(panelKeyboardHidden, HIDDEN_PANEL);
        panelKeyboard.add(panelKeyboardNumerical, NUM_PANEL);
        panelKeyboard.add(panelKeyBoardQwerty, QWERTY_PANEL);
    }

    private void initializeQwertyKeyboard() {
        qButton.addActionListener(new ButtonClickListener(theController, "q"));
        wButton.addActionListener(new ButtonClickListener(theController, "w"));
        eButton.addActionListener(new ButtonClickListener(theController, "e"));
        rButton.addActionListener(new ButtonClickListener(theController, "r"));
        tButton.addActionListener(new ButtonClickListener(theController, "t"));
        yButton.addActionListener(new ButtonClickListener(theController, "y"));
        uButton.addActionListener(new ButtonClickListener(theController, "u"));
        iButton.addActionListener(new ButtonClickListener(theController, "i"));
        oButton.addActionListener(new ButtonClickListener(theController, "o"));
        pButton.addActionListener(new ButtonClickListener(theController, "p"));
        aButton.addActionListener(new ButtonClickListener(theController, "a"));
        dButton.addActionListener(new ButtonClickListener(theController, "d"));
        fButton.addActionListener(new ButtonClickListener(theController, "f"));
        lButton.addActionListener(new ButtonClickListener(theController, "l"));
        kButton.addActionListener(new ButtonClickListener(theController, "k"));
        sButton.addActionListener(new ButtonClickListener(theController, "s"));
        gButton.addActionListener(new ButtonClickListener(theController, "g"));
        hButton.addActionListener(new ButtonClickListener(theController, "h"));
        jButton.addActionListener(new ButtonClickListener(theController, "j"));
        vButton.addActionListener(new ButtonClickListener(theController, "v"));
        cButton.addActionListener(new ButtonClickListener(theController, "c"));
        bButton.addActionListener(new ButtonClickListener(theController, "b"));
        SHIFTButton.addActionListener(new ButtonClickListener(theController, "shift"));
        mButton.addActionListener(new ButtonClickListener(theController, "m"));
        zButton.addActionListener(new ButtonClickListener(theController, "z"));;
        xButton.addActionListener(new ButtonClickListener(theController, "x"));
        nButton.addActionListener(new ButtonClickListener(theController, "n"));
        SPACEButton.addActionListener(new ButtonClickListener(theController, " "));
        DRAFTButton.addActionListener(new ButtonClickListener(theController, "draft"));
        SENDButton.addActionListener(new ButtonClickListener(theController, "send"));

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


    @Override
    public void UpdateText(String newText) {
        textScreen.setText(newText);
    }

    @Override
    public void UpdateKeyboard(KeyboardType type) {
        CardLayout layout = (CardLayout) panelKeyboard.getLayout();
        switch(type){
            case HIDDEN:
                layout.show(panelKeyboard, HIDDEN_PANEL);
                break;
            case NUM:
                layout.show(panelKeyboard, NUM_PANEL);
                break;
            case QWERTY:
                layout.show(panelKeyboard, QWERTY_PANEL);
                break;
        }
    }
}
