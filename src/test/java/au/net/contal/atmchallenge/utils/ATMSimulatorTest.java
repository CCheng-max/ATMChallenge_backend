package au.net.contal.atmchallenge.utils;

import au.net.contal.atmchallenge.exception.ATMException;
import au.net.contal.atmchallenge.exception.NotEnoughBankNotesException;
import au.net.contal.atmchallenge.exception.UnavailableBankNoteCombinationException;
import au.net.contal.atmchallenge.model.BankNote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.joshka.junit.json.params.JsonFileSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ATMSimulatorTest {
    @Test
    void testInitial(){
        MockedStatic<BankNotesInitializer> bankNotesInitializerStatic = Mockito.mockStatic(BankNotesInitializer.class);
        List<BankNote> bankNoteList =new ArrayList<>();
        bankNoteList.add(new BankNote(20,10));
        bankNoteList.add(new BankNote(10,10));
        bankNoteList.add(new BankNote(50, 20));
        bankNotesInitializerStatic.when(() -> BankNotesInitializer.initBankNotes()).thenReturn(bankNoteList);
        ATMSimulator instance = new ATMSimulator();
        assertEquals(50,bankNoteList.get(0).getBankNoteValue() );
    }
    static class WithDrawTest {
        int withdrawAmount;
        List<BankNote> initialBankNotes;
        List<BankNote> expectedWithdrawBankNotes;
        List<BankNote> expectedAvailableBankNotes;

        public int getWithdrawAmount() {
            return withdrawAmount;
        }

        public List<BankNote> getInitialBankNotes() {
            return initialBankNotes;
        }

        public void setInitialBankNotes(List<BankNote> initialBankNotes) {
            this.initialBankNotes = initialBankNotes;
        }

        public void setWithdrawAmount(int withdrawAmount) {
            this.withdrawAmount = withdrawAmount;
        }

        public List<BankNote> getExpectedWithdrawBankNotes() {
            return expectedWithdrawBankNotes;
        }

        public void setExpectedWithdrawBankNotes(List<BankNote> expectedWithdrawBankNotes) {
            this.expectedWithdrawBankNotes = expectedWithdrawBankNotes;
        }

        public List<BankNote> getExpectedAvailableBankNotes() {
            return expectedAvailableBankNotes;
        }

        public void setExpectedAvailableBankNotes(List<BankNote> expectedAvailableBankNotes) {
            this.expectedAvailableBankNotes = expectedAvailableBankNotes;
        }

    }
    @ParameterizedTest
    @JsonFileSource( resources={
            "/data/ATMSimulatorWithDraw20Success.json",
            "/data/ATMSimulatorWithDraw40Success.json",
            "/data/ATMSimulatorWithDraw50Success.json",
            "/data/ATMSimulatorWithDraw70Success.json",
            "/data/ATMSimulatorWithDraw100Success.json",
            "/data/ATMSimulatorWithDraw150Success.json"
    })
    public void test(JsonObject jsonObject) throws JsonProcessingException, ATMException {
        ObjectMapper objectMapper =new ObjectMapper();
        WithDrawTest withDrawTest = objectMapper.readValue(jsonObject.toString(), WithDrawTest.class);
        ATMSimulator atmSimulator = new ATMSimulator(withDrawTest.getInitialBankNotes());
        List<BankNote> bankNotes = atmSimulator.withdraw(withDrawTest.withdrawAmount);
        System.out.println(withDrawTest);
        assertEquals(withDrawTest.expectedAvailableBankNotes,atmSimulator.availableBankNotes());
        assertEquals(withDrawTest.expectedWithdrawBankNotes, bankNotes);
    }
    @ParameterizedTest
    @JsonFileSource( resources={
            "/data/ATMSimulatorWithDraw60Exception.json",
            "/data/ATMSimulatorWithDraw80Exception.json",
            "/data/ATMSimulatorWithDraw110Exception.json",
            "/data/ATMSimulatorWithDraw200Exception.json"
    })
    public void withDrawTestFailedWithUnavailableBankNoteCombinationException(JsonObject jsonObject) throws JsonProcessingException {
        ObjectMapper objectMapper =new ObjectMapper();
        WithDrawTest withDrawTest = objectMapper.readValue(jsonObject.toString(), WithDrawTest.class);
        ATMSimulator atmSimulator = new ATMSimulator(withDrawTest.getInitialBankNotes());
        try {
            atmSimulator.withdraw(withDrawTest.withdrawAmount);
        }catch (Exception e){
            assertEquals(UnavailableBankNoteCombinationException.class, e.getClass());
        }
        assertEquals(withDrawTest.expectedAvailableBankNotes,atmSimulator.availableBankNotes());
    }
    @ParameterizedTest
    @JsonFileSource( resources={
            "/data/ATMSimulatorWithDraw400Exception.json"
    })
    public void withDrawTestFailedWithUnavailableBankNoteCombinationException2( JsonObject jsonObject) throws JsonProcessingException {
        ObjectMapper objectMapper =new ObjectMapper();
        WithDrawTest withDrawTest = objectMapper.readValue(jsonObject.toString(), WithDrawTest.class);
        ATMSimulator atmSimulator = new ATMSimulator(withDrawTest.getInitialBankNotes());
        try {
            atmSimulator.withdraw(withDrawTest.withdrawAmount);
        }catch (Exception e){
            assertEquals(NotEnoughBankNotesException.class, e.getClass());
        }
        assertEquals(withDrawTest.expectedAvailableBankNotes,atmSimulator.availableBankNotes());
    }
}
