package org.pgm.ootdproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="reply_like")
public class ReplyLike {
    @EmbeddedId
    private ReplyLikeId id;

    @MapsId("replyId")
    @ManyToOne
    @JoinColumn(name = "reply_id", nullable = false)
    private Reply reply;

    @MapsId("userId")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 날짜 형식 지정
    @Column(name = "liked_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime likedDate;

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("0")
    private Boolean isDeleted;
}
