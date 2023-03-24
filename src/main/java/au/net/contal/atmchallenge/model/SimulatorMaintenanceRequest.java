package au.net.contal.atmchallenge.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class SimulatorMaintenanceRequest {
    private Map<String,BankNote> initialBankNotes;
}
