package com.medvid.andrii.diplomawork.util;

public class ObjectWrapper<T> {
    public T wrapperValue;

    public ObjectWrapper(T wrapperValue) {
        this.wrapperValue = wrapperValue;
    }

    public T getWrapperValue() {
        return wrapperValue;
    }

    public void setWrapperValue(T wrapperValue) {
        this.wrapperValue = wrapperValue;
    }
}
