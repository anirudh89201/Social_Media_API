package com.rest_service.REST_API.Posts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rest_service.REST_API.User.UserModel;
import jakarta.persistence.*;

@Entity
@Table(name = "POSTS")
public class PostModel {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "description")
    private String description;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private UserModel user;
    protected PostModel(){};
    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public PostModel(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
