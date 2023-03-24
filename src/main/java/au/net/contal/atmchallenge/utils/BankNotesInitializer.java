package au.net.contal.atmchallenge.utils;

import au.net.contal.atmchallenge.model.BankNote;

import java.util.ArrayList;
import java.util.List;

public class BankNotesInitializer {
    public static List<BankNote> initBankNotes(){
        List<BankNote> bankNotes =new ArrayList<>();
        bankNotes.add(new BankNote(1,100));
        bankNotes.add(new BankNote(2,100));
        bankNotes.add(new BankNote(5,100));
        bankNotes.add(new BankNote(10,100));
        bankNotes.add(new BankNote(20,100));
        bankNotes.add(new BankNote(50,100));
        bankNotes.add(new BankNote(100,100));
        return bankNotes;
    }
}
