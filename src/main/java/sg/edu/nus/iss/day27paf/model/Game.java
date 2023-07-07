package sg.edu.nus.iss.day27paf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Integer gid;
    private String name;
    private Integer year;
    private Integer ranking;
}
