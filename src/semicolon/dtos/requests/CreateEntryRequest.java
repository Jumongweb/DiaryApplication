package semicolon.dtos.requests;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class CreateEntryRequest {
    private String title;
    private String body;
    private String author;
}
