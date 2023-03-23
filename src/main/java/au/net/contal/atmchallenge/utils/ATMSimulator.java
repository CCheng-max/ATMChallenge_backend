package au.net.contal.atmchallenge.utils;

import au.net.contal.atmchallenge.exception.ATMException;
import au.net.contal.atmchallenge.exception.NotEnoughBankNotesException;
import au.net.contal.atmchallenge.exception.UnavailableBankNoteCombinationException;
import au.net.contal.atmchallenge.model.BankNote;

import java.util.*;
import java.util.stream.Collectors;

public class ATMSimulator {
    private Map<Integer,BankNote> banknotes =new LinkedHashMap<>();
    public ATMSimulator(){
        this(BankNotesInitializer.initBankNotes());
    }
    public ATMSimulator(List<BankNote> banknoteList){
        banknoteList.sort(Comparator.comparingInt(BankNote::getBankNoteValue).reversed());
        banknoteList.stream().forEach(bankNote -> banknotes.put(bankNote.getBankNoteValue(), bankNote));
    }
    public List<BankNote> withdraw(int amount) throws ATMException {
        int remainAmount = amount;
        int totalAmount = 0;
        List<BankNote> bankNotesToWithdraw =new ArrayList<>();
        for(Map.Entry<Integer, BankNote> bankNoteEntry : banknotes.entrySet()){
            BankNote bankNote = bankNoteEntry.getValue();
            if(bankNote.getBankNoteCount() == 0){
                continue;
            }
            totalAmount += bankNote.getBankNoteCount() * bankNote.getBankNoteValue();
            int notesToWithdraw  =  remainAmount/bankNote.getBankNoteValue() <= bankNote.getBankNoteCount()
                    ? remainAmount/bankNote.getBankNoteValue():bankNote.getBankNoteCount();
            if(notesToWithdraw >0) {
                bankNotesToWithdraw.add(new BankNote(bankNote.getBankNoteValue(), notesToWithdraw));
            }
            remainAmount -= notesToWithdraw * bankNote.getBankNoteValue();
            if(remainAmount == 0){
                break;
            }
        }
        if(remainAmount != 0){
            if( totalAmount < amount) {
                throw new NotEnoughBankNotesException();
            }else{
                throw new UnavailableBankNoteCombinationException();
            }
        }
        reduceBankNoteCount(bankNotesToWithdraw);
        return bankNotesToWithdraw;
    }

    private void reduceBankNoteCount(List<BankNote> bankNotesToWithdraw) {
        bankNotesToWithdraw.stream().forEach(bnWithdraw -> {
            BankNote bankNote = banknotes.get(bnWithdraw.getBankNoteValue());
            bankNote.setBankNoteCount(bankNote.getBankNoteCount() - bnWithdraw.getBankNoteCount());
        });
    }

    public List<BankNote> availableBankNotes(){
        return banknotes.values().stream().map(b->b.clone()).collect(Collectors.toList());
    }
}
