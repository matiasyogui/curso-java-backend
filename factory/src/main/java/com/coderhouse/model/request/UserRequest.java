package com.coderhouse.model.request;

import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "El campo type no puede vacio")
    @Pattern(regexp = "^(ADMIN|EDITOR|CLIENT)$", message = "Solo acepta: ADMIN, EDITOR, CLIENT")
    private String type;
    @NotNull(message = "El campo username es obligatorio")
    private String username;
    @NotNull(message = "El campo password es obligatorio")
    private String password;
    private String fullName;
    private Integer telephone;
}
