package br.com.fiap.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 5, max = 80)
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Size(min = 5, max = 100)
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(min = 8, max = 60)
    private String password;

    @NotNull(message = "Idade é obrigatório")
    private Integer age;

    @NotBlank(message = "Tipo de perfil é obrigatório")
    @Size(min = 3, max = 25)
    private String profileType;

    public UserTO() {
    }

    public UserTO(Long id, String name, String email, String password, Integer age, String profileType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.age = age;
        this.profileType = profileType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }
}
