package com.team33.evotingsystem.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int roleId;

    @Column(name = "role_name")
    private	String roleName;

    @ManyToMany(mappedBy = "roles")
    List<LoginCredentials> credentials;
}
