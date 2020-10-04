package com.example.bpdts.BPDTSTEST.models;

import javax.persistence.*;


@Entity
@Table(name="users")
public class User {

        @Id
        @GeneratedValue
        private Long id;

        @Column(name = "first_name")
        private String firstName;

        @Column(name = "last_name")
        private String lastName;

        @Column(name = "email")
        private String email;

        @Column(name = "ipAddress")
        private String ipAddress;

        @Column(name = "latitude")
        private double latitude;

        @Column(name = "longitude")
        private double longitude;

        @Column(name = "City")
        private String city;

        public User(String firstName, String lastName, String email, String ipAddress, double latitude, double longitude, String city) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email =  email;
            this.ipAddress = ipAddress;
            this.latitude = latitude;
            this.longitude = longitude;
            this.city = city;
        }

        public User() {

        };

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
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

        public String getIpAddress() {
                return ipAddress;
        }

        public double getLatitude() {
                return latitude;
        }

        public double getLongitude() {
                return longitude;
        }

        public String getCity() { return city;}
}
