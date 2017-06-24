package com.metrohospital.tgs.cococititask.datamodal;
import java.util.List;
/**
 * Created by pankaj on 6/23/2017.
 */
public class LoginModel {
    public String status;
    public String info;
    public Data data;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public Data getData() {
        return data;
    }
    public void setData(Data data) {
        this.data = data;
    }
    public class Data {
        public User user;
        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }
        public class User {
            public String access_token;
            public String email;
            public String getAccess_token() {
                return access_token;
            }
            public void setAccess_token(String access_token) {
                this.access_token = access_token;
            }
            public String getEmail() {
                return email;
            }
            public void setEmail(String email) {
                this.email = email;
            }
        }
    }
}
//"status": "success",
//        "info": "LoginModel successful",
//        "data": {
//        "user": {
//        "access_token": "ptkrdubqwfilmxze",
//        "email": "test001@test.com"
//        }
//        }