package code;

import code.interfaces.IModel;

// Context according to State Pattern, implemented by Controllers
abstract class Context {

    StateBase State;
    final IModel Model;

    Context(StateBase state, IModel model)
    {
        State = state;
        Model = model;
    }

}
