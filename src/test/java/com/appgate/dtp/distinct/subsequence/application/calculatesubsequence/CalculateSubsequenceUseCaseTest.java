package com.appgate.dtp.distinct.subsequence.application.calculatesubsequence;

import com.appgate.dtp.distinct.subsequence.domain.CalculateSubsequenceCommandMother;
import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.InvalidSequenceLengthException;
import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.SubsequencesNumber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests for calculating subsequence use case")
class CalculateSubsequenceUseCaseTest {

    private CalculateSubsequenceCommand command;
    private CalculateSubsequenceUseCase useCase;
    private SubsequencesNumber response;
    private Exception exception;
    @BeforeEach
    void setup() {
        this.command = null;
        this.useCase = new CalculateSubsequenceUseCaseImpl();
        this.response = null;
        this.exception = null;
    }

    @ParameterizedTest
    @DisplayName("""
            Given a request with valid data
            when calculate subsequence use case is executed
            then must do the calculation
            and return the number of subsequences
        """)
    @CsvSource({
        "rabbbit, rabbit, 3",
        "babgbag, bag, 5",
    })
    void shouldCalculateSubsequence_WhenHasAValidFacebookCommand(String sequence,
                                                                 String subsequenceToSearch, int subsequenceNumber) {
        givenThereIsValidCommand(sequence, subsequenceToSearch);
        whenUseCaseIsExecuted();
        thenThereWasNoException();
        thenSubsequenceNumberResponseWasReturned(subsequenceNumber);
    }

    @Test
    @DisplayName("""
            Given a request with invalid data
            when calculate subsequence case is executed
            then must be throws a invalid length exception
        """)
    void shouldReturnException_WhenHasAValidTweeterCommand() {
        assertThrows(InvalidSequenceLengthException.class, this::givenThereIsInvalidCommand);
    }

    private void givenThereIsValidCommand(String sequence, String subsequenceToSearch) {
        this.command = CalculateSubsequenceCommandMother.validCommand(sequence, subsequenceToSearch);
    }

    private void givenThereIsInvalidCommand() {
        this.command = CalculateSubsequenceCommandMother.invalidCommand();
    }

    private void whenUseCaseIsExecuted() {
        try {
            this.response = this.useCase.execute(this.command);
        } catch (Exception exception) {
            this.exception = exception;
        }
    }

    private void thenThereWasNoException() {
        if (this.exception != null) {
            exception.printStackTrace();
        }
        Assertions.assertNull(this.exception);
    }
    private void thenSubsequenceNumberResponseWasReturned(Integer subsequenceNumber) {
        Assertions.assertNotNull(response);
        Assertions.assertEquals(subsequenceNumber, response.getAmount());
    }
}
