package code;

public abstract class StateBase {
    abstract void handlePhoneButton(Context context);
    abstract void handleHangupButton(Context context);
    abstract void handleStarButton(Context context);
    abstract void handlePoundButton(Context context);

    abstract void handleOtherButton(Context context, String button, int timesPressed);

    public void handleButton(Context context, String button, int timesPressed){
        switch (button){
            case "*": handleStarButton(context); break;
            case "#": handlePoundButton(context); break;
            case "phone": handlePhoneButton(context);break;
            case "hangup": handleHangupButton(context); break;
            default:
                handleOtherButton(context, button, timesPressed);
                //speichere Nummer oder Nachricht
        }
    };
}


class StateDialing extends StateBase
{

    @Override
    public void handlePhoneButton(Context context) {
        // make phone call, switch State
        context.Model.makePhoneCall();
        context.State = new StateCalling();
    }

    @Override
    public void handleHangupButton(Context context) {
        // do nothing
    }

    @Override
    public void handleStarButton(Context context) {
        // start messaging
        //try to fetch draft if one exists
        context.Model.setTextMessage( context.Model.getDraft());
        context.State = new StateMessaging();
    }

    @Override
    public void handlePoundButton(Context context) {
        // do nothing
    }

    @Override
    void handleOtherButton(Context context, String button, int timesPressed) {
        //just directly use the numbers, timesPressed is ignored
        context.Model.setPhoneNumber( context.Model.getPhoneNumber().concat(button));
    }
}

class StateCalling extends StateBase
{

    @Override
    public void handlePhoneButton(Context context) {
        //do nothing, they're all deactivated
    }

    @Override
    public void handleHangupButton(Context context) {
        context.Model.endCall();
        //TODO: change interface (red button?)
        context.State = new StateDialing();
    }

    @Override
    public void handleStarButton(Context context) {
        //do nothing, they're all deactivated
    }

    @Override
    public void handlePoundButton(Context context) {
        //do nothing, they're all deactivated
    }

    @Override
    void handleOtherButton(Context context, String button, int timesPressed) {
        //do nothing, they're all deactivated
    }
}
class StateMessaging extends StateBase
{
    enum eMessageCase { LOWERCASE, UPPERCASE};
    eMessageCase messageCase;

    StateMessaging() { messageCase = eMessageCase.LOWERCASE;};

    @Override
    public void handlePhoneButton(Context context) {
        context.Model.sendTextMessage();
        context.State = new StateDialing();
    }

    @Override
    public void handleHangupButton(Context context) {
        context.Model.saveDraft();
        context.State = new StateDialing();
    }

    @Override
    public void handleStarButton(Context context) {
        // do nothing
    }

    @Override
    public void handlePoundButton(Context context) {
        if(messageCase == eMessageCase.LOWERCASE)
            messageCase = eMessageCase.UPPERCASE;
        else
            messageCase = eMessageCase.LOWERCASE;
    }


    @Override
    void handleOtherButton(Context context, String button, int timesPressed) {

        //TODO: replace last letter with correct one
        if(timesPressed > 0){
            String parsedKey = KeyMap.getString(button, timesPressed);
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