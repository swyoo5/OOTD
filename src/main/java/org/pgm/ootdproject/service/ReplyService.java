package org.pgm.ootdproject.service;

import org.pgm.ootdproject.DTO.BoardDTO;
import org.pgm.ootdproject.DTO.ReplyDTO;
import org.pgm.ootdproject.DTO.UserDTO;
import org.pgm.ootdproject.entity.Board;
import org.pgm.ootdproject.entity.User;

import java.util.List;
import java.util.Optional;

public interface ReplyService {
    Optional<ReplyDTO> readReply(Long id);
    List<ReplyDTO> readAllReplies();
    List<ReplyDTO> readRepliesByUserId(Long userId);

    ReplyDTO updateReply(ReplyDTO replyDTO, String content);
    List<ReplyDTO> readRepliesByBoardId(Long boardId);
    ReplyDTO createReply(BoardDTO board, UserDTO user, String content);
    void saveReply(ReplyDTO replyDTO);
}
