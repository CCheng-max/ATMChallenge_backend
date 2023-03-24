package au.net.contal.atmchallenge.services;

import au.net.contal.atmchallenge.exception.ATMException;
import au.net.contal.atmchallenge.exception.ATMNotInitializedException;
import au.net.contal.atmchallenge.model.BankNote;
import au.net.contal.atmchallenge.model.Transaction;
import au.net.contal.atmchallenge.model.WithdrawalRequest;
import au.net.contal.atmchallenge.utils.ATMSimulator;
import au.net.contal.atmchallenge.utils.BankNotesInitializer;
import au.net.contal.atmchallenge.utils.I18N;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ATMSimulatorService {
    ATMSimulator atmSimulator =new ATMSimulator(BankNotesInitializer.initBankNotes());
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
    public synchronized Map<String,BankNote> initialATMSimulator(Map<String,BankNote> initialBankNotes) throws ATMException {
        if(initialBankNotes == null ){
            throw new ATMException(I18N.INITIAL_BANKNOTES_ILLEGAL);
        }
        if(initialBankNotes.values().stream().filter(bankNote -> bankNote.getBankNoteCount()<0).findAny().isPresent()){
            throw new ATMException(I18N.INITIAL_BANKNOTES_ILLEGAL);
        }
        atmSimulator = new ATMSimulator(new ArrayList<>(initialBankNotes.values()));
        return atmSimulator.availableBankNotes();
    }
    public synchronized Map<String,BankNote> availableBankNotes() throws ATMNotInitializedException{
        if(!isInitialed()){
            throw new ATMNotInitializedException();
        }
        return atmSimulator.availableBankNotes();
    }
}
