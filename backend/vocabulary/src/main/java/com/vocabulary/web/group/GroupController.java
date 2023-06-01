package com.vocabulary.web.group;

import com.vocabulary.domain.group.domain.Voca;
import com.vocabulary.domain.group.domain.VocaWord;
import com.vocabulary.domain.group.service.GroupService;
import com.vocabulary.domain.word.domain.Word;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.web.login.session.MemberSessionDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping("/groups")
    public String groups(@Login MemberSessionDto loginMember, Model model) {
        List<Voca> info = groupService.getGroup(loginMember.getId());
        model.addAttribute("groups", info);
        return "group/groups";
    }

    @PostMapping("/groups")
    public String groupSave(@Login MemberSessionDto loginMember, Model model,
                            @RequestParam("groupName") String groupName) {
        Voca voca = new Voca();
        voca.setId(loginMember.getId());
        voca.setGroupName(groupName);
        groupService.saveGroup(voca);
        return "redirect:/groups";
    }

    @GetMapping("/groupEdit")
    public String groupEditForm(@Login MemberSessionDto loginMember, Model model) {
        List<Voca> info = groupService.getGroup(loginMember.getId());
        model.addAttribute("groups", info);
        return "group/groupsEdit";
    }

    @PostMapping("/groupEdit")
    public String groupEditForm(@Login MemberSessionDto loginMember, @RequestParam(value = "groupId", required = false) Integer[] deleteGroup) {
        if (deleteGroup != null) {
            for (Integer groupId : deleteGroup) {
                Voca voca = new Voca();
                voca.setGroupId(groupId);
                groupService.deleteGroup(voca);
            }
        }
        return "redirect:/groupEdit";
    }

    @GetMapping("/addGroup")
    public String addGroupForm(@Login MemberSessionDto loginMember, Model model, @RequestParam(value = "wordId", required = false) Integer wordId, HttpServletResponse response) {
        if (wordId != null) {
            Cookie cookie = new Cookie("wordId", wordId.toString());
            response.addCookie(cookie);
        }
        List<Voca> info = groupService.getGroup(loginMember.getId());
        model.addAttribute("groups", info);
        return "group/addGroup";
    }

    @PostMapping("/addGroup")
    public String addGroup(@Login MemberSessionDto loginMember, @CookieValue(value = "wordId") Cookie wordId,
                           @RequestParam(value = "groupId", required = false) Integer[] groups) {
        if (groups != null) {
            for (Integer group : groups) {
                VocaWord vocaWord = new VocaWord();
                vocaWord.setWordId(Integer.valueOf(wordId.getValue()));
                vocaWord.setGroupId(group);
                groupService.saveWord(vocaWord);
            }
        }

        return "redirect:/addGroup";
    }

    @PostMapping("/addGroup/1")
    public String addGroupModal(@Login MemberSessionDto loginMember, @RequestParam(value = "groupName", required = false) String groupName) {
        Voca voca = new Voca();
        voca.setId(loginMember.getId());
        voca.setGroupName(groupName);
        groupService.saveGroup(voca);
        return "redirect:/addGroup";
    }

    @GetMapping("/group")
    public String groupWords(@RequestParam(value = "groupId", required = false) Integer groupId, Model model, HttpServletResponse response,
                             @CookieValue(value = "cookieGroupId", required = false) Cookie cookieGroupId) {
        if (groupId != null) {
            cookieGroupId = new Cookie("cookieGroupId", groupId.toString());
            response.addCookie(cookieGroupId);
        }

        List<Word> groupWords = groupService.getGroupWords(Integer.valueOf(cookieGroupId.getValue()));
        model.addAttribute("words", groupWords);
        model.addAttribute("groupName", groupService.findByGroupId(Integer.valueOf(cookieGroupId.getValue())));
        return "group/group";
    }

    @GetMapping("/groupWordEdit")
    public String groupWordEditForm(@CookieValue(value = "cookieGroupId") Cookie groupId, Model model) {
        List<Word> groupWords = groupService.getGroupWords(Integer.valueOf(groupId.getValue()));
        model.addAttribute("words", groupWords);
        model.addAttribute("groupName", groupService.findByGroupId(Integer.valueOf(groupId.getValue())));
        return "group/groupEdit";
    }
    @PostMapping("/groupWordEdit")
    public String groupWordsEdit(@CookieValue(value = "cookieGroupId") Cookie groupId, @RequestParam(value = "wordId", required = false) Integer[] words) {
        Integer groupId1 = Integer.valueOf(groupId.getValue());;
        for (Integer word : words) {
            VocaWord vocaWord = new VocaWord();
            vocaWord.setGroupId(groupId1);
            vocaWord.setWordId(word);
            groupService.deleteWord(vocaWord);
        }
        return "redirect:/groupWordEdit";
    }
}