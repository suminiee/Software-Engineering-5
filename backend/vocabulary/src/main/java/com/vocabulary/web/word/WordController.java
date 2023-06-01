package com.vocabulary.web.word;

import com.vocabulary.domain.word.domain.Word;

import com.vocabulary.domain.word.dto.WordForm.*;
import com.vocabulary.domain.word.service.WordService;
import com.vocabulary.web.login.session.MemberSessionDto;
import com.vocabulary.web.login.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String words(@Login MemberSessionDto loginMember, Model model,
                        @RequestParam(value = "searchCond", required = false) String cond) {
        if ((loginMember == null) || !loginMember.getRole()) {
            return "redirect:/main";
        }
        if (cond != null) {
            model.addAttribute("words", wordService.findAllByCond(cond));
        }
        else {
            model.addAttribute("words", wordService.findAll());
        }
        return "admin/adminPage";
    }

    /**
     * 단어 목록 검색 출력
     */
    @PostMapping("/words")
    public String condWords(@Validated @RequestParam WordSearchCond cond, BindingResult bindingResult) {
        return "redirect:/words?cond="+cond.getSearchCond();
    }

    /**
     * 단어 저장
     */
    @PostMapping("/wordAdd")
    public String save(@Validated WordSaveForm form, BindingResult bindingResult) {
        log.info(form.toString());
        Word word = new Word();
        word.setSpelling(form.getSpelling());
        word.setMean(form.getMean());
        wordService.save(word);
        return "redirect:/words";
    }

    @GetMapping("/wordEdit")
    public String editForm(@RequestParam(value = "searchCond", required = false) String cond, Model model) {
        if (cond != null) {
            model.addAttribute("words", wordService.findAllByCond(cond));
        }
        else {
            model.addAttribute("words", wordService.findAll());
        }
        return "admin/adminEdit";
    }

    @PostMapping("/wordEdit/cond")
    public String editCondForm(@Validated @RequestParam WordSearchCond cond, BindingResult bindingResult) {
        log.info("cond", cond.toString());
        return "redirect:/wordEdit?cond="+cond.getSearchCond();
    }

    @PostMapping("/wordEdit")
    public String edit(@Validated @ModelAttribute WordUpdateForm form, BindingResult bindingResult) {
        log.info(form.toString());
        wordService.update(form);
        return "redirect:/wordEdit";
    }

    @PostMapping("/wordEdit/1")
    public String delete(@RequestParam(value = "wordId", required = false) Integer[] wordIds) {
        if (wordIds != null) {
            for (Integer wordId : wordIds) {
                wordService.delete(wordId);
            }
        }
        return "redirect:/wordEdit";
    }

    /**
     * 단어 수정
     */
    @PutMapping()
    public String update(@Validated @ModelAttribute WordUpdateForm form, BindingResult bindingResult) {
        wordService.update(form);
        return "redirect:/words";
    }
}
