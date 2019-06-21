package code.model;

import code.KeyboardType;
import code.interfaces.IModel;
import code.interfaces.IView;

import java.util.*;

public class PhoneModel implements IModel {

    private static final int MAX_TEXT_LENGTH = 160;
    private String currentPhoneNumber = "";
    private String currentTextMessage = "";

    private KeyboardType eKeyboardType = KeyboardType.HIDDEN;

    private final LinkedList<IView> Views = new LinkedList<>();

    // drafts will be saved as a phone number - text message pair
    private final HashMap<String,String> Drafts = new HashMap<>();


    // makes phone call to current number
    @Override
    public void makePhoneCall() {
        System.out.println("phone call to number: " + currentPhoneNumber);
    }

    // ends phone call and deletes entered data
    @Override
    public void endCall(){
        System.out.println("phone call ended.");
        clearData();
    }

    // sends text message to current phone number, deletes draft and text fields
    @Override
    public void sendTextMessage() {
        System.out.println("message to number: " + currentPhoneNumber + ": " + currentTextMessage);
        deleteDraft();
        clearData();
    }

    // returns draft for current phone number
    @Override
    public String getDraft() {
        return Drafts.get(currentPhoneNumber);
    }

    // saves current text message as draft for current phone number
    public void saveDraft(){
        Drafts.put(currentPhoneNumber, currentTextMessage);
        System.out.println("saved draft: " + currentTextMessage + " to: " + currentPhoneNumber);
        clearData();
    }

    // returns current message text
    @Override
    public String getTextMessage() {
        if(currentTextMessage == null)
            return "";
        else
            return currentTextMessage;
    }

    // if parameter msg isn't null, the first 160 characters are saved as current text message.
    // All Views are notified of the changed data
    @Override
    public void setTextMessage(String msg) {
        if(msg != null){
            if(!msg.isEmpty() && msg.length() > MAX_TEXT_LENGTH)
                msg = msg.substring(0, MAX_TEXT_LENGTH);
            currentTextMessage = msg;
            NotifyViews(currentTextMessage);
        }
    }

    // returns current phone number
    @Override
    public String getPhoneNumber() {
        if(currentPhoneNumber == null)
            return "";
        else
            return currentPhoneNumber;
    }

    // saves parameter nmbr as current phone number and notifies all views of the changed data.
    @Override
    public void setPhoneNumber(String nmbr) {
        currentPhoneNumber = nmbr;
        NotifyViews(currentPhoneNumber);
    }

    // sets enum keyboard type and notifies all observing views of the changed value.
    @Override
    public void setKeyboardType(KeyboardType type) {
        eKeyboardType = type;
        NotifyKeyboard();
    }

    // clears current text message & phone number and notifies all views of the changed data.
    @Override
    public void clearData() {
        currentTextMessage = "";
        currentPhoneNumber = "";
        NotifyViews("");
    }

    // deletes the draft for the current phone number.
    public void deleteDraft(){
        Drafts.remove(currentPhoneNumber);
    }

    public void RegisterView(IView Target)
    {
        this.Views.add(Target);
    }

    public void NotifyViews(String text){
        for(IView Current : Views)
            Current.UpdateText(text);
    }

    public void NotifyKeyboard(){
        for(IView Current : Views)
            Current.UpdateKeyboard(eKeyboardType);
    }

}
