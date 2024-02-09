package com.example.kun_uz_lesson1.util;

import com.example.kun_uz_lesson1.dto.JwtDTO;
import com.example.kun_uz_lesson1.enums.ProfileRole;
import com.example.kun_uz_lesson1.exp.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;

public class HttpRequestUtil {
    public static Integer getProfileId(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");


        if (requiredRoleList.length==0)return id;


        for (ProfileRole profileRole : requiredRoleList) {
            if (role.equals(profileRole)) {
                return id;
            }
        }
        throw new ForbiddenException("Method not allowed");
    }
    public static JwtDTO getJWTDTO(HttpServletRequest request, ProfileRole... requiredRoleList) {
        Integer id = (Integer) request.getAttribute("id");
        ProfileRole role = (ProfileRole) request.getAttribute("role");
        JwtDTO dto = new JwtDTO(id,role);
        for (ProfileRole requiredRole : requiredRoleList) {
            if (role.equals(requiredRole)) {
                return dto;
            }
        }
        throw new ForbiddenException("Method not allowed");
    }

}
