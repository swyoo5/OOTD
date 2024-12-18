package org.pgm.ootdproject.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pgm.ootdproject.DTO.BoardDTO;
import org.pgm.ootdproject.DTO.ReplyDTO;
import org.pgm.ootdproject.DTO.UserDTO;
import org.pgm.ootdproject.entity.Board;
import org.pgm.ootdproject.entity.Reply;
import org.pgm.ootdproject.entity.User;
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
    private final BoardServiceImpl boardServiceImpl;

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

    @Override
    public ReplyDTO updateReply(ReplyDTO replyDTO, String content) {
        Reply reply = replyRepository.findById(replyDTO.getReplyId())
                .orElseThrow(() -> new IllegalArgumentException("Reply not found"));
        reply.setContent(content);
        replyRepository.save(reply);
        return convertEntityToDTO(reply);
    }

    @Override
    public List<ReplyDTO> readRepliesByBoardId(Long boardId) {
        return replyRepository.findByBoardBoardId(boardId).stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReplyDTO createReply(BoardDTO boardDTO, UserDTO userDTO, String content) {
        Board board = convertBoardDTOToEntity(boardDTO);
        User user = convertUserDTOToEntity(userDTO);
        Reply reply = Reply.builder()
                .board(board)
                .user(user)
                .content(content)
                .isDeleted(false)
                .build();
        replyRepository.save(reply);
        return convertEntityToDTO(reply);
    }

    @Override
    public void saveReply(ReplyDTO replyDTO) {
        Reply reply = convertDTOToEntity(replyDTO);
        replyRepository.save(reply);
    }

    private Reply convertDTOToEntity(ReplyDTO replyDTO) {
        Board board = new Board();
        board.setBoardId(replyDTO.getBoardId());

        User user = new User();
        user.setUserId(replyDTO.getUserId());

        return Reply.builder()
                .replyId(replyDTO.getReplyId())
                .board(board)
                .user(user)
                .content(replyDTO.getContent())
                .createdDate(replyDTO.getCreatedDate())
                .isDeleted(replyDTO.getIsDeleted())
                .build();
    }

    private Board convertBoardDTOToEntity(BoardDTO boardDTO) {
        return Board.builder()
                .boardId(boardDTO.getBoardId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .purchaseLink(boardDTO.getPurchaseLink())
                .regDate(boardDTO.getRegDate())
                .build();
    }

    private User convertUserDTOToEntity(UserDTO userDTO) {
        return User.builder()
                .userId(userDTO.getUserId())
                .nickname(userDTO.getNickname())
                .loginId(userDTO.getLoginId())
                .email(userDTO.getEmail())
                .build();
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
