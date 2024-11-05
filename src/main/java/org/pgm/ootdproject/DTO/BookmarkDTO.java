package org.pgm.ootdproject.DTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookmarkDTO {
    private Long boardId;

    private Long userId;

    @NotEmpty
    private LocalDateTime bookmarkedDate;

    private String userNickname;
    private String title;
    private String content;
    private String purchaseLink;
    private LocalDateTime regDate;
}
