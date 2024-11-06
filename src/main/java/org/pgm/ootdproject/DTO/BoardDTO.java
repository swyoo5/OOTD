package org.pgm.ootdproject.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
    private long boardId;

    @NotEmpty
    @Size(min = 30, max = 255)
    private String title;

    @Size(min = 100, max = 2000)
    private String content;

    @Size(min = 10, max = 255)
    private String purchaseLink;

    @NotEmpty
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime regDate;

    @NotEmpty
    private long visitCount;

    private long userId;
    private String userLoginId;
    private long popularityScore;
}
