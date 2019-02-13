package pl.wiacekp.genetic.dto;

import pl.wiacekp.genetic.enums.InputEnum;

public class InputDto {
    private InputEnum up;
    private InputEnum down;
    private InputEnum left;
    private InputEnum right;
    private InputEnum current;

    public InputDto() {
    }

    public InputDto(InputEnum up, InputEnum down, InputEnum left, InputEnum right, InputEnum current) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
        this.current = current;
    }

    public InputEnum getUp() {
        return up;
    }

    public void setUp(InputEnum up) {
        this.up = up;
    }

    public InputEnum getDown() {
        return down;
    }

    public void setDown(InputEnum down) {
        this.down = down;
    }

    public InputEnum getLeft() {
        return left;
    }

    public void setLeft(InputEnum left) {
        this.left = left;
    }

    public InputEnum getRight() {
        return right;
    }

    public void setRight(InputEnum right) {
        this.right = right;
    }

    public InputEnum getCurrent() {
        return current;
    }

    public void setCurrent(InputEnum current) {
        this.current = current;
    }
}
