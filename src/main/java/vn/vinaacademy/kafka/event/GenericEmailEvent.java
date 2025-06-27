package vn.vinaacademy.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericEmailEvent {
    private EmailEventType type;
    private Map<String, Object> data;

    public enum EmailEventType {
        VERIFICATION,
        PASSWORD_RESET,
        WELCOME,
        NOTIFICATION,
        PAYMENT_SUCCESS,
        PAYMENT_FAILED
    }

}
