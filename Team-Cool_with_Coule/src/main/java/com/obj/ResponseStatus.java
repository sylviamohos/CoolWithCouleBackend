package main.java.com.obj;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ResponseStatus {
    private int code;
    private String message;
}
