package org.pgm.ootdproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="board")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private long boardId;

    @ManyToOne
    // 외래 키 설정
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(length = 255, nullable = false)
    private String title;

    @Column(length = 2000)
    private String content;

    @Column(name = "purchase_link", length = 500)
    private String purchaseLink;

    @Column(name = "reg_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "visit_count", nullable = false)
    @ColumnDefault("0")
    private long visitCount;

    // 인기 게시물 선정 지표
    @Column(name = "popularity_score", nullable = false)
    @ColumnDefault("0")
    private float popularityScore;

    // 자식 엔터티 Cascade
    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BoardImage> boardImages;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BoardLike> boardLikes;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReportedBoard> reportedBoards;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Reply> replies;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<BookMark> bookmarks;

    @PrePersist
    @PreUpdate
    private void calculatePopularityScore() {
        float likeCount = boardLikes != null ? boardLikes.size() : 0;
        float bookmarkCount = bookmarks != null ? bookmarks.size() : 0;
        float visitCount = this.visitCount;
        this.popularityScore = 0.4f * likeCount + 0.4f * bookmarkCount + 0.2f * visitCount;
    }
}
