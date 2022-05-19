package main.java.com.sequence.product.result;

import lombok.*;
import main.java.com.obj.ResponseStatus;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class DELETEProductResult {
    private ResponseStatus responseStatus;
}
