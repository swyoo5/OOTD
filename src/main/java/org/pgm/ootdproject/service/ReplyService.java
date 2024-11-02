package org.pgm.ootdproject.service;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    Optional<Reply> readReply(Long id);
    public List<Reply> readAllReplies();
}
