package code;

import code.interfaces.IController;
import code.interfaces.IModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PhoneController implements IController {
    private IModel theModel;

    public PhoneController(IModel theModel)
    {
        this.theModel = theModel;
    }

    public void Update()
    {
        theModel.UpdateState();
    }

    @Override
    public void HandleButtonClick(String value) {
        // perform action depending on state (Messaging <-> Dialing <-> Calling)
        //TODO: state, case-enum
//        switch (value){
//            case "accept": theModel.makePhoneCall(); break;
//            case "decline": theModel.
//        }
//        if(value == "accept"){
//            theModel.makePhoneCall();
//        }

    }

}



class NumberButtonClickListener implements ActionListener
{
    private String value;
    private IController theController;

    public NumberButtonClickListener(IController theController, String value)
    {
        this.theController = theController;
        this.value = value;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theController.HandleButtonClick(value);
    }

}

