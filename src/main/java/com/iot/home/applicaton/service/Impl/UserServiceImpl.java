package com.iot.home.applicaton.service.Impl;

import com.iot.home.applicaton.io.entity.PasswordResetTokenEntity;
import com.iot.home.applicaton.io.entity.UserEntity;
import com.iot.home.applicaton.io.repository.PasswordResetTokenRepository;
import com.iot.home.applicaton.io.repository.UserRepository;
import com.iot.home.applicaton.service.EmailService;
import com.iot.home.applicaton.service.UserService;
import com.iot.home.applicaton.shared.Utils;
import com.iot.home.applicaton.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    EmailService emailService;
    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;
    @Override
    public UserDto createUser(UserDto userDetails) {
        UserEntity userCheckExist= userRepository.findByEmail(userDetails.getEmail());

        if(userCheckExist!=null&&userCheckExist.getEmailVerificationStatus()) throw new RuntimeException("User already exist!!!");

        UserDto returnValue= new UserDto();
        ModelMapper mapper= new ModelMapper();

        UserEntity userEntity= mapper.map(userDetails,UserEntity.class);
        userEntity.setUserId(Utils.generateUserId(30));

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDetails.getPassword()));
        userEntity.setEmailVerificationToken(Utils.generateEmailVerificationToken(userEntity.getUserId()));
        emailService.sendVerificationEmail(userEntity.getEmail(),Utils.SUBJECT_EMAIL_CONFIRMATION,Utils.EMAIL_CONFIRMATION_URL+userEntity.getEmailVerificationToken());
        userEntity= userRepository.save(userEntity);
        returnValue= mapper.map(userEntity,UserDto.class);

        return returnValue;
    }

    @Override
    public boolean verifyEmailToken(String token) {
        boolean returnValue= false;
        UserEntity userEntity= userRepository.findUserByEmailVerificationToken(token);

        if(userEntity!=null) {
            if (!Utils.hasTokenExpired(token)) {
                userEntity.setEmailVerificationToken(null);
                userEntity.setEmailVerificationStatus(true);
                userRepository.save(userEntity);
                returnValue=true;
            }
        }
        return returnValue;

    }

    @Override
    public boolean requestPasswordReset(String email) {
        boolean returnValue=false;
        UserEntity userEntity= userRepository.findByEmail(email);
        if(userEntity!=null&&userEntity.getEmailVerificationStatus()){
            String passwordToken=Utils.generatePasswordVerificationToken(userEntity.getUserId());
            PasswordResetTokenEntity passwordResetTokenEntity= new PasswordResetTokenEntity();
            passwordResetTokenEntity.setToken(passwordToken);
            passwordResetTokenEntity.setUserDetails(userEntity);
            passwordResetTokenRepository.save(passwordResetTokenEntity);
            emailService.sendVerificationEmail(email,Utils.SUBJECT_EMAIL_CONFIRMATION_FOR_PASSWORD_RESET,passwordToken);
            returnValue=true;
        }

        return returnValue;

    }

    @Override
    public boolean resetPassword(String token, String password) {
        boolean returnValue=false;
        if(!Utils.hasTokenExpired(token)){
            PasswordResetTokenEntity passwordResetTokenEntity= passwordResetTokenRepository.findPasswordResetTokenEntityByToken(token);
            UserEntity userEntity= passwordResetTokenEntity.getUserDetails();
            String encryptPassword= bCryptPasswordEncoder.encode(password);
            userEntity.setEncryptedPassword(encryptPassword);
            UserEntity userDetailsSaved= userRepository.save(userEntity);

            if(encryptPassword.equalsIgnoreCase(userDetailsSaved.getEncryptedPassword())){
                returnValue=true;
            }

            passwordResetTokenRepository.delete(passwordResetTokenEntity);


        }

        return  returnValue;
    }

    @Override
    public UserDto getUser(String email) {
        UserDto returnValue = new UserDto();
        UserEntity userEntity= userRepository.findByEmail(email);
        ModelMapper modelMapper= new ModelMapper();
        returnValue= modelMapper.map(userEntity,UserDto.class);
        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity=userRepository.findByEmail(username);
        if(userEntity==null) throw new UsernameNotFoundException(username);
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(),new ArrayList<>());

    }
}
