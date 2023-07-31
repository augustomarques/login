package br.com.amarques.login.dto.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "The [firstname] cannot be empty")
    private String firstname;
    @NotEmpty(message = "The [lastname] cannot be empty")
    private String lastname;
    @Email
    private String email;
    @NotEmpty(message = "The [password] cannot be empty")
    private String password;

}
