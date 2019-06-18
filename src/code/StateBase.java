package code;

// abstract class defining methods to handle different button presses.
// the result of the method calls depends on the current state of the application.
// context is passed to all the methods so the State can be changed.
abstract class StateBase {
    protected void handlePhoneButton(Context context) {}

    protected void handleHangupButton(Context context) {}

    protected void handleStarButton(Context context) {}

    protected void handlePoundButton(Context context) {}

    protected void handleActionButton(Context context, int timesPressed) {}

    protected void handleSendButton(Context context) {}

    protected void handleShiftButton(Context context) {}

    protected void handleDraftButton(Context context) {}

    protected void handleOtherButton(Context context, String button, int timesPressed){}

    void handleButton(Context context, String button, int timesPressed){
        switch (button){
            case "*": handleStarButton(context); break;
            case "#": handlePoundButton(context); break;
            case "phone": handlePhoneButton(context);break;
            case "hangup": handleHangupButton(context); break;
            case "send": handleSendButton(context); break;
            case "shift": handleShiftButton(context); break;
            case "draft": handleDraftButton(context); break;
            case "action": handleActionButton(context, timesPressed); break;
            default:
                handleOtherButton(context, button, timesPressed);
        }
    }
}