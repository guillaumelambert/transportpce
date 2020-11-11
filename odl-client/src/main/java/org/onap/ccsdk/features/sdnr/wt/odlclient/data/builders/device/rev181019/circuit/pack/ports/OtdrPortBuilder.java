/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.circuit.pack.ports;
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
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.Direction;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.ports.OtdrPort;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link OtdrPortBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     OtdrPortBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new OtdrPortBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of OtdrPortBuilder, as instances can be freely passed around without
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
 * @see OtdrPortBuilder
 * @see Builder
 *
 */
public class OtdrPortBuilder implements Builder<OtdrPort> {

    private Uint32 _launchCableLength;
    private Direction _portDirection;


    Map<Class<? extends Augmentation<OtdrPort>>, Augmentation<OtdrPort>> augmentation = Collections.emptyMap();

    public OtdrPortBuilder() {
    }

    public OtdrPortBuilder(OtdrPort base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<OtdrPort>>, Augmentation<OtdrPort>> aug =((AugmentationHolder<OtdrPort>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this._launchCableLength = base.getLaunchCableLength();
        this._portDirection = base.getPortDirection();
    }


    public Uint32 getLaunchCableLength() {
        return _launchCableLength;
    }
    
    public Direction getPortDirection() {
        return _portDirection;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<OtdrPort>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public OtdrPortBuilder setLaunchCableLength(final Uint32 value) {
        this._launchCableLength = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setLaunchCableLength(Uint32)} instead.
     
    @Deprecated(forRemoval = true)
    public OtdrPortBuilder setLaunchCableLength(final Long value) {
        return setLaunchCableLength(CodeHelpers.compatUint(value));
    }*/
    
    public OtdrPortBuilder setPortDirection(final Direction value) {
        this._portDirection = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public OtdrPortBuilder addAugmentation(Augmentation<OtdrPort> augmentation) {
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
    public OtdrPortBuilder addAugmentation(Class<? extends Augmentation<OtdrPort>> augmentationType, Augmentation<OtdrPort> augmentationValue) {
        return augmentationValue == null ? removeAugmentation(augmentationType) : doAddAugmentation(augmentationType, augmentationValue);
    }*/
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public OtdrPortBuilder removeAugmentation(Class<? extends Augmentation<OtdrPort>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }
    
    private OtdrPortBuilder doAddAugmentation(Class<? extends Augmentation<OtdrPort>> augmentationType, Augmentation<OtdrPort> augmentationValue) {
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    @Override
    public OtdrPort build() {
        return new OtdrPortImpl(this);
    }

    private static final class OtdrPortImpl
        extends AbstractAugmentable<OtdrPort>
        implements OtdrPort {
    
        private final Uint32 _launchCableLength;
        private final Direction _portDirection;
    
        OtdrPortImpl(OtdrPortBuilder base) {
            super(base.augmentation);
            this._launchCableLength = base.getLaunchCableLength();
            this._portDirection = base.getPortDirection();
        }
    
        @Override
        public Uint32 getLaunchCableLength() {
            return _launchCableLength;
        }
        
        @Override
        public Direction getPortDirection() {
            return _portDirection;
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
            result = prime * result + Objects.hashCode(_launchCableLength);
            result = prime * result + Objects.hashCode(_portDirection);
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
            if (!OtdrPort.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            OtdrPort other = (OtdrPort)obj;
            if (!Objects.equals(_launchCableLength, other.getLaunchCableLength())) {
                return false;
            }
            if (!Objects.equals(_portDirection, other.getPortDirection())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                OtdrPortImpl otherImpl = (OtdrPortImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<OtdrPort>>, Augmentation<OtdrPort>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("OtdrPort");
            CodeHelpers.appendValue(helper, "_launchCableLength", _launchCableLength);
            CodeHelpers.appendValue(helper, "_portDirection", _portDirection);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
