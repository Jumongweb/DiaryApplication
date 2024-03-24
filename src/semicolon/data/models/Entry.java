package semicolon.data.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document
public class Entry {
    @Id
    private String id;
    private String author;
    private String title;
    private String body;
    private LocalDate dateCreated = LocalDate.now();

    @Override
    public String toString(){
        return String.format("""
                id: %s%n
                author: %s%n
                title: %s%n
                body: %s%n
                date: %s%n
                """, getId(), getAuthor(), getTitle(), getBody(), getDateCreated());
    }
}
