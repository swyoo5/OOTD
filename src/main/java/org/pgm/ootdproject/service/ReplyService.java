package org.pgm.ootdproject.service;

import org.pgm.ootdproject.DTO.ReplyDTO;
import org.pgm.ootdproject.entity.Reply;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    Optional<ReplyDTO> readReply(Long id);
    public List<ReplyDTO> readAllReplies();
    public List<ReplyDTO> readRepliesByUserId(Long userId);
}
