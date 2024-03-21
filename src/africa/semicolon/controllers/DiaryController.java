//package africa.semicolon.controllers;
//
//import africa.semicolon.dtos.requests.CreateEntryRequest;
//import africa.semicolon.dtos.requests.LoginRequest;
//import africa.semicolon.dtos.requests.ReqisterRequest;
//import africa.semicolon.exceptions.DiaryAppException;
//import africa.semicolon.dtos.requests.LoginRequest;
//import africa.semicolon.services.DiaryService;
//
//
//public class DiaryController {
//    private static final DiaryService diaryService;
//
//    public static String register(RegisterRequest registerRequest){
//        try{
//            diaryService.register(registerRequest);
//            return "You registration was successful";
//        } catch (DiaryAppException e){
//            return e.getMessage();
//        }
//    }
//
//    public static String login(LoginRequest request){
//        try{
//            diaryService.login(request);
//            return "Login successful";
//        } catch (DiaryAppException e){
//            return e.getMessage();
//        }
//    }
//
//    public static String logout(String username){
//        try{
//            diaryService.logout(username);
//            return "Logout successful";
//        } catch (DiaryAppException e){
//            return e.getMessage();
//        }
//    }
//
//    public static String createEntry(CreateEntryRequest request){
//        try{
//            diaryService.createEntryWith(request);
//            return "Entry created successfully";
//        } catch (DiaryAppException e){
//            return e.getMessage();
//        }
//    }
//
//    public static String updateEntry(CreateEntryRequest request){
////        try {
////
////        }
//        return "0";
//    }
