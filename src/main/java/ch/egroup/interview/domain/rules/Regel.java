package ch.egroup.interview.domain.rules;

import ch.egroup.interview.domain.anagraphics.Branche;
import ch.egroup.interview.domain.anagraphics.Kunde;
import ch.egroup.interview.domain.anagraphics.Mitarbeiter;
import ch.egroup.interview.domain.rules.composition.CompositionStrategy;
import ch.egroup.interview.domain.rules.composition.CompositionStrategyConverter;
import ch.egroup.interview.domain.rules.concrete.ArbeitzeitRegel;
import ch.egroup.interview.domain.rules.concrete.FerienRegel;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Regel_Type")

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter

@JsonTypeInfo( use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "regel_type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ArbeitzeitRegel.class, name = "ArbeitzeitRegel"),
        @JsonSubTypes.Type(value = FerienRegel.class, name = "FerienRegel")
})
public abstract class Regel {
 
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
 
    @Column
    protected String description;
 
    @Version
    @Column(name = "version")
    protected int version;

    @Column(columnDefinition = "DATE")
    protected LocalDate validFrom;

    @Column(columnDefinition = "DATE")
    protected LocalDate validTo;

    @Convert(converter = CompositionStrategyConverter.class)
    protected CompositionStrategy compositionStrategy;

    @ManyToOne
    @JoinColumn(name = "mitarbeiter_id")
    private Mitarbeiter mitarbeiter;

    @ManyToOne
    @JoinColumn(name = "branche_id")
    private Branche branche;

    @ManyToOne
    @JoinColumn(name = "kunde_id")
    private Kunde kunde;

    public Set<? super Regel> addToSet(Set<? super Regel> inputSet){
        //apply current strategy
        compositionStrategy.composeWith(inputSet, this);
        return inputSet;
    }
}