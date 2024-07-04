package com.example.study_board.repository;

import com.example.study_board.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final SqlSessionTemplate sql;
    public void save(BoardDTO boardDTO) {
        sql.insert("Board.save", boardDTO);
    }
}
