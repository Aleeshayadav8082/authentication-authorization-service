package com.maveric.authenticationauthorizationservice.feignclient;

import com.maveric.authenticationauthorizationservice.exception.UserFeignException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@Slf4j
public class FeignCustomErrorDecoder implements ErrorDecoder {

    @Override public Exception decode(String methodKey, Response response) {
        byte[] bodyData = new byte[0];
        try {
            bodyData = Util.toByteArray(response.body().asInputStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String responseBody = new String(bodyData);
        JSONObject responseObj = null;
        try {
            responseObj = new JSONObject(responseBody);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        String message = null;
        try {
            message = responseObj.getString("message");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        HttpStatus responseStatus = HttpStatus.valueOf(response.status());

        if (responseStatus.is5xxServerError()) {
            System.out.println(message);
            return new UserFeignException(message);
        } else if (responseStatus.is4xxClientError()) {
            return new UserFeignException(message);
        } else {
            return new Exception("Generic exception");
        }
    }
}
