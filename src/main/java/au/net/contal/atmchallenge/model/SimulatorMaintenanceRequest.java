package au.net.contal.atmchallenge.model;

import lombok.Data;

import java.util.List;

@Data
public class SimulatorMaintenanceRequest {
    private List<BankNote> initialBankNotes;
}
