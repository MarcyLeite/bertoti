package com.magi.secondfloor.repository;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.magi.secondfloor.entity.Move;

public interface MoveInterface extends JpaRepository<Move, BigInteger> {
    @Query(
        value = "SELECT * FROM MOVE WHERE " +
        "(cast(?1 as varchar) IS NULL OR name LIKE ?1%) AND " +
        "(cast(?2 as varchar) IS NULL OR type = ?2) AND " +
        "(cast(?3 as varchar) IS NULL OR category = ?3) AND " +
        "(cast(?4 as integer) IS NULL OR power = ?4) AND " +
        "(cast(?5 as integer) IS NULL OR accuracy = ?5) AND " +
        "(cast(?6 as integer) IS NULL OR pp = ?6) AND " +
        "(cast(?7 as varchar) IS NULL OR description LIKE ?7%)",
        // AND (?2 IS NULL OR type LIKE '?2%') AND (?3 IS NULL OR category LIKE '?3%') AND (?4 IS NULL OR power = ?4) AND (?5 IS NULL OR accuracy = ?5) AND (?6 IS NULL OR pp = ?6) AND (?6 IS NULL OR description LIKE ?6)
        nativeQuery = true
    )
    List<Move> findBySearch(
        String name,
        String type,
        String category,
        Integer power,
        Integer accuracy,
        Integer pp,
        String description
    );
}