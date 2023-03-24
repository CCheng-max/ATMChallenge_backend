package au.net.contal.atmchallenge.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WithdrawResult {
    private Transaction transaction;
    private boolean result;
    private String message;
    private Map<String,BankNote> availableBankNotes;
}
