package code.interfaces;

import code.KeyboardType;

public interface IModel {

    void makePhoneCall();
    void endCall();
    void sendTextMessage();

    String getDraft();
    void saveDraft();

    String getTextMessage();
    void setTextMessage(String msg);
    String getPhoneNumber();
    void setPhoneNumber(String nmbr);

    void clearData();
    void deleteDraft();

    void setKeyboardType(KeyboardType type);

    void RegisterView(IView Target);
    void NotifyViews(String text);
    void NotifyKeyboard();


}
