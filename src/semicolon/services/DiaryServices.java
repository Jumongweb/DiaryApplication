package semicolon.services;

import semicolon.data.models.Diary;
import semicolon.data.models.Entry;
import semicolon.dtos.requests.*;

import java.util.List;

public interface DiaryServices {
    void register(ReqisterRequest registerRequest);
    Diary findDiaryBy(String username);
    void login(LoginRequest loginRequest);
    void logout(String username);
    void deregister(RemoveUserRequest removeUserRequest);
    void createEntryWith(CreateEntryRequest createEntryRequest);
    void updateEntryWith(UpdateEntryRequest updateEntryRequest);
    void deleteEntry(String id, String username);
    Entry getEntry(String id, String username);
    List<Entry> getEntriesFor(String username);
}