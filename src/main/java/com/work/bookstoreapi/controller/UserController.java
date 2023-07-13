package com.work.bookstoreapi.controller;

import com.work.bookstoreapi.jwt.JwtTokenUtil;
import com.work.bookstoreapi.service.ApiResponse;
import com.work.bookstoreapi.service.UserService;
import com.work.bookstoreapi.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;

@RestController
@RequestMapping("/bookstore-api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserService userService;

    ApiResponse response;

    //endpoint to authenticate user with email and password
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = jwtTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken, user);

            return ResponseEntity.ok(response);
        } catch (BadCredentialsException ex){
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            response = new ApiResponse("401","incorrect user credentials", null);
            return new ResponseEntity<>(response,HttpStatus.UNAUTHORIZED);

//            System.out.println(ResponseEntity.notFound());
//            return ResponseEntity.ok(ResponseEntity.notFound());
        }
    }

    //endpoint to create new user
    @PostMapping("/add-new")
    public ResponseEntity<?> create(@RequestPart("body") User user, @RequestPart("image")MultipartFile file){
            return userService.addNewUser(user, file);
    }



    //endpoint to get all users
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllUsers(){
        try{
            return userService.listUsers();
        } catch (Exception ex){
            return ResponseEntity.ok(ex.getMessage());
        }

    }

    //endpoint to create a default user
    @GetMapping("/initiate-default")
    public ResponseEntity<?> addDefaultUser(){
        try{
            User defaultUser = new User();
            return userService.addNewUser(defaultUser, null);
        }catch (Exception ex){
            return ResponseEntity.ok(ex.getMessage());
        }

    }

    //endpoint to check if user exists for forgot password
    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid ForgotRequest forgotRequest){
        try{
            return userService.userForgotPassword(forgotRequest);
        } catch (Exception ex){
            return ResponseEntity.ok(ex.getMessage());
        }

    }

    //endpoint to update user password
    @PutMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid UserReset userReset){
//        try{
            return userService.resetUserPassword(userReset);
//        } catch (Exception ex){
//            return ResponseEntity.ok(ex.getMessage());
//        }

    }

    //endpoint to change user password
    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChgPwdRequest chgPwdRequest){
        try{

//            String emailOfUser = chgPwdRequest.getEmailOfUser();
//            String oldPassword = chgPwdRequest.getOldPassword();
//            String newPassword = chgPwdRequest.getNewPassword();
            userService.changeUserPassword(chgPwdRequest);
            return ResponseEntity.ok("it okay");
        }catch(Exception ex){
            return ResponseEntity.ok(ex.getMessage());
        }
    }

    //endpoint to update user details
    @PutMapping("/update-user-details-by-id")
    public ResponseEntity<?> updateDetails(@RequestBody User user){
        try{
            return userService.updateUserDetails(user);
        } catch(Exception ex){
            return ResponseEntity.ok(ex.getMessage());
        }
    }

    //endpoint to get user details
    @GetMapping("/get-user-details")
    public ResponseEntity<?> getUserDetails(@RequestParam Integer userid){
        try{
            return userService.getUserDetails(userid);
        } catch(Exception ex){
            return ResponseEntity.ok(ex.getMessage());
        }
    }
}
