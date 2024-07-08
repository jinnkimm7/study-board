package com.example.study_board.controller;

import com.example.study_board.dto.BoardDTO;
import com.example.study_board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    public String save(BoardDTO boardDTO) {
        boardService.save(boardDTO);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        // 조회수 처리
        boardService.updateHits(id);

        // 상세 정보 가져오기
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);

        System.out.println("boardDTO = " + boardDTO);
        return "detail";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        BoardDTO foundBoardDTO = boardService.findById(id);
        model.addAttribute("foundBoard", foundBoardDTO);

        return "update";
    }

    @PostMapping("/update/{id}")
    public String update(BoardDTO updatedBoardDTO, Model model) {
        boardService.update(updatedBoardDTO);
        boardService.findById(updatedBoardDTO.getId());
        model.addAttribute("board", updatedBoardDTO);

        return "detail";
    }
}
