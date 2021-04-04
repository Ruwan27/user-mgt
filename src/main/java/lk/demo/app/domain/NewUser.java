package lk.demo.app.domain;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
@JsonPropertyOrder({"newUser"})
public class NewUser {

    private String name;
    private String empId;
    private String address;
    private String email;
}
