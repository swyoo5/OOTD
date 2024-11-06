package org.pgm.ootdproject.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pgm.ootdproject.DTO.BoardDTO;
import org.pgm.ootdproject.entity.Board;
import org.pgm.ootdproject.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        float likeCount = board.getBoardLikes().size();
        float bookmarkCount = board.getBookmarks().size();
        float visitCount = board.getVisitCount();
        float popularityScore = 0.4f * likeCount + 0.4f * bookmarkCount + 0.2f * visitCount;
        board.setPopularityScore(popularityScore);

        boardRepository.save(board);
    }

    @Transactional
    public void updateAllPopularityScores() {
        List<Board> boards = boardRepository.findAll();

        for (Board board : boards) {
            float likeCount = board.getBoardLikes() != null ? board.getBoardLikes().size() : 0;
            float bookmarkCount = board.getBookmarks() != null ? board.getBookmarks().size() : 0;
            float visitCount = board.getVisitCount();
            float popularityScore = 0.4f * likeCount + 0.4f * bookmarkCount + 0.2f * visitCount;

            // 점수 설정
            board.setPopularityScore(popularityScore);
        }

        // 데이터베이스에 모든 게시물을 저장
        boardRepository.saveAll(boards);
    }

    private Board convertDTOToEntity(BoardDTO boardDTO) {

        return Board.builder()
                .boardId(boardDTO.getBoardId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .purchaseLink(boardDTO.getPurchaseLink())
                .regDate(boardDTO.getRegDate())
                .visitCount(boardDTO.getVisitCount())
                .popularityScore(boardDTO.getPopularityScore())
                .build();
    }
    private BoardDTO convertEntityToDTO(Board board) {
        float likeCount = board.getBoardLikes().size();
        float bookmarkCount = board.getBookmarks().size();
        float visitCount = board.getVisitCount();
        float popularityScore = 0.4f * likeCount + 0.4f * bookmarkCount + 0.2f * visitCount;

        return BoardDTO.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .purchaseLink(board.getPurchaseLink())
                .regDate(board.getRegDate())
                .userId(board.getUser().getUserId())
                .userLoginId(board.getUser().getLoginId())
                .popularityScore(popularityScore)
                .build();
    }
}
