package br.com.fiap.to;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class TesteRapidoTO {

    private Long testId;

    private Long userId;

    @NotBlank(message = "Respostas são obrigatórias")
    private String answers;

    @NotBlank(message = "Resultado é obrigatório")
    private String result;

    @FutureOrPresent
    private LocalDate realizationDate;

    public TesteRapidoTO() {
    }

    public TesteRapidoTO(Long testId, Long userId, String answers, String result, LocalDate realizationDate) {
        this.testId = testId;
        this.userId = userId;
        this.answers = answers;
        this.result = result;
        this.realizationDate = realizationDate;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LocalDate getRealizationDate() {
        return realizationDate;
    }

    public void setRealizationDate(LocalDate realizationDate) {
        this.realizationDate = realizationDate;
    }
}
