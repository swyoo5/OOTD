package org.pgm.ootdproject.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pgm.ootdproject.DTO.ReplyDTO;
import org.pgm.ootdproject.entity.Reply;
import org.pgm.ootdproject.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    @Override
    public Optional<ReplyDTO> readReply(Long replyId) {
        return replyRepository.findById(replyId).map(this::convertEntityToDTO);
    }

    @Override
    public List<ReplyDTO> readAllReplies() {
        return replyRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReplyDTO> readRepliesByUserId(Long userId) {
        return replyRepository.findByUserUserId(userId).stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private ReplyDTO convertEntityToDTO(Reply reply) {
        return ReplyDTO.builder()
                .replyId(reply.getReplyId())
                .content(reply.getContent())
                .createdDate(reply.getCreatedDate())
                .isDeleted(reply.getIsDeleted())
                .boardId(reply.getBoard().getBoardId())
                .userId(reply.getUser().getUserId())
                .build();
    }
}
