package code;

public abstract class StateBase {
    abstract void handlePhoneButton(Context context);
    abstract void handleHangupButton(Context context);
    abstract void handleStarButton(Context context);
    abstract void handlePoundButton(Context context);

    abstract void handleOtherButton(Context context, String button);

    public void handleButton(Context context, String button){
        switch (button){
            case "*": handleStarButton(context); break;
            case "#": handlePoundButton(context); break;
            case "phone": handlePhoneButton(context);break;
            case "hangup": handleHangupButton(context); break;
            default:
                handleOtherButton(context, button);
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
        context.Model.updateMessage(context.Model.getDraft());
        if(draft.isEmpty()){

        }
    }

    @Override
    public void handlePoundButton(Context context) {

    }

    @Override
    void handleOtherButton(Context context, String button) {

    }
}

class StateCalling extends StateBase
{

    @Override
    public void handlePhoneButton(Context context) {

    }

    @Override
    public void handleHangupButton(Context context) {

    }

    @Override
    public void handleStarButton(Context context) {

    }

    @Override
    public void handlePoundButton(Context context) {

    }
}
class StateMessaging extends StateBase
{
    @Override
    public void handlePhoneButton(Context context) {

    }

    @Override
    public void handleHangupButton(Context context) {

    }

    @Override
    public void handleStarButton(Context context) {

    }

    @Override
    public void handlePoundButton(Context context) {

    }
}