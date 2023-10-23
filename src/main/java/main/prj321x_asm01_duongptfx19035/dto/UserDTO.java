package main.prj321x_asm01_duongptfx19035.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 kí tự.")
    private String password;

    private String fullname;

    private String username;

    private String phone;

    private String address;

    private String note;

    private String role;

}
