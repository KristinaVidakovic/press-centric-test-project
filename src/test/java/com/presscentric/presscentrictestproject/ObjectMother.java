package com.presscentric.presscentrictestproject;

import com.presscentric.presscentrictestproject.model.User;

import java.util.Map;
import java.util.Optional;

public class ObjectMother {
    public static User createUser(Optional<Map> properties) {
        User user = new User(1, "email@email.com", "Name");
        if (properties.isPresent()) {
            if (properties.get().containsKey("id")) {
                user.setId((Integer) properties.get().get("id"));
            }
            if (properties.get().containsKey("email")) {
                user.setEmail((String) properties.get().get("email"));
            }
            if (properties.get().containsKey("name")) {
                user.setName((String) properties.get().get("name"));
            }
        }
        return user;
    }
}
