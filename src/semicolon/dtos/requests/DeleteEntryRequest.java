package semicolon.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class DeleteEntryRequest {
    private String id;
    private String username;

}
