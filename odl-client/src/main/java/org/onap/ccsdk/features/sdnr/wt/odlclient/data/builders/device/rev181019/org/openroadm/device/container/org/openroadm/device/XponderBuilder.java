/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device;
import com.google.common.base.MoreObjects;
import java.lang.Boolean;
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
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.XpdrNodeTypes;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Xponder;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.XponderKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.xponder.XpdrPort;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * Class that builds {@link XponderBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     XponderBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new XponderBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of XponderBuilder, as instances can be freely passed around without
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
 * @see XponderBuilder
 * @see Builder
 *
 */
public class XponderBuilder implements Builder<Xponder> {

    private Uint16 _xpdrNumber;
    private List<XpdrPort> _xpdrPort;
    private XpdrNodeTypes _xpdrType;
    private Boolean _recolor;
    private XponderKey key;


    Map<Class<? extends Augmentation<Xponder>>, Augmentation<Xponder>> augmentation = Collections.emptyMap();

    public XponderBuilder() {
    }
    public XponderBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Xponder arg) {
        this._xpdrNumber = arg.getXpdrNumber();
        this._xpdrType = arg.getXpdrType();
        this._recolor = arg.isRecolor();
        this._xpdrPort = arg.getXpdrPort();
    }

    public XponderBuilder(Xponder base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<Xponder>>, Augmentation<Xponder>> aug =((AugmentationHolder<Xponder>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._xpdrNumber = base.getXpdrNumber();
        this._xpdrPort = base.getXpdrPort();
        this._xpdrType = base.getXpdrType();
        this._recolor = base.isRecolor();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Xponder</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Xponder) {
            this._xpdrNumber = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Xponder)arg).getXpdrNumber();
            this._xpdrType = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Xponder)arg).getXpdrType();
            this._recolor = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Xponder)arg).isRecolor();
            this._xpdrPort = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Xponder)arg).getXpdrPort();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Xponder]");
    }

    public XponderKey key() {
        return key;
    }

    public Uint16 getXpdrNumber() {
        return _xpdrNumber;
    }

    public List<XpdrPort> getXpdrPort() {
        return _xpdrPort;
    }

    public XpdrNodeTypes getXpdrType() {
        return _xpdrType;
    }

    public Boolean isRecolor() {
        return _recolor;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Xponder>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }

    public XponderBuilder withKey(final XponderKey key) {
        this.key = key;
        return this;
    }

    public XponderBuilder setXpdrNumber(final Uint16 value) {
        this._xpdrNumber = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setXpdrNumber(Uint16)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public XponderBuilder setXpdrNumber(final Integer value) {
//        return setXpdrNumber(CodeHelpers.compatUint(value));
//    }
    public XponderBuilder setXpdrPort(final List<XpdrPort> values) {
        this._xpdrPort = values;
        return this;
    }


    public XponderBuilder setXpdrType(final XpdrNodeTypes value) {
        this._xpdrType = value;
        return this;
    }

    public XponderBuilder setRecolor(final Boolean value) {
        this._recolor = value;
        return this;
    }

    public XponderBuilder addAugmentation(Class<? extends Augmentation<Xponder>> augmentationType, Augmentation<Xponder> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public XponderBuilder removeAugmentation(Class<? extends Augmentation<Xponder>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public Xponder build() {
        return new XponderImpl(this);
    }

    private static final class XponderImpl
        extends AbstractAugmentable<Xponder>
        implements Xponder {

        private final Uint16 _xpdrNumber;
        private final List<XpdrPort> _xpdrPort;
        private final XpdrNodeTypes _xpdrType;
        private final Boolean _recolor;
        private final XponderKey key;

        XponderImpl(XponderBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new XponderKey(base.getXpdrNumber());
            }
            this._xpdrNumber = key.getXpdrNumber();
            this._xpdrPort = base.getXpdrPort();
            this._xpdrType = base.getXpdrType();
            this._recolor = base.isRecolor();
        }

        @Override
        public XponderKey key() {
            return key;
        }

        @Override
        public Uint16 getXpdrNumber() {
            return _xpdrNumber;
        }

        @Override
        public List<XpdrPort> getXpdrPort() {
            return _xpdrPort;
        }

        @Override
        public XpdrNodeTypes getXpdrType() {
            return _xpdrType;
        }

        @Override
        public Boolean isRecolor() {
            return _recolor;
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
            result = prime * result + Objects.hashCode(_xpdrNumber);
            result = prime * result + Objects.hashCode(_xpdrPort);
            result = prime * result + Objects.hashCode(_xpdrType);
            result = prime * result + Objects.hashCode(_recolor);
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
            if (!Xponder.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            Xponder other = (Xponder)obj;
            if (!Objects.equals(_xpdrNumber, other.getXpdrNumber())) {
                return false;
            }
            if (!Objects.equals(_xpdrPort, other.getXpdrPort())) {
                return false;
            }
            if (!Objects.equals(_xpdrType, other.getXpdrType())) {
                return false;
            }
            if (!Objects.equals(_recolor, other.isRecolor())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                XponderImpl otherImpl = (XponderImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<Xponder>>, Augmentation<Xponder>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Xponder");
            CodeHelpers.appendValue(helper, "_xpdrNumber", _xpdrNumber);
            CodeHelpers.appendValue(helper, "_xpdrPort", _xpdrPort);
            CodeHelpers.appendValue(helper, "_xpdrType", _xpdrType);
            CodeHelpers.appendValue(helper, "_recolor", _recolor);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
