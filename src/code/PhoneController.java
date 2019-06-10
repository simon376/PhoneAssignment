package code;

import code.interfaces.IController;
import code.interfaces.IModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhoneController extends Context implements IController {
    private IModel theModel;
    private KeyMap theKeyMap;


    public PhoneController(StateBase stateBase, IModel theModel)
    {
        super(stateBase, theModel);
        this.theModel = theModel;
        this.theKeyMap = new KeyMap();
    }

    public void Update()
    {
        theModel.UpdateState();
    }

    @Override
    public void HandleButtonClick(String value, int timesPressed) {
        State.handleButton(this, value, timesPressed);

    }

}



