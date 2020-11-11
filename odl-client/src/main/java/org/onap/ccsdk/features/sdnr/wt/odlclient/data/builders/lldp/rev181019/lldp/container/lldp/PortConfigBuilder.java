/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.lldp.rev181019.lldp.container.lldp;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.lldp.rev181019.lldp.container.lldp.PortConfigKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.lldp.rev181019.lldp.container.lldp.PortConfig;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link PortConfigBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     PortConfigBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new PortConfigBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of PortConfigBuilder, as instances can be freely passed around without
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
 * @see PortConfigBuilder
 * @see Builder
 *
 */
public class PortConfigBuilder implements Builder<PortConfig> {

    private PortConfig.AdminStatus _adminStatus;
    private String _ifName;
    private PortConfigKey key;


    Map<Class<? extends Augmentation<PortConfig>>, Augmentation<PortConfig>> augmentation = Collections.emptyMap();

    public PortConfigBuilder() {
    }

    public PortConfigBuilder(PortConfig base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<PortConfig>>, Augmentation<PortConfig>> aug =((AugmentationHolder<PortConfig>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._ifName = base.getIfName();
        this._adminStatus = base.getAdminStatus();
    }


    public PortConfigKey key() {
        return key;
    }
    
    public PortConfig.AdminStatus getAdminStatus() {
        return _adminStatus;
    }
    
    public String getIfName() {
        return _ifName;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<PortConfig>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public PortConfigBuilder withKey(final PortConfigKey key) {
        this.key = key;
        return this;
    }
    
    public PortConfigBuilder setAdminStatus(final PortConfig.AdminStatus value) {
        this._adminStatus = value;
        return this;
    }
    
    public PortConfigBuilder setIfName(final String value) {
        this._ifName = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public PortConfigBuilder addAugmentation(Augmentation<PortConfig> augmentation) {
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
    public PortConfigBuilder addAugmentation(Class<? extends Augmentation<PortConfig>> augmentationType, Augmentation<PortConfig> augmentationValue) {
        return augmentationValue == null ? removeAugmentation(augmentationType) : doAddAugmentation(augmentationType, augmentationValue);
    }*/
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public PortConfigBuilder removeAugmentation(Class<? extends Augmentation<PortConfig>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }
    
    private PortConfigBuilder doAddAugmentation(Class<? extends Augmentation<PortConfig>> augmentationType, Augmentation<PortConfig> augmentationValue) {
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    @Override
    public PortConfig build() {
        return new PortConfigImpl(this);
    }

    private static final class PortConfigImpl
        extends AbstractAugmentable<PortConfig>
        implements PortConfig {
    
        private final PortConfig.AdminStatus _adminStatus;
        private final String _ifName;
        private final PortConfigKey key;
    
        PortConfigImpl(PortConfigBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new PortConfigKey(base.getIfName());
            }
            this._ifName = key.getIfName();
            this._adminStatus = base.getAdminStatus();
        }
    
        @Override
        public PortConfigKey key() {
            return key;
        }
        
        @Override
        public PortConfig.AdminStatus getAdminStatus() {
            return _adminStatus;
        }
        
        @Override
        public String getIfName() {
            return _ifName;
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
            result = prime * result + Objects.hashCode(_adminStatus);
            result = prime * result + Objects.hashCode(_ifName);
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
            if (!PortConfig.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            PortConfig other = (PortConfig)obj;
            if (!Objects.equals(_adminStatus, other.getAdminStatus())) {
                return false;
            }
            if (!Objects.equals(_ifName, other.getIfName())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                PortConfigImpl otherImpl = (PortConfigImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<PortConfig>>, Augmentation<PortConfig>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("PortConfig");
            CodeHelpers.appendValue(helper, "_adminStatus", _adminStatus);
            CodeHelpers.appendValue(helper, "_ifName", _ifName);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
