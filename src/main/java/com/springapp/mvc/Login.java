/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.springapp.mvc;

/**
 *
 * @author ����
 */
public class Login {
    
    String login;
    String password;
    
    public Login()
    {
        
    }
    public Login(String login, String password)
    {
        this.login=login;
        this.password=password;
    }
    
    public void setLogin(String login)
    {
        this.login=login;
    }
    public void setPassword(String password)
    {
        this.password=password;
    }
    
    public String getLogin()
    {
        return this.login;
    }
    public String getPassword()
    {
        return this.password;
    }
    
}
