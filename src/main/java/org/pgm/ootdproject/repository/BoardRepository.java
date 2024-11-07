package org.pgm.ootdproject.repository;

import org.pgm.ootdproject.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findById(Long id);
    List<Board> findByUserUserId(Long userId);
//    List<Board> findTop10ByRegDateAfterOrderByPopularityScoreDesc(LocalDateTime regDate);
@Query(value = """
        select b 
        from Board b 
        left join b.boardLikes bl 
        left join b.bookmarks bm 
        where b.regDate >= :fourWeeksAgo
        group by b
        order by (4 * coalesce(sum(case when bl.isDeleted = false then 1 else 0 end), 0) 
        + 4 * coalesce(count(bm), 0)
        + 2 * b.visitCount) desc
        limit 10
    """)
    List<Board> findTop10ByPopularity(LocalDateTime fourWeeksAgo);
}
