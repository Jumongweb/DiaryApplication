package semicolon.data.repository;

import org.springframework.stereotype.Repository;
import semicolon.data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Repository
public interface EntryRepository extends MongoRepository<Entry, String> {
    List<Entry> findByAuthor(String author);
}
