package semicolon.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.data.models.Diary;

@Repository
public interface DiaryRepository extends MongoRepository<Diary, String> {

}
