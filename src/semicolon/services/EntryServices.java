package semicolon.services;

import semicolon.data.models.Entry;
import semicolon.dtos.requests.CreateEntryRequest;

import java.util.List;

public interface EntryService {
    List<Entry> getEntriesFor(String username);

    Entry createNewEntryWith(String username);

    Entry findByAuthor(String author);

    void deleteWith(String title);
    void addEntry(CreateEntryRequest createEntryRequest);

    int getNumberOfEntries();
}
