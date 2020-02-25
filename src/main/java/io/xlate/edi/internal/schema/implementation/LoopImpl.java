package io.xlate.edi.internal.schema.implementation;

import java.util.List;

import io.xlate.edi.schema.EDIComplexType;
import io.xlate.edi.schema.EDIReference;
import io.xlate.edi.schema.EDIType;
import io.xlate.edi.schema.implementation.Discriminator;
import io.xlate.edi.schema.implementation.EDITypeImplementation;
import io.xlate.edi.schema.implementation.LoopImplementation;

public class LoopImpl implements LoopImplementation {

    private static final String TOSTRING_FORMAT = "id: %s, minOccurs: %d, maxOccurs: %d, discriminator: { %s }, standard: { %s }";
    private final String id;
    private final String typeId;
    private final Discriminator discriminator;
    private final List<EDITypeImplementation> sequence;

    private EDIComplexType standard;
    private int minOccurs;
    private int maxOccurs;

    public LoopImpl(int minOccurs,
            int maxOccurs,
            String id,
            String typeId,
            Discriminator discriminator,
            List<EDITypeImplementation> sequence) {
        this.minOccurs = minOccurs;
        this.maxOccurs = maxOccurs;
        this.id = id;
        this.typeId = typeId;
        this.discriminator = discriminator;
        this.sequence = sequence;
    }

    @Override
    public String toString() {
        return String.format(TOSTRING_FORMAT, id, minOccurs, maxOccurs, discriminator, standard);
    }

    @Override
    public EDIType getReferencedType() {
        return standard;
    }

    @Override
    public int getMinOccurs() {
        return minOccurs;
    }

    @Override
    public int getMaxOccurs() {
        return maxOccurs;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Discriminator getDiscriminator() {
        return discriminator;
    }

    @Override
    public List<EDITypeImplementation> getSequence() {
        return sequence;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setStandard(EDIReference standard) {
        this.standard = (EDIComplexType) standard.getReferencedType();
        if (this.minOccurs < 0) {
            this.minOccurs = standard.getMinOccurs();
        }
        if (this.maxOccurs < 0) {
            this.maxOccurs = standard.getMaxOccurs();
        }
    }
}
