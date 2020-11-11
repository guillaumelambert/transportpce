/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.NonBlockingList;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.NonBlockingListKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.non.blocking.list.PluggableOpticsHolderList;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.non.blocking.list.PluggableOpticsHolderListKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.non.blocking.list.PortList;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.odu.switching.pools.non.blocking.list.PortListKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link NonBlockingListBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     NonBlockingListBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new NonBlockingListBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of NonBlockingListBuilder, as instances can be freely passed around without
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
 * @see NonBlockingListBuilder
 * @see Builder
 *
 */
public class NonBlockingListBuilder implements Builder<NonBlockingList> {

    private Uint32 _interconnectBandwidth;
    private Uint32 _interconnectBandwidthUnit;
    private Uint16 _nblNumber;
    private Map<PluggableOpticsHolderListKey, PluggableOpticsHolderList> _pluggableOpticsHolderList;
    private Map<PortListKey, PortList> _portList;
    private NonBlockingListKey key;


    Map<Class<? extends Augmentation<NonBlockingList>>, Augmentation<NonBlockingList>> augmentation = Collections.emptyMap();

    public NonBlockingListBuilder() {
    }

    public NonBlockingListBuilder(NonBlockingList base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<NonBlockingList>>, Augmentation<NonBlockingList>> aug =((AugmentationHolder<NonBlockingList>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._nblNumber = base.getNblNumber();
        this._interconnectBandwidth = base.getInterconnectBandwidth();
        this._interconnectBandwidthUnit = base.getInterconnectBandwidthUnit();
        this._pluggableOpticsHolderList = base.getPluggableOpticsHolderList();
        this._portList = base.getPortList();
    }


    public NonBlockingListKey key() {
        return key;
    }
    
    public Uint32 getInterconnectBandwidth() {
        return _interconnectBandwidth;
    }
    
    public Uint32 getInterconnectBandwidthUnit() {
        return _interconnectBandwidthUnit;
    }
    
    public Uint16 getNblNumber() {
        return _nblNumber;
    }
    
    public Map<PluggableOpticsHolderListKey, PluggableOpticsHolderList> getPluggableOpticsHolderList() {
        return _pluggableOpticsHolderList;
    }
    
    public Map<PortListKey, PortList> getPortList() {
        return _portList;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<NonBlockingList>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public NonBlockingListBuilder withKey(final NonBlockingListKey key) {
        this.key = key;
        return this;
    }
    
    public NonBlockingListBuilder setInterconnectBandwidth(final Uint32 value) {
        this._interconnectBandwidth = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setInterconnectBandwidth(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public NonBlockingListBuilder setInterconnectBandwidth(final Long value) {
        return setInterconnectBandwidth(CodeHelpers.compatUint(value));
    }
    
    public NonBlockingListBuilder setInterconnectBandwidthUnit(final Uint32 value) {
        this._interconnectBandwidthUnit = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setInterconnectBandwidthUnit(Uint32)} instead.
     */
    @Deprecated(forRemoval = true)
    public NonBlockingListBuilder setInterconnectBandwidthUnit(final Long value) {
        return setInterconnectBandwidthUnit(CodeHelpers.compatUint(value));
    }
    
    public NonBlockingListBuilder setNblNumber(final Uint16 value) {
        this._nblNumber = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setNblNumber(Uint16)} instead.
     */
    @Deprecated(forRemoval = true)
    public NonBlockingListBuilder setNblNumber(final Integer value) {
        return setNblNumber(CodeHelpers.compatUint(value));
    }
    public NonBlockingListBuilder setPluggableOpticsHolderList(final Map<PluggableOpticsHolderListKey, PluggableOpticsHolderList> values) {
        this._pluggableOpticsHolderList = values;
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
      * @deprecated Use {#link #setPluggableOpticsHolderList(Map)} instead.
      */
    @Deprecated(forRemoval = true)
    public NonBlockingListBuilder setPluggableOpticsHolderList(final List<PluggableOpticsHolderList> values) {
        return setPluggableOpticsHolderList(CodeHelpers.compatMap(values));
    }
    public NonBlockingListBuilder setPortList(final Map<PortListKey, PortList> values) {
        this._portList = values;
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
      * @deprecated Use {#link #setPortList(Map)} instead.
      */
    @Deprecated(forRemoval = true)
    public NonBlockingListBuilder setPortList(final List<PortList> values) {
        return setPortList(CodeHelpers.compatMap(values));
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public NonBlockingListBuilder addAugmentation(Augmentation<NonBlockingList> augmentation) {
        return doAddAugmentation(augmentation.implementedInterface(), augmentation);
    }
    
    /**
      * Add or remove an augmentation to this builder's product.
      *
      * @param augmentationType augmentation type to be added or removed
      * @param augmentationValue augmentation value, null if the augmentation type should be removed
      * @return this builder
      * @deprecated Use either {@link #addAugmentation(Augmentation)} or {@link #removeAugmentation(Class)} instead.
      */
    @Deprecated(forRemoval = true)
    public NonBlockingListBuilder addAugmentation(Class<? extends Augmentation<NonBlockingList>> augmentationType, Augmentation<NonBlockingList> augmentationValue) {
        return augmentationValue == null ? removeAugmentation(augmentationType) : doAddAugmentation(augmentationType, augmentationValue);
    }
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public NonBlockingListBuilder removeAugmentation(Class<? extends Augmentation<NonBlockingList>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }
    
    private NonBlockingListBuilder doAddAugmentation(Class<? extends Augmentation<NonBlockingList>> augmentationType, Augmentation<NonBlockingList> augmentationValue) {
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    @Override
    public NonBlockingList build() {
        return new NonBlockingListImpl(this);
    }

    private static final class NonBlockingListImpl
        extends AbstractAugmentable<NonBlockingList>
        implements NonBlockingList {
    
        private final Uint32 _interconnectBandwidth;
        private final Uint32 _interconnectBandwidthUnit;
        private final Uint16 _nblNumber;
        private final Map<PluggableOpticsHolderListKey, PluggableOpticsHolderList> _pluggableOpticsHolderList;
        private final Map<PortListKey, PortList> _portList;
        private final NonBlockingListKey key;
    
        NonBlockingListImpl(NonBlockingListBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new NonBlockingListKey(base.getNblNumber());
            }
            this._nblNumber = key.getNblNumber();
            this._interconnectBandwidth = base.getInterconnectBandwidth();
            this._interconnectBandwidthUnit = base.getInterconnectBandwidthUnit();
            this._pluggableOpticsHolderList = CodeHelpers.emptyToNull(base.getPluggableOpticsHolderList());
            this._portList = CodeHelpers.emptyToNull(base.getPortList());
        }
    
        @Override
        public NonBlockingListKey key() {
            return key;
        }
        
        @Override
        public Uint32 getInterconnectBandwidth() {
            return _interconnectBandwidth;
        }
        
        @Override
        public Uint32 getInterconnectBandwidthUnit() {
            return _interconnectBandwidthUnit;
        }
        
        @Override
        public Uint16 getNblNumber() {
            return _nblNumber;
        }
        
        @Override
        public Map<PluggableOpticsHolderListKey, PluggableOpticsHolderList> getPluggableOpticsHolderList() {
            return _pluggableOpticsHolderList;
        }
        
        @Override
        public Map<PortListKey, PortList> getPortList() {
            return _portList;
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
            result = prime * result + Objects.hashCode(_interconnectBandwidth);
            result = prime * result + Objects.hashCode(_interconnectBandwidthUnit);
            result = prime * result + Objects.hashCode(_nblNumber);
            result = prime * result + Objects.hashCode(_pluggableOpticsHolderList);
            result = prime * result + Objects.hashCode(_portList);
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
            if (!NonBlockingList.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            NonBlockingList other = (NonBlockingList)obj;
            if (!Objects.equals(_interconnectBandwidth, other.getInterconnectBandwidth())) {
                return false;
            }
            if (!Objects.equals(_interconnectBandwidthUnit, other.getInterconnectBandwidthUnit())) {
                return false;
            }
            if (!Objects.equals(_nblNumber, other.getNblNumber())) {
                return false;
            }
            if (!Objects.equals(_pluggableOpticsHolderList, other.getPluggableOpticsHolderList())) {
                return false;
            }
            if (!Objects.equals(_portList, other.getPortList())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                NonBlockingListImpl otherImpl = (NonBlockingListImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<NonBlockingList>>, Augmentation<NonBlockingList>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("NonBlockingList");
            CodeHelpers.appendValue(helper, "_interconnectBandwidth", _interconnectBandwidth);
            CodeHelpers.appendValue(helper, "_interconnectBandwidthUnit", _interconnectBandwidthUnit);
            CodeHelpers.appendValue(helper, "_nblNumber", _nblNumber);
            CodeHelpers.appendValue(helper, "_pluggableOpticsHolderList", _pluggableOpticsHolderList);
            CodeHelpers.appendValue(helper, "_portList", _portList);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
