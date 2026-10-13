package com.example.btvnbuoi2.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "`group`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer id;

    @Column(name = "group_name", length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Account creator;

    @Column(name = "create_date")
    private LocalDateTime createDate;
}
