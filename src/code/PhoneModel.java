package code;

import code.interfaces.IModel;
import code.interfaces.IView;

import java.util.HashMap;
import java.util.LinkedList;

public class PhoneModel implements IModel {

    @Override
    public void makePhoneCall() {
        System.out.println("phone call to number: " + currentPhoneNumber);
    }

    public void endCall(){
        System.out.println("phone call ended.");
    }

    @Override
    public void sendTextMessage() {
        System.out.println("message to number: " + currentPhoneNumber + ": " + currentTextMessage);
    }

    @Override
    public void updateNumber(String number) {
        this.currentPhoneNumber = number;
        NotifyViewsPhone();
    }

    @Override
    public void updateMessage(String message) {
        this.currentTextMessage = message;
        NotifyViewsText();
    }

    @Override
    public String getDraft(String phoneNumber) {
        String draft = Drafts.get(phoneNumber);
        if(!draft.isEmpty())
            return draft;
        else
            return "";

    }


    public void saveDraft(){
        Drafts.put(currentPhoneNumber, currentTextMessage);
    }

    void deleteDraft(){
        Drafts.remove(currentPhoneNumber);
    }

    private int theState = 0;

    private String currentPhoneNumber;
    private String currentTextMessage;

    private LinkedList<IView> Views = new LinkedList<IView>();

    // drafts will be saved as a phonenumber-textmessage pair
    private HashMap<String,String> Drafts = new HashMap<>();

    public void RegisterView(IView Target)
    {
        this.Views.add(Target);
    }

    private void NotifyViewsPhone(){
        for(IView Current : Views)
            Current.Update(currentPhoneNumber);

    }

    private void NotifyViewsText()
    {
        for(IView Current : Views)
            Current.Update(currentTextMessage);
    }

    //TODO
    public void UpdateState()
    {
        this.theState++;
    }

}
