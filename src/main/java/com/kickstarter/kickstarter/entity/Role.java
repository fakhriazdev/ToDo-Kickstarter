package com.kickstarter.kickstarter.entity;

import com.kickstarter.kickstarter.constant.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_role")
public class Role {
    @Id
    @Column(name = "id")
    private String id;
    @Enumerated(EnumType.STRING)
    private ERole name;
}
