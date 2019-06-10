package code;

import code.interfaces.IModel;
import code.interfaces.IView;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public String getDraft() {
        return Drafts.get(currentPhoneNumber);
    }


    public void saveDraft(){
        Drafts.put(currentPhoneNumber, currentTextMessage);
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
        currentTextMessage = msg;
        NotifyViews(currentTextMessage);
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
    public void clearData() {
        currentTextMessage = "";
        currentPhoneNumber = "";
    }

    void deleteDraft(){
        Drafts.remove(currentPhoneNumber);
    }

    private int theState = 0;

//TODO: remove null's from messages & numbers,


    private String currentPhoneNumber;
    private String currentTextMessage;

    private LinkedList<IView> Views = new LinkedList<IView>();

    // drafts will be saved as a phonenumber-textmessage pair
    private HashMap<String,String> Drafts = new HashMap<>();


    public void RegisterView(IView Target)
    {
        this.Views.add(Target);
    }

    private void NotifyViews(String text){
        for(IView Current : Views)
            Current.Update(text);

    }

    //TODO
    public void UpdateState()
    {
        this.theState++;
    }


}
