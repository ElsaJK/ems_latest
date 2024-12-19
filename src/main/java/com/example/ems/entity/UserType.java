package com.example.ems.entity;


public enum UserType {
    ADMIN(NAMES.ADMIN),MEMBER(NAMES.MEMBER);
    UserType(String role){

    }
    public static class NAMES{
        public static final String ADMIN ="ADMIN";
        public static final String MEMBER ="MEMBER";

        private NAMES(){

        }

    }
}
