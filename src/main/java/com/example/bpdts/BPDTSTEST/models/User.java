package com.example.bpdts.BPDTSTEST.models;


public class User {

        private String firstName;
        private String lastName;
        private String email;
        private String ipAddress;
        private double latitude;
        private double longitude;

        public User(String firstName, String lastName, String email, String ipAddress, double latitude, double longitude) {
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


        public String getLastName() {
            return lastName;
        }

        public String getEmail() {
            return email;
        }
}
