package com.rest_service.REST_API.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest_service.REST_API.Posts.PostModel;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Users")
public class UserModel {
    @Id
    private Integer id;
    @Size(min=2,message ="Name should have atleast 2 characters..")
    @Column(name="Name")
    private String Name;
    @Column(name = "birth_date")
    private String birthDate;

    public List<PostModel> getPosts() {
        return posts;
    }

    public void setPosts(List<PostModel> posts) {
        this.posts = posts;
    }

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<PostModel> posts;
    public UserModel(){};
    public UserModel(Integer id, String name, String birthDate) {
        this.id = id;
        Name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
    @Override
    public String toString(){
        return "id:=" + id + "Name:=" + Name + "Birth Date is:=" + birthDate;

    }
}
