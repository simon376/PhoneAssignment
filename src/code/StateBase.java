package code;

public abstract class StateBase {
    void handlePhoneButton(Context context) {};
    void handleHangupButton(Context context) {};
    void handleStarButton(Context context) {};
    void handlePoundButton(Context context) {};
    void handleActionButton(Context context, int timesPressed) {};
    void handleSendButton(Context context) {};
    void handleShiftButton(Context context) {};
    void handleDraftButton(Context context) {};
    void handleOtherButton(Context context, String button, int timesPressed){};

    public void handleButton(Context context, String button, int timesPressed){
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
                //speichere Nummer oder Nachricht
        }
    };
}