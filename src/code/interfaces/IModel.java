package code.interfaces;

import code.KeyboardType;

public interface IModel {

    void RegisterView(IView Target);
    void UpdateState();

    void makePhoneCall();
    void endCall();
    void sendTextMessage();

    String getDraft();
    void saveDraft();

    String getTextMessage();
    void setTextMessage(String msg);
    String getPhoneNumber();
    void setPhoneNumber(String nmbr);

    void SwitchKeyboardType(KeyboardType type);

    void clearData();

    void deleteDraft();
}
