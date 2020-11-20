/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.otn.odu.interfaces.rev181019.opu.opu.msi;
import com.google.common.base.MoreObjects;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.common.types.rev171215.OdtuTypeIdentity;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.opu.opu.msi.RxMsi;
import org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.opu.opu.msi.RxMsiKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * Class that builds {@link RxMsiBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     RxMsiBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new RxMsiBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of RxMsiBuilder, as instances can be freely passed around without
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
 * @see RxMsiBuilder
 * @see Builder
 *
 */
public class RxMsiBuilder implements Builder<RxMsi> {

    private Class<? extends OdtuTypeIdentity> _odtuType;
    private Uint16 _tribPort;
    private String _tribPortPayload;
    private Uint16 _tribSlot;
    private RxMsiKey key;


    Map<Class<? extends Augmentation<RxMsi>>, Augmentation<RxMsi>> augmentation = Collections.emptyMap();

    public RxMsiBuilder() {
    }
    public RxMsiBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.MsiEntry arg) {
        this._tribSlot = arg.getTribSlot();
        this._odtuType = arg.getOdtuType();
        this._tribPort = arg.getTribPort();
        this._tribPortPayload = arg.getTribPortPayload();
    }

    public RxMsiBuilder(RxMsi base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<RxMsi>>, Augmentation<RxMsi>> aug =((AugmentationHolder<RxMsi>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._tribSlot = base.getTribSlot();
        this._odtuType = base.getOdtuType();
        this._tribPort = base.getTribPort();
        this._tribPortPayload = base.getTribPortPayload();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.MsiEntry</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.MsiEntry) {
            this._tribSlot = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.MsiEntry)arg).getTribSlot();
            this._odtuType = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.MsiEntry)arg).getOdtuType();
            this._tribPort = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.MsiEntry)arg).getTribPort();
            this._tribPortPayload = ((org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.MsiEntry)arg).getTribPortPayload();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.otn.odu.interfaces.rev181019.MsiEntry]");
    }

    public RxMsiKey key() {
        return key;
    }

    public Class<? extends OdtuTypeIdentity> getOdtuType() {
        return _odtuType;
    }

    public Uint16 getTribPort() {
        return _tribPort;
    }

    public String getTribPortPayload() {
        return _tribPortPayload;
    }

    public Uint16 getTribSlot() {
        return _tribSlot;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<RxMsi>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }

    public RxMsiBuilder withKey(final RxMsiKey key) {
        this.key = key;
        return this;
    }

    public RxMsiBuilder setOdtuType(final Class<? extends OdtuTypeIdentity> value) {
        this._odtuType = value;
        return this;
    }

    public RxMsiBuilder setTribPort(final Uint16 value) {
        this._tribPort = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setTribPort(Uint16)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public RxMsiBuilder setTribPort(final Integer value) {
//        return setTribPort(CodeHelpers.compatUint(value));
//    }

    public RxMsiBuilder setTribPortPayload(final String value) {
        this._tribPortPayload = value;
        return this;
    }

    public RxMsiBuilder setTribSlot(final Uint16 value) {
        this._tribSlot = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setTribSlot(Uint16)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public RxMsiBuilder setTribSlot(final Integer value) {
//        return setTribSlot(CodeHelpers.compatUint(value));
//    }

    public RxMsiBuilder addAugmentation(Class<? extends Augmentation<RxMsi>> augmentationType, Augmentation<RxMsi> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public RxMsiBuilder removeAugmentation(Class<? extends Augmentation<RxMsi>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public RxMsi build() {
        return new RxMsiImpl(this);
    }

    private static final class RxMsiImpl
        extends AbstractAugmentable<RxMsi>
        implements RxMsi {

        private final Class<? extends OdtuTypeIdentity> _odtuType;
        private final Uint16 _tribPort;
        private final String _tribPortPayload;
        private final Uint16 _tribSlot;
        private final RxMsiKey key;

        RxMsiImpl(RxMsiBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new RxMsiKey(base.getTribSlot());
            }
            this._tribSlot = key.getTribSlot();
            this._odtuType = base.getOdtuType();
            this._tribPort = base.getTribPort();
            this._tribPortPayload = base.getTribPortPayload();
        }

        @Override
        public RxMsiKey key() {
            return key;
        }

        @Override
        public Class<? extends OdtuTypeIdentity> getOdtuType() {
            return _odtuType;
        }

        @Override
        public Uint16 getTribPort() {
            return _tribPort;
        }

        @Override
        public String getTribPortPayload() {
            return _tribPortPayload;
        }

        @Override
        public Uint16 getTribSlot() {
            return _tribSlot;
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
            result = prime * result + Objects.hashCode(_odtuType);
            result = prime * result + Objects.hashCode(_tribPort);
            result = prime * result + Objects.hashCode(_tribPortPayload);
            result = prime * result + Objects.hashCode(_tribSlot);
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
            if (!RxMsi.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            RxMsi other = (RxMsi)obj;
            if (!Objects.equals(_odtuType, other.getOdtuType())) {
                return false;
            }
            if (!Objects.equals(_tribPort, other.getTribPort())) {
                return false;
            }
            if (!Objects.equals(_tribPortPayload, other.getTribPortPayload())) {
                return false;
            }
            if (!Objects.equals(_tribSlot, other.getTribSlot())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                RxMsiImpl otherImpl = (RxMsiImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<RxMsi>>, Augmentation<RxMsi>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("RxMsi");
            CodeHelpers.appendValue(helper, "_odtuType", _odtuType);
            CodeHelpers.appendValue(helper, "_tribPort", _tribPort);
            CodeHelpers.appendValue(helper, "_tribPortPayload", _tribPortPayload);
            CodeHelpers.appendValue(helper, "_tribSlot", _tribSlot);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
