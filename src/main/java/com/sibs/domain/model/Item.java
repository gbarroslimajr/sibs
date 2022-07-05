package com.sibs.domain.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "id")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "item", schema = "public")
public class Item {

    @Id
    @EqualsAndHashCode.Include
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private Double quantity;


}
