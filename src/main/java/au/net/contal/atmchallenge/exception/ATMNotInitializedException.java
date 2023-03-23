package au.net.contal.atmchallenge.exception;

import au.net.contal.atmchallenge.utils.I18N;

public class ATMNotInitializedException extends ATMException{
    public ATMNotInitializedException(){
        super(I18N.ATM_NOT_INITIALIZED);
    }
}
