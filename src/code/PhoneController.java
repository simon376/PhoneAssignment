package code;

import code.interfaces.IController;
import code.interfaces.IModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhoneController extends Context implements IController {
    private IModel theModel;
    private KeyMap theKeyMap;

    private JButton lastButtonPressed;
    private int buttonCounter;

    private long timerStart;
    private static final long MAX_TIME_DIFF = 500;


    public PhoneController(StateBase stateBase, IModel theModel)
    {
        super(stateBase, theModel);
        this.theModel = theModel;
        this.theKeyMap = new KeyMap();
        this.timerStart = 0;
        this.buttonCounter = 0;
    }

    public void Update()
    {
        theModel.UpdateState();
    }

    @Override
    public void HandleButtonClick(JButton button, String value) {

        if (lastButtonPressed == button)
        {
            //The same button was clicked two times in a row
            long difference = (System.currentTimeMillis() - timerStart);
            if(difference < MAX_TIME_DIFF)
                buttonCounter++;
        }
        else
        {
            buttonCounter = 0;
        }
        timerStart = System.currentTimeMillis();
        lastButtonPressed = button;

        State.handleButton(this, value, buttonCounter);

    }

}



