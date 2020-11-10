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
import java.lang.Object;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.AmplifierGainRange;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.AmplifierTypes;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.LineAmplifierControlMode;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.PowerDBm;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.RatioDB;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.LineAmplifier;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.LineAmplifierKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.CircuitPack;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.CircuitPackKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.LinePort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.LinePortKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.OscPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.OscPortKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.OtdrPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.line.amplifier.OtdrPortKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint8;

/**
 * Class that builds {@link LineAmplifierBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     LineAmplifierBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new LineAmplifierBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of LineAmplifierBuilder, as instances can be freely passed around without
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
 * @see LineAmplifierBuilder
 * @see Builder
 *
 */
public class LineAmplifierBuilder implements Builder<LineAmplifier> {

    private AmplifierGainRange _ampGainRange;
    private Uint8 _ampNumber;
    private AmplifierTypes _ampType;
    private Map<CircuitPackKey, CircuitPack> _circuitPack;
    private LineAmplifierControlMode _controlMode;
    private PowerDBm _egressAverageChannelPower;
    private String _ilaDirectionLabel;
    private Map<LinePortKey, LinePort> _linePort;
    private Map<OscPortKey, OscPort> _oscPort;
    private Map<OtdrPortKey, OtdrPort> _otdrPort;
    private RatioDB _outVoaAtt;
    private Uint8 _partnerAmp;
    private RatioDB _targetGain;
    private RatioDB _targetTilt;
    private LineAmplifierKey key;


    Map<Class<? extends Augmentation<LineAmplifier>>, Augmentation<LineAmplifier>> augmentation = Collections.emptyMap();

    public LineAmplifierBuilder() {
    }
    public LineAmplifierBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier arg) {
        this._ampNumber = arg.getAmpNumber();
        this._ampType = arg.getAmpType();
        this._controlMode = arg.getControlMode();
        this._ampGainRange = arg.getAmpGainRange();
        this._targetGain = arg.getTargetGain();
        this._targetTilt = arg.getTargetTilt();
        this._egressAverageChannelPower = arg.getEgressAverageChannelPower();
        this._outVoaAtt = arg.getOutVoaAtt();
        this._partnerAmp = arg.getPartnerAmp();
        this._ilaDirectionLabel = arg.getIlaDirectionLabel();
    }

    public LineAmplifierBuilder(LineAmplifier base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<LineAmplifier>>, Augmentation<LineAmplifier>> aug =((AugmentationHolder<LineAmplifier>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._ampNumber = base.getAmpNumber();
        this._ampGainRange = base.getAmpGainRange();
        this._ampType = base.getAmpType();
        this._circuitPack = base.getCircuitPack();
        this._controlMode = base.getControlMode();
        this._egressAverageChannelPower = base.getEgressAverageChannelPower();
        this._ilaDirectionLabel = base.getIlaDirectionLabel();
        this._linePort = base.getLinePort();
        this._oscPort = base.getOscPort();
        this._otdrPort = base.getOtdrPort();
        this._outVoaAtt = base.getOutVoaAtt();
        this._partnerAmp = base.getPartnerAmp();
        this._targetGain = base.getTargetGain();
        this._targetTilt = base.getTargetTilt();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier) {
            this._ampNumber = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getAmpNumber();
            this._ampType = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getAmpType();
            this._controlMode = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getControlMode();
            this._ampGainRange = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getAmpGainRange();
            this._targetGain = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getTargetGain();
            this._targetTilt = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getTargetTilt();
            this._egressAverageChannelPower = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getEgressAverageChannelPower();
            this._outVoaAtt = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getOutVoaAtt();
            this._partnerAmp = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getPartnerAmp();
            this._ilaDirectionLabel = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier)arg).getIlaDirectionLabel();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Amplifier]");
    }

    public LineAmplifierKey key() {
        return key;
    }

    public AmplifierGainRange getAmpGainRange() {
        return _ampGainRange;
    }

    public Uint8 getAmpNumber() {
        return _ampNumber;
    }

    public AmplifierTypes getAmpType() {
        return _ampType;
    }

    public Map<CircuitPackKey, CircuitPack> getCircuitPack() {
        return _circuitPack;
    }

    public LineAmplifierControlMode getControlMode() {
        return _controlMode;
    }

    public PowerDBm getEgressAverageChannelPower() {
        return _egressAverageChannelPower;
    }

    public String getIlaDirectionLabel() {
        return _ilaDirectionLabel;
    }

    public Map<LinePortKey, LinePort> getLinePort() {
        return _linePort;
    }

    public Map<OscPortKey, OscPort> getOscPort() {
        return _oscPort;
    }

    public Map<OtdrPortKey, OtdrPort> getOtdrPort() {
        return _otdrPort;
    }

    public RatioDB getOutVoaAtt() {
        return _outVoaAtt;
    }

    public Uint8 getPartnerAmp() {
        return _partnerAmp;
    }

    public RatioDB getTargetGain() {
        return _targetGain;
    }

    public RatioDB getTargetTilt() {
        return _targetTilt;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<LineAmplifier>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }

    public LineAmplifierBuilder withKey(final LineAmplifierKey key) {
        this.key = key;
        return this;
    }

    public LineAmplifierBuilder setAmpGainRange(final AmplifierGainRange value) {
        this._ampGainRange = value;
        return this;
    }

    private static void checkAmpNumberRange(final short value) {
        if (value >= (short)1 && value <= (short)128) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..128]]", value);
    }

    public LineAmplifierBuilder setAmpNumber(final Uint8 value) {
        if (value != null) {
            checkAmpNumberRange(value.shortValue());

        }
        this._ampNumber = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setAmpNumber(Uint8)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public LineAmplifierBuilder setAmpNumber(final Short value) {
//        return setAmpNumber(CodeHelpers.compatUint(value));
//    }

    public LineAmplifierBuilder setAmpType(final AmplifierTypes value) {
        this._ampType = value;
        return this;
    }

    public LineAmplifierBuilder setCircuitPack(final Map<CircuitPackKey, CircuitPack> values) {
        this._circuitPack = values;
        return this;
    }


    public LineAmplifierBuilder setControlMode(final LineAmplifierControlMode value) {
        this._controlMode = value;
        return this;
    }

    public LineAmplifierBuilder setEgressAverageChannelPower(final PowerDBm value) {
        this._egressAverageChannelPower = value;
        return this;
    }

    public LineAmplifierBuilder setIlaDirectionLabel(final String value) {
        this._ilaDirectionLabel = value;
        return this;
    }
    public LineAmplifierBuilder setLinePort(final Map<LinePortKey, LinePort> values) {
        this._linePort = values;
        return this;
    }

    public LineAmplifierBuilder setOscPort(final Map<OscPortKey, OscPort> values) {
        this._oscPort = values;
        return this;
    }

    public LineAmplifierBuilder setOtdrPort(final Map<OtdrPortKey, OtdrPort> values) {
        this._otdrPort = values;
        return this;
    }


    public LineAmplifierBuilder setOutVoaAtt(final RatioDB value) {
        this._outVoaAtt = value;
        return this;
    }

    private static void checkPartnerAmpRange(final short value) {
        if (value >= (short)1 && value <= (short)128) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..128]]", value);
    }

    public LineAmplifierBuilder setPartnerAmp(final Uint8 value) {
        if (value != null) {
            checkPartnerAmpRange(value.shortValue());

        }
        this._partnerAmp = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setPartnerAmp(Uint8)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public LineAmplifierBuilder setPartnerAmp(final Short value) {
//        return setPartnerAmp(CodeHelpers.compatUint(value));
//    }

    public LineAmplifierBuilder setTargetGain(final RatioDB value) {
        this._targetGain = value;
        return this;
    }

    public LineAmplifierBuilder setTargetTilt(final RatioDB value) {
        this._targetTilt = value;
        return this;
    }

    public LineAmplifierBuilder addAugmentation(Class<? extends Augmentation<LineAmplifier>> augmentationType, 
        Augmentation<LineAmplifier> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public LineAmplifierBuilder removeAugmentation(Class<? extends Augmentation<LineAmplifier>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public LineAmplifier build() {
        return new LineAmplifierImpl(this);
    }

    private static final class LineAmplifierImpl
        extends AbstractAugmentable<LineAmplifier>
        implements LineAmplifier {

        private final AmplifierGainRange _ampGainRange;
        private final Uint8 _ampNumber;
        private final AmplifierTypes _ampType;
        private final Map<CircuitPackKey, CircuitPack> _circuitPack;
        private final LineAmplifierControlMode _controlMode;
        private final PowerDBm _egressAverageChannelPower;
        private final String _ilaDirectionLabel;
        private final Map<LinePortKey,LinePort> _linePort;
        private final Map<OscPortKey, OscPort> _oscPort;
        private final Map<OtdrPortKey, OtdrPort> _otdrPort;
        private final RatioDB _outVoaAtt;
        private final Uint8 _partnerAmp;
        private final RatioDB _targetGain;
        private final RatioDB _targetTilt;
        private final LineAmplifierKey key;

        LineAmplifierImpl(LineAmplifierBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new LineAmplifierKey(base.getAmpNumber());
            }
            this._ampNumber = key.getAmpNumber();
            this._ampGainRange = base.getAmpGainRange();
            this._ampType = base.getAmpType();
            this._circuitPack = base.getCircuitPack();
            this._controlMode = base.getControlMode();
            this._egressAverageChannelPower = base.getEgressAverageChannelPower();
            this._ilaDirectionLabel = base.getIlaDirectionLabel();
            this._linePort = base.getLinePort();
            this._oscPort = base.getOscPort();
            this._otdrPort = base.getOtdrPort();
            this._outVoaAtt = base.getOutVoaAtt();
            this._partnerAmp = base.getPartnerAmp();
            this._targetGain = base.getTargetGain();
            this._targetTilt = base.getTargetTilt();
        }

        @Override
        public LineAmplifierKey key() {
            return key;
        }

        @Override
        public AmplifierGainRange getAmpGainRange() {
            return _ampGainRange;
        }

        @Override
        public Uint8 getAmpNumber() {
            return _ampNumber;
        }

        @Override
        public AmplifierTypes getAmpType() {
            return _ampType;
        }

        @Override
        public Map<CircuitPackKey, CircuitPack> getCircuitPack() {
            return _circuitPack;
        }

        @Override
        public LineAmplifierControlMode getControlMode() {
            return _controlMode;
        }

        @Override
        public PowerDBm getEgressAverageChannelPower() {
            return _egressAverageChannelPower;
        }

        @Override
        public String getIlaDirectionLabel() {
            return _ilaDirectionLabel;
        }

        @Override
        public Map<LinePortKey,LinePort> getLinePort() {
            return _linePort;
        }

        @Override
        public Map<OscPortKey, OscPort> getOscPort() {
            return _oscPort;
        }

        @Override
        public Map<OtdrPortKey,OtdrPort> getOtdrPort() {
            return _otdrPort;
        }

        @Override
        public RatioDB getOutVoaAtt() {
            return _outVoaAtt;
        }

        @Override
        public Uint8 getPartnerAmp() {
            return _partnerAmp;
        }

        @Override
        public RatioDB getTargetGain() {
            return _targetGain;
        }

        @Override
        public RatioDB getTargetTilt() {
            return _targetTilt;
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
            result = prime * result + Objects.hashCode(_ampGainRange);
            result = prime * result + Objects.hashCode(_ampNumber);
            result = prime * result + Objects.hashCode(_ampType);
            result = prime * result + Objects.hashCode(_circuitPack);
            result = prime * result + Objects.hashCode(_controlMode);
            result = prime * result + Objects.hashCode(_egressAverageChannelPower);
            result = prime * result + Objects.hashCode(_ilaDirectionLabel);
            result = prime * result + Objects.hashCode(_linePort);
            result = prime * result + Objects.hashCode(_oscPort);
            result = prime * result + Objects.hashCode(_otdrPort);
            result = prime * result + Objects.hashCode(_outVoaAtt);
            result = prime * result + Objects.hashCode(_partnerAmp);
            result = prime * result + Objects.hashCode(_targetGain);
            result = prime * result + Objects.hashCode(_targetTilt);
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
            if (!LineAmplifier.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            LineAmplifier other = (LineAmplifier)obj;
            if (!Objects.equals(_ampGainRange, other.getAmpGainRange())) {
                return false;
            }
            if (!Objects.equals(_ampNumber, other.getAmpNumber())) {
                return false;
            }
            if (!Objects.equals(_ampType, other.getAmpType())) {
                return false;
            }
            if (!Objects.equals(_circuitPack, other.getCircuitPack())) {
                return false;
            }
            if (!Objects.equals(_controlMode, other.getControlMode())) {
                return false;
            }
            if (!Objects.equals(_egressAverageChannelPower, other.getEgressAverageChannelPower())) {
                return false;
            }
            if (!Objects.equals(_ilaDirectionLabel, other.getIlaDirectionLabel())) {
                return false;
            }
            if (!Objects.equals(_linePort, other.getLinePort())) {
                return false;
            }
            if (!Objects.equals(_oscPort, other.getOscPort())) {
                return false;
            }
            if (!Objects.equals(_otdrPort, other.getOtdrPort())) {
                return false;
            }
            if (!Objects.equals(_outVoaAtt, other.getOutVoaAtt())) {
                return false;
            }
            if (!Objects.equals(_partnerAmp, other.getPartnerAmp())) {
                return false;
            }
            if (!Objects.equals(_targetGain, other.getTargetGain())) {
                return false;
            }
            if (!Objects.equals(_targetTilt, other.getTargetTilt())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                LineAmplifierImpl otherImpl = (LineAmplifierImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<LineAmplifier>>, Augmentation<LineAmplifier>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("LineAmplifier");
            CodeHelpers.appendValue(helper, "_ampGainRange", _ampGainRange);
            CodeHelpers.appendValue(helper, "_ampNumber", _ampNumber);
            CodeHelpers.appendValue(helper, "_ampType", _ampType);
            CodeHelpers.appendValue(helper, "_circuitPack", _circuitPack);
            CodeHelpers.appendValue(helper, "_controlMode", _controlMode);
            CodeHelpers.appendValue(helper, "_egressAverageChannelPower", _egressAverageChannelPower);
            CodeHelpers.appendValue(helper, "_ilaDirectionLabel", _ilaDirectionLabel);
            CodeHelpers.appendValue(helper, "_linePort", _linePort);
            CodeHelpers.appendValue(helper, "_oscPort", _oscPort);
            CodeHelpers.appendValue(helper, "_otdrPort", _otdrPort);
            CodeHelpers.appendValue(helper, "_outVoaAtt", _outVoaAtt);
            CodeHelpers.appendValue(helper, "_partnerAmp", _partnerAmp);
            CodeHelpers.appendValue(helper, "_targetGain", _targetGain);
            CodeHelpers.appendValue(helper, "_targetTilt", _targetTilt);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
