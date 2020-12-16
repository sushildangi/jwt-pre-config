package com.sushildangi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private String id;

    @Column(length = 60, name = "name")
    private String name;
}
