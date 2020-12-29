package com.local.study.java8.springjava8.model;


import java.util.Set;

/**
 * 〈功能概述〉<br>
 *
 * @author: yiche
 * @date: 2020/11/17 11:19 上午
 */
public class Document {

    private String[] profiles;

    private Set<Profile> activeProfiles;

    private Set<Profile> includeProfiles;


    public String[] getProfiles() {
        return profiles;
    }

    public void setProfiles(String[] profiles) {
        this.profiles = profiles;
    }

    public Set<Profile> getActiveProfiles() {
        return activeProfiles;
    }

    public Set<Profile> getIncludeProfiles() {
        return includeProfiles;
    }
}
