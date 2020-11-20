/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.odu.interfaces.rev181019.odu.attributes;
import com.google.common.base.MoreObjects;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace.TimDetectMode;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes.MonitoringMode;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes.TcmDirection;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.odu.attributes.Tcm;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.odu.attributes.TcmKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint8;

/**
 * Class that builds {@link TcmBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     TcmBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new TcmBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of TcmBuilder, as instances can be freely passed around without
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
 * @see TcmBuilder
 * @see Builder
 *
 */
public class TcmBuilder implements Builder<Tcm> {

    private String _acceptedDapi;
    private String _acceptedOperator;
    private String _acceptedSapi;
    private Uint8 _degmIntervals;
    private Uint16 _degthrPercentage;
    private String _expectedDapi;
    private String _expectedSapi;
    private Uint8 _layer;
    private MonitoringMode _monitoringMode;
    private TcmDirection _tcmDirection;
    private TimDetectMode _timDetectMode;
    private String _txDapi;
    private String _txOperator;
    private String _txSapi;
    private Boolean _ltcActEnabled;
    private Boolean _proactiveDelayMeasurementEnabled;
    private Boolean _timActEnabled;
    private TcmKey key;


    Map<Class<? extends Augmentation<Tcm>>, Augmentation<Tcm>> augmentation = Collections.emptyMap();

    public TcmBuilder() {
    }
    public TcmBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes arg) {
        this._layer = arg.getLayer();
        this._monitoringMode = arg.getMonitoringMode();
        this._ltcActEnabled = arg.isLtcActEnabled();
        this._proactiveDelayMeasurementEnabled = arg.isProactiveDelayMeasurementEnabled();
        this._tcmDirection = arg.getTcmDirection();
        this._txSapi = arg.getTxSapi();
        this._txDapi = arg.getTxDapi();
        this._txOperator = arg.getTxOperator();
        this._acceptedSapi = arg.getAcceptedSapi();
        this._acceptedDapi = arg.getAcceptedDapi();
        this._acceptedOperator = arg.getAcceptedOperator();
        this._expectedSapi = arg.getExpectedSapi();
        this._expectedDapi = arg.getExpectedDapi();
        this._timActEnabled = arg.isTimActEnabled();
        this._timDetectMode = arg.getTimDetectMode();
        this._degmIntervals = arg.getDegmIntervals();
        this._degthrPercentage = arg.getDegthrPercentage();
    }
    public TcmBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace arg) {
        this._txSapi = arg.getTxSapi();
        this._txDapi = arg.getTxDapi();
        this._txOperator = arg.getTxOperator();
        this._acceptedSapi = arg.getAcceptedSapi();
        this._acceptedDapi = arg.getAcceptedDapi();
        this._acceptedOperator = arg.getAcceptedOperator();
        this._expectedSapi = arg.getExpectedSapi();
        this._expectedDapi = arg.getExpectedDapi();
        this._timActEnabled = arg.isTimActEnabled();
        this._timDetectMode = arg.getTimDetectMode();
    }
    public TcmBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.DegThreshold arg) {
        this._degmIntervals = arg.getDegmIntervals();
        this._degthrPercentage = arg.getDegthrPercentage();
    }

    public TcmBuilder(Tcm base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<Tcm>>, Augmentation<Tcm>> aug =((AugmentationHolder<Tcm>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._layer = base.getLayer();
        this._tcmDirection = base.getTcmDirection();
        this._acceptedDapi = base.getAcceptedDapi();
        this._acceptedOperator = base.getAcceptedOperator();
        this._acceptedSapi = base.getAcceptedSapi();
        this._degmIntervals = base.getDegmIntervals();
        this._degthrPercentage = base.getDegthrPercentage();
        this._expectedDapi = base.getExpectedDapi();
        this._expectedSapi = base.getExpectedSapi();
        this._monitoringMode = base.getMonitoringMode();
        this._timDetectMode = base.getTimDetectMode();
        this._txDapi = base.getTxDapi();
        this._txOperator = base.getTxOperator();
        this._txSapi = base.getTxSapi();
        this._ltcActEnabled = base.isLtcActEnabled();
        this._proactiveDelayMeasurementEnabled = base.isProactiveDelayMeasurementEnabled();
        this._timActEnabled = base.isTimActEnabled();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes</li>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.DegThreshold</li>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes) {
            this._layer = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes)arg).getLayer();
            this._monitoringMode = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes)arg).getMonitoringMode();
            this._ltcActEnabled = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes)arg).isLtcActEnabled();
            this._proactiveDelayMeasurementEnabled = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes)arg).isProactiveDelayMeasurementEnabled();
            this._tcmDirection = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes)arg).getTcmDirection();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.DegThreshold) {
            this._degmIntervals = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.DegThreshold)arg).getDegmIntervals();
            this._degthrPercentage = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.DegThreshold)arg).getDegthrPercentage();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace) {
            this._txSapi = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).getTxSapi();
            this._txDapi = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).getTxDapi();
            this._txOperator = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).getTxOperator();
            this._acceptedSapi = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).getAcceptedSapi();
            this._acceptedDapi = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).getAcceptedDapi();
            this._acceptedOperator = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).getAcceptedOperator();
            this._expectedSapi = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).getExpectedSapi();
            this._expectedDapi = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).getExpectedDapi();
            this._timActEnabled = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).isTimActEnabled();
            this._timDetectMode = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace)arg).getTimDetectMode();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes, org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.DegThreshold, org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace]");
    }

    public TcmKey key() {
        return key;
    }

    public String getAcceptedDapi() {
        return _acceptedDapi;
    }

    public String getAcceptedOperator() {
        return _acceptedOperator;
    }

    public String getAcceptedSapi() {
        return _acceptedSapi;
    }

    public Uint8 getDegmIntervals() {
        return _degmIntervals;
    }

    public Uint16 getDegthrPercentage() {
        return _degthrPercentage;
    }

    public String getExpectedDapi() {
        return _expectedDapi;
    }

    public String getExpectedSapi() {
        return _expectedSapi;
    }

    public Uint8 getLayer() {
        return _layer;
    }

    public MonitoringMode getMonitoringMode() {
        return _monitoringMode;
    }

    public TcmDirection getTcmDirection() {
        return _tcmDirection;
    }

    public TimDetectMode getTimDetectMode() {
        return _timDetectMode;
    }

    public String getTxDapi() {
        return _txDapi;
    }

    public String getTxOperator() {
        return _txOperator;
    }

    public String getTxSapi() {
        return _txSapi;
    }

    public Boolean isLtcActEnabled() {
        return _ltcActEnabled;
    }

    public Boolean isProactiveDelayMeasurementEnabled() {
        return _proactiveDelayMeasurementEnabled;
    }

    public Boolean isTimActEnabled() {
        return _timActEnabled;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Tcm>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }

    public TcmBuilder withKey(final TcmKey key) {
        this.key = key;
        return this;
    }

    public TcmBuilder setAcceptedDapi(final String value) {
        this._acceptedDapi = value;
        return this;
    }

    public TcmBuilder setAcceptedOperator(final String value) {
        this._acceptedOperator = value;
        return this;
    }

    public TcmBuilder setAcceptedSapi(final String value) {
        this._acceptedSapi = value;
        return this;
    }

    private static void checkDegmIntervalsRange(final short value) {
        if (value >= (short)2 && value <= (short)10) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[2..10]]", value);
    }

    public TcmBuilder setDegmIntervals(final Uint8 value) {
        if (value != null) {
            checkDegmIntervalsRange(value.shortValue());

        }
        this._degmIntervals = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setDegmIntervals(Uint8)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public TcmBuilder setDegmIntervals(final Short value) {
//        return setDegmIntervals(CodeHelpers.compatUint(value));
//    }

    private static void checkDegthrPercentageRange(final int value) {
        if (value >= 1 && value <= 10000) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..10000]]", value);
    }

    public TcmBuilder setDegthrPercentage(final Uint16 value) {
        if (value != null) {
            checkDegthrPercentageRange(value.intValue());

        }
        this._degthrPercentage = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setDegthrPercentage(Uint16)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public TcmBuilder setDegthrPercentage(final Integer value) {
//        return setDegthrPercentage(CodeHelpers.compatUint(value));
//    }

    private static void check_expectedDapiLength(final String value) {
        final int length = value.length();
        if (length <= 15) {
            return;
        }
        CodeHelpers.throwInvalidLength("[[0..15]]", value);
    }

    public TcmBuilder setExpectedDapi(final String value) {
        if (value != null) {
            check_expectedDapiLength(value);

        }
        this._expectedDapi = value;
        return this;
    }

    private static void check_expectedSapiLength(final String value) {
        final int length = value.length();
        if (length <= 15) {
            return;
        }
        CodeHelpers.throwInvalidLength("[[0..15]]", value);
    }

    public TcmBuilder setExpectedSapi(final String value) {
        if (value != null) {
            check_expectedSapiLength(value);

        }
        this._expectedSapi = value;
        return this;
    }

    private static void checkLayerRange(final short value) {
        if (value >= (short)1 && value <= (short)6) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[1..6]]", value);
    }

    public TcmBuilder setLayer(final Uint8 value) {
        if (value != null) {
            checkLayerRange(value.shortValue());

        }
        this._layer = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setLayer(Uint8)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public TcmBuilder setLayer(final Short value) {
//        return setLayer(CodeHelpers.compatUint(value));
//    }

    public TcmBuilder setMonitoringMode(final MonitoringMode value) {
        this._monitoringMode = value;
        return this;
    }

    public TcmBuilder setTcmDirection(final TcmDirection value) {
        this._tcmDirection = value;
        return this;
    }

    public TcmBuilder setTimDetectMode(final TimDetectMode value) {
        this._timDetectMode = value;
        return this;
    }

    private static void check_txDapiLength(final String value) {
        final int length = value.length();
        if (length <= 15) {
            return;
        }
        CodeHelpers.throwInvalidLength("[[0..15]]", value);
    }

    public TcmBuilder setTxDapi(final String value) {
        if (value != null) {
            check_txDapiLength(value);

        }
        this._txDapi = value;
        return this;
    }

    private static void check_txOperatorLength(final String value) {
        final int length = value.length();
        if (length <= 32) {
            return;
        }
        CodeHelpers.throwInvalidLength("[[0..32]]", value);
    }

    public TcmBuilder setTxOperator(final String value) {
        if (value != null) {
            check_txOperatorLength(value);

        }
        this._txOperator = value;
        return this;
    }

    private static void check_txSapiLength(final String value) {
        final int length = value.length();
        if (length <= 15) {
            return;
        }
        CodeHelpers.throwInvalidLength("[[0..15]]", value);
    }

    public TcmBuilder setTxSapi(final String value) {
        if (value != null) {
            check_txSapiLength(value);

        }
        this._txSapi = value;
        return this;
    }

    public TcmBuilder setLtcActEnabled(final Boolean value) {
        this._ltcActEnabled = value;
        return this;
    }

    public TcmBuilder setProactiveDelayMeasurementEnabled(final Boolean value) {
        this._proactiveDelayMeasurementEnabled = value;
        return this;
    }

    public TcmBuilder setTimActEnabled(final Boolean value) {
        this._timActEnabled = value;
        return this;
    }

    public TcmBuilder addAugmentation(Class<? extends Augmentation<Tcm>> augmentationType, Augmentation<Tcm> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public TcmBuilder removeAugmentation(Class<? extends Augmentation<Tcm>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Tcm build() {
        return new TcmImpl(this);
    }

    private static final class TcmImpl
        extends AbstractAugmentable<Tcm>
        implements Tcm {

        private final String _acceptedDapi;
        private final String _acceptedOperator;
        private final String _acceptedSapi;
        private final Uint8 _degmIntervals;
        private final Uint16 _degthrPercentage;
        private final String _expectedDapi;
        private final String _expectedSapi;
        private final Uint8 _layer;
        private final org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes.MonitoringMode _monitoringMode;
        private final org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes.TcmDirection _tcmDirection;
        private final org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace.TimDetectMode _timDetectMode;
        private final String _txDapi;
        private final String _txOperator;
        private final String _txSapi;
        private final Boolean _ltcActEnabled;
        private final Boolean _proactiveDelayMeasurementEnabled;
        private final Boolean _timActEnabled;
        private final TcmKey key;

        TcmImpl(TcmBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new TcmKey(base.getLayer(), base.getTcmDirection());
            }
            this._layer = key.getLayer();
            this._tcmDirection = key.getTcmDirection();
            this._acceptedDapi = base.getAcceptedDapi();
            this._acceptedOperator = base.getAcceptedOperator();
            this._acceptedSapi = base.getAcceptedSapi();
            this._degmIntervals = base.getDegmIntervals();
            this._degthrPercentage = base.getDegthrPercentage();
            this._expectedDapi = base.getExpectedDapi();
            this._expectedSapi = base.getExpectedSapi();
            this._monitoringMode = base.getMonitoringMode();
            this._timDetectMode = base.getTimDetectMode();
            this._txDapi = base.getTxDapi();
            this._txOperator = base.getTxOperator();
            this._txSapi = base.getTxSapi();
            this._ltcActEnabled = base.isLtcActEnabled();
            this._proactiveDelayMeasurementEnabled = base.isProactiveDelayMeasurementEnabled();
            this._timActEnabled = base.isTimActEnabled();
        }

        @Override
        public TcmKey key() {
            return key;
        }

        @Override
        public String getAcceptedDapi() {
            return _acceptedDapi;
        }

        @Override
        public String getAcceptedOperator() {
            return _acceptedOperator;
        }

        @Override
        public String getAcceptedSapi() {
            return _acceptedSapi;
        }

        @Override
        public Uint8 getDegmIntervals() {
            return _degmIntervals;
        }

        @Override
        public Uint16 getDegthrPercentage() {
            return _degthrPercentage;
        }

        @Override
        public String getExpectedDapi() {
            return _expectedDapi;
        }

        @Override
        public String getExpectedSapi() {
            return _expectedSapi;
        }

        @Override
        public Uint8 getLayer() {
            return _layer;
        }

        @Override
        public org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes.MonitoringMode getMonitoringMode() {
            return _monitoringMode;
        }

        @Override
        public org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.TcmAttributes.TcmDirection getTcmDirection() {
            return _tcmDirection;
        }

        @Override
        public org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.rev170626.TrailTrace.TimDetectMode getTimDetectMode() {
            return _timDetectMode;
        }

        @Override
        public String getTxDapi() {
            return _txDapi;
        }

        @Override
        public String getTxOperator() {
            return _txOperator;
        }

        @Override
        public String getTxSapi() {
            return _txSapi;
        }

        @Override
        public Boolean isLtcActEnabled() {
            return _ltcActEnabled;
        }

        @Override
        public Boolean isProactiveDelayMeasurementEnabled() {
            return _proactiveDelayMeasurementEnabled;
        }

        @Override
        public Boolean isTimActEnabled() {
            return _timActEnabled;
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
            result = prime * result + Objects.hashCode(_acceptedDapi);
            result = prime * result + Objects.hashCode(_acceptedOperator);
            result = prime * result + Objects.hashCode(_acceptedSapi);
            result = prime * result + Objects.hashCode(_degmIntervals);
            result = prime * result + Objects.hashCode(_degthrPercentage);
            result = prime * result + Objects.hashCode(_expectedDapi);
            result = prime * result + Objects.hashCode(_expectedSapi);
            result = prime * result + Objects.hashCode(_layer);
            result = prime * result + Objects.hashCode(_monitoringMode);
            result = prime * result + Objects.hashCode(_tcmDirection);
            result = prime * result + Objects.hashCode(_timDetectMode);
            result = prime * result + Objects.hashCode(_txDapi);
            result = prime * result + Objects.hashCode(_txOperator);
            result = prime * result + Objects.hashCode(_txSapi);
            result = prime * result + Objects.hashCode(_ltcActEnabled);
            result = prime * result + Objects.hashCode(_proactiveDelayMeasurementEnabled);
            result = prime * result + Objects.hashCode(_timActEnabled);
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
            if (!Tcm.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            Tcm other = (Tcm)obj;
            if (!Objects.equals(_acceptedDapi, other.getAcceptedDapi())) {
                return false;
            }
            if (!Objects.equals(_acceptedOperator, other.getAcceptedOperator())) {
                return false;
            }
            if (!Objects.equals(_acceptedSapi, other.getAcceptedSapi())) {
                return false;
            }
            if (!Objects.equals(_degmIntervals, other.getDegmIntervals())) {
                return false;
            }
            if (!Objects.equals(_degthrPercentage, other.getDegthrPercentage())) {
                return false;
            }
            if (!Objects.equals(_expectedDapi, other.getExpectedDapi())) {
                return false;
            }
            if (!Objects.equals(_expectedSapi, other.getExpectedSapi())) {
                return false;
            }
            if (!Objects.equals(_layer, other.getLayer())) {
                return false;
            }
            if (!Objects.equals(_monitoringMode, other.getMonitoringMode())) {
                return false;
            }
            if (!Objects.equals(_tcmDirection, other.getTcmDirection())) {
                return false;
            }
            if (!Objects.equals(_timDetectMode, other.getTimDetectMode())) {
                return false;
            }
            if (!Objects.equals(_txDapi, other.getTxDapi())) {
                return false;
            }
            if (!Objects.equals(_txOperator, other.getTxOperator())) {
                return false;
            }
            if (!Objects.equals(_txSapi, other.getTxSapi())) {
                return false;
            }
            if (!Objects.equals(_ltcActEnabled, other.isLtcActEnabled())) {
                return false;
            }
            if (!Objects.equals(_proactiveDelayMeasurementEnabled, other.isProactiveDelayMeasurementEnabled())) {
                return false;
            }
            if (!Objects.equals(_timActEnabled, other.isTimActEnabled())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                TcmImpl otherImpl = (TcmImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<Tcm>>, Augmentation<Tcm>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Tcm");
            CodeHelpers.appendValue(helper, "_acceptedDapi", _acceptedDapi);
            CodeHelpers.appendValue(helper, "_acceptedOperator", _acceptedOperator);
            CodeHelpers.appendValue(helper, "_acceptedSapi", _acceptedSapi);
            CodeHelpers.appendValue(helper, "_degmIntervals", _degmIntervals);
            CodeHelpers.appendValue(helper, "_degthrPercentage", _degthrPercentage);
            CodeHelpers.appendValue(helper, "_expectedDapi", _expectedDapi);
            CodeHelpers.appendValue(helper, "_expectedSapi", _expectedSapi);
            CodeHelpers.appendValue(helper, "_layer", _layer);
            CodeHelpers.appendValue(helper, "_monitoringMode", _monitoringMode);
            CodeHelpers.appendValue(helper, "_tcmDirection", _tcmDirection);
            CodeHelpers.appendValue(helper, "_timDetectMode", _timDetectMode);
            CodeHelpers.appendValue(helper, "_txDapi", _txDapi);
            CodeHelpers.appendValue(helper, "_txOperator", _txOperator);
            CodeHelpers.appendValue(helper, "_txSapi", _txSapi);
            CodeHelpers.appendValue(helper, "_ltcActEnabled", _ltcActEnabled);
            CodeHelpers.appendValue(helper, "_proactiveDelayMeasurementEnabled", _proactiveDelayMeasurementEnabled);
            CodeHelpers.appendValue(helper, "_timActEnabled", _timActEnabled);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
