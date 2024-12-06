package br.com.joelfranciscofilho.maribelldesignpatterns.core;

public abstract class Observer<T> {
    public abstract void update(T event);
}
