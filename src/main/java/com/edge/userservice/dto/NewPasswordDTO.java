package com.edge.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDTO {

    private String currentPassword;
    private String newPassword;
    private String newPasswordConfirmation;

}
