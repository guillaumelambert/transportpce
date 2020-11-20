/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.odu.interfaces.rev181019.parent.odu.allocation;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.parent.odu.allocation.ParentOduAllocation;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * Class that builds {@link ParentOduAllocationBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ParentOduAllocationBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ParentOduAllocationBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of ParentOduAllocationBuilder, as instances can be freely passed around without
 * worrying about synchronization issues.
 *
 * <p>
 * As a side note: method chaining results in:
 * <ul>
 *   <li>very efficient Java bytecode, as the method invocation result, in this case the Builder reference, is
 *       on the stack, so further method invocations just need to fill method arguments for the next method
 *       invocation, which is terminated by {@link #build()}, which is then returned from the method</li>
 *   <li>better understanding by humans, as the scope of mutable state (the builder) is kept to a minimum and is
 *       very localized</li>
 *   <li>better optimization oportunities, as the object scope is minimized in terms of invocation (rather than
 *       method) stack, making <a href="https://en.wikipedia.org/wiki/Escape_analysis">escape analysis</a> a lot
 *       easier. Given enough compiler (JIT/AOT) prowess, the cost of th builder object can be completely
 *       eliminated</li>
 * </ul>
 *
 * @see ParentOduAllocationBuilder
 * @see Builder
 *
 */
public class ParentOduAllocationBuilder implements Builder<ParentOduAllocation> {

    private Uint16 _tribPortNumber;
    private List<Uint16> _tribSlots;


    Map<Class<? extends Augmentation<ParentOduAllocation>>, Augmentation<ParentOduAllocation>> augmentation = Collections.emptyMap();

    public ParentOduAllocationBuilder() {
    }

    public ParentOduAllocationBuilder(ParentOduAllocation base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<ParentOduAllocation>>, Augmentation<ParentOduAllocation>> aug =((AugmentationHolder<ParentOduAllocation>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this._tribPortNumber = base.getTribPortNumber();
        this._tribSlots = base.getTribSlots();
    }


    public Uint16 getTribPortNumber() {
        return _tribPortNumber;
    }
    
    public List<Uint16> getTribSlots() {
        return _tribSlots;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<ParentOduAllocation>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }

    
    private static void checkTribPortNumberRange(final int value) {
        if (value >= 1 && value <= 80) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..80]]", value);
    }
    
    public ParentOduAllocationBuilder setTribPortNumber(final Uint16 value) {
        if (value != null) {
            checkTribPortNumberRange(value.intValue());
            
        }
        this._tribPortNumber = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setTribPortNumber(Uint16)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public ParentOduAllocationBuilder setTribPortNumber(final Integer value) {
//        return setTribPortNumber(CodeHelpers.compatUint(value));
//    }
    private static void checkTribSlotsRange(final int value) {
        if (value >= 1 && value <= 80) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..80]]", value);
    }
    public ParentOduAllocationBuilder setTribSlots(final List<Uint16> values) {
    if (values != null) {
       for (Uint16 value : values) {
           checkTribSlotsRange(value.intValue());
           
       }
    }
        this._tribSlots = values;
        return this;
    }
    
    
    public ParentOduAllocationBuilder addAugmentation(Class<? extends Augmentation<ParentOduAllocation>> augmentationType, Augmentation<ParentOduAllocation> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }
    
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }
    
    public ParentOduAllocationBuilder removeAugmentation(Class<? extends Augmentation<ParentOduAllocation>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public ParentOduAllocation build() {
        return new ParentOduAllocationImpl(this);
    }

    private static final class ParentOduAllocationImpl
        extends AbstractAugmentable<ParentOduAllocation>
        implements ParentOduAllocation {
    
        private final Uint16 _tribPortNumber;
        private final List<Uint16> _tribSlots;
    
        ParentOduAllocationImpl(ParentOduAllocationBuilder base) {
            super(base.augmentation);
            this._tribPortNumber = base.getTribPortNumber();
            this._tribSlots = base.getTribSlots();
        }
    
        @Override
        public Uint16 getTribPortNumber() {
            return _tribPortNumber;
        }
        
        @Override
        public List<Uint16> getTribSlots() {
            return _tribSlots;
        }
    
        private int hash = 0;
        private volatile boolean hashValid = false;
        
        @Override
        public int hashCode() {
            if (hashValid) {
                return hash;
            }
        
            final int prime = 31;
            int result = 1;
            result = prime * result + Objects.hashCode(_tribPortNumber);
            result = prime * result + Objects.hashCode(_tribSlots);
            result = prime * result + Objects.hashCode(augmentations());
        
            hash = result;
            hashValid = true;
            return result;
        }
    
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DataObject)) {
                return false;
            }
            if (!ParentOduAllocation.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            ParentOduAllocation other = (ParentOduAllocation)obj;
            if (!Objects.equals(_tribPortNumber, other.getTribPortNumber())) {
                return false;
            }
            if (!Objects.equals(_tribSlots, other.getTribSlots())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                ParentOduAllocationImpl otherImpl = (ParentOduAllocationImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<ParentOduAllocation>>, Augmentation<ParentOduAllocation>> e : augmentations().entrySet()) {
                    if (!e.getValue().equals(other.augmentation(e.getKey()))) {
                        return false;
                    }
                }
                // .. and give the other one the chance to do the same
                if (!obj.equals(this)) {
                    return false;
                }
            }
            return true;
        }
    
        @Override
        public String toString() {
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("ParentOduAllocation");
            CodeHelpers.appendValue(helper, "_tribPortNumber", _tribPortNumber);
            CodeHelpers.appendValue(helper, "_tribSlots", _tribSlots);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
