package au.net.contal.atmchallenge.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulatorResetResult {
    private String message;
    private Map<String, BankNote> availableBankNotes;
}
