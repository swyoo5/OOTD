package org.pgm.ootdproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    Optional<Reply> findById(Long id);
}
