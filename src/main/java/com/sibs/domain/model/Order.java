package com.sibs.domain.model;


import com.sibs.api.dto.OrderDTO;
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
@Table(name = "orders", schema = "public")
@SqlResultSetMapping(
        name = "OrderDtoMapping",
        classes = {
                @ConstructorResult(
                        targetClass = OrderDTO.class,
                        columns = {
                                @ColumnResult(name = "id", type = Long.class),
                                @ColumnResult(name = "creation_date", type = OffsetDateTime.class),
                                @ColumnResult(name = "fulfilled", type = Double.class),
                                @ColumnResult(name = "pending", type = Double.class),
                                @ColumnResult(name = "quantity", type = Double.class),
                                @ColumnResult(name = "item_id", type = Integer.class),
                                @ColumnResult(name = "user_id", type = Integer.class),
                                @ColumnResult(name = "itemName", type = String.class),
                                @ColumnResult(name = "userName", type = String.class)
                        }
                )
        }
)
public class Order {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private OffsetDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(nullable = false)
    private Double quantity;

    @Column()
    private Double fulfilled;

    @Column()
    private Double pending;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_stock_movement",
            joinColumns = {
                    @JoinColumn(name = "order_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "stock_movement_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})

    private Set<StockMovement> stockMovement  = new HashSet<>();;
}
