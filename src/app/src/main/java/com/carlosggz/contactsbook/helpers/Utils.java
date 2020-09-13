package com.carlosggz.contactsbook.helpers;

import android.util.Patterns;

import com.carlosggz.contactsbook.model.api.ApiResult;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.HttpException;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {
    public static boolean isEmptyOrNull(String s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNullOrWhiteSpace(String s) {
        return s == null || s.trim().length() == 0;
    }

    public static boolean isValidEmail(String email) {
        return !isNullOrWhiteSpace(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidPhone(String phone) {
        return !isNullOrWhiteSpace(phone) && Patterns.PHONE.matcher(phone).matches();
    }

    public static ApiResult deserializeError(Throwable x) {

        if (!(x instanceof HttpException)) {
            return null;
        }

        ResponseBody error = ((HttpException)x).response().errorBody();
        GsonConverterFactory factory = GsonConverterFactory.create();
        Converter<ResponseBody, ApiResult> converter = (Converter<ResponseBody, ApiResult>)factory.responseBodyConverter(ApiResult.class, new Annotation[0], null);

        try {
            return converter.convert(error);
        } catch (IOException e) {
            return null;
        }
    }

    public static String getErrorFromResponse(Throwable x) {

        ApiResult result = deserializeError(x) ;

        if (result != null) {
            return result.getErrors().stream().map(Object::toString).collect(Collectors.joining("\n"));
        }

        if (x instanceof java.net.SocketTimeoutException) {
            return "Error connecting to the remote server";
        }

        return x.getMessage();
    }
}
