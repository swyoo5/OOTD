package org.pgm.ootdproject.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="chatroom")
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    private long chatRoomId;

    @Column(length = 255, nullable = false)
    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "created_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    // 자식 엔터티 Cascade
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ReportedChatRoom> reportedChatRooms;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChatParticipant> chatParticipants;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<ChatMessage> chatMessages;
}
