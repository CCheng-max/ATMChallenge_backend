package au.net.contal.atmchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private int amount;

    private List<BankNote> bankNotes;
}
