package com.dng.api.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CATEGORY")
@SequenceGenerator(name = "categorySeq", sequenceName = "CAT_SEQ", allocationSize = 1, initialValue = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category  {
    @Id
    private String id;
    private String name;
    @OneToMany(mappedBy = "category")
    @JsonBackReference
    Set<Course> categories;
}
