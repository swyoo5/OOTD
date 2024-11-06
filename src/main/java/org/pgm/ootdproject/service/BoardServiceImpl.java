package org.pgm.ootdproject.service;

import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pgm.ootdproject.DTO.BoardDTO;
import org.pgm.ootdproject.entity.Board;
import org.pgm.ootdproject.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Data
@Transactional
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    // 내가 쓴 게시물 하나 조회
    public Optional<BoardDTO> readBoard(Long id) {
        return boardRepository.findById(id).map(this::convertEntityToDTO);
    }

    // 삭제
    @Override
    public void deleteBoard(Long boardId) {
        boardRepository.deleteById(boardId);
    }

    // 내가 쓴 게시물 전체 조회
    @Override
    public List<BoardDTO> readBoardsByUserId(Long userId) {
        return boardRepository.findByUserUserId(userId).stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    // 조회수 증가
    @Transactional
    public void increaseVisitCount(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        board.setVisitCount(board.getVisitCount() + 1);
        boardRepository.save(board);
    }

    @Override
    public Optional<BoardDTO> findById(Long boardId) {
        return boardRepository.findById(boardId).map(this::convertEntityToDTO);
    }

    @Override
    public void saveBoard(BoardDTO boardDTO) {
        Board board = convertDTOToEntity(boardDTO);
        boardRepository.save(board);
    }

    private Board convertDTOToEntity(BoardDTO boardDTO) {
        return Board.builder()
                .boardId(boardDTO.getBoardId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .purchaseLink(boardDTO.getPurchaseLink())
                .regDate(boardDTO.getRegDate())
                .build();
    }
    private BoardDTO convertEntityToDTO(Board board) {
        return BoardDTO.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .purchaseLink(board.getPurchaseLink())
                .regDate(board.getRegDate())
                .userId(board.getUser().getUserId())
                .userLoginId(board.getUser().getLoginId())
                .build();
    }
}
