package vn.vinaacademy.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationCreateEvent {
    private String title;
    private String content;
    private String targetUrl;
    private NotificationType type;
    private UUID userId;

    //    private String hash;  sau update len de hash value valid neu k se bi fake noti
    public enum NotificationType {
        SYSTEM,
        MESSAGE,
        PAYMENT_SUCCESS,
        COURSE_REVIEW,
        COURSE_APPROVAL,
        SUPPORT_REPLY,
        PROMOTION,
        FINANCIAL_ALERT,
        STAFF_REQUEST,
        INSTRUCTOR_REQUEST

    }
}