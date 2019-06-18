package code;

import code.interfaces.IController;
import code.interfaces.IModel;

import javax.swing.*;

public class OldschoolPhoneController extends Context implements IController {

    private JButton lastButtonPressed;
    private int buttonCounter;

    private long timerStart;
    private static final long MAX_TIME_DIFF = 500;


    OldschoolPhoneController(StateBase stateBase, IModel theModel)
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


class StateDialingOld extends StateBase
{

    @Override
    protected void handlePhoneButton(Context context) {
        // make phone call, switch State
        context.Model.makePhoneCall();
        context.State = new StateCallingOld();
    }

    @Override
    protected void handleStarButton(Context context) {
        //try to fetch draft if one exists
        // start messaging
        context.Model.setTextMessage( context.Model.getDraft());
        context.State = new StateMessagingOld();
    }

    @Override
    protected void handleOtherButton(Context context, String button, int timesPressed) {
        //just directly use the numbers, timesPressed is ignored
        context.Model.setPhoneNumber( context.Model.getPhoneNumber().concat(button));
    }
}

class StateCallingOld extends StateBase
{

    @Override
    protected void handleHangupButton(Context context) {
        context.Model.endCall();
        context.State = new StateDialingOld();
    }

    // all other buttons are disabled
}
class StateMessagingOld extends StateBase
{
    enum eMessageCase { LOWERCASE, UPPERCASE}
    private eMessageCase messageCase;
    private KeyMap theKeyMap;


    StateMessagingOld() { 
        messageCase = eMessageCase.LOWERCASE;
        theKeyMap = KeyMap.getInstance();
    }

    @Override
    protected void handlePhoneButton(Context context) {
        context.Model.sendTextMessage();
        context.State = new StateDialingOld();
    }

    @Override
    protected void handleHangupButton(Context context) {
        context.Model.saveDraft();
        context.State = new StateDialingOld();
    }

    @Override
    protected void handlePoundButton(Context context) {
        if(messageCase == eMessageCase.LOWERCASE)
            messageCase = eMessageCase.UPPERCASE;
        else
            messageCase = eMessageCase.LOWERCASE;
    }


    @Override
    protected void handleOtherButton(Context context, String button, int timesPressed) {

        if(timesPressed > 0){
            String parsedKey = theKeyMap.getString(button, timesPressed);
            if(parsedKey.isEmpty())
                parsedKey = button;

            //remove last character and replace with correct one
            String msg = context.Model.getTextMessage();
            if(msg.length() > 1){
                msg = msg.substring(0, msg.length()-1);
            }

            if(messageCase == eMessageCase.UPPERCASE)
                msg = msg.concat(parsedKey.toUpperCase());
            else
                msg = msg.concat(parsedKey.toLowerCase());
            context.Model.setTextMessage(msg);

        }
        else{
            context.Model.setTextMessage(context.Model.getTextMessage().concat(button));
        }

    }
}


