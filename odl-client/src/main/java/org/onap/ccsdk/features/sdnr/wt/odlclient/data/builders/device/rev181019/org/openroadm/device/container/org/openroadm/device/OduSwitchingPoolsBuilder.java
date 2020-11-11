/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Deprecated;
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
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.OduSwitchingPools;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.OduSwitchingPoolsKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.NonBlockingList;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.NonBlockingListKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.switching.pool.types.rev171215.SwitchingPoolTypes;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * Class that builds {@link OduSwitchingPoolsBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     OduSwitchingPoolsBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new OduSwitchingPoolsBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of OduSwitchingPoolsBuilder, as instances can be freely passed around without
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
 * @see OduSwitchingPoolsBuilder
 * @see Builder
 *
 */
public class OduSwitchingPoolsBuilder implements Builder<OduSwitchingPools> {

    private Map<NonBlockingListKey, NonBlockingList> _nonBlockingList;
    private Uint16 _switchingPoolNumber;
    private SwitchingPoolTypes _switchingPoolType;
    private OduSwitchingPoolsKey key;


    Map<Class<? extends Augmentation<OduSwitchingPools>>, Augmentation<OduSwitchingPools>> augmentation = Collections.emptyMap();

    public OduSwitchingPoolsBuilder() {
    }

    public OduSwitchingPoolsBuilder(OduSwitchingPools base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<OduSwitchingPools>>, Augmentation<OduSwitchingPools>> aug =((AugmentationHolder<OduSwitchingPools>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._switchingPoolNumber = base.getSwitchingPoolNumber();
        this._nonBlockingList = base.getNonBlockingList();
        this._switchingPoolType = base.getSwitchingPoolType();
    }


    public OduSwitchingPoolsKey key() {
        return key;
    }
    
    public Map<NonBlockingListKey, NonBlockingList> getNonBlockingList() {
        return _nonBlockingList;
    }
    
    public Uint16 getSwitchingPoolNumber() {
        return _switchingPoolNumber;
    }
    
    public SwitchingPoolTypes getSwitchingPoolType() {
        return _switchingPoolType;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<OduSwitchingPools>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public OduSwitchingPoolsBuilder withKey(final OduSwitchingPoolsKey key) {
        this.key = key;
        return this;
    }
    public OduSwitchingPoolsBuilder setNonBlockingList(final Map<NonBlockingListKey, NonBlockingList> values) {
        this._nonBlockingList = values;
        return this;
    }
    
    /**
      * Utility migration setter.
      *
      * <b>IMPORTANT NOTE</b>: This method does not completely match previous mechanics, as the list is processed as
      *                        during this method's execution. Any future modifications of the list are <b>NOT</b>
      *                        reflected in this builder nor its products.
      *
      * @param values Legacy List of values
      * @return this builder
      * @throws IllegalArgumentException if the list contains entries with the same key
      * @throws NullPointerException if the list contains a null entry
      * @deprecated Use {#link #setNonBlockingList(Map)} instead.
      
    @Deprecated(forRemoval = true)
    public OduSwitchingPoolsBuilder setNonBlockingList(final List<NonBlockingList> values) {
        return setNonBlockingList(CodeHelpers.compatMap(values));
    }*/
    
    public OduSwitchingPoolsBuilder setSwitchingPoolNumber(final Uint16 value) {
        this._switchingPoolNumber = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setSwitchingPoolNumber(Uint16)} instead.
     
    @Deprecated(forRemoval = true)
    public OduSwitchingPoolsBuilder setSwitchingPoolNumber(final Integer value) {
        return setSwitchingPoolNumber(CodeHelpers.compatUint(value));
    }*/
    
    public OduSwitchingPoolsBuilder setSwitchingPoolType(final SwitchingPoolTypes value) {
        this._switchingPoolType = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public OduSwitchingPoolsBuilder addAugmentation(Augmentation<OduSwitchingPools> augmentation) {
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
    public OduSwitchingPoolsBuilder addAugmentation(Class<? extends Augmentation<OduSwitchingPools>> augmentationType, Augmentation<OduSwitchingPools> augmentationValue) {
        return augmentationValue == null ? removeAugmentation(augmentationType) : doAddAugmentation(augmentationType, augmentationValue);
    }*/
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public OduSwitchingPoolsBuilder removeAugmentation(Class<? extends Augmentation<OduSwitchingPools>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }
    
    private OduSwitchingPoolsBuilder doAddAugmentation(Class<? extends Augmentation<OduSwitchingPools>> augmentationType, Augmentation<OduSwitchingPools> augmentationValue) {
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    @Override
    public OduSwitchingPools build() {
        return new OduSwitchingPoolsImpl(this);
    }

    private static final class OduSwitchingPoolsImpl
        extends AbstractAugmentable<OduSwitchingPools>
        implements OduSwitchingPools {
    
        private final Map<NonBlockingListKey, NonBlockingList> _nonBlockingList;
        private final Uint16 _switchingPoolNumber;
        private final SwitchingPoolTypes _switchingPoolType;
        private final OduSwitchingPoolsKey key;
    
        OduSwitchingPoolsImpl(OduSwitchingPoolsBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new OduSwitchingPoolsKey(base.getSwitchingPoolNumber());
            }
            this._switchingPoolNumber = key.getSwitchingPoolNumber();
            this._nonBlockingList = CodeHelpers.emptyToNull(base.getNonBlockingList());
            this._switchingPoolType = base.getSwitchingPoolType();
        }
    
        @Override
        public OduSwitchingPoolsKey key() {
            return key;
        }
        
        @Override
        public Map<NonBlockingListKey, NonBlockingList> getNonBlockingList() {
            return _nonBlockingList;
        }
        
        @Override
        public Uint16 getSwitchingPoolNumber() {
            return _switchingPoolNumber;
        }
        
        @Override
        public SwitchingPoolTypes getSwitchingPoolType() {
            return _switchingPoolType;
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
            result = prime * result + Objects.hashCode(_nonBlockingList);
            result = prime * result + Objects.hashCode(_switchingPoolNumber);
            result = prime * result + Objects.hashCode(_switchingPoolType);
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
            if (!OduSwitchingPools.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            OduSwitchingPools other = (OduSwitchingPools)obj;
            if (!Objects.equals(_nonBlockingList, other.getNonBlockingList())) {
                return false;
            }
            if (!Objects.equals(_switchingPoolNumber, other.getSwitchingPoolNumber())) {
                return false;
            }
            if (!Objects.equals(_switchingPoolType, other.getSwitchingPoolType())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                OduSwitchingPoolsImpl otherImpl = (OduSwitchingPoolsImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<OduSwitchingPools>>, Augmentation<OduSwitchingPools>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("OduSwitchingPools");
            CodeHelpers.appendValue(helper, "_nonBlockingList", _nonBlockingList);
            CodeHelpers.appendValue(helper, "_switchingPoolNumber", _switchingPoolNumber);
            CodeHelpers.appendValue(helper, "_switchingPoolType", _switchingPoolType);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
