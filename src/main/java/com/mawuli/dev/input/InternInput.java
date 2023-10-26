package com.mawuli.dev.input;

import com.mawuli.dev.entity.Intern;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
public class InternInput {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String school;
    private String dateOfBirth;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Intern generateIntern() throws Exception {
        try{
            Date date = Date.valueOf(dateOfBirth);
            return new Intern(id, firstName, lastName, school, date);
        }catch (Exception e){
            throw new Exception("Invalid Date Format: should be of format Y-d-m");
        }
    }
}
