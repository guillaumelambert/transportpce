/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.srg;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.srg.CircuitPacks;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.srg.CircuitPacksKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link CircuitPacksBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     CircuitPacksBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new CircuitPacksBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of CircuitPacksBuilder, as instances can be freely passed around without
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
 * @see CircuitPacksBuilder
 * @see Builder
 *
 */
public class CircuitPacksBuilder implements Builder<CircuitPacks> {

    private String _circuitPackName;
    private Uint32 _index;
    private CircuitPacksKey key;


    Map<Class<? extends Augmentation<CircuitPacks>>, Augmentation<CircuitPacks>> augmentation = Collections.emptyMap();

    public CircuitPacksBuilder() {
    }
    public CircuitPacksBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName arg) {
        this._circuitPackName = arg.getCircuitPackName();
    }

    public CircuitPacksBuilder(CircuitPacks base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<CircuitPacks>>, Augmentation<CircuitPacks>> aug =((AugmentationHolder<CircuitPacks>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._index = base.getIndex();
        this._circuitPackName = base.getCircuitPackName();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName) {
            this._circuitPackName = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName)arg).getCircuitPackName();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.CircuitPackName]");
    }

    public CircuitPacksKey key() {
        return key;
    }

    public String getCircuitPackName() {
        return _circuitPackName;
    }

    public Uint32 getIndex() {
        return _index;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<CircuitPacks>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }

    public CircuitPacksBuilder withKey(final CircuitPacksKey key) {
        this.key = key;
        return this;
    }

    public CircuitPacksBuilder setCircuitPackName(final String value) {
        this._circuitPackName = value;
        return this;
    }

    public CircuitPacksBuilder setIndex(final Uint32 value) {
        this._index = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setIndex(Uint32)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public CircuitPacksBuilder setIndex(final Long value) {
//        return setIndex(CodeHelpers.compatUint(value));
//    }

    public CircuitPacksBuilder addAugmentation(Class<? extends Augmentation<CircuitPacks>> augmentationType, Augmentation<CircuitPacks> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public CircuitPacksBuilder removeAugmentation(Class<? extends Augmentation<CircuitPacks>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public CircuitPacks build() {
        return new CircuitPacksImpl(this);
    }

    private static final class CircuitPacksImpl
        extends AbstractAugmentable<CircuitPacks>
        implements CircuitPacks {

        private final String _circuitPackName;
        private final Uint32 _index;
        private final CircuitPacksKey key;

        CircuitPacksImpl(CircuitPacksBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new CircuitPacksKey(base.getIndex());
            }
            this._index = key.getIndex();
            this._circuitPackName = base.getCircuitPackName();
        }

        @Override
        public CircuitPacksKey key() {
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
            if (!CircuitPacks.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            CircuitPacks other = (CircuitPacks)obj;
            if (!Objects.equals(_circuitPackName, other.getCircuitPackName())) {
                return false;
            }
            if (!Objects.equals(_index, other.getIndex())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                CircuitPacksImpl otherImpl = (CircuitPacksImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<CircuitPacks>>, Augmentation<CircuitPacks>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("CircuitPacks");
            CodeHelpers.appendValue(helper, "_circuitPackName", _circuitPackName);
            CodeHelpers.appendValue(helper, "_index", _index);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
