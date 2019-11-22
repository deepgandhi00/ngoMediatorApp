package com.example.deepgandhi.ngoapp.models.Request;

import java.util.Date;

public class RequestUser {
        private String password;

        private String phone;

        private String date_of_birth;

        private String name;

        private String email;

        public String getPassword ()
        {
            return password;
        }

        public void setPassword (String password)
        {
            this.password = password;
        }

        public String getPhone ()
        {
            return phone;
        }

        public void setPhone (String phone)
        {
            this.phone = phone;
        }

        public String getDate_of_birth ()
        {
            return date_of_birth;
        }

        public void setDate_of_birth (String date_of_birth)
        {
            this.date_of_birth = date_of_birth;
        }

        public String getName ()
        {
            return name;
        }

        public void setName (String name)
        {
            this.name = name;
        }

        public String getEmail ()
        {
            return email;
        }

        public void setEmail (String email)
        {
            this.email = email;
        }
}
