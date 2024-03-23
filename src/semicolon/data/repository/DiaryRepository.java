package africa.semicolon.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import africa.semicolon.data.models.Diary;

public interface DiaryRepository extends MongoRepository<Diary, String> {

}
