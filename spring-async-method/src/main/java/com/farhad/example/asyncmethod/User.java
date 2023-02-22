package com.farhad.example.asyncmethod;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    
    private String name ;
    private String blog ; 
    private String location;
    private String email ;
    private int public_repos ;
    private int public_gists ;
    // private String company ;
    // private int followers;
    // private int following ;
    // private String twitter_username ;
    // private String type;
    // private String url;
    // private String avatar_url;
    // private String events_url;
    // private String followers_url;
    // private String following_url;
    // private String gists_url;
    // private String gravatar_id;
    // private String html_url;
}
