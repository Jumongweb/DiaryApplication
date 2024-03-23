package semicolon.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import semicolon.data.models.Entry;
import semicolon.services.EntryServices;

import java.util.List;

public class EntryController {
    public EntryServices entryServices;

    @GetMapping("/entries/{username}")
    public List<Entry> getEntriesBelongingTo(@PathVariable String username){
        return entryServices.getEntriesFor(username);
    }
}
