package au.net.contal.atmchallenge.model;

import lombok.Data;

import java.util.List;

@Data
public class WithdrawResult {
    private Transaction transaction;
    private boolean result;
    private String message;
    private List<BankNote> availableBankNotes;
}
