package ch.egroup.interview.domain.rules.concrete;

import ch.egroup.interview.domain.rules.Regel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "LohnRegel")
@DiscriminatorValue("LohnRegel")

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class LohnRegel extends Regel {
    @Column
    private double minLohn;
}