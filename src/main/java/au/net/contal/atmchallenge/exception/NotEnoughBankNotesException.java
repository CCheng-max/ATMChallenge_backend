package au.net.contal.atmchallenge.exception;

import au.net.contal.atmchallenge.utils.I18N;

public class NotEnoughBankNotesException extends ATMException {
    public NotEnoughBankNotesException(){
        super(I18N.NOT_ENOUGH_BANKNOTES_ERROR);
    }

}
