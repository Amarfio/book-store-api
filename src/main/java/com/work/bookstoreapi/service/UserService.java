package com.work.bookstoreapi.service;

import com.work.bookstoreapi.controller.ChgPwdRequest;
import com.work.bookstoreapi.controller.ForgotRequest;
import com.work.bookstoreapi.controller.UserReset;
import com.work.bookstoreapi.user.User;
import com.work.bookstoreapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;


    @Autowired
    PasswordEncoder passwordEncoder;

    //this is for response
    String responseCode = "500";
    String message = "default error message";
    Object responseObject =null;
    ApiResponse response= new ApiResponse(responseCode, message, responseObject);

    ResponseEntity responseEntity;

    private final String FOLDER_PATH ="C:\\Users\\Joshua Amarfio\\Videos\\Learning\\Java\\Web_Development_With_Java Spring Framework\\Projects\\book-store-api\\src\\main\\resources\\images\\UploadedImages\\";


    //method to create new user
    public ResponseEntity<ApiResponse> addNewUser(User user, MultipartFile file){

        User newUser = null;

        //code to get the file path
        String filePath = FOLDER_PATH+file.getOriginalFilename();

        try{


            Optional<User> checkUser = userRepo.findByEmail(user.getEmail());
            //create new user if email does not already exist
            if(!checkUser.isEmpty()) {
                responseCode = "403";
                message = "User email already exists";
                response = new ApiResponse(responseCode, message, responseObject);

                responseEntity = new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }else{

                //add new user
                newUser = user;
                String hashedPassword = generateEncodedPassword(user.getPassword());
                newUser.setPassword(hashedPassword);
                newUser.setCreatedAt(LocalDateTime.now());
                newUser.setFileName(file.getOriginalFilename());
                newUser.setFilePath(filePath);
                newUser.setFileType(file.getContentType());

                //get user image
//                FileData fileData = fileDataRepository.save(FileData.builder()
//                        .name()
//                        .type()
//                        .filePath(filePath).build());

                file.transferTo(new File(filePath));

//                if(fileData != null){
//                    result = "file uploaded successfully: " + filePath;
//                    return result;
//                }
//                return result;
                userRepo.save(newUser);

                response.setMessage("User account created successfully");
                response.setResponseCode("201");
                response.setData(newUser);

                responseEntity = new ResponseEntity<>(response, HttpStatus.CREATED);
            }
            return responseEntity;
        }catch(Exception ex){
//               return  ResponseEntity.ok(new ApiResponse("400", ex.getMessage(), null));
            response.setResponseCode("500");
            response.setMessage(ex.getMessage());
            response.setData(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return  responseEntity;
        }

    }

    //get all users
    public ResponseEntity listUsers(){

        try{
            List<User> users= userRepo.findAll();
            if(users.isEmpty()){
                responseCode = "204";
                message = "no data found";
                response = new ApiResponse(responseCode, message, responseObject);

                responseEntity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }else {
                responseCode = "200";
                message = "data available";
                responseObject = users;
                response = new ApiResponse(responseCode, message, responseObject);
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
            }

            return responseEntity;
        }catch(Exception ex){
            response.setResponseCode("500");
            response.setMessage(ex.getMessage());
            response.setData(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
            return responseEntity;
        }
    }

    //find out if user details is in the database
    public ResponseEntity<?> userForgotPassword(ForgotRequest forgotRequest){

        try{

            System.out.println(forgotRequest.getEmail());
            User userDetails = null;

            //put the user details here
            userDetails = userRepo.findByEmail(forgotRequest.getEmail()).get();
            System.out.println(userDetails.getName());

            //check user email exists and has details
            if(userDetails!=null){

                response.setResponseCode("200");
                response.setMessage("");
                response.setData(null);
//                userDetails=null;
                responseEntity = new ResponseEntity<>(response, HttpStatus.OK);


            }else{

                response.setResponseCode("204");
                response.setMessage("No user with this email exists");
                responseEntity = new ResponseEntity<>(response, HttpStatus.NO_CONTENT);


            }

            //return the response of the operation

            return responseEntity;
        }
        catch(NoSuchElementException noSuchElementException){
            response.setResponseCode(String.valueOf(HttpStatus.EXPECTATION_FAILED));
            response.setMessage("No user with this email exists");
            response.setData(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

            return responseEntity;
        }
        catch(Exception ex){
            response.setResponseCode("400");
            response.setMessage(ex.toString());
            response.setData(null);
            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

            return  responseEntity;
        }

    }

    //update user password with the email and password provided
    public ResponseEntity<?> resetUserPassword(UserReset userReset){

        //run the code and check for an error using the exception block...
        try{
            Optional<User> userDetails = null;

            //put the user details here
            userDetails = userRepo.findByEmail(userReset.getEmail());
            User updatedUserPassword = userDetails.get();

            updatedUserPassword.setPassword(generateEncodedPassword(userReset.getPassword()));
            updatedUserPassword.setUpdatedAt(LocalDateTime.now());
            userRepo.save(updatedUserPassword);
            userDetails = null;

            response.setResponseCode("200");
            response.setMessage("Password reset successful");
            response.setData(userDetails);

            responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

            return responseEntity;
        }
        catch(NoSuchElementException noSuchElementException){
            response.setResponseCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()));
            response.setMessage("email not found");
            response.setData(null);

            responseEntity = new ResponseEntity<>(response, HttpStatus.EXPECTATION_FAILED);

            return responseEntity;
        }
        catch(Exception exception){
//            System.out.println(HttpStatus.EXPECTATION_FAILED.value());
            response.setResponseCode(String.valueOf(HttpStatus.EXPECTATION_FAILED.value()));
            response.setMessage(exception.getMessage());
            response.setData(null);

            responseEntity = new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            return responseEntity;
        }


    }

    //change user password with the email, old password and new password
    public void changeUserPassword(ChgPwdRequest chgPwdRequest) {

        try{
//            System.out.println(emailOfUser);
            User newUser = new User();
            Optional<User> checkUser = userRepo.findById(chgPwdRequest.getId());

            System.out.println("we are here!!");
            Optional<User> userDetails = null;
            userDetails = userRepo.findById(chgPwdRequest.getId());
            User existingUser = userDetails.get();
            System.out.println(chgPwdRequest.getOldPassword());
            System.out.println(existingUser.getEmail());
            System.out.println(existingUser.getPassword());
            System.out.println(existingUser.getName());

            String hashedOldPassword = generateEncodedPassword(chgPwdRequest.getOldPassword());
            System.out.println(generateEncodedPassword(chgPwdRequest.getOldPassword()));

            if(generateEncodedPassword(chgPwdRequest.getOldPassword()).equals(existingUser.getPassword())){
                System.out.println("the passwords are the same");
            }
            else{
                System.out.println("incorrect password");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }


    }

    public ResponseEntity<ApiResponse> updateUserDetails (User user){


        try{
            Optional<User> checkUser = userRepo.findById(user.getId());
            //create new user if email does not already exist
            if(!checkUser.isEmpty()) {
                User existingUser = checkUser.get();
                existingUser.setName(user.getName());
                existingUser.setEmail(user.getEmail());
                existingUser.setPassword(user.getPassword());
                existingUser.setUpdatedAt(LocalDateTime.now());
                userRepo.save(existingUser);

                responseCode = "200";
                message = "Details Updated Successfully";
                response = new ApiResponse(responseCode, message, existingUser);
            }else{

                //add new user
                message = "User does not exist";
                response = new ApiResponse(responseCode, message, responseObject);
            }
            return ResponseEntity.ok(response);
        }catch(Exception ex) {
            return ResponseEntity.ok(new ApiResponse("400", "Error occured", ex.getMessage()));
        }
    }

    public ResponseEntity<ApiResponse> getUserDetails (Integer userId){
        try{
            Optional<User> checkUser = userRepo.findById(userId);
            //create new user if email does not already exist
            if(!checkUser.isEmpty()) {
                User existingUser = checkUser.get();
                responseCode = "200";
                message = "User data found";
                response = new ApiResponse(responseCode, message, existingUser);
            }else{

                //add new user
                message = "User does not exist";
                response = new ApiResponse(responseCode, message, responseObject);
            }
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            return ResponseEntity.ok(new ApiResponse("400", "Error occured", ex.getMessage()));
        }
    }


    //method to hash the user password for the database
    public String generateEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }



}
