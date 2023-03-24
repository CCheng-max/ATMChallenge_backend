package au.net.contal.atmchallenge.controller;

import au.net.contal.atmchallenge.exception.ATMException;
import au.net.contal.atmchallenge.model.BankNote;
import au.net.contal.atmchallenge.model.SimulatorMaintenanceRequest;
import au.net.contal.atmchallenge.model.SimulatorResetResult;
import au.net.contal.atmchallenge.services.ATMSimulatorService;
import au.net.contal.atmchallenge.utils.I18N;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/maintenance/banknotes")
public class ATMMaintenanceController {
    @Autowired
    ATMSimulatorService atmService;

    @PostMapping(value = "/reset")
    public ResponseEntity<SimulatorResetResult> resetBankNotes(@RequestBody SimulatorMaintenanceRequest simulatorMaintenanceRequest ){
        try{
            Map<String, BankNote> availableBankNotes = atmService.initialATMSimulator(simulatorMaintenanceRequest.getInitialBankNotes());
            return ResponseEntity.status(HttpStatus.OK).body(new SimulatorResetResult(I18N.INITIAL_BANKNOTES_SUCCESS, availableBankNotes));
        }catch (ATMException atmException){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimulatorResetResult(atmException.getMessage(), Collections.emptyMap()));
        }
    }
    @GetMapping(value ="")
    public ResponseEntity<Map<String,BankNote>> availableBankNotes(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(atmService.availableBankNotes());
        } catch (ATMException e) {
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(null);
        }
    }
}
