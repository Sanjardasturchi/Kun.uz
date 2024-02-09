package com.example.kun_uz_lesson1.service;

import com.example.kun_uz_lesson1.dto.SMSHistoryDTO;
import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class SMSService {
    @Value("${my.eskiz.uz.email}")
    private String email;
    @Value("${my.eskiz.uz.password}")
    private String password;
    @Value("${sms.fly.uz.url}")
    private String url;

    public String getTokenWithAuthorization() {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("email", email)
                .addFormDataPart("password", password)
                .build();
        Request request = new Request.Builder()
                .url( url +"/api/auth/login")
                .method("POST", body)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new IOException();
            } else {
                JSONObject object = new JSONObject(response.body().string());
                JSONObject data = object.getJSONObject("data");
                Object token = data.get("token");
                return token.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }
    public void send(String phone, String text, String code) {
        // create sms history
        sendSmsHTTP(phone, text + code);
    }
    public void sendSmsHTTP(String phone, String text) {
        String token = "Bearer " + getTokenWithAuthorization();

        OkHttpClient client = new OkHttpClient();

        if (phone.startsWith("+")) {
            phone = phone.substring(1);
        }

        RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("mobile_phone", phone)
                .addFormDataPart("message", text)
                .addFormDataPart("from", "4546")
                .build();

        Request request = new Request.Builder()
                .url(url + "/api/message/sms/send")
                .method("POST", body)
                .header("Authorization", token)
                .build();

        try {
            Response response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*Thread thread = new Thread(() -> {
            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    System.out.println(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();*/
    }



    public SMSHistoryDTO create(SMSHistoryDTO dto) {
        return null;
    }

    public List<SMSHistoryDTO> getByPhone(String phone) {
        return null;
    }

    public List<SMSHistoryDTO> getByGivenDate(LocalDate date) {
        return null;
    }

    public List<SMSHistoryDTO> getByPagination(Integer page, Integer size) {
        return null;
    }
}
