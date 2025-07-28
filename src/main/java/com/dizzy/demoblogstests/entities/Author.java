package com.dizzy.demoblogstests.entities;



import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // name must be a combination of "FirstName SecondName"
    private String name;

    @OneToMany(mappedBy = "author")
    @JsonManagedReference
    private List<Blog> blogs;
}
