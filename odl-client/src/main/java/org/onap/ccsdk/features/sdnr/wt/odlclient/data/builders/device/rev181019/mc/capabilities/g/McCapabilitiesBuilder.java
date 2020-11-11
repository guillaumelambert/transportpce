/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.mc.capabilities.g;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.FrequencyGHz;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.mc.capabilities.g.McCapabilities;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link McCapabilitiesBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     McCapabilitiesBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new McCapabilitiesBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of McCapabilitiesBuilder, as instances can be freely passed around without
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
 * @see McCapabilitiesBuilder
 * @see Builder
 *
 */
public class McCapabilitiesBuilder implements Builder<McCapabilities> {

    private FrequencyGHz _centerFreqGranularity;
    private Uint32 _maxSlots;
    private Uint32 _minSlots;
    private FrequencyGHz _slotWidthGranularity;


    Map<Class<? extends Augmentation<McCapabilities>>, Augmentation<McCapabilities>> augmentation = Collections.emptyMap();

    public McCapabilitiesBuilder() {
    }

    public McCapabilitiesBuilder(McCapabilities base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<McCapabilities>>, Augmentation<McCapabilities>> aug =((AugmentationHolder<McCapabilities>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this._centerFreqGranularity = base.getCenterFreqGranularity();
        this._maxSlots = base.getMaxSlots();
        this._minSlots = base.getMinSlots();
        this._slotWidthGranularity = base.getSlotWidthGranularity();
    }


    public FrequencyGHz getCenterFreqGranularity() {
        return _centerFreqGranularity;
    }
    
    public Uint32 getMaxSlots() {
        return _maxSlots;
    }
    
    public Uint32 getMinSlots() {
        return _minSlots;
    }
    
    public FrequencyGHz getSlotWidthGranularity() {
        return _slotWidthGranularity;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<McCapabilities>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public McCapabilitiesBuilder setCenterFreqGranularity(final FrequencyGHz value) {
        this._centerFreqGranularity = value;
        return this;
    }
    
    public McCapabilitiesBuilder setMaxSlots(final Uint32 value) {
        this._maxSlots = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setMaxSlots(Uint32)} instead.
     
    @Deprecated(forRemoval = true)
    public McCapabilitiesBuilder setMaxSlots(final Long value) {
        return setMaxSlots(CodeHelpers.compatUint(value));
    }*/
    
    public McCapabilitiesBuilder setMinSlots(final Uint32 value) {
        this._minSlots = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setMinSlots(Uint32)} instead.
     
    @Deprecated(forRemoval = true)
    public McCapabilitiesBuilder setMinSlots(final Long value) {
        return setMinSlots(CodeHelpers.compatUint(value));
    }*/
    
    public McCapabilitiesBuilder setSlotWidthGranularity(final FrequencyGHz value) {
        this._slotWidthGranularity = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public McCapabilitiesBuilder addAugmentation(Augmentation<McCapabilities> augmentation) {
        return doAddAugmentation(augmentation.implementedInterface(), augmentation);
    }
    
    /**
      * Add or remove an augmentation to this builder's product.
      *
      * @param augmentationType augmentation type to be added or removed
      * @param augmentationValue augmentation value, null if the augmentation type should be removed
      * @return this builder
      * @deprecated Use either {@link #addAugmentation(Augmentation)} or {@link #removeAugmentation(Class)} instead.
     
    @Deprecated(forRemoval = true)
    public McCapabilitiesBuilder addAugmentation(Class<? extends Augmentation<McCapabilities>> augmentationType, Augmentation<McCapabilities> augmentationValue) {
        return augmentationValue == null ? removeAugmentation(augmentationType) : doAddAugmentation(augmentationType, augmentationValue);
    } */
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public McCapabilitiesBuilder removeAugmentation(Class<? extends Augmentation<McCapabilities>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }
    
    private McCapabilitiesBuilder doAddAugmentation(Class<? extends Augmentation<McCapabilities>> augmentationType, Augmentation<McCapabilities> augmentationValue) {
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    @Override
    public McCapabilities build() {
        return new McCapabilitiesImpl(this);
    }

    private static final class McCapabilitiesImpl
        extends AbstractAugmentable<McCapabilities>
        implements McCapabilities {
    
        private final FrequencyGHz _centerFreqGranularity;
        private final Uint32 _maxSlots;
        private final Uint32 _minSlots;
        private final FrequencyGHz _slotWidthGranularity;
    
        McCapabilitiesImpl(McCapabilitiesBuilder base) {
            super(base.augmentation);
            this._centerFreqGranularity = base.getCenterFreqGranularity();
            this._maxSlots = base.getMaxSlots();
            this._minSlots = base.getMinSlots();
            this._slotWidthGranularity = base.getSlotWidthGranularity();
        }
    
        @Override
        public FrequencyGHz getCenterFreqGranularity() {
            return _centerFreqGranularity;
        }
        
        @Override
        public Uint32 getMaxSlots() {
            return _maxSlots;
        }
        
        @Override
        public Uint32 getMinSlots() {
            return _minSlots;
        }
        
        @Override
        public FrequencyGHz getSlotWidthGranularity() {
            return _slotWidthGranularity;
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
            result = prime * result + Objects.hashCode(_centerFreqGranularity);
            result = prime * result + Objects.hashCode(_maxSlots);
            result = prime * result + Objects.hashCode(_minSlots);
            result = prime * result + Objects.hashCode(_slotWidthGranularity);
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
            if (!McCapabilities.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            McCapabilities other = (McCapabilities)obj;
            if (!Objects.equals(_centerFreqGranularity, other.getCenterFreqGranularity())) {
                return false;
            }
            if (!Objects.equals(_maxSlots, other.getMaxSlots())) {
                return false;
            }
            if (!Objects.equals(_minSlots, other.getMinSlots())) {
                return false;
            }
            if (!Objects.equals(_slotWidthGranularity, other.getSlotWidthGranularity())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                McCapabilitiesImpl otherImpl = (McCapabilitiesImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<McCapabilities>>, Augmentation<McCapabilities>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("McCapabilities");
            CodeHelpers.appendValue(helper, "_centerFreqGranularity", _centerFreqGranularity);
            CodeHelpers.appendValue(helper, "_maxSlots", _maxSlots);
            CodeHelpers.appendValue(helper, "_minSlots", _minSlots);
            CodeHelpers.appendValue(helper, "_slotWidthGranularity", _slotWidthGranularity);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
