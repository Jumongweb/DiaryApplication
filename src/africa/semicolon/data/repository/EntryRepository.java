package africa.semicolon.data.repository;

import africa.semicolon.data.models.Entry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EntryRepository extends MongoRepository<Entry, String> {

}
