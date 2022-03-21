/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.googleLogin;

/**
 *
 * @author HP
 */
public class GooglePojo {
  private String id;
  private String email;

    public GooglePojo() {
        id="";
        email = "";
    }

    public GooglePojo(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }   
}
