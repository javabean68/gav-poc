package ch.egroup.interview.domain.rules.composition;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class CompositionStrategyConverter implements AttributeConverter<CompositionStrategy,String> {

    public static final String OVERWRITE = "overwrite";
    public static final String REPLACE = "replace";

    @Override
    public String convertToDatabaseColumn(CompositionStrategy compositionStrategy) {
        if(compositionStrategy == null) return OVERWRITE;
        return compositionStrategy.getClass().getSimpleName().toLowerCase();
    }

    @Override
    public CompositionStrategy convertToEntityAttribute(String dbData) {
        //works as a factory
        if (dbData.equals(OVERWRITE)) {
            return new OverwriteStrategy();
        } else if (dbData.equals(REPLACE)) {
            return new UnionStrategy();
        }
        return null;
    }
}