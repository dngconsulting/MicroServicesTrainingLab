package com.dng.api.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import javax.persistence.*;

@Entity
@Table(name = "COURSE")
@SequenceGenerator(name = "courseSeq", sequenceName = "COURSE_SEQ", allocationSize = 1, initialValue = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    private String id;

    private String code;

    private String name;

    private String description;

    /*@ManyToOne
    @JsonManagedReference
    private Category category;*/
}