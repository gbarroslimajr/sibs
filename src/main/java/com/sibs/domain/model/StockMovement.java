package com.sibs.domain.model;


import com.sibs.api.dto.StockMovementDTO;
import lombok.*;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(exclude = "id")
@Table(name = "stock_movements", schema = "public")
@SqlResultSetMapping(
        name = "StockMovementDtoMapping",
        classes = {
                @ConstructorResult(
                        targetClass = StockMovementDTO.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "creation_date", type = OffsetDateTime.class),
                                @ColumnResult(name = "quantity", type = Double.class),
                                @ColumnResult(name = "item_id", type = Integer.class),
                                @ColumnResult(name = "itemname", type = String.class)

                        }
                )
        }
)
public class StockMovement {

    @Id
    @EqualsAndHashCode.Include
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(nullable = false)
    private OffsetDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Getter @Setter
    private Double quantity;

    @ManyToMany(mappedBy = "stockMovement" , fetch = FetchType.EAGER)
    private Set<Order> orders = new HashSet<>();;
}
