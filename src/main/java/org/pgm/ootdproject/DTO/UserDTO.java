package org.pgm.ootdproject.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long userId;

    @NotEmpty
    @Size(min = 5, max = 255)
    private String loginId;

    @NotEmpty
    @Size(min = 5, max = 255)
    private String email;

    @NotEmpty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    @Size(min = 5, max = 255)
    private String profileImage;

    @Size(min = 5, max = 20)
    private String nickname;

    @Size(min = 5, max = 255)
    private String introduce;
}
