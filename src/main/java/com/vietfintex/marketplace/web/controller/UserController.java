package com.vietfintex.marketplace.web.controller;

import com.vietfintex.marketplace.web.dto.ResponseDTO;
import com.vietfintex.marketplace.web.dto.UserDTO;
import com.vietfintex.marketplace.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO findByUserId(@PathVariable final Long userId) {
        ResponseDTO response = new ResponseDTO(false);
        try {
            if (isNull(userId)) {
                response.setErrorMessage("userId must be not null");
                return response;
            }
            UserDTO users = userService.findOne(userId);
            response.setSuccess(true);
            response.setObjectReturn(users);
        } catch (Exception e) {
            response.setErrorMessage("Co loi xay ra: " + e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public ResponseDTO login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        ResponseDTO response = new ResponseDTO(false);
        try {
            if (isEmpty(username) || isEmpty(password)) {
                response.setErrorMessage("least username or email or phone be not null and password be not null");
                return response;
            }

            UserDTO users = userService.login(username, password);
            response.setSuccess(true);
            response.setObjectReturn(users);
        } catch (Exception e) {
            response.setErrorMessage("Co loi xay ra: " + e.getMessage());
        }
        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseDTO register(@RequestBody final UserDTO user) {
        ResponseDTO response = new ResponseDTO(false);
        try {
            userService.validate(user);
            UserDTO users = userService.register(user);
            response.setSuccess(true);
            response.setObjectReturn(users);
        } catch (Exception e) {
            response.setErrorMessage("Co loi xay ra: " + e.getMessage());
        }
        return response;
    }

}
