package code.interfaces;

public interface IModel {

    void RegisterView(IView Target);
    void UpdateState();

    void makePhoneCall();
    void endCall();
    void sendTextMessage();

    void updateNumber(String number);
    void updateMessage(String message);

    String getDraft(String phoneNumber);
}
