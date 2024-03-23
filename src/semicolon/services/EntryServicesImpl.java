package semicolon.services;

import semicolon.data.models.Entry;
import semicolon.data.repository.EntryRepository;
import semicolon.exceptions.EmptyListException;
import semicolon.exceptions.EntryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntryServicesImpl implements EntryServices {
    @Autowired
    private EntryRepository repository;

    @Override
    public void save(Entry entry) {
        repository.save(entry);
    }

    @Override
    public void deleteEntry(String id) {
        Optional<Entry> entry = repository.findById(id);
        if (entry.isEmpty()) throw new EntryNotFoundException("Entry not found");

        repository.deleteById(id);
    }

    @Override
    public Entry getEntry(String id) {
        Optional<Entry> entry = repository.findById(id);
        if (entry.isEmpty()) throw new EntryNotFoundException("Entry not found");

        return entry.get();
    }

    @Override
    public List<Entry> getEntriesFor(String username) {
        List<Entry> entries = repository.findByAuthor(username.toLowerCase());
        if (entries.isEmpty()) throw new EmptyListException("No entry found");

        return entries;
    }
}