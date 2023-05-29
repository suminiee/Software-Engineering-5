package com.vocabulary.web.word;

import com.vocabulary.domain.word.domain.Word;

import com.vocabulary.domain.word.dto.WordForm.*;
import com.vocabulary.domain.word.dto.WordSearchCond;
import com.vocabulary.domain.word.service.WordService;
import com.vocabulary.web.login.session.MemberSessionDto;
import com.vocabulary.web.login.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@Controller
public class WordController {

    private final WordService wordService;

    /**
     * 단어 목록 전체 출력
     */
    @GetMapping("/words")
    public String words(@Login MemberSessionDto loginMember, Model model) {
        if ((loginMember == null) || !loginMember.getRole()) {
            return "redirect:/main";
        }
        model.addAttribute("words", wordService.findAll());
        return "word/wordsV1";
    }

    /**
     * 단어 목록 검색 출력
     */
    @GetMapping("/cond")
    public String condWords(@ModelAttribute WordSearchCond cond, Model model) {
        model.addAttribute("words", wordService.findAllByCond(cond));
        return "word/wordsV1";
    }

    /**
     * 단어 저장
     */
    @PostMapping()
    public String save(@Validated @ModelAttribute WordSaveForm form) {
        Word word = new Word();
        word.setSpelling(form.getSpelling());
        word.setMean(form.getMean());

        wordService.save(word);

        return "redirect:/words";
    }
    
    /**
     * 단어 삭제
     */
    @DeleteMapping()
    public String delete(@Validated @ModelAttribute WordDeleteForm form) {
        wordService.delete(form.getWordId());
        return "redirect:/words";
    }

    /**
     * 단어 수정
     */
    @PutMapping()
    public String update(@Validated @ModelAttribute WordUpdateForm form) {
        wordService.update(form);
        return "redirect:/words";
    }
}
