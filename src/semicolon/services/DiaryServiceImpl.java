package semicolon.services;

import semicolon.data.models.Diary;
import semicolon.data.models.Entry;
import semicolon.data.repository.DiaryRepository;
import semicolon.dtos.requests.*;
import semicolon.dtos.requests.RemoveUserRequest;
import semicolon.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiaryServiceImpl implements DiaryServices {
    @Autowired
    private DiaryRepository repository;
    @Autowired
    private EntryServices entryServices;

    @Override
    public void register(ReqisterRequest request) {
        validate(request);

        Diary newDiary = new Diary();
        newDiary.setUsername(request.getUsername().toLowerCase());
        newDiary.setPassword(request.getPassword());

        repository.save(newDiary);
    }

    private void validate(ReqisterRequest request) {
        //validateNull(request);
        //validateBlank(request);
        validateDuplicate(request);
    }

    private void validateDuplicate(ReqisterRequest request) {
        boolean isDuplicate = repository.findById(request.getUsername().toLowerCase()).isPresent();
        if (isDuplicate) throw new UsernameExistException("Username already exists.");
    }


//    private void validateBlank(ReqisterRequest request) {
//        boolean isBlank = request.getUsername().isBlank() || request.getPassword().isBlank();
//        if (isBlank) throw new InvalidArgumentException("Username and password cannot be blank.");
//    }
//
//    private void validateNull(RegisterRequest request) {
//        boolean isNull = request.getUsername() == null || request.getPassword() == null;
//        if (isNull) throw new InvalidArgumentException("Username and password cannot be null.");
//    }

    @Override
    public Diary findDiaryBy(String username) {
        Optional<Diary> foundDiary = repository.findById(username.toLowerCase());
        if (foundDiary.isEmpty()) throw new DiaryNotFoundException("Diary not found.");

        return foundDiary.get();
    }

    @Override
    public void login(LoginRequest request) {
        Diary foundDiary = findDiaryBy(request.getUsername().toLowerCase());
        if (isPasswordIncorrect(foundDiary, request.getPassword())) throw new InvalidPasswordException("Password is incorrect.");
        foundDiary.setLocked(false);

        repository.save(foundDiary);
    }

    @Override
    public void logout(String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        foundDiary.setLocked(true);

        repository.save(foundDiary);
    }

    private boolean isPasswordIncorrect(Diary foundDiary, String password) {
        return !foundDiary.getPassword().equals(password);
    }

    @Override
    public void deregister(RemoveUserRequest request) {
        Diary foundDiary = findDiaryBy(request.getUsername().toLowerCase());
        checkLockStatusOf(foundDiary);
        if (isPasswordIncorrect(foundDiary, request.getPassword())) throw new InvalidPasswordException("Password is incorrect.");

        repository.delete(foundDiary);
    }

    private void checkLockStatusOf(Diary diary) {
        if (diary.isLocked()) throw new DiaryLockedException("You need to login to use this service.");
    }

    @Override
    public void createEntryWith(CreateEntryRequest request) {
        Diary foundDiary = findDiaryBy(request.getAuthor().toLowerCase());
        checkLockStatusOf(foundDiary);

        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setAuthor(request.getAuthor().toLowerCase());

        entryServices.save(entry);
    }

    @Override
    public void updateEntryWith(UpdateEntryRequest request) {
        Diary foundDiary = findDiaryBy(request.getAuthor().toLowerCase());
        checkLockStatusOf(foundDiary);

        Entry entry = new Entry();
        entry.setTitle(request.getTitle());
        entry.setBody(request.getBody());
        entry.setAuthor(request.getAuthor().toLowerCase());
        entry.setId(request.getId());

        entryServices.save(entry);
    }

    @Override
    public void deleteEntry(String id, String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkLockStatusOf(foundDiary);

        entryServices.deleteEntry(id);
    }

    @Override
    public Entry getEntry(String id, String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkLockStatusOf(foundDiary);

        return entryServices.getEntry(id);
    }

    @Override
    public List<Entry> getEntriesFor(String username) {
        Diary foundDiary = findDiaryBy(username.toLowerCase());
        checkLockStatusOf(foundDiary);

        return entryServices.getEntriesFor(username);
    }
}