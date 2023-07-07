package sg.edu.nus.iss.day27paf.repository;

import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.day27paf.model.GameComment;

@Repository
public class CommentRepository {
    
    @Autowired
    MongoTemplate template;

    /**
     * "_id" 
        "c_id"
        "user"
        "rating"
        "c_text"
        "gid"
     */

    public ObjectId insertComment(GameComment gComment) {
        Document doc = new Document();
        doc.put("c_id", UUID.randomUUID().toString().substring(0, 8));
        doc.put("user", gComment.getUser());
        doc.put("rating", gComment.getRating());
        doc.put("c_text", gComment.getCText());
        doc.put("gid", gComment.getGId());

        Document insertedDoc = template.insert(doc, "comment");
        return insertedDoc.getObjectId("_id");
    }

    public GameComment findGameCommentByObjectId(ObjectId id) {
        Criteria criteria = Criteria.where("_id").is(id);
        Query query = Query.query(criteria);
        List<Document> result = template.find(query, Document.class, "comment");
        Document dComment = result.get(0);
        return new GameComment(
                    dComment.getString("user"),
                    dComment.getInteger("rating"),
                    dComment.getString("c_text"),
                    dComment.getInteger("gid"));
    }
}
