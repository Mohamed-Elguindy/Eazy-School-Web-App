package com.elguindy.eazyschoolwebapp.model;

import jakarta.persistence.*;
import lombok.*;




@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity{

    @Id
    String day;

    String reason;
    @Enumerated(EnumType.STRING)
    Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
