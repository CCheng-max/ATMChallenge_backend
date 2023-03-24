package au.net.contal.atmchallenge.utils;

public class I18N {
    public static final String INVALID_WITHDRAW_AMOUNT ="We're sorry, but the withdraw amount is invalid.";
    public static final String INITIAL_BANKNOTES_SUCCESS = "The ATM simulator has been initialized successfully.";
    public static final String UNAVAILABLE_BANKNOTE_COMBINATION_ERROR ="We're sorry, but the requested transaction cannot be completed due to unavailable banknote combination. Please try another amount or contact your bank for further assistance.";
    public static final String NOT_ENOUGH_BANKNOTES_ERROR="We're sorry, but the requested transaction cannot be completed due to insufficient banknotes in the ATM. Please try another amount or contact your bank for further assistance";
    public static final String ATM_NOT_INITIALIZED = "We're sorry, but the ATM simulator has not been initialized yet. Please initialize the simulator with the initial banknote values and counts before attempting any transactions.";
    public static final String INITIAL_BANKNOTES_ILLEGAL = "We're sorry, but the ATM simulator cannot be initialized because the request does not include valid initial banknote values and counts. Please provide this information and try again.";
    public static final String WITHDRAW_SUCCESSFULLY = "Congratulations, your withdrawal request has been successfully completed. Please take your cash from the dispenser. Thank you for using our ATM.";
}
