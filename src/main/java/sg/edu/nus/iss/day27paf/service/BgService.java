package sg.edu.nus.iss.day27paf.service;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import sg.edu.nus.iss.day27paf.model.Game;
import sg.edu.nus.iss.day27paf.model.GameComment;
import sg.edu.nus.iss.day27paf.repository.BgRepository;
import sg.edu.nus.iss.day27paf.repository.CommentRepository;

@Service
public class BgService {
    
    @Autowired
    BgRepository bgRepo;

    @Autowired
    CommentRepository cRepo;

    public Optional<Game> findGameByName(String bgname) {
        List<Document> gameList = bgRepo.findGameByName(bgname);
        if (gameList.isEmpty()) {
            // If empty, return empty optional
            return Optional.empty();
        }

        // If not empty, grab the id save it to session
        Document returnedGame = gameList.get(0);

        return Optional.of(new Game(
                                returnedGame.getInteger("gid"), 
                                returnedGame.getString("name"),
                                returnedGame.getInteger("year"),
                                returnedGame.getInteger("ranking")));
    }

    public GameComment findGameCommentByObjectId(ObjectId id) {
        return cRepo.findGameCommentByObjectId(id);
    }

    public ObjectId insertComment(GameComment gComment) {
        return cRepo.insertComment(gComment);
    }
}
