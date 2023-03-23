package au.net.contal.atmchallenge.controller;

import au.net.contal.atmchallenge.exception.ATMException;
import au.net.contal.atmchallenge.model.BankNote;
import au.net.contal.atmchallenge.model.SimulatorMaintenanceRequest;
import au.net.contal.atmchallenge.services.ATMSimulatorService;
import au.net.contal.atmchallenge.utils.I18N;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/maintenance")
public class ATMMaintenanceController {
    @Autowired
    ATMSimulatorService atmService;

    @PostMapping(value = "/reset")
    public ResponseEntity<String> resetBankNotes(@RequestBody SimulatorMaintenanceRequest simulatorMaintenanceRequest ){
        try{
            atmService.initialATMSimulator(simulatorMaintenanceRequest.getInitialBankNotes());
        }catch (ATMException atmException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(atmException.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(I18N.INITIAL_BANKNOTES_SUCCESS);
    }
    @GetMapping(value ="/banknotes")
    public ResponseEntity<List<BankNote>> availableBankNotes(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(atmService.availableBankNotes());
        } catch (ATMException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }
}
