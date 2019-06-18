package code;

import code.interfaces.IController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener
{
    private String value;
    private IController theController;

    public ButtonClickListener(IController theController, String value)
    {
        this.theController = theController;
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonPressed = (JButton) e.getSource();
        theController.HandleButtonClick(buttonPressed, value);
    }

}
