package org.pgm.ootdproject.DTO;

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
public class ReplyDTO {
    private long replyId;

    @NotEmpty
    @Size(min = 15, max = 2000)
    private String content;

    @NotEmpty
    private LocalDateTime createdDate;

    @NotEmpty
    private Boolean isDeleted = false;

    private Long boardId;
    private Long userId;
    private String userNickname;
}
