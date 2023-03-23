package au.net.contal.atmchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankNote implements  Cloneable {
    private int bankNoteValue;
    private int bankNoteCount;
    public BankNote clone(){
        return new BankNote(bankNoteValue, bankNoteCount);
    }
}
