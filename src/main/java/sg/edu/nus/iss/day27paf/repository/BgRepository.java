package sg.edu.nus.iss.day27paf.repository;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BgRepository {
    
    @Autowired
    private MongoTemplate template;

    public List<Document> findGameByName(String bgName) {
        Criteria criteria = Criteria.where("name").is(bgName);
        Query query = Query.query(criteria);
        List<Document> result = template.find(query, Document.class, "game");

        return result;
    }
}
