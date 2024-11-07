package org.pgm.ootdproject.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pgm.ootdproject.DTO.BoardDTO;
import org.pgm.ootdproject.entity.Board;
import org.pgm.ootdproject.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

//    @Override
//    public void saveBoard(BoardDTO boardDTO) {
//        Board board = convertDTOToEntity(boardDTO);
//
//        long likeCount = board.getBoardLikes().size();
//        long bookmarkCount = board.getBookmarks().size();
//        long visitCount = board.getVisitCount();
//        long popularityScore = 4 * likeCount + 4 * bookmarkCount + 2 * visitCount;
//        board.setPopularityScore(popularityScore);
//
//        boardRepository.save(board);
//    }
//
//    @Transactional
//    public void updateAllPopularityScores() {
//        List<Board> boards = boardRepository.findAll();
//
//        for (Board board : boards) {
//            long likeCount = board.getBoardLikes() != null ? board.getBoardLikes().size() : 1;
//            long bookmarkCount = board.getBookmarks() != null ? board.getBookmarks().size() : 1;
//            long visitCount = board.getVisitCount();
//            long popularityScore = 4 * likeCount + 4 * bookmarkCount + 2 * visitCount;
//
//            // 점수 설정
//            board.setPopularityScore(popularityScore);
//        }
//
//        // 데이터베이스에 모든 게시물을 저장
//        boardRepository.saveAll(boards);
//    }
//
//    // 상위 10개
//    @Transactional(readOnly = true)
//    public List<BoardDTO> getPopularBoards() {
//        return boardRepository.findTop10ByRegDateAfterOrderByPopularityScoreDesc(LocalDateTime.now().minusWeeks(4))
//                .stream()
//                .map(this::convertEntityToDTO)
//                .collect(Collectors.toList());
//    }

    @Override
    public List<BoardDTO> getPopularBoards() {
        LocalDateTime fourWeeksAgo = LocalDateTime.now().minusWeeks(4);
        return boardRepository.findTop10ByPopularity(fourWeeksAgo)
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private Board convertDTOToEntity(BoardDTO boardDTO) {

        return Board.builder()
                .boardId(boardDTO.getBoardId())
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .purchaseLink(boardDTO.getPurchaseLink())
                .regDate(boardDTO.getRegDate())
                .visitCount(boardDTO.getVisitCount())
//                .popularityScore(boardDTO.getPopularityScore())
                .build();
    }
    private BoardDTO convertEntityToDTO(Board board) {
//        long likeCount = board.getBoardLikes().size();
//        long bookmarkCount = board.getBookmarks().size();
//        long visitCount = board.getVisitCount();
//        long popularityScore = 4 * likeCount + 4 * bookmarkCount + 2 * visitCount;

        return BoardDTO.builder()
                .boardId(board.getBoardId())
                .title(board.getTitle())
                .content(board.getContent())
                .purchaseLink(board.getPurchaseLink())
                .regDate(board.getRegDate())
                .userId(board.getUser().getUserId())
                .userLoginId(board.getUser().getLoginId())
//                .popularityScore(popularityScore)
                .build();
    }


}
