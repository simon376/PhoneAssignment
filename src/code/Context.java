package code;

import code.interfaces.IModel;

public class Context {

    public StateBase State;
    public IModel Model;

    public Context(StateBase state, IModel model)
    {
        State = state;
        Model = model;
        Model.clearData();
    }


//    public void requestButtonHandling(String button) {State.handleButton(this, button);}
//    public void requestPhoneClick(){
//        State.handlePhoneButton(this);
//    }
//    public void requestHangupClick() { State.handleHangupButton(this);}
//    public void requestPoundClick() { State.handlePoundButton(this);}
//    public void requestStarClick() { State.handleStarButton(this);}
}
