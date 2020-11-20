/*
 * Copyright (C) 2020 highstreet technologies GmbH Intellectual Property.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.maintenance.testsignal.rev171215.maint.testsignal;
import com.google.common.base.MoreObjects;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.maintenance.testsignal.rev171215.maint.testsignal.MaintTestsignal;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint32;

/**
 * Class that builds {@link MaintTestsignalBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     MaintTestsignalBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new MaintTestsignalBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of MaintTestsignalBuilder, as instances can be freely passed around without
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
 * @see MaintTestsignalBuilder
 * @see Builder
 *
 */
public class MaintTestsignalBuilder implements Builder<MaintTestsignal> {

    private Uint32 _bitErrors;
    private Uint32 _bitErrorsTerminal;
    private String _syncSeconds;
    private String _syncSecondsTerminal;
    private MaintTestsignal.TestPattern _testPattern;
    private MaintTestsignal.Type _type;
    private Boolean _enabled;


    Map<Class<? extends Augmentation<MaintTestsignal>>, Augmentation<MaintTestsignal>> augmentation = Collections.emptyMap();

    public MaintTestsignalBuilder() {
    }

    public MaintTestsignalBuilder(MaintTestsignal base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<MaintTestsignal>>, Augmentation<MaintTestsignal>> aug =((AugmentationHolder<MaintTestsignal>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this._bitErrors = base.getBitErrors();
        this._bitErrorsTerminal = base.getBitErrorsTerminal();
        this._syncSeconds = base.getSyncSeconds();
        this._syncSecondsTerminal = base.getSyncSecondsTerminal();
        this._testPattern = base.getTestPattern();
        this._type = base.getType();
        this._enabled = base.isEnabled();
    }


    public Uint32 getBitErrors() {
        return _bitErrors;
    }

    public Uint32 getBitErrorsTerminal() {
        return _bitErrorsTerminal;
    }

    public String getSyncSeconds() {
        return _syncSeconds;
    }

    public String getSyncSecondsTerminal() {
        return _syncSecondsTerminal;
    }

    public MaintTestsignal.TestPattern getTestPattern() {
        return _testPattern;
    }

    public MaintTestsignal.Type getType() {
        return _type;
    }

    public Boolean isEnabled() {
        return _enabled;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<MaintTestsignal>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(CodeHelpers.nonNullValue(augmentationType, "augmentationType"));
    }


    private static void checkBitErrorsRange(final long value) {
    }

    public MaintTestsignalBuilder setBitErrors(final Uint32 value) {
        if (value != null) {
            checkBitErrorsRange(value.longValue());

        }
        this._bitErrors = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setBitErrors(Uint32)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public MaintTestsignalBuilder setBitErrors(final Long value) {
//        return setBitErrors(CodeHelpers.compatUint(value));
//    }

    private static void checkBitErrorsTerminalRange(final long value) {
    }

    public MaintTestsignalBuilder setBitErrorsTerminal(final Uint32 value) {
        if (value != null) {
            checkBitErrorsTerminalRange(value.longValue());

        }
        this._bitErrorsTerminal = value;
        return this;
    }

    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * #@deprecated Use {#link setBitErrorsTerminal(Uint32)} instead.
     */
//    @Deprecated(forRemoval = true)
//    public MaintTestsignalBuilder setBitErrorsTerminal(final Long value) {
//        return setBitErrorsTerminal(CodeHelpers.compatUint(value));
//    }

    public MaintTestsignalBuilder setSyncSeconds(final String value) {
        this._syncSeconds = value;
        return this;
    }

    public MaintTestsignalBuilder setSyncSecondsTerminal(final String value) {
        this._syncSecondsTerminal = value;
        return this;
    }

    public MaintTestsignalBuilder setTestPattern(final MaintTestsignal.TestPattern value) {
        this._testPattern = value;
        return this;
    }

    public MaintTestsignalBuilder setType(final MaintTestsignal.Type value) {
        this._type = value;
        return this;
    }

    public MaintTestsignalBuilder setEnabled(final Boolean value) {
        this._enabled = value;
        return this;
    }

    public MaintTestsignalBuilder addAugmentation(Class<? extends Augmentation<MaintTestsignal>> augmentationType, Augmentation<MaintTestsignal> augmentationValue) {
        if (augmentationValue == null) {
            return removeAugmentation(augmentationType);
        }

        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }

        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    public MaintTestsignalBuilder removeAugmentation(Class<? extends Augmentation<MaintTestsignal>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }

    @Override
    public MaintTestsignal build() {
        return new MaintTestsignalImpl(this);
    }

    private static final class MaintTestsignalImpl
        extends AbstractAugmentable<MaintTestsignal>
        implements MaintTestsignal {

        private final Uint32 _bitErrors;
        private final Uint32 _bitErrorsTerminal;
        private final String _syncSeconds;
        private final String _syncSecondsTerminal;
        private final MaintTestsignal.TestPattern _testPattern;
        private final MaintTestsignal.Type _type;
        private final Boolean _enabled;

        MaintTestsignalImpl(MaintTestsignalBuilder base) {
            super(base.augmentation);
            this._bitErrors = base.getBitErrors();
            this._bitErrorsTerminal = base.getBitErrorsTerminal();
            this._syncSeconds = base.getSyncSeconds();
            this._syncSecondsTerminal = base.getSyncSecondsTerminal();
            this._testPattern = base.getTestPattern();
            this._type = base.getType();
            this._enabled = base.isEnabled();
        }

        @Override
        public Uint32 getBitErrors() {
            return _bitErrors;
        }

        @Override
        public Uint32 getBitErrorsTerminal() {
            return _bitErrorsTerminal;
        }

        @Override
        public String getSyncSeconds() {
            return _syncSeconds;
        }

        @Override
        public String getSyncSecondsTerminal() {
            return _syncSecondsTerminal;
        }

        @Override
        public MaintTestsignal.TestPattern getTestPattern() {
            return _testPattern;
        }

        @Override
        public MaintTestsignal.Type getType() {
            return _type;
        }

        @Override
        public Boolean isEnabled() {
            return _enabled;
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
            result = prime * result + Objects.hashCode(_bitErrors);
            result = prime * result + Objects.hashCode(_bitErrorsTerminal);
            result = prime * result + Objects.hashCode(_syncSeconds);
            result = prime * result + Objects.hashCode(_syncSecondsTerminal);
            result = prime * result + Objects.hashCode(_testPattern);
            result = prime * result + Objects.hashCode(_type);
            result = prime * result + Objects.hashCode(_enabled);
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
            if (!MaintTestsignal.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            MaintTestsignal other = (MaintTestsignal)obj;
            if (!Objects.equals(_bitErrors, other.getBitErrors())) {
                return false;
            }
            if (!Objects.equals(_bitErrorsTerminal, other.getBitErrorsTerminal())) {
                return false;
            }
            if (!Objects.equals(_syncSeconds, other.getSyncSeconds())) {
                return false;
            }
            if (!Objects.equals(_syncSecondsTerminal, other.getSyncSecondsTerminal())) {
                return false;
            }
            if (!Objects.equals(_testPattern, other.getTestPattern())) {
                return false;
            }
            if (!Objects.equals(_type, other.getType())) {
                return false;
            }
            if (!Objects.equals(_enabled, other.isEnabled())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                MaintTestsignalImpl otherImpl = (MaintTestsignalImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<MaintTestsignal>>, Augmentation<MaintTestsignal>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("MaintTestsignal");
            CodeHelpers.appendValue(helper, "_bitErrors", _bitErrors);
            CodeHelpers.appendValue(helper, "_bitErrorsTerminal", _bitErrorsTerminal);
            CodeHelpers.appendValue(helper, "_syncSeconds", _syncSeconds);
            CodeHelpers.appendValue(helper, "_syncSecondsTerminal", _syncSecondsTerminal);
            CodeHelpers.appendValue(helper, "_testPattern", _testPattern);
            CodeHelpers.appendValue(helper, "_type", _type);
            CodeHelpers.appendValue(helper, "_enabled", _enabled);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
