package au.net.contal.atmchallenge.services;

import au.net.contal.atmchallenge.exception.ATMException;
import au.net.contal.atmchallenge.exception.ATMNotInitializedException;
import au.net.contal.atmchallenge.model.BankNote;
import au.net.contal.atmchallenge.model.Transaction;
import au.net.contal.atmchallenge.model.WithdrawalRequest;
import au.net.contal.atmchallenge.utils.ATMSimulator;
import au.net.contal.atmchallenge.utils.I18N;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ATMSimulatorService {
    ATMSimulator atmSimulator;
    public synchronized Transaction withdrawal( WithdrawalRequest withdrawalRequest) throws ATMException{
        if(!isInitialed()){
            throw new ATMNotInitializedException();
        }
        List<BankNote> withdrawBankNotes = atmSimulator.withdraw(withdrawalRequest.getAmount());
        return new Transaction(withdrawalRequest.getAmount(), withdrawBankNotes);
    }
    public synchronized boolean isInitialed(){
        return atmSimulator != null;
    }
    public synchronized boolean initialATMSimulator(List<BankNote> initialBankNotes) throws ATMException {
        if(initialBankNotes == null ){
            throw new ATMException(I18N.INITIAL_BANKNOTES_ILLEGAL);
        }
        atmSimulator = new ATMSimulator(initialBankNotes);
        return true;
    }
    public synchronized List<BankNote> availableBankNotes() throws ATMNotInitializedException{
        if(!isInitialed()){
            throw new ATMNotInitializedException();
        }
        return atmSimulator.availableBankNotes();
    }
}
