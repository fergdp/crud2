package com.madrefoca.crud2.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "Months")
public class Month extends AuditModel implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Getter @Setter
    private Long id;

    @Getter @Setter
    private String name;

}
