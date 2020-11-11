/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.degree;
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
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.ConnectionPorts;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.ConnectionPortsKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link ConnectionPortsBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ConnectionPortsBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ConnectionPortsBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of ConnectionPortsBuilder, as instances can be freely passed around without
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
 * @see ConnectionPortsBuilder
 * @see Builder
 *
 */
public class ConnectionPortsBuilder implements Builder<ConnectionPorts> {

    private String _circuitPackName;
    private Uint32 _index;
    private Object _portName;
    private ConnectionPortsKey key;


    Map<Class<? extends Augmentation<ConnectionPorts>>, Augmentation<ConnectionPorts>> augmentation = Collections.emptyMap();

    public ConnectionPortsBuilder() {
    }
    public ConnectionPortsBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.PortName arg) {
        this._portName = arg.getPortName();
        this._circuitPackName = arg.getCircuitPackName();
    }
    public ConnectionPortsBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName arg) {
        this._circuitPackName = arg.getCircuitPackName();
    }

    public ConnectionPortsBuilder(ConnectionPorts base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<ConnectionPorts>>, Augmentation<ConnectionPorts>> aug =((AugmentationHolder<ConnectionPorts>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._index = base.getIndex();
        this._circuitPackName = base.getCircuitPackName();
        this._portName = base.getPortName();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.PortName</li>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.PortName) {
            this._portName = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.PortName)arg).getPortName();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName) {
            this._circuitPackName = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName)arg).getCircuitPackName();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.PortName, org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName]");
    }

    public ConnectionPortsKey key() {
        return key;
    }
    
    public String getCircuitPackName() {
        return _circuitPackName;
    }
    
    public Uint32 getIndex() {
        return _index;
    }
    
    public Object getPortName() {
        return _portName;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<ConnectionPorts>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public ConnectionPortsBuilder withKey(final ConnectionPortsKey key) {
        this.key = key;
        return this;
    }
    
    public ConnectionPortsBuilder setCircuitPackName(final String value) {
        this._circuitPackName = value;
        return this;
    }
    
    public ConnectionPortsBuilder setIndex(final Uint32 value) {
        this._index = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setIndex(Uint32)} instead.
     
    @Deprecated(forRemoval = true)
    public ConnectionPortsBuilder setIndex(final Long value) {
        return setIndex(CodeHelpers.compatUint(value));
    }*/
    
    public ConnectionPortsBuilder setPortName(final Object value) {
        this._portName = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public ConnectionPortsBuilder addAugmentation(Augmentation<ConnectionPorts> augmentation) {
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
    public ConnectionPortsBuilder addAugmentation(Class<? extends Augmentation<ConnectionPorts>> augmentationType, Augmentation<ConnectionPorts> augmentationValue) {
        return augmentationValue == null ? removeAugmentation(augmentationType) : doAddAugmentation(augmentationType, augmentationValue);
    }*/
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public ConnectionPortsBuilder removeAugmentation(Class<? extends Augmentation<ConnectionPorts>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }
    
    private ConnectionPortsBuilder doAddAugmentation(Class<? extends Augmentation<ConnectionPorts>> augmentationType, Augmentation<ConnectionPorts> augmentationValue) {
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    @Override
    public ConnectionPorts build() {
        return new ConnectionPortsImpl(this);
    }

    private static final class ConnectionPortsImpl
        extends AbstractAugmentable<ConnectionPorts>
        implements ConnectionPorts {
    
        private final String _circuitPackName;
        private final Uint32 _index;
        private final Object _portName;
        private final ConnectionPortsKey key;
    
        ConnectionPortsImpl(ConnectionPortsBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new ConnectionPortsKey(base.getIndex());
            }
            this._index = key.getIndex();
            this._circuitPackName = base.getCircuitPackName();
            this._portName = base.getPortName();
        }
    
        @Override
        public ConnectionPortsKey key() {
            return key;
        }
        
        @Override
        public String getCircuitPackName() {
            return _circuitPackName;
        }
        
        @Override
        public Uint32 getIndex() {
            return _index;
        }
        
        @Override
        public Object getPortName() {
            return _portName;
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
            result = prime * result + Objects.hashCode(_circuitPackName);
            result = prime * result + Objects.hashCode(_index);
            result = prime * result + Objects.hashCode(_portName);
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
            if (!ConnectionPorts.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            ConnectionPorts other = (ConnectionPorts)obj;
            if (!Objects.equals(_circuitPackName, other.getCircuitPackName())) {
                return false;
            }
            if (!Objects.equals(_index, other.getIndex())) {
                return false;
            }
            if (!Objects.equals(_portName, other.getPortName())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                ConnectionPortsImpl otherImpl = (ConnectionPortsImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<ConnectionPorts>>, Augmentation<ConnectionPorts>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("ConnectionPorts");
            CodeHelpers.appendValue(helper, "_circuitPackName", _circuitPackName);
            CodeHelpers.appendValue(helper, "_index", _index);
            CodeHelpers.appendValue(helper, "_portName", _portName);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
