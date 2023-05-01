package com.duc.ptc.utils;

import com.duc.ptc.constant.Const;
import com.duc.ptc.modals.User;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class Helper {
    public static User getUser(String userType) {
        final Optional<User> returnData;
        List<User> user;
        String env = getEnvironment();
        ObjectMapper mapper = new ObjectMapper();

        File userData = new File(Thread.currentThread().getContextClassLoader().getResource(Const.USER_DATA_PATH).getFile());
        try {
            String userNode = mapper.readTree(userData).at(String.format("/%s", env)).toString();
            user = mapper.readValue(userNode, new TypeReference<List<User>>() {});
            returnData = user.stream().filter(item -> item.getUserType().equalsIgnoreCase(userType)).findAny();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (returnData.isPresent()) {
            System.out.println(returnData.get());
            return returnData.get();
        } else {
            return null;
        }
    }

//    public static User getUser(String userType){
//        String env = getEnvironment();
//        User user = null;
//        String jsonExpression = String.format("$.%s[?(@.userType == '%s')]", env, userType);
//        File userData = new File(Thread.currentThread().getContextClassLoader().getResource(Const.USER_DATA_PATH).getFile());
//        try {
//            user = JsonPath.parse(userData).read(jsonExpression);
//        } catch(IOException e) {
//            e.printStackTrace();
//        }
//        return user;
//    }

    private static String getEnvironment() {
        String env = System.getProperty("env");
        if (env == null || env.contains("prod")) {
            return "PROD";
        }
        if (env.contains("qc")) {
            return "QC";
        }
        if (env.contains("dev")) {
            return "DEV";
        }
        return null;
    }
}
