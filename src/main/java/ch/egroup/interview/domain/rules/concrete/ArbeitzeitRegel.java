package ch.egroup.interview.domain.rules.concrete;

import ch.egroup.interview.domain.rules.Regel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "ArbeitzeitRegel")
@DiscriminatorValue("ArbeitzeitRegel")

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArbeitzeitRegel extends Regel {
    @Column
    private int maxNumOfHoursPerDay;

    @Column
    private int pauseDuration;
}