package models;

import enums.ButtonType;

public abstract class Button {
    protected ButtonType buttonType;
    protected boolean isPressed;

    public Button(ButtonType buttonType) {
        this.buttonType = buttonType;
        this.isPressed = false;
    }

    public abstract void press();

    public void reset() {
        this.isPressed = false;
    }

    public boolean isPressed() {
        return isPressed;
    }

    public ButtonType getButtonType() {
        return buttonType;
    }
}
