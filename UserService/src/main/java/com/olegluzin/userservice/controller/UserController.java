package com.olegluzin.userservice.controller;

import com.olegluzin.userservice.dto.InstrumentsDto;
import com.olegluzin.userservice.dto.UserDto;
import com.olegluzin.userservice.model.User;
import com.olegluzin.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}/stocks")
    public User addStocksToUser(@PathVariable String id, @RequestBody InstrumentsDto instrumentsDto) {
        return userService.addStocksToUser(id, instrumentsDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userService.deleteUserById(id);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
