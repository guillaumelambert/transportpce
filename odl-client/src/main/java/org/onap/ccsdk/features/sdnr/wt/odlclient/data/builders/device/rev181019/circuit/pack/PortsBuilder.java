/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.circuit.pack;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.Direction;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.PortQual;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.State;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.Ports;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.PortsKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.ports.IlaPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.ports.OtdrPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.ports.RoadmPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.circuit.pack.ports.TransponderPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.port.Interfaces;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.port.ParentPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.port.PartnerPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.equipment.states.types.rev171215.AdminStates;
import org.opendaylight.yang.gen.v1.http.org.openroadm.port.types.rev181019.PortWavelengthTypes;
import org.opendaylight.yang.gen.v1.http.org.openroadm.port.types.rev181019.SupportedIfCapability;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;

/**
 * Class that builds {@link PortsBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     PortsBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new PortsBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of PortsBuilder, as instances can be freely passed around without
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
 * @see PortsBuilder
 * @see Builder
 *
 */
public class PortsBuilder implements Builder<Ports> {

    private AdminStates _administrativeState;
    private String _circuitId;
    private IlaPort _ilaPort;
    private List<Interfaces> _interfaces;
    private String _label;
    private String _logicalConnectionPoint;
    private State _operationalState;
    private OtdrPort _otdrPort;
    private ParentPort _parentPort;
    private PartnerPort _partnerPort;
    private Direction _portDirection;
    private String _portName;
    private PortQual _portQual;
    private String _portType;
    private PortWavelengthTypes _portWavelengthType;
    private RoadmPort _roadmPort;
    private List<Class<? extends SupportedIfCapability>> _supportedInterfaceCapability;
    private TransponderPort _transponderPort;
    private PortsKey key;


    Map<Class<? extends Augmentation<Ports>>, Augmentation<Ports>> augmentation = Collections.emptyMap();

    public PortsBuilder() {
    }
    public PortsBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port arg) {
        this._portName = arg.getPortName();
        this._portType = arg.getPortType();
        this._portQual = arg.getPortQual();
        this._portWavelengthType = arg.getPortWavelengthType();
        this._portDirection = arg.getPortDirection();
        this._label = arg.getLabel();
        this._circuitId = arg.getCircuitId();
        this._administrativeState = arg.getAdministrativeState();
        this._operationalState = arg.getOperationalState();
        this._supportedInterfaceCapability = arg.getSupportedInterfaceCapability();
        this._logicalConnectionPoint = arg.getLogicalConnectionPoint();
        this._partnerPort = arg.getPartnerPort();
        this._parentPort = arg.getParentPort();
        this._interfaces = arg.getInterfaces();
    }

    public PortsBuilder(Ports base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<Ports>>, Augmentation<Ports>> aug =((AugmentationHolder<Ports>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._portName = base.getPortName();
        this._administrativeState = base.getAdministrativeState();
        this._circuitId = base.getCircuitId();
        this._ilaPort = base.getIlaPort();
        this._interfaces = base.getInterfaces();
        this._label = base.getLabel();
        this._logicalConnectionPoint = base.getLogicalConnectionPoint();
        this._operationalState = base.getOperationalState();
        this._otdrPort = base.getOtdrPort();
        this._parentPort = base.getParentPort();
        this._partnerPort = base.getPartnerPort();
        this._portDirection = base.getPortDirection();
        this._portQual = base.getPortQual();
        this._portType = base.getPortType();
        this._portWavelengthType = base.getPortWavelengthType();
        this._roadmPort = base.getRoadmPort();
        this._supportedInterfaceCapability = base.getSupportedInterfaceCapability();
        this._transponderPort = base.getTransponderPort();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port) {
            this._portName = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getPortName();
            this._portType = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getPortType();
            this._portQual = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getPortQual();
            this._portWavelengthType = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getPortWavelengthType();
            this._portDirection = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getPortDirection();
            this._label = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getLabel();
            this._circuitId = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getCircuitId();
            this._administrativeState = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getAdministrativeState();
            this._operationalState = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getOperationalState();
            this._supportedInterfaceCapability = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getSupportedInterfaceCapability();
            this._logicalConnectionPoint = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getLogicalConnectionPoint();
            this._partnerPort = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getPartnerPort();
            this._parentPort = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getParentPort();
            this._interfaces = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port)arg).getInterfaces();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Port]");
    }

    public PortsKey key() {
        return key;
    }
    
    public AdminStates getAdministrativeState() {
        return _administrativeState;
    }
    
    public String getCircuitId() {
        return _circuitId;
    }
    
    public IlaPort getIlaPort() {
        return _ilaPort;
    }
    
    public List<Interfaces> getInterfaces() {
        return _interfaces;
    }
    
    public String getLabel() {
        return _label;
    }
    
    public String getLogicalConnectionPoint() {
        return _logicalConnectionPoint;
    }
    
    public State getOperationalState() {
        return _operationalState;
    }
    
    public OtdrPort getOtdrPort() {
        return _otdrPort;
    }
    
    public ParentPort getParentPort() {
        return _parentPort;
    }
    
    public PartnerPort getPartnerPort() {
        return _partnerPort;
    }
    
    public Direction getPortDirection() {
        return _portDirection;
    }
    
    public String getPortName() {
        return _portName;
    }
    
    public PortQual getPortQual() {
        return _portQual;
    }
    
    public String getPortType() {
        return _portType;
    }
    
    public PortWavelengthTypes getPortWavelengthType() {
        return _portWavelengthType;
    }
    
    public RoadmPort getRoadmPort() {
        return _roadmPort;
    }
    
    public List<Class<? extends SupportedIfCapability>> getSupportedInterfaceCapability() {
        return _supportedInterfaceCapability;
    }
    
    public TransponderPort getTransponderPort() {
        return _transponderPort;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Ports>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public PortsBuilder withKey(final PortsKey key) {
        this.key = key;
        return this;
    }
    
    public PortsBuilder setAdministrativeState(final AdminStates value) {
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
    
    public PortsBuilder setCircuitId(final String value) {
        if (value != null) {
            check_circuitIdLength(value);
            
        }
        this._circuitId = value;
        return this;
    }
    
    public PortsBuilder setIlaPort(final IlaPort value) {
        this._ilaPort = value;
        return this;
    }
    public PortsBuilder setInterfaces(final List<Interfaces> values) {
        this._interfaces = values;
        return this;
    }
    
    
    public PortsBuilder setLabel(final String value) {
        this._label = value;
        return this;
    }
    
    public PortsBuilder setLogicalConnectionPoint(final String value) {
        this._logicalConnectionPoint = value;
        return this;
    }
    
    public PortsBuilder setOperationalState(final State value) {
        this._operationalState = value;
        return this;
    }
    
    public PortsBuilder setOtdrPort(final OtdrPort value) {
        this._otdrPort = value;
        return this;
    }
    
    public PortsBuilder setParentPort(final ParentPort value) {
        this._parentPort = value;
        return this;
    }
    
    public PortsBuilder setPartnerPort(final PartnerPort value) {
        this._partnerPort = value;
        return this;
    }
    
    public PortsBuilder setPortDirection(final Direction value) {
        this._portDirection = value;
        return this;
    }
    
    public PortsBuilder setPortName(final String value) {
        this._portName = value;
        return this;
    }
    
    public PortsBuilder setPortQual(final PortQual value) {
        this._portQual = value;
        return this;
    }
    
    public PortsBuilder setPortType(final String value) {
        this._portType = value;
        return this;
    }
    
    public PortsBuilder setPortWavelengthType(final PortWavelengthTypes value) {
        this._portWavelengthType = value;
        return this;
    }
    
    public PortsBuilder setRoadmPort(final RoadmPort value) {
        this._roadmPort = value;
        return this;
    }
    public PortsBuilder setSupportedInterfaceCapability(final List<Class<? extends SupportedIfCapability>> values) {
        this._supportedInterfaceCapability = values;
        return this;
    }
    
    
    public PortsBuilder setTransponderPort(final TransponderPort value) {
        this._transponderPort = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public PortsBuilder addAugmentation(Augmentation<Ports> augmentation) {
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
    public PortsBuilder addAugmentation(Class<? extends Augmentation<Ports>> augmentationType, Augmentation<Ports> augmentationValue) {
        return augmentationValue == null ? removeAugmentation(augmentationType) : doAddAugmentation(augmentationType, augmentationValue);
    }*/
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public PortsBuilder removeAugmentation(Class<? extends Augmentation<Ports>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }
    
    private PortsBuilder doAddAugmentation(Class<? extends Augmentation<Ports>> augmentationType, Augmentation<Ports> augmentationValue) {
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    @Override
    public Ports build() {
        return new PortsImpl(this);
    }

    private static final class PortsImpl
        extends AbstractAugmentable<Ports>
        implements Ports {
    
        private final AdminStates _administrativeState;
        private final String _circuitId;
        private final IlaPort _ilaPort;
        private final List<Interfaces> _interfaces;
        private final String _label;
        private final String _logicalConnectionPoint;
        private final State _operationalState;
        private final OtdrPort _otdrPort;
        private final ParentPort _parentPort;
        private final PartnerPort _partnerPort;
        private final Direction _portDirection;
        private final String _portName;
        private final PortQual _portQual;
        private final String _portType;
        private final PortWavelengthTypes _portWavelengthType;
        private final RoadmPort _roadmPort;
        private final List<Class<? extends SupportedIfCapability>> _supportedInterfaceCapability;
        private final TransponderPort _transponderPort;
        private final PortsKey key;
    
        PortsImpl(PortsBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new PortsKey(base.getPortName());
            }
            this._portName = key.getPortName();
            this._administrativeState = base.getAdministrativeState();
            this._circuitId = base.getCircuitId();
            this._ilaPort = base.getIlaPort();
            this._interfaces = CodeHelpers.emptyToNull(base.getInterfaces());
            this._label = base.getLabel();
            this._logicalConnectionPoint = base.getLogicalConnectionPoint();
            this._operationalState = base.getOperationalState();
            this._otdrPort = base.getOtdrPort();
            this._parentPort = base.getParentPort();
            this._partnerPort = base.getPartnerPort();
            this._portDirection = base.getPortDirection();
            this._portQual = base.getPortQual();
            this._portType = base.getPortType();
            this._portWavelengthType = base.getPortWavelengthType();
            this._roadmPort = base.getRoadmPort();
            this._supportedInterfaceCapability = base.getSupportedInterfaceCapability();
            this._transponderPort = base.getTransponderPort();
        }
    
        @Override
        public PortsKey key() {
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
        public IlaPort getIlaPort() {
            return _ilaPort;
        }
        
        @Override
        public List<Interfaces> getInterfaces() {
            return _interfaces;
        }
        
        @Override
        public String getLabel() {
            return _label;
        }
        
        @Override
        public String getLogicalConnectionPoint() {
            return _logicalConnectionPoint;
        }
        
        @Override
        public State getOperationalState() {
            return _operationalState;
        }
        
        @Override
        public OtdrPort getOtdrPort() {
            return _otdrPort;
        }
        
        @Override
        public ParentPort getParentPort() {
            return _parentPort;
        }
        
        @Override
        public PartnerPort getPartnerPort() {
            return _partnerPort;
        }
        
        @Override
        public Direction getPortDirection() {
            return _portDirection;
        }
        
        @Override
        public String getPortName() {
            return _portName;
        }
        
        @Override
        public PortQual getPortQual() {
            return _portQual;
        }
        
        @Override
        public String getPortType() {
            return _portType;
        }
        
        @Override
        public PortWavelengthTypes getPortWavelengthType() {
            return _portWavelengthType;
        }
        
        @Override
        public RoadmPort getRoadmPort() {
            return _roadmPort;
        }
        
        @Override
        public List<Class<? extends SupportedIfCapability>> getSupportedInterfaceCapability() {
            return _supportedInterfaceCapability;
        }
        
        @Override
        public TransponderPort getTransponderPort() {
            return _transponderPort;
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
            result = prime * result + Objects.hashCode(_ilaPort);
            result = prime * result + Objects.hashCode(_interfaces);
            result = prime * result + Objects.hashCode(_label);
            result = prime * result + Objects.hashCode(_logicalConnectionPoint);
            result = prime * result + Objects.hashCode(_operationalState);
            result = prime * result + Objects.hashCode(_otdrPort);
            result = prime * result + Objects.hashCode(_parentPort);
            result = prime * result + Objects.hashCode(_partnerPort);
            result = prime * result + Objects.hashCode(_portDirection);
            result = prime * result + Objects.hashCode(_portName);
            result = prime * result + Objects.hashCode(_portQual);
            result = prime * result + Objects.hashCode(_portType);
            result = prime * result + Objects.hashCode(_portWavelengthType);
            result = prime * result + Objects.hashCode(_roadmPort);
            result = prime * result + Objects.hashCode(_supportedInterfaceCapability);
            result = prime * result + Objects.hashCode(_transponderPort);
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
            if (!Ports.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            Ports other = (Ports)obj;
            if (!Objects.equals(_administrativeState, other.getAdministrativeState())) {
                return false;
            }
            if (!Objects.equals(_circuitId, other.getCircuitId())) {
                return false;
            }
            if (!Objects.equals(_ilaPort, other.getIlaPort())) {
                return false;
            }
            if (!Objects.equals(_interfaces, other.getInterfaces())) {
                return false;
            }
            if (!Objects.equals(_label, other.getLabel())) {
                return false;
            }
            if (!Objects.equals(_logicalConnectionPoint, other.getLogicalConnectionPoint())) {
                return false;
            }
            if (!Objects.equals(_operationalState, other.getOperationalState())) {
                return false;
            }
            if (!Objects.equals(_otdrPort, other.getOtdrPort())) {
                return false;
            }
            if (!Objects.equals(_parentPort, other.getParentPort())) {
                return false;
            }
            if (!Objects.equals(_partnerPort, other.getPartnerPort())) {
                return false;
            }
            if (!Objects.equals(_portDirection, other.getPortDirection())) {
                return false;
            }
            if (!Objects.equals(_portName, other.getPortName())) {
                return false;
            }
            if (!Objects.equals(_portQual, other.getPortQual())) {
                return false;
            }
            if (!Objects.equals(_portType, other.getPortType())) {
                return false;
            }
            if (!Objects.equals(_portWavelengthType, other.getPortWavelengthType())) {
                return false;
            }
            if (!Objects.equals(_roadmPort, other.getRoadmPort())) {
                return false;
            }
            if (!Objects.equals(_supportedInterfaceCapability, other.getSupportedInterfaceCapability())) {
                return false;
            }
            if (!Objects.equals(_transponderPort, other.getTransponderPort())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                PortsImpl otherImpl = (PortsImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<Ports>>, Augmentation<Ports>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Ports");
            CodeHelpers.appendValue(helper, "_administrativeState", _administrativeState);
            CodeHelpers.appendValue(helper, "_circuitId", _circuitId);
            CodeHelpers.appendValue(helper, "_ilaPort", _ilaPort);
            CodeHelpers.appendValue(helper, "_interfaces", _interfaces);
            CodeHelpers.appendValue(helper, "_label", _label);
            CodeHelpers.appendValue(helper, "_logicalConnectionPoint", _logicalConnectionPoint);
            CodeHelpers.appendValue(helper, "_operationalState", _operationalState);
            CodeHelpers.appendValue(helper, "_otdrPort", _otdrPort);
            CodeHelpers.appendValue(helper, "_parentPort", _parentPort);
            CodeHelpers.appendValue(helper, "_partnerPort", _partnerPort);
            CodeHelpers.appendValue(helper, "_portDirection", _portDirection);
            CodeHelpers.appendValue(helper, "_portName", _portName);
            CodeHelpers.appendValue(helper, "_portQual", _portQual);
            CodeHelpers.appendValue(helper, "_portType", _portType);
            CodeHelpers.appendValue(helper, "_portWavelengthType", _portWavelengthType);
            CodeHelpers.appendValue(helper, "_roadmPort", _roadmPort);
            CodeHelpers.appendValue(helper, "_supportedInterfaceCapability", _supportedInterfaceCapability);
            CodeHelpers.appendValue(helper, "_transponderPort", _transponderPort);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
