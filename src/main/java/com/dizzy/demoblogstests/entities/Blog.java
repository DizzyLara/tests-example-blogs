package com.dizzy.demoblogstests.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Blog {

    @Id
    @GeneratedValue
    private Long id;
    // title must not be longer than 65 characters, must not be shorter than 8 characters
    private String title;
    // optional, must not be longer than 120 characters when present.
    private String subtitle;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;

    @OneToMany(mappedBy = "blog")
    @JsonManagedReference
    private List<BlogPost> posts;


}
