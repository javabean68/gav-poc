package ch.egroup.interview.domain.rules.concrete;

import ch.egroup.interview.domain.rules.Regel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "FerienRegel")
@DiscriminatorValue("FerienRegel")

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class FerienRegel extends Regel {
    @Column
    private int maxNumOfDays;
}