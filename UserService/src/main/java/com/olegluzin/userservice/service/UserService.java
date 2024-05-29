package com.olegluzin.userservice.service;

import com.olegluzin.userservice.dto.InstrumentsDto;
import com.olegluzin.userservice.dto.UserDto;
import com.olegluzin.userservice.exception.UserAlreadyExistsException;
import com.olegluzin.userservice.exception.UserNotFoundException;
import com.olegluzin.userservice.model.Instrument;
import com.olegluzin.userservice.model.User;
import com.olegluzin.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserDto userDto){
        if(userRepository.existsById(userDto.getId())){
            throw new UserAlreadyExistsException("User already exists. Try another Id.");
        }
        return userRepository.save(new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getPortfolio()));
    }

    public User addStocksToUser(String id, InstrumentsDto instrumentsDto){
        User user = userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found."));
        Set<Instrument> addInstruments = instrumentsDto.getInstruments();
        addInstruments.removeAll(user.getPortfolio());
        addInstruments.addAll(user.getPortfolio());
        user.setPortfolio(addInstruments);
        return userRepository.save(user);
    }

    public User getUserById(String id){
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException("User not found. Try another id"));
    }

    public void deleteUserById(String id){
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
