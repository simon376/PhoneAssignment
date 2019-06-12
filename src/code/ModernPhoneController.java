package code;

import code.interfaces.IController;
import code.interfaces.IModel;

import javax.swing.*;

public class ModernPhoneController extends Context implements IController {
    private IModel theModel;
    private KeyMap theKeyMap;

    private JButton lastButtonPressed;
    private int buttonCounter;

    private long timerStart;
    private static final long MAX_TIME_DIFF = 500;


    public ModernPhoneController(StateBase stateBase, IModel theModel)
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


class StateStartModern extends StateBase
{

    @Override
    void handleActionButton(Context context, int timesPressed) {
        //TODO: show numkeyboard
        context.Model.SwitchKeyboardType(KeyboardType.NUM);
        context.State = new StateDialingModern();
    }
}

class StateDialingModern extends StateBase
{

    @Override
    public void handlePhoneButton(Context context) {
        // make phone call, switch State
        context.Model.makePhoneCall();
        context.State = new StateCallingModern();
    }

    @Override
    void handleOtherButton(Context context, String button, int timesPressed) {
        //just directly use the numbers, timesPressed is ignored
        context.Model.setPhoneNumber( context.Model.getPhoneNumber().concat(button));
    }

    @Override
    void handleActionButton(Context context, int timesPressed) {
        //somehow detect double-click
        // show qwerty keyboard,

        if(timesPressed == 1){
            // start messaging
            //try to fetch draft if one exists
            context.Model.setTextMessage( context.Model.getDraft());

            context.Model.SwitchKeyboardType(KeyboardType.QWERTY);
            context.State = new StateMessagingModern();
        }
        else if(timesPressed == 2){
            context.Model.deleteDraft(); // try to delete draft if existing
            context.Model.setTextMessage("");
            context.Model.SwitchKeyboardType(KeyboardType.QWERTY);
            context.State = new StateMessagingModern();
        }

    }
}

class StateCallingModern extends StateBase
{

    @Override
    void handlePhoneButton(Context context) {
        context.Model.endCall();
        //TODO: change interface (red button?)
        context.Model.SwitchKeyboardType(KeyboardType.HIDDEN);
        context.State = new StateStartModern();
    }

    // all other buttons are disabled
}
class StateMessagingModern extends StateBase
{
    enum eMessageCase { LOWERCASE, UPPERCASE};
    eMessageCase messageCase;

    StateMessagingModern() { messageCase = eMessageCase.LOWERCASE;};

    @Override
    public void handleSendButton(Context context) {
        context.Model.sendTextMessage();
        context.Model.SwitchKeyboardType(KeyboardType.HIDDEN);
        context.State = new StateStartModern();
    }

    @Override
    public void handleDraftButton(Context context) {
        context.Model.saveDraft();
        context.Model.SwitchKeyboardType(KeyboardType.HIDDEN);
        context.State = new StateStartModern();
    }

    @Override
    public void handleShiftButton(Context context) {
        if(messageCase == eMessageCase.LOWERCASE)
            messageCase = eMessageCase.UPPERCASE;
        else
            messageCase = eMessageCase.LOWERCASE;
    }


    @Override
    void handleOtherButton(Context context, String button, int timesPressed) {
        String msg = context.Model.getTextMessage();
        if(messageCase == eMessageCase.UPPERCASE)
            msg = msg.concat(button.toUpperCase());
        else
            msg = msg.concat(button.toLowerCase());
        context.Model.setTextMessage(msg);

    }
}

