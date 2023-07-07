package sg.edu.nus.iss.day27paf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameComment {
    private String user;
    private Integer rating;
    private String cText;
    private Integer gId;
}
