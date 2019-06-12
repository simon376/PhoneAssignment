package code;

import code.interfaces.IController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO: Fuse with OldschoolPhoneController? but then i'd have one controller for every button...
public class ButtonClickListener implements ActionListener
{
    private String value;
    private IController theController;
//TODO: das muss doch in den Controller, weil er global überwachen muss welcher button zuletzt gedrückt wurde,
// nicht jeder listener für jeden button einzeln
//    private JButton lastButtonPressed;
//    private int buttonCounter;
//
//    private long timerStart;
//    private static final long MAX_TIME_DIFF = 500;

    public ButtonClickListener(IController theController, String value)
    {
        this.theController = theController;
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();
        //TODO groß-klein-schreibung funktioniert erst ab zweitem klick
        theController.HandleButtonClick(buttonPressed, value);
    }

}
