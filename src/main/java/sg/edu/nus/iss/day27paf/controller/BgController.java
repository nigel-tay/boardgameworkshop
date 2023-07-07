package sg.edu.nus.iss.day27paf.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.day27paf.model.Game;
import sg.edu.nus.iss.day27paf.model.GameComment;
import sg.edu.nus.iss.day27paf.repository.BgRepository;
import sg.edu.nus.iss.day27paf.service.BgService;

@Controller
@RequestMapping
public class BgController {
    
    @Autowired
    BgService bgService;

    @GetMapping
    public String showIndex() {
        return "index";
    }

    @GetMapping("/gameform")
    public String showGameForm(@RequestParam String bgname, Model m, HttpSession session) {
        // Call repo, if empty optional is returned, game does not exist, ask user to put in a different name
        if (bgService.findGameByName(bgname).isEmpty()) {
            return "index";
        }

        // If not empty, grab the id save it to session
        Game rGame = bgService.findGameByName(bgname).get();
        session.setAttribute("gid", rGame.getGid());
        m.addAttribute("bgame", rGame);
        m.addAttribute("gcomment", new GameComment());
        return "gameform";
    }

    @PostMapping("/comment")
    public String insertComment(GameComment gComment, Model m){
        ObjectId commentId = bgService.insertComment(gComment);
        GameComment returnedGame = bgService.findGameCommentByObjectId(commentId);
        m.addAttribute("gcomment", returnedGame);
        return "singlecomment";

    }
}
