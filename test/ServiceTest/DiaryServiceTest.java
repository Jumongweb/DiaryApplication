//package ServiceTest;
//
//import dtos.requests.CreateEntryRequest;
//import dtos.requests.LoginRequest;
//import dtos.requests.RegisterRequest;
//import exceptions.InvalidPasswordException;
//import exceptions.InvalidUserException;
//import exceptions.UsernameExistException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import services.DiaryService;
//import services.DiaryServiceImp;
//import services.EntryService;
//import services.EntryServiceImpl;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class DiaryServiceImpTest {
//    RegisterRequest registerRequest =  new RegisterRequest();
//    private DiaryService diaryService;
//    private static EntryService entryService = new EntryServiceImpl();
//    @BeforeEach
//    public void initializer(){
//        diaryService = new DiaryServiceImp();
//    }
//
//    @Test
//    public void testThatUserServiceHasZeroNumberOfUserByDefault(){
//        assertEquals(0, diaryService.getNumberOfUsers());
//    }
//    @Test
//    public void testThatUserCanBeAddedThroughRequestService(){
//        RegisterRequest registerRequest = new RegisterRequest();
//        registerRequest.setPassword("password");
//        registerRequest.setUsername("username");
//        diaryService.register(registerRequest);
//        assertEquals(1, diaryService.getNumberOfUsers());
//    }
//
//    @Test
//    public void testThatUserWithTheSameUsernameCannotBeAddedThroughRequestService(){
//        RegisterRequest registerRequest1 = registerRequest;
//        RegisterRequest registerRequest2 = registerRequest;
//        registerRequest1.setUsername("userName");
//        registerRequest1.setPassword("password");
//        registerRequest2.setUsername("userName");
//        registerRequest2.setPassword("password");
//        diaryService.register(registerRequest1);
//        assertThrows(UsernameExistException.class, ()->diaryService.register(registerRequest2));
//        assertEquals(1, diaryService.getNumberOfUsers());
//    }
//
//    @Test
//    public void testAddThreeUser_NumberOfUserIsThree(){
//        assertEquals(0, diaryService.getNumberOfUsers());
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        RegisterRequest registerRequest2 = new RegisterRequest();
//        RegisterRequest registerRequest3 = new RegisterRequest();
//        registerRequest1.setUsername("userName1");
//        registerRequest1.setPassword("password1");
//        registerRequest2.setUsername("userName2");
//        registerRequest2.setPassword("password2");
//        registerRequest3.setUsername("userName3");
//        registerRequest3.setPassword("password3");
//        diaryService.register(registerRequest1);
//        diaryService.register(registerRequest2);
//        diaryService.register(registerRequest3);
//        assertEquals(3, diaryService.getNumberOfUsers());
//    }
//
//    @Test
//    public void testThatUserCanLoginWithCorrectPassword(){
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        registerRequest1.setUsername("username");
//        registerRequest1.setPassword("password");
//        diaryService.register(registerRequest1);
//        assertTrue(diaryService.findUserBy("username").isLocked());
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername("username");
//        loginRequest.setPassword("password");
//        diaryService.login(loginRequest);
//        assertFalse(diaryService.findUserBy("username").isLocked());
//    }
//
//    @Test
//    public void testThatDiaryServiceThrowExceptionIfAnUnregisteredUserTryToLogIn(){
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        registerRequest1.setUsername("username");
//        registerRequest1.setPassword("password");
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername("username");
//        loginRequest.setPassword("password");
//        assertThrows(InvalidUserException.class, ()->diaryService.login(loginRequest));
//    }
//
//    @Test
//    public void testThatDiaryServiceThrowExceptionIfRegisteredUserTryToLogInWithWrongPassword(){
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        registerRequest1.setUsername("username");
//        registerRequest1.setPassword("password");
//        diaryService.register(registerRequest1);
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername("username");
//        loginRequest.setPassword("wrong password");
//        assertThrows(InvalidPasswordException.class, ()->diaryService.login(loginRequest));
//    }
//
//    @Test
//    public void testThatDiaryServiceCanSearchforUserByUsername(){
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        registerRequest1.setUsername("username");
//        registerRequest1.setPassword("password");
//        diaryService.register(registerRequest1);
//        System.out.println(diaryService.findUserBy("username"));
//        assertEquals(registerRequest1.getUsername(), diaryService.findUserBy("username").getUsername());
//    }
//
//    @Test
//    public void testThatUserCanLogOutThroughDiariesService(){
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        registerRequest1.setUsername("username");
//        registerRequest1.setPassword("password");
//        diaryService.register(registerRequest1);
//        assertTrue(diaryService.findUserBy("username").isLocked());
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername("username");
//        loginRequest.setPassword("password");
//        diaryService.login(loginRequest);
//        assertFalse(diaryService.findUserBy("username").isLocked());
//        diaryService.logout("username");
//        assertTrue(diaryService.findUserBy("username").isLocked());
//    }
//
//    @Test
//    public void testThatUserCannotLogOutThroughDiariesServiceWithWrongPassword(){
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        registerRequest1.setUsername("username");
//        registerRequest1.setPassword("password");
//        diaryService.register(registerRequest1);
//        assertTrue(diaryService.findUserBy("username").isLocked());
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername("username");
//        loginRequest.setPassword("password");
//        diaryService.login(loginRequest);
//        assertFalse(diaryService.findUserBy("username").isLocked());
//        assertThrows(InvalidUserException.class, ()->diaryService.logout("wrong username"));
//        assertFalse(diaryService.findUserBy("username").isLocked());
//    }
//
//    @Test
//    public void testThatUserCanCreateEntryThroughDiaryService(){
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        registerRequest1.setUsername("username");
//        registerRequest1.setPassword("password");
//        diaryService.register(registerRequest1);
//        assertTrue(diaryService.findUserBy("username").isLocked());
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername("username");
//        loginRequest.setPassword("password");
//        diaryService.login(loginRequest);
//        assertFalse(diaryService.findUserBy("username").isLocked());
//
//        CreateEntryRequest createEntryRequest = new CreateEntryRequest();
//        createEntryRequest.setTitle("entry title");
//        createEntryRequest.setBody("first entry in the diary");
//        createEntryRequest.setAuthor("jumong");
//        diaryService.createEntryWith(createEntryRequest);
//        assertEquals(createEntryRequest.getTitle(), entryService.getEntryBy(1).getTitle());
//    }
//
//}
