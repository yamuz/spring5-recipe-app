package guru.springframework.domain;

import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
public class UnitOfMeasure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ingredient ingredient;


}
