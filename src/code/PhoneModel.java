package code;

import code.interfaces.IModel;
import code.interfaces.IView;

import java.util.*;

public class PhoneModel implements IModel {

    @Override
    public void makePhoneCall() {
        System.out.println("phone call to number: " + currentPhoneNumber);
    }

    public void endCall(){
        System.out.println("phone call ended.");
        clearData();
    }

    @Override
    public void sendTextMessage() {
        System.out.println("message to number: " + currentPhoneNumber + ": " + currentTextMessage);
        deleteDraft();
        clearData();
    }


    @Override
    public String getDraft() {
        return Drafts.get(currentPhoneNumber);
    }


    public void saveDraft(){
        Drafts.put(currentPhoneNumber, currentTextMessage);
        System.out.println("saved draft: " + currentTextMessage + " to: " + currentPhoneNumber);
        clearData();
    }

    @Override
    public String getTextMessage() {
        if(currentTextMessage == null)
            return "";
        else
            return currentTextMessage;
    }

    @Override
    public void setTextMessage(String msg) {
        if(msg != null){
            if(!msg.isEmpty() && msg.length() > MAX_TEXT_LENGTH)
                msg = msg.substring(0, MAX_TEXT_LENGTH);
            currentTextMessage = msg;
            NotifyViews(currentTextMessage);

        }
    }

    @Override
    public String getPhoneNumber() {
        if(currentPhoneNumber == null)
            return "";
        else
            return currentPhoneNumber;
    }

    @Override
    public void setPhoneNumber(String nmbr) {
        currentPhoneNumber = nmbr;
        NotifyViews(currentPhoneNumber);
    }

    @Override
    public void SwitchKeyboardType(KeyboardType type) {
        eKeyboardType = type;
        NotifyKeyboard();
    }

    @Override
    public void clearData() {
        currentTextMessage = "";
        currentPhoneNumber = "";
        NotifyViews("");
    }

    public void deleteDraft(){
        Drafts.remove(currentPhoneNumber);
    }

    private int theState = 0;

//TODO: remove null's from messages & numbers,

    private static final int MAX_TEXT_LENGTH = 160;

    private String currentPhoneNumber = "";
    private String currentTextMessage = "";

    private KeyboardType eKeyboardType = KeyboardType.HIDDEN;

    private LinkedList<IView> Views = new LinkedList<IView>();

    // drafts will be saved as a phonenumber-textmessage pair
    private HashMap<String,String> Drafts = new HashMap<>();


    public void RegisterView(IView Target)
    {
        this.Views.add(Target);
    }

    private void NotifyViews(String text){
        for(IView Current : Views)
            Current.UpdateText(text);
    }

    private void NotifyKeyboard(){
        for(IView Current : Views)
            Current.SwitchKeyboard(eKeyboardType);
    }


    //TODO
    public void UpdateState()
    {
        this.theState++;
    }


}
