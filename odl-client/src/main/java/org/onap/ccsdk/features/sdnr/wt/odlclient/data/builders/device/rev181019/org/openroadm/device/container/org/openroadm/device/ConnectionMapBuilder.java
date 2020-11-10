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
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.ConnectionMap;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.ConnectionMapKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.connection.map.Destination;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.connection.map.DestinationKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.connection.map.Source;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link ConnectionMapBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     ConnectionMapBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new ConnectionMapBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of ConnectionMapBuilder, as instances can be freely passed around without
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
 * @see ConnectionMapBuilder
 * @see Builder
 *
 */
public class ConnectionMapBuilder implements Builder<ConnectionMap> {

    private Uint32 _connectionMapNumber;
    private Map<DestinationKey, Destination> _destination;
    private Source _source;
    private ConnectionMapKey key;


    Map<Class<? extends Augmentation<ConnectionMap>>, Augmentation<ConnectionMap>> augmentation = Collections.emptyMap();

    public ConnectionMapBuilder() {
    }

    public ConnectionMapBuilder(ConnectionMap base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<ConnectionMap>>, Augmentation<ConnectionMap>> aug =((AugmentationHolder<ConnectionMap>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._connectionMapNumber = base.getConnectionMapNumber();
        this._destination = base.getDestination();
        this._source = base.getSource();
    }


    public ConnectionMapKey key() {
        return key;
    }

    public Uint32 getConnectionMapNumber() {
        return _connectionMapNumber;
    }

    public Map<DestinationKey, Destination> getDestination() {
        return _destination;
    }

    public Source getSource() {
        return _source;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<ConnectionMap>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }

    public ConnectionMapBuilder withKey(final ConnectionMapKey key) {
        this.key = key;
        return this;
    }

    public ConnectionMapBuilder setConnectionMapNumber(final Uint32 value) {
        this._connectionMapNumber = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setConnectionMapNumber(Uint32)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public ConnectionMapBuilder setConnectionMapNumber(final Long value) {
//        return setConnectionMapNumber(CodeHelpers.compatUint(value));
//    }
    public ConnectionMapBuilder setDestination(final Map<DestinationKey,Destination> values) {
        this._destination = values;
        return this;
    }


    public ConnectionMapBuilder setSource(final Source value) {
        this._source = value;
        return this;
    }

    public ConnectionMapBuilder addAugmentation(Class<? extends Augmentation<ConnectionMap>> augmentationType, Augmentation<ConnectionMap> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public ConnectionMapBuilder removeAugmentation(Class<? extends Augmentation<ConnectionMap>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public ConnectionMap build() {
        return new ConnectionMapImpl(this);
    }

    private static final class ConnectionMapImpl
        extends AbstractAugmentable<ConnectionMap>
        implements ConnectionMap {

        private final Uint32 _connectionMapNumber;
        private final Map<DestinationKey, Destination> _destination;
        private final Source _source;
        private final ConnectionMapKey key;

        ConnectionMapImpl(ConnectionMapBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new ConnectionMapKey(base.getConnectionMapNumber());
            }
            this._connectionMapNumber = key.getConnectionMapNumber();
            this._destination = base.getDestination();
            this._source = base.getSource();
        }

        @Override
        public ConnectionMapKey key() {
            return key;
        }

        @Override
        public Uint32 getConnectionMapNumber() {
            return _connectionMapNumber;
        }

        @Override
        public Map<DestinationKey, Destination> getDestination() {
            return _destination;
        }

        @Override
        public Source getSource() {
            return _source;
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
            result = prime * result + Objects.hashCode(_connectionMapNumber);
            result = prime * result + Objects.hashCode(_destination);
            result = prime * result + Objects.hashCode(_source);
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
            if (!ConnectionMap.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            ConnectionMap other = (ConnectionMap)obj;
            if (!Objects.equals(_connectionMapNumber, other.getConnectionMapNumber())) {
                return false;
            }
            if (!Objects.equals(_destination, other.getDestination())) {
                return false;
            }
            if (!Objects.equals(_source, other.getSource())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                ConnectionMapImpl otherImpl = (ConnectionMapImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<ConnectionMap>>, Augmentation<ConnectionMap>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("ConnectionMap");
            CodeHelpers.appendValue(helper, "_connectionMapNumber", _connectionMapNumber);
            CodeHelpers.appendValue(helper, "_destination", _destination);
            CodeHelpers.appendValue(helper, "_source", _source);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
