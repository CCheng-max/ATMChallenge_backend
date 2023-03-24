package au.net.contal.atmchallenge.services;

import au.net.contal.atmchallenge.model.BankNote;
import au.net.contal.atmchallenge.utils.I18N;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMSimulatorServiceTest {
    @Test
    public void testInitialATMSimulatorNegativeCount(){
        ATMSimulatorService atmSimulatorService = new ATMSimulatorService();
        try{
            Map<String, BankNote> initialBankNotes =new HashMap<>();
            initialBankNotes.put("AUD100", new BankNote(100, -1));
            atmSimulatorService.initialATMSimulator(initialBankNotes);
        }catch (Exception e){
            assertEquals(I18N.INITIAL_BANKNOTES_ILLEGAL, e.getMessage());
        }
    }
}
