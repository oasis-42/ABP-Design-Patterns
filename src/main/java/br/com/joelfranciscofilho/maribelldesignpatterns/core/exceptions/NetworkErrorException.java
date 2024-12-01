package br.com.joelfranciscofilho.maribelldesignpatterns.core.exceptions;

public class NetworkErrorException extends Exception {
    public NetworkErrorException(String message) {
        super("Network error: " + message);
    }
}
