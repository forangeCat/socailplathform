package app.generator.modol;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class User implements Serializable {
    private Integer id;

    private String randomNumber;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(String randomNumber) {
        this.randomNumber = randomNumber == null ? null : randomNumber.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}