package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class SuportTO {

    private Long id;

    @NotBlank(message = "Assunto é obrigatório")
    private String subject;

    private String score;

    @NotBlank(message = "Data de abertura é obrigatória")
    private LocalDate openingDate;

    @NotBlank(message = "Email é obrigatório")
    private String email;

    public SuportTO() {
    }

    public SuportTO(Long id, String subject, String score, LocalDate openingDate, String email) {
        this.id = id;
        this.subject = subject;
        this.score = score;
        this.openingDate = openingDate;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
