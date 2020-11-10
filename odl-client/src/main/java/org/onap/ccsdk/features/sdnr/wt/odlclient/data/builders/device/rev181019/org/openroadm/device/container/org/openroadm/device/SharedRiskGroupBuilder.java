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
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.WavelengthDuplicationType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.mc.capabilities.g.McCapabilities;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.SharedRiskGroup;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.SharedRiskGroupKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.srg.CircuitPacks;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.srg.CircuitPacksKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * Class that builds {@link SharedRiskGroupBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     SharedRiskGroupBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new SharedRiskGroupBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of SharedRiskGroupBuilder, as instances can be freely passed around without
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
 * @see SharedRiskGroupBuilder
 * @see Builder
 *
 */
public class SharedRiskGroupBuilder implements Builder<SharedRiskGroup> {

    private Map<CircuitPacksKey, CircuitPacks> _circuitPacks;
    private Uint16 _currentProvisionedAddDropPorts;
    private Uint16 _maxAddDropPorts;
    private McCapabilities _mcCapabilities;
    private Uint16 _srgNumber;
    private WavelengthDuplicationType _wavelengthDuplication;
    private SharedRiskGroupKey key;


    Map<Class<? extends Augmentation<SharedRiskGroup>>, Augmentation<SharedRiskGroup>> augmentation = Collections.emptyMap();

    public SharedRiskGroupBuilder() {
    }
    public SharedRiskGroupBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Srg arg) {
        this._maxAddDropPorts = arg.getMaxAddDropPorts();
        this._currentProvisionedAddDropPorts = arg.getCurrentProvisionedAddDropPorts();
        this._srgNumber = arg.getSrgNumber();
        this._wavelengthDuplication = arg.getWavelengthDuplication();
        this._circuitPacks = arg.getCircuitPacks();
        this._mcCapabilities = arg.getMcCapabilities();
    }
    public SharedRiskGroupBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG arg) {
        this._mcCapabilities = arg.getMcCapabilities();
    }

    public SharedRiskGroupBuilder(SharedRiskGroup base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<SharedRiskGroup>>, Augmentation<SharedRiskGroup>> aug =((AugmentationHolder<SharedRiskGroup>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._srgNumber = base.getSrgNumber();
        this._circuitPacks = base.getCircuitPacks();
        this._currentProvisionedAddDropPorts = base.getCurrentProvisionedAddDropPorts();
        this._maxAddDropPorts = base.getMaxAddDropPorts();
        this._mcCapabilities = base.getMcCapabilities();
        this._wavelengthDuplication = base.getWavelengthDuplication();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Srg</li>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Srg) {
            this._maxAddDropPorts = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Srg)arg).getMaxAddDropPorts();
            this._currentProvisionedAddDropPorts = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Srg)arg).getCurrentProvisionedAddDropPorts();
            this._srgNumber = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Srg)arg).getSrgNumber();
            this._wavelengthDuplication = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Srg)arg).getWavelengthDuplication();
            this._circuitPacks = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Srg)arg).getCircuitPacks();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG) {
            this._mcCapabilities = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG)arg).getMcCapabilities();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Srg, org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG]");
    }

    public SharedRiskGroupKey key() {
        return key;
    }

    public Map<CircuitPacksKey, CircuitPacks> getCircuitPacks() {
        return _circuitPacks;
    }

    public Uint16 getCurrentProvisionedAddDropPorts() {
        return _currentProvisionedAddDropPorts;
    }

    public Uint16 getMaxAddDropPorts() {
        return _maxAddDropPorts;
    }

    public McCapabilities getMcCapabilities() {
        return _mcCapabilities;
    }

    public Uint16 getSrgNumber() {
        return _srgNumber;
    }

    public WavelengthDuplicationType getWavelengthDuplication() {
        return _wavelengthDuplication;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<SharedRiskGroup>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }

    public SharedRiskGroupBuilder withKey(final SharedRiskGroupKey key) {
        this.key = key;
        return this;
    }
    public SharedRiskGroupBuilder setCircuitPacks(final Map<CircuitPacksKey, CircuitPacks> values) {
        this._circuitPacks = values;
        return this;
    }


    public SharedRiskGroupBuilder setCurrentProvisionedAddDropPorts(final Uint16 value) {
        this._currentProvisionedAddDropPorts = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setCurrentProvisionedAddDropPorts(Uint16)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public SharedRiskGroupBuilder setCurrentProvisionedAddDropPorts(final Integer value) {
//        return setCurrentProvisionedAddDropPorts(CodeHelpers.compatUint(value));
//    }

    public SharedRiskGroupBuilder setMaxAddDropPorts(final Uint16 value) {
        this._maxAddDropPorts = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setMaxAddDropPorts(Uint16)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public SharedRiskGroupBuilder setMaxAddDropPorts(final Integer value) {
//        return setMaxAddDropPorts(CodeHelpers.compatUint(value));
//    }

    public SharedRiskGroupBuilder setMcCapabilities(final McCapabilities value) {
        this._mcCapabilities = value;
        return this;
    }

    public SharedRiskGroupBuilder setSrgNumber(final Uint16 value) {
        this._srgNumber = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setSrgNumber(Uint16)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public SharedRiskGroupBuilder setSrgNumber(final Integer value) {
//        return setSrgNumber(CodeHelpers.compatUint(value));
//    }

    public SharedRiskGroupBuilder setWavelengthDuplication(final WavelengthDuplicationType value) {
        this._wavelengthDuplication = value;
        return this;
    }

    public SharedRiskGroupBuilder addAugmentation(Class<? extends Augmentation<SharedRiskGroup>> augmentationType, Augmentation<SharedRiskGroup> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public SharedRiskGroupBuilder removeAugmentation(Class<? extends Augmentation<SharedRiskGroup>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public SharedRiskGroup build() {
        return new SharedRiskGroupImpl(this);
    }

    private static final class SharedRiskGroupImpl
        extends AbstractAugmentable<SharedRiskGroup>
        implements SharedRiskGroup {

        private final Map<CircuitPacksKey, CircuitPacks> _circuitPacks;
        private final Uint16 _currentProvisionedAddDropPorts;
        private final Uint16 _maxAddDropPorts;
        private final McCapabilities _mcCapabilities;
        private final Uint16 _srgNumber;
        private final WavelengthDuplicationType _wavelengthDuplication;
        private final SharedRiskGroupKey key;

        SharedRiskGroupImpl(SharedRiskGroupBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new SharedRiskGroupKey(base.getSrgNumber());
            }
            this._srgNumber = key.getSrgNumber();
            this._circuitPacks = base.getCircuitPacks();
            this._currentProvisionedAddDropPorts = base.getCurrentProvisionedAddDropPorts();
            this._maxAddDropPorts = base.getMaxAddDropPorts();
            this._mcCapabilities = base.getMcCapabilities();
            this._wavelengthDuplication = base.getWavelengthDuplication();
        }

        @Override
        public SharedRiskGroupKey key() {
            return key;
        }

        @Override
        public Map<CircuitPacksKey, CircuitPacks> getCircuitPacks() {
            return _circuitPacks;
        }

        @Override
        public Uint16 getCurrentProvisionedAddDropPorts() {
            return _currentProvisionedAddDropPorts;
        }

        @Override
        public Uint16 getMaxAddDropPorts() {
            return _maxAddDropPorts;
        }

        @Override
        public McCapabilities getMcCapabilities() {
            return _mcCapabilities;
        }

        @Override
        public Uint16 getSrgNumber() {
            return _srgNumber;
        }

        @Override
        public WavelengthDuplicationType getWavelengthDuplication() {
            return _wavelengthDuplication;
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
            result = prime * result + Objects.hashCode(_circuitPacks);
            result = prime * result + Objects.hashCode(_currentProvisionedAddDropPorts);
            result = prime * result + Objects.hashCode(_maxAddDropPorts);
            result = prime * result + Objects.hashCode(_mcCapabilities);
            result = prime * result + Objects.hashCode(_srgNumber);
            result = prime * result + Objects.hashCode(_wavelengthDuplication);
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
            if (!SharedRiskGroup.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            SharedRiskGroup other = (SharedRiskGroup)obj;
            if (!Objects.equals(_circuitPacks, other.getCircuitPacks())) {
                return false;
            }
            if (!Objects.equals(_currentProvisionedAddDropPorts, other.getCurrentProvisionedAddDropPorts())) {
                return false;
            }
            if (!Objects.equals(_maxAddDropPorts, other.getMaxAddDropPorts())) {
                return false;
            }
            if (!Objects.equals(_mcCapabilities, other.getMcCapabilities())) {
                return false;
            }
            if (!Objects.equals(_srgNumber, other.getSrgNumber())) {
                return false;
            }
            if (!Objects.equals(_wavelengthDuplication, other.getWavelengthDuplication())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                SharedRiskGroupImpl otherImpl = (SharedRiskGroupImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<SharedRiskGroup>>, Augmentation<SharedRiskGroup>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("SharedRiskGroup");
            CodeHelpers.appendValue(helper, "_circuitPacks", _circuitPacks);
            CodeHelpers.appendValue(helper, "_currentProvisionedAddDropPorts", _currentProvisionedAddDropPorts);
            CodeHelpers.appendValue(helper, "_maxAddDropPorts", _maxAddDropPorts);
            CodeHelpers.appendValue(helper, "_mcCapabilities", _mcCapabilities);
            CodeHelpers.appendValue(helper, "_srgNumber", _srgNumber);
            CodeHelpers.appendValue(helper, "_wavelengthDuplication", _wavelengthDuplication);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
