package com.kickstarter.kickstarter.entity;

import com.kickstarter.kickstarter.constant.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_status")
public class Status {
    @Id
    private String id;
    @Enumerated(EnumType.STRING)
    private EStatus status;
}
