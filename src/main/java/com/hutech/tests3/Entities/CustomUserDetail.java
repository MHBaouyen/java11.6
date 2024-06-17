package com.hutech.tests3.Entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetail implements UserDetails{

    private User user;
    public CustomUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        String roles = "ADMIN,MODIFIER,USER";
        int index = roles.indexOf(user.getRole().getRole_name());
        String substring = roles.substring(index);
        String authString[] = substring.split(",");
        for (String auth : authString) {
            authorities.add(new SimpleGrantedAuthority(auth));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public String getDisplayName() {
        // Return HTML string with dropdown options
        StringBuilder dropdownHtml = new StringBuilder();
        dropdownHtml.append("<div class='dropdown'>")
                .append("<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>")
                .append(user.getUsername())
                .append("</button>")
                .append("<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>")
                .append("<a class='dropdown-item' href='/change-password'>Đổi mật khẩu</a>")
                .append("<a class='dropdown-item' href='/profile'>Xem thông tin cá nhân</a>")
                .append("<a class='dropdown-item' href='/logout'>Đăng xuất</a>")
                .append("</div>")
                .append("</div>");
        return dropdownHtml.toString();
    }
}
