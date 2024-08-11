package com.iot.home.application.ui.controller;

import com.iot.home.application.service.UserService;
import com.iot.home.application.shared.dto.UserDto;
import com.iot.home.application.ui.model.request.PasswordRequestResetModel;
import com.iot.home.application.ui.model.request.PasswordResetModel;
import com.iot.home.application.ui.model.request.UserDetailsRequestModel;
import com.iot.home.application.ui.model.response.OperationStatusModel;
import com.iot.home.application.ui.model.response.RequestOperationName;
import com.iot.home.application.ui.model.response.RequestOperationStatus;
import com.iot.home.application.ui.model.response.UserRest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
    ,produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel){
        UserRest returnValue= new UserRest();
        ModelMapper mapper= new ModelMapper();
        UserDto userDto=mapper.map(userDetailsRequestModel,UserDto.class);

        UserDto userSave= userService.createUser(userDto);

        returnValue=mapper.map(userSave,UserRest.class);

        return returnValue;


    }
    // http:localhost:8088/users/email-verification
    @GetMapping(path = "/email-verification",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel verifyEmailToken(@RequestParam(value="token") String token){
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());

        boolean isVerified=userService.verifyEmailToken(token);
        if (isVerified) {
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());

        }else {
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }

        return returnValue;
    }
    @PostMapping(path = "/password-reset-request", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
    ,consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public OperationStatusModel requestReset(@RequestBody PasswordRequestResetModel passwordRequestResetModel){
        OperationStatusModel returnValue = new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
        boolean isValidEmail= userService.requestPasswordReset(passwordRequestResetModel.getEmail());

        if(isValidEmail){
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }else {
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return returnValue;
    }

    @PostMapping(path = "/password-reset",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE}
            ,consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    OperationStatusModel resetPassword(@RequestBody PasswordResetModel passwordResetModel){
        OperationStatusModel returnValue= new OperationStatusModel();
        returnValue.setOperationName(RequestOperationName.PASSWORD_RESET.name());
        boolean isResetSuccess= userService.resetPassword(passwordResetModel.getToken(),passwordResetModel.getPassword());

        if(isResetSuccess){
            returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
        }else{
            returnValue.setOperationResult(RequestOperationStatus.ERROR.name());
        }
        return returnValue;
    }


}
