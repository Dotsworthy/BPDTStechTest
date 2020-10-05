# BPDTStechTest
Test App that takes all users from BPTDS test API and creates a new API, containing all users from BPDTS API that either live in London or coordinates are 60 miles from London.

To Run:

Run BpdtstestApplication from main/java/com.example.bpdts.BPDTSTEST

Routes:

localhost:8080/users - Fetch all users

localhost:8080/users?id=# - Fetch specific user

localhost:8080/users?city="" - Fetch users by city

The API will return 9 entries, 6 users living in London, 3 users who are within 60 miles of London.
