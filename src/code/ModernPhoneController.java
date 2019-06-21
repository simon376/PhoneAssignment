package code;

import code.interfaces.IController;
import code.interfaces.IModel;

import javax.swing.*;

public class ModernPhoneController extends Context implements IController {

    private JButton lastButtonPressed;
    private int buttonCounter;

    private long timerStart;
    private static final long MAX_TIME_DIFF = 500;


    ModernPhoneController(StateBase stateBase, IModel theModel)
    {
        super(stateBase, theModel);
        this.timerStart = 0;
        this.buttonCounter = 0;
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


class StateStartModern extends StateBase
{

    @Override
    protected void handleActionButton(Context context, int timesPressed) {
        context.Model.setKeyboardType(KeyboardType.DIALING);
        context.State = new StateDialingModern();
    }
}

class StateDialingModern extends StateBase
{

    @Override
    protected void handlePhoneButton(Context context) {
        // make phone call, switch State
        context.Model.makePhoneCall();
        context.Model.setKeyboardType(KeyboardType.CALLING);
        context.State = new StateCallingModern();
    }

    @Override
    protected void handleOtherButton(Context context, String button, int timesPressed) {
        //just directly use the numbers, timesPressed is ignored
        context.Model.setPhoneNumber( context.Model.getPhoneNumber().concat(button));
    }

    @Override
    protected void handleActionButton(Context context, int timesPressed) {
        if(timesPressed == 1){
            //try to fetch draft if one exists
            context.Model.setTextMessage( context.Model.getDraft());
        }
        else if(timesPressed == 2){
            context.Model.deleteDraft(); // try to delete draft if existing
            context.Model.setTextMessage("");
        }
        else
            return;

        // start messaging
        context.Model.setKeyboardType(KeyboardType.QWERTY);
        context.State = new StateMessagingModern();

    }
}

class StateCallingModern extends StateBase
{

    @Override
    protected void handlePhoneButton(Context context) {
        context.Model.endCall();
        context.Model.setKeyboardType(KeyboardType.HIDDEN);
        context.State = new StateStartModern();
    }

    // all other buttons are disabled
}

class StateMessagingModern extends StateBase
{
    enum eMessageCase { LOWERCASE, UPPERCASE}
    private eMessageCase messageCase;

    StateMessagingModern() { messageCase = eMessageCase.LOWERCASE;}

    @Override
    protected void handleSendButton(Context context) {
        context.Model.sendTextMessage();
        context.Model.setKeyboardType(KeyboardType.HIDDEN);
        context.State = new StateStartModern();
    }

    @Override
    protected void handleDraftButton(Context context) {
        context.Model.saveDraft();
        context.Model.setKeyboardType(KeyboardType.HIDDEN);
        context.State = new StateStartModern();
    }

    @Override
    protected void handleShiftButton(Context context) {
        if(messageCase == eMessageCase.LOWERCASE)
            messageCase = eMessageCase.UPPERCASE;
        else
            messageCase = eMessageCase.LOWERCASE;
    }


    @Override
    protected void handleOtherButton(Context context, String button, int timesPressed) {
        // add string to current text message
        String msg = context.Model.getTextMessage();
        if(messageCase == eMessageCase.UPPERCASE)
            msg = msg.concat(button.toUpperCase());
        else
            msg = msg.concat(button.toLowerCase());

        context.Model.setTextMessage(msg);
    }
}

