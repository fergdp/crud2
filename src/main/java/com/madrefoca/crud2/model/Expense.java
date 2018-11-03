package com.madrefoca.crud2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Expenses")
public class Expense extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Float amount;

    @ManyToOne(optional = false)
    @JoinColumn(name = "month_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Month month;


}
