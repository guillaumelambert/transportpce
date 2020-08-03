/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.lldp.rev181019.lldp.container.lldp.GlobalConfig;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint8;

/**
 * Class that builds {@link GlobalConfigBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     GlobalConfigBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new GlobalConfigBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of GlobalConfigBuilder, as instances can be freely passed around without
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
 * @see GlobalConfigBuilder
 * @see Builder
 *
 */
public class GlobalConfigBuilder implements Builder<GlobalConfig> {

    private GlobalConfig.AdminStatus _adminStatus;
    private Uint8 _msgTxHoldMultiplier;
    private Uint16 _msgTxInterval;


    Map<Class<? extends Augmentation<GlobalConfig>>, Augmentation<GlobalConfig>> augmentation = Collections.emptyMap();

    public GlobalConfigBuilder() {
    }

    public GlobalConfigBuilder(GlobalConfig base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<GlobalConfig>>, Augmentation<GlobalConfig>> aug =((AugmentationHolder<GlobalConfig>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this._adminStatus = base.getAdminStatus();
        this._msgTxHoldMultiplier = base.getMsgTxHoldMultiplier();
        this._msgTxInterval = base.getMsgTxInterval();
    }


    public GlobalConfig.AdminStatus getAdminStatus() {
        return _adminStatus;
    }

    public Uint8 getMsgTxHoldMultiplier() {
        return _msgTxHoldMultiplier;
    }

    public Uint16 getMsgTxInterval() {
        return _msgTxInterval;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<GlobalConfig>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }


    public GlobalConfigBuilder setAdminStatus(final GlobalConfig.AdminStatus value) {
        this._adminStatus = value;
        return this;
    }

    private static void checkMsgTxHoldMultiplierRange(final short value) {
        if (value >= (short)2 && value <= (short)10) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[2..10]]", value);
    }

    public GlobalConfigBuilder setMsgTxHoldMultiplier(final Uint8 value) {
        if (value != null) {
            checkMsgTxHoldMultiplierRange(value.shortValue());

        }
        this._msgTxHoldMultiplier = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setMsgTxHoldMultiplier(Uint8)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public GlobalConfigBuilder setMsgTxHoldMultiplier(final Short value) {
//        return setMsgTxHoldMultiplier(CodeHelpers.compatUint(value));
//    }

    private static void checkMsgTxIntervalRange(final int value) {
        if (value >= 5 && value <= 32768) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[5..32768]]", value);
    }

    public GlobalConfigBuilder setMsgTxInterval(final Uint16 value) {
        if (value != null) {
            checkMsgTxIntervalRange(value.intValue());

        }
        this._msgTxInterval = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setMsgTxInterval(Uint16)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public GlobalConfigBuilder setMsgTxInterval(final Integer value) {
//        return setMsgTxInterval(CodeHelpers.compatUint(value));
//    }

    public GlobalConfigBuilder addAugmentation(Class<? extends Augmentation<GlobalConfig>> augmentationType, Augmentation<GlobalConfig> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public GlobalConfigBuilder removeAugmentation(Class<? extends Augmentation<GlobalConfig>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public GlobalConfig build() {
        return new GlobalConfigImpl(this);
    }

    private static final class GlobalConfigImpl
        extends AbstractAugmentable<GlobalConfig>
        implements GlobalConfig {

        private final GlobalConfig.AdminStatus _adminStatus;
        private final Uint8 _msgTxHoldMultiplier;
        private final Uint16 _msgTxInterval;

        GlobalConfigImpl(GlobalConfigBuilder base) {
            super(base.augmentation);
            this._adminStatus = base.getAdminStatus();
            this._msgTxHoldMultiplier = base.getMsgTxHoldMultiplier();
            this._msgTxInterval = base.getMsgTxInterval();
        }

        @Override
        public GlobalConfig.AdminStatus getAdminStatus() {
            return _adminStatus;
        }

        @Override
        public Uint8 getMsgTxHoldMultiplier() {
            return _msgTxHoldMultiplier;
        }

        @Override
        public Uint16 getMsgTxInterval() {
            return _msgTxInterval;
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
            result = prime * result + Objects.hashCode(_msgTxHoldMultiplier);
            result = prime * result + Objects.hashCode(_msgTxInterval);
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
            if (!GlobalConfig.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            GlobalConfig other = (GlobalConfig)obj;
            if (!Objects.equals(_adminStatus, other.getAdminStatus())) {
                return false;
            }
            if (!Objects.equals(_msgTxHoldMultiplier, other.getMsgTxHoldMultiplier())) {
                return false;
            }
            if (!Objects.equals(_msgTxInterval, other.getMsgTxInterval())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                GlobalConfigImpl otherImpl = (GlobalConfigImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<GlobalConfig>>, Augmentation<GlobalConfig>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("GlobalConfig");
            CodeHelpers.appendValue(helper, "_adminStatus", _adminStatus);
            CodeHelpers.appendValue(helper, "_msgTxHoldMultiplier", _msgTxHoldMultiplier);
            CodeHelpers.appendValue(helper, "_msgTxInterval", _msgTxInterval);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
