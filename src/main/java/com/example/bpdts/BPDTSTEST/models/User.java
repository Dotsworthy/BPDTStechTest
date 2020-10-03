package com.example.bpdts.BPDTSTEST.models;


public class User {

        private String firstName;
        private String lastName;
        private String email;
        private String ipAddress;
        private int latitude;
        private int longitude;

        public User(String firstName, String lastName, String email, String ipAddress, int latitude, int longitude) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email =  email;
            this.ipAddress = ipAddress;
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getFirstName() {
            return firstName;
        }




}
