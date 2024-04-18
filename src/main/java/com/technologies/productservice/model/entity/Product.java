package com.technologies.productservice.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    private double price;

    private int quantity;

    private double total;

    @Transient // This annotation ensures that this field is not persisted in the database
    private String formattedTotal;

    public String getFormattedTotal() {
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return format.format(this.total);
    }

    //To show all value
    public String getTotal() {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(total);
    }

    @Getter
    public enum ProductType {
        BOOK,
        LAPTOP
    }


}
