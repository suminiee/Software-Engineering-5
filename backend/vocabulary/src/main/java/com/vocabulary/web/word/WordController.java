package com.vocabulary.web.word;

import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.domain.word.dto.WordDeleteForm;
import com.vocabulary.domain.word.dto.WordSearchCond;
import com.vocabulary.domain.word.dto.WordUpdateForm;
import com.vocabulary.web.login.session.MemberSessionDto;
import com.vocabulary.domain.member.domain.Role;
import com.vocabulary.domain.word.repository.WordRepository;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.domain.word.dto.WordSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/words")
public class WordController {

    private final WordRepository wordRepository;

    /**
     * login aop 필요
     */
    
    /**
     * 단어 목록 전체 출력
     */
    @GetMapping()
    public String words(@Login MemberSessionDto loginMember, Model model) {
        if (loginMember == null || loginMember.getRole() != Role.MANAGER) {
            return "redirect:/home";
        }
        model.addAttribute("wordList", wordRepository.findAll());
        return "words";
    }

    /**
     * 단어 목록 검색 출력
     */
    @PostMapping()
    public String condWords(@ModelAttribute WordSearchCond cond, Model model) {
        model.addAttribute("wordList", wordRepository.findAll(cond));
        return "words";
    }

    /**
     * 단어 저장
     */
    @PostMapping()
    public String save(@Validated @ModelAttribute WordSaveForm form) {
        Word word = new Word();
        word.setSpelling(form.getSpelling());
        word.setMean(form.getMean());

        wordRepository.save(word);

        return "redirect:/words";
    }
    
    /**
     * 단어 삭제
     */
    @DeleteMapping()
    public String delete(@Validated @ModelAttribute WordDeleteForm form) {
        wordRepository.delete(form.getWordId());
        return "redirect:/words";
    }

    /**
     * 단어 수정
     */
    @PatchMapping()
    public String update(@Validated @ModelAttribute WordUpdateForm form) {
        wordRepository.update(form);
        return "redirect:/words";
    }
}
