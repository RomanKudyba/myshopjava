package my.shop.com.myshopjava.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class BuyersDto {

    @Email
    private String email;

    @Size(max = 12)
    private String phone;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("mid_name")
    private String midName;

    private String surname;

    private Integer age;
}
