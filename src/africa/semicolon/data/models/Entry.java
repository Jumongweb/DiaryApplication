package data.models;

import java.time.LocalDate;

//@Data
public class Entry {
    private String id;
    private String author;
    private String title;
    private String body;
    private LocalDate dateCreated = LocalDate.now();
}
