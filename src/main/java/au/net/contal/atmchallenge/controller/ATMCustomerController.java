package au.net.contal.atmchallenge.controller;

import au.net.contal.atmchallenge.exception.ATMException;
import au.net.contal.atmchallenge.exception.NotEnoughBankNotesException;
import au.net.contal.atmchallenge.exception.UnavailableBankNoteCombinationException;
import au.net.contal.atmchallenge.model.Transaction;
import au.net.contal.atmchallenge.model.WithdrawResult;
import au.net.contal.atmchallenge.model.WithdrawalRequest;
import au.net.contal.atmchallenge.services.ATMSimulatorService;
import au.net.contal.atmchallenge.utils.I18N;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
public class ATMCustomerController {
    @Autowired
    ATMSimulatorService atmService;

    @PutMapping(value = "")
    public ResponseEntity<WithdrawResult> withdrawal(@RequestBody WithdrawalRequest withdrawalRequest ){
        WithdrawResult withdrawResult = new WithdrawResult();
        try{
            Transaction withdrawalTransaction = atmService.withdrawal( withdrawalRequest);
            withdrawResult.setResult(true);
            withdrawResult.setTransaction(withdrawalTransaction);
            withdrawResult.setMessage(I18N.WITHDRAW_SUCCESSFULLY);
            withdrawResult.setAvailableBankNotes(atmService.availableBankNotes());
            return ResponseEntity.status(HttpStatus.OK).body(withdrawResult);
        }catch (NotEnoughBankNotesException|UnavailableBankNoteCombinationException e){
            withdrawResult.setResult(false);
            withdrawResult.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(withdrawResult);
        }catch (ATMException e){
            withdrawResult.setResult(false);
            withdrawResult.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(withdrawResult);
        }
    }

}
