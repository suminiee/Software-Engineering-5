package com.vocabulary.web.group;

import com.vocabulary.domain.group.domain.Voca;
import com.vocabulary.domain.group.domain.VocaWord;
import com.vocabulary.domain.group.service.GroupService;
import com.vocabulary.web.login.argumentresolver.Login;
import com.vocabulary.web.login.session.MemberSessionDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
        return "group/groupEdit";
    }

    @PostMapping("/groupEdit")
    public String groupEditForm(@Login MemberSessionDto loginMember, @RequestParam(value = "groupId", required = false) Integer[] deleteGroup) {
        for (Integer groupId : deleteGroup) {
            Voca voca = new Voca();
            voca.setGroupId(groupId);
            groupService.deleteGroup(voca);
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
        for (Integer group : groups) {
            VocaWord vocaWord = new VocaWord();
            vocaWord.setWordId(Integer.valueOf(wordId.getValue()));
            vocaWord.setGroupId(group);
            groupService.saveWord(vocaWord);
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

//    @GetMapping("/group/{groupId}")
//    public String groupWords(@PathVariable Integer groupId, Model model) {
//        List<Word> groupWords = groupService.getGroupWords(groupId);
//        model.addAttribute("words", groupWords);
//        return "groupWords";
//    }
//
//    @PostMapping("/group/{groupId}")
//    public String groupWords(@PathVariable Integer groupId, Model model) {
//        List<Word> groupWords = groupService.getGroupWords(groupId);
//        model.addAttribute("words", groupWords);
//        return "groupWords";
//    }
//
//    @DeleteMapping("/group/{groupId}")
//    public String groupWords(@PathVariable Integer groupId, Model model) {
//        List<Word> groupWords = groupService.getGroupWords(groupId);
//        model.addAttribute("words", groupWords);
//        return "groupWords";
//    }
}
