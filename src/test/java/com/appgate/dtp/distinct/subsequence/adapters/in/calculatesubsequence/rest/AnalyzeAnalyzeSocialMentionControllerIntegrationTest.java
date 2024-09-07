package com.appgate.dtp.distinct.subsequence.adapters.in.calculatesubsequence.rest;

import com.appgate.dtp.distinct.subsequence.adapters.in.shared.TestUtil;
import com.appgate.dtp.distinct.subsequence.application.calculatesubsequence.CalculateSubsequenceUseCase;
import com.appgate.dtp.distinct.subsequence.domain.calculatesubsequence.SubsequencesNumber;
import com.appgate.dtp.shared.config.IntegrationTest;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CalculateSubsequenceController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName("Tests for calculate subsequence controller")
@Tag(IntegrationTest.TAG)
class AnalyzeAnalyzeSocialMentionControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    private SubsequencesNumber mockedSubsequencesNumber;
    @MockBean
    private CalculateSubsequenceUseCase useCase;

    private String urlRequest;
    private String requestBody;
    private int responseStatus;

    @BeforeEach
    void setup() {
        this.mockedSubsequencesNumber = new SubsequencesNumber(1);
    }

    @ParameterizedTest
    @DisplayName("""
        Given a valid event request for calculate subsequence,
        When the user call the rest endpoint with POST
        Should retrieve 201 Ok with the risk level
        """)
    @CsvSource({
        "sequence, subsequenceToSearch",
        "rabbbit, rabbit",
        "babgbag, bag",
    })
    void shouldAnalyzingSocialMentionWhenHasAValidRequest(String sequence,
                                                          String subsequenceToSearch) throws Exception {
        givenUrlRequestParams();
        givenAValidRequestBody(sequence, subsequenceToSearch);
        givenUseCaseExecuteIsOk();
        whenRestEndpointIsCalled();
        thenStatusIs(HttpStatus.CREATED);
    }

    @ParameterizedTest
    @DisplayName("""
        Given a valid event request for calculating subsequence,
        When the user call the rest endpoint with POST
        Should retrieve 404 Bad Request
        """)
    @CsvSource({
        " , subsequenceToSearch",
        "sequence, ",
    })
    void shouldReturnABadRequestWhenHasAInvalidRequest(String sequence,
                                                       String subsequenceToSearch) throws Exception {
        givenUrlRequestParams();
        givenAValidRequestBody(sequence, subsequenceToSearch);
        whenRestEndpointIsCalled();
        thenStatusIs(HttpStatus.BAD_REQUEST);
    }

    @ParameterizedTest
    @DisplayName("""
        Given a valid event request for calculating subsequence,
        When the user call the rest endpoint with POST
        Should retrieve 409 Conflict
        """)
    @CsvSource({
        "'', subsequenceToSearch",
    })
    void shouldReturnAConflictWhenHasAInvalidRequest(String sequence,
                                                       String subsequenceToSearch) throws Exception {
        givenUrlRequestParams();
        givenAValidRequestBody(sequence, subsequenceToSearch);
        whenRestEndpointIsCalled();
        thenStatusIs(HttpStatus.CONFLICT);
    }

    @Test
    @DisplayName("""
        Given a valid event request for analyzing social mention,
        When the user call the rest endpoint with POST
        Should retrieve 500 Internal Error if something fail
        """)
    void shouldReturnAInternalErrorWhenSomethingFail() throws Exception {
        givenUrlRequestParams();
        givenAValidRequestBody("account", "2020-01-01");
        givenUseCaseExecuteIsFailed();
        whenRestEndpointIsCalled();
        thenStatusIs(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void givenUrlRequestParams() {
        this.urlRequest = "/api/v1/calculate-subsequence";
    }

    private void givenAValidRequestBody(String sequence,
                                        String subsequenceToSearch) {
        this.requestBody = TestUtil.buildRequestBodyAsJSON(sequence, subsequenceToSearch);
    }

    private void whenRestEndpointIsCalled() throws Exception {

        var result = mockMvc
            .perform(post(urlRequest)
                .content(this.requestBody)
                .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE))
            .andReturn();
        responseStatus = result.getResponse().getStatus();
    }

    private void givenUseCaseExecuteIsOk() {
        Mockito.when(this.useCase.execute(any()))
            .thenReturn(this.mockedSubsequencesNumber);
    }

    private void givenUseCaseExecuteIsFailed() {
        Mockito.when(this.useCase.execute(any()))
            .thenThrow(new RuntimeException("Launching exception"));
    }

    private void thenStatusIs(HttpStatus httpStatus) {
        Assertions.assertEquals(httpStatus.value(), this.responseStatus);
    }
}
