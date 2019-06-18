package code.interfaces;

import code.KeyboardType;

public interface IView {
    void UpdateText(String newText);
    void UpdateKeyboard(KeyboardType type);
}
