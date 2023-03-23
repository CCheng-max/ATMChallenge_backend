package au.net.contal.atmchallenge.exception;

import au.net.contal.atmchallenge.utils.I18N;

public class UnavailableBankNoteCombinationException extends ATMException{
    public UnavailableBankNoteCombinationException(){
        super(I18N.UNAVAILABLE_BANKNOTE_COMBINATION_ERROR);
    }
}
