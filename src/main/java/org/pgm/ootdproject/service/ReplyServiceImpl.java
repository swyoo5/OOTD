package org.pgm.ootdproject.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pgm.ootdproject.repository.ReplyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;

    @Override
    public Optional<Reply> readReply(Long replyId) {
        return replyRepository.findById(replyId);
    }

    @Override
    public List<Reply> readAllReplies() {
        return replyRepository.findAll();
    }


}
