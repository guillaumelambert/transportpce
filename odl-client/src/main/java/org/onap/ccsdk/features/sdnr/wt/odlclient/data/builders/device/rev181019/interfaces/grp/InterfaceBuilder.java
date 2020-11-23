/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.interfaces.grp;
import com.google.common.base.MoreObjects;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.netconf.shaded.exificient.core.exceptions.UnsupportedOption;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.State;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.interfaces.grp.Interface;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.interfaces.grp.InterfaceKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.equipment.states.types.rev171215.AdminStates;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.EthernetCsmacd;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.InterfaceType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.MediaChannelTrailTerminationPoint;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.NetworkMediaChannelConnectionTerminationPoint;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.OpenROADMOpticalMultiplex;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.OpticalChannel;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.OpticalTransport;
import org.opendaylight.yang.gen.v1.http.org.openroadm.interfaces.rev170626.OtnOdu;
import org.opendaylight.yang.gen.v1.http.org.openroadm.optical.transport.interfaces.rev181019.Interface1;
import org.opendaylight.yang.gen.v1.http.org.openroadm.optical.transport.interfaces.rev181019.Interface1Builder;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link InterfaceBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     InterfaceBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new InterfaceBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of InterfaceBuilder, as instances can be freely passed around without
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
 * @see InterfaceBuilder
 * @see Builder
 *
 */
public class InterfaceBuilder implements Builder<Interface> {

    private AdminStates _administrativeState;
    private String _circuitId;
    private String _description;
    private String _name;
    private State _operationalState;
    private String _supportingCircuitPackName;
    private String _supportingInterface;
    private Object _supportingPort;
    private Class<? extends InterfaceType> _type;
    //private InterfaceType _type2;
    private InterfaceKey key;


    Map<Class<? extends Augmentation<Interface>>, Augmentation<Interface>> augmentation = Collections.emptyMap();

    public InterfaceBuilder() {
        this.addAugmentation(Interface1.class, new Interface1Builder().build());
    }
    public InterfaceBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.SupportingPortName arg) {
        this._supportingCircuitPackName = arg.getSupportingCircuitPackName();
        this._supportingPort = arg.getSupportingPort();
        this.addAugmentation(Interface1.class, new Interface1Builder().build());
    }

    public InterfaceBuilder(Interface base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<Interface>>, Augmentation<Interface>> aug =((AugmentationHolder<Interface>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._name = base.getName();
        this._administrativeState = base.getAdministrativeState();
        this._circuitId = base.getCircuitId();
        this._description = base.getDescription();
        this._operationalState = base.getOperationalState();
        this._supportingCircuitPackName = base.getSupportingCircuitPackName();
        this._supportingInterface = base.getSupportingInterface();
        this._supportingPort = base.getSupportingPort();
        this._type = base.getType();
        this.addAugmentation(Interface1.class, new Interface1Builder().build());

    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.SupportingPortName</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.SupportingPortName) {
            this._supportingCircuitPackName = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.SupportingPortName)arg).getSupportingCircuitPackName();
            this._supportingPort = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.SupportingPortName)arg).getSupportingPort();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.SupportingPortName]");
    }

    public InterfaceKey key() {
        return key;
    }

    public AdminStates getAdministrativeState() {
        return _administrativeState;
    }

    public String getCircuitId() {
        return _circuitId;
    }

    public String getDescription() {
        return _description;
    }

    public String getName() {
        return _name;
    }

    public State getOperationalState() {
        return _operationalState;
    }

    public String getSupportingCircuitPackName() {
        return _supportingCircuitPackName;
    }

    public String getSupportingInterface() {
        return _supportingInterface;
    }

    public Object getSupportingPort() {
        return _supportingPort;
    }

    public Class<? extends InterfaceType> getType() {
        return _type;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Interface>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }

    public InterfaceBuilder withKey(final InterfaceKey key) {
        this.key = key;
        return this;
    }

    public InterfaceBuilder setAdministrativeState(final AdminStates value) {
        this._administrativeState = value;
        return this;
    }

    private static void check_circuitIdLength(final String value) {
        final int length = value.length();
        if (length <= 45) {
            return;
        }
        CodeHelpers.throwInvalidLength("[[0..45]]", value);
    }

    public InterfaceBuilder setCircuitId(final String value) {
        if (value != null) {
            check_circuitIdLength(value);

        }
        this._circuitId = value;
        return this;
    }

    public InterfaceBuilder setDescription(final String value) {
        this._description = value;
        return this;
    }

    public InterfaceBuilder setName(final String value) {
        this._name = value;
        return this;
    }

    public InterfaceBuilder setOperationalState(final State value) {
        this._operationalState = value;
        return this;
    }

    public InterfaceBuilder setSupportingCircuitPackName(final String value) {
        this._supportingCircuitPackName = value;
        return this;
    }

    public InterfaceBuilder setSupportingInterface(final String value) {
        this._supportingInterface = value;
        return this;
    }

    public InterfaceBuilder setSupportingPort(final Object value) {
        this._supportingPort = value;
        return this;
    }

    public InterfaceBuilder setType(String value) throws UnsupportedOption {

        if (value.endsWith("ethernetCsmacd")) {
            this._type = EthernetCsmacd.class;
        } else if (value.endsWith("opticalTransport")) {
            this._type = OpticalTransport.class;
        } else if (value.endsWith("openROADMOpticalMultiplex")) {
            this._type = OpenROADMOpticalMultiplex.class;
        } else if (value.endsWith("otnOdu")) {
            this._type = OtnOdu.class;
        }else if (value.endsWith("opticalChannel")) {
            this._type = OpticalChannel.class;
        }else if (value.endsWith("mediaChannelTrailTerminationPoint")) {
            this._type = MediaChannelTrailTerminationPoint.class;
        }
        else if(value.endsWith("networkMediaChannelConnectionTerminationPoint")) {
        	this._type = NetworkMediaChannelConnectionTerminationPoint.class;
        }
        else {
        	throw new UnsupportedOption(String.format("%s is not supported", value));
        }
        return this;
    }

    public InterfaceBuilder addAugmentation(Class<? extends Augmentation<Interface>> augmentationType, Augmentation<Interface> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public InterfaceBuilder removeAugmentation(Class<? extends Augmentation<Interface>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Interface build() {
        return new InterfaceImpl(this);
    }

    private static final class InterfaceImpl
        extends AbstractAugmentable<Interface>
        implements Interface {

        private final AdminStates _administrativeState;
        private final String _circuitId;
        private final String _description;
        private final String _name;
        private final State _operationalState;
        private final String _supportingCircuitPackName;
        private final String _supportingInterface;
        private final Object _supportingPort;
        private final Class<? extends InterfaceType> _type;
        private final InterfaceKey key;

        InterfaceImpl(InterfaceBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new InterfaceKey(base.getName());
            }
            this._name = key.getName();
            this._administrativeState = base.getAdministrativeState();
            this._circuitId = base.getCircuitId();
            this._description = base.getDescription();
            this._operationalState = base.getOperationalState();
            this._supportingCircuitPackName = base.getSupportingCircuitPackName();
            this._supportingInterface = base.getSupportingInterface();
            this._supportingPort = base.getSupportingPort();
            this._type = base.getType();
        }

        @Override
        public InterfaceKey key() {
            return key;
        }

        @Override
        public AdminStates getAdministrativeState() {
            return _administrativeState;
        }

        @Override
        public String getCircuitId() {
            return _circuitId;
        }

        @Override
        public String getDescription() {
            return _description;
        }

        @Override
        public String getName() {
            return _name;
        }

        @Override
        public State getOperationalState() {
            return _operationalState;
        }

        @Override
        public String getSupportingCircuitPackName() {
            return _supportingCircuitPackName;
        }

        @Override
        public String getSupportingInterface() {
            return _supportingInterface;
        }

        @Override
        public Object getSupportingPort() {
            return _supportingPort;
        }

        @Override
        public Class<? extends InterfaceType> getType() {
            return _type;
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
            result = prime * result + Objects.hashCode(_administrativeState);
            result = prime * result + Objects.hashCode(_circuitId);
            result = prime * result + Objects.hashCode(_description);
            result = prime * result + Objects.hashCode(_name);
            result = prime * result + Objects.hashCode(_operationalState);
            result = prime * result + Objects.hashCode(_supportingCircuitPackName);
            result = prime * result + Objects.hashCode(_supportingInterface);
            result = prime * result + Objects.hashCode(_supportingPort);
            result = prime * result + Objects.hashCode(_type);
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
            if (!Interface.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            Interface other = (Interface)obj;
            if (!Objects.equals(_administrativeState, other.getAdministrativeState())) {
                return false;
            }
            if (!Objects.equals(_circuitId, other.getCircuitId())) {
                return false;
            }
            if (!Objects.equals(_description, other.getDescription())) {
                return false;
            }
            if (!Objects.equals(_name, other.getName())) {
                return false;
            }
            if (!Objects.equals(_operationalState, other.getOperationalState())) {
                return false;
            }
            if (!Objects.equals(_supportingCircuitPackName, other.getSupportingCircuitPackName())) {
                return false;
            }
            if (!Objects.equals(_supportingInterface, other.getSupportingInterface())) {
                return false;
            }
            if (!Objects.equals(_supportingPort, other.getSupportingPort())) {
                return false;
            }
            if (!Objects.equals(_type, other.getType())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                InterfaceImpl otherImpl = (InterfaceImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<Interface>>, Augmentation<Interface>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Interface");
            CodeHelpers.appendValue(helper, "_administrativeState", _administrativeState);
            CodeHelpers.appendValue(helper, "_circuitId", _circuitId);
            CodeHelpers.appendValue(helper, "_description", _description);
            CodeHelpers.appendValue(helper, "_name", _name);
            CodeHelpers.appendValue(helper, "_operationalState", _operationalState);
            CodeHelpers.appendValue(helper, "_supportingCircuitPackName", _supportingCircuitPackName);
            CodeHelpers.appendValue(helper, "_supportingInterface", _supportingInterface);
            CodeHelpers.appendValue(helper, "_supportingPort", _supportingPort);
            CodeHelpers.appendValue(helper, "_type", _type);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
