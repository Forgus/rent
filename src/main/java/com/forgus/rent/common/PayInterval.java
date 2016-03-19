package com.forgus.rent.common;

/**
 * 交租间隔（押一付一、押一付三）
 * Created by cwb on 2016/3/19.
 */
public enum PayInterval {

    ONE_MONTH(1),
    THREE_MONTH(3);

    private int value;

    PayInterval(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
