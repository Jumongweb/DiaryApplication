package semicolon.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class DiaryUserRequest {
    private String username;
    private String password;
}
