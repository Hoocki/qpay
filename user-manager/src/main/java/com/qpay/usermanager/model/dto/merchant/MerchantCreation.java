package com.qpay.usermanager.model.dto.merchant;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public final class MerchantCreation {

    private @NotBlank(message = "Name is mandatory") String name;

    private @NotBlank(message = "Email is mandatory") String email;

    private @NotBlank(message = "Password is mandatory") String password;
}
