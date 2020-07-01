package com.sg.btbb.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author brian russick
 */
public class User {
    
    private int userId;
    
    @NotEmpty(message = "Must enter name!")
    @Length(max = 75, message = "Input is 75 char max! Use less characters!")
    private String name;
    
    @NotEmpty(message = "Must enter user name!")
    @Length(max = 75, message = "Input is 75 char max! Use less characters!")
    private String userName;
    
    @Email(message = "Must enter email address!")
    @Length(max = 75, message = "Input is 75 char max! Use less characters!")
    private String email;
    
    @NotEmpty(message = "Must enter password!")
    @Length(max = 75, message = "Input is 75 char max! Use less characters!")
    private String password;
    
    private List<DesignationRole> designationRoles = new ArrayList<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<DesignationRole> getDesignationRoles() {
        return designationRoles;
    }

    public void setDesignationRoles(List<DesignationRole> designationRoles) {
        this.designationRoles = designationRoles;
    }
    
    public void addRole(DesignationRole designationRole) {
        designationRoles.add(designationRole);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.userId;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.userName);
        hash = 23 * hash + Objects.hashCode(this.email);
        hash = 23 * hash + Objects.hashCode(this.password);
        hash = 23 * hash + Objects.hashCode(this.designationRoles);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.userId != other.userId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.designationRoles, other.designationRoles)) {
            return false;
        }
        return true;
    }  
}