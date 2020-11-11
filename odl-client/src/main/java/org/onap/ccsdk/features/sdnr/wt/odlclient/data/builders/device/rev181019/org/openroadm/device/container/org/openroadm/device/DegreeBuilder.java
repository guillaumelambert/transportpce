package org.onap.ccsdk.features.sdnr.wt.odlclient.data.builders.device.rev181019.org.openroadm.device.container.org.openroadm.device;
import com.google.common.base.MoreObjects;
import java.lang.Class;
import java.lang.Deprecated;
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
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.CircuitPacks;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.CircuitPacksKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.ConnectionPorts;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.ConnectionPortsKey;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.degree.OtdrPort;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.mc.capabilities.g.McCapabilities;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Degree;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.DegreeKey;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;

/**
 * Class that builds {@link DegreeBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     DegreeBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new DegreeBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of DegreeBuilder, as instances can be freely passed around without
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
 * @see DegreeBuilder
 * @see Builder
 *
 */
public class DegreeBuilder implements Builder<Degree> {

    private Map<CircuitPacksKey, CircuitPacks> _circuitPacks;
    private Map<ConnectionPortsKey, ConnectionPorts> _connectionPorts;
    private Uint16 _degreeNumber;
    private Uint16 _maxWavelengths;
    private McCapabilities _mcCapabilities;
    private OtdrPort _otdrPort;
    private DegreeKey key;


    Map<Class<? extends Augmentation<Degree>>, Augmentation<Degree>> augmentation = Collections.emptyMap();

    public DegreeBuilder() {
    }
    public DegreeBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Degree arg) {
        this._degreeNumber = arg.getDegreeNumber();
        this._maxWavelengths = arg.getMaxWavelengths();
        this._circuitPacks = arg.getCircuitPacks();
        this._connectionPorts = arg.getConnectionPorts();
        this._otdrPort = arg.getOtdrPort();
        this._mcCapabilities = arg.getMcCapabilities();
    }
    public DegreeBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG arg) {
        this._mcCapabilities = arg.getMcCapabilities();
    }

    public DegreeBuilder(Degree base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<Degree>>, Augmentation<Degree>> aug =((AugmentationHolder<Degree>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this.key = base.key();
        this._degreeNumber = base.getDegreeNumber();
        this._circuitPacks = base.getCircuitPacks();
        this._connectionPorts = base.getConnectionPorts();
        this._maxWavelengths = base.getMaxWavelengths();
        this._mcCapabilities = base.getMcCapabilities();
        this._otdrPort = base.getOtdrPort();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG</li>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Degree</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG) {
            this._mcCapabilities = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG)arg).getMcCapabilities();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Degree) {
            this._degreeNumber = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Degree)arg).getDegreeNumber();
            this._maxWavelengths = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Degree)arg).getMaxWavelengths();
            this._circuitPacks = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Degree)arg).getCircuitPacks();
            this._connectionPorts = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Degree)arg).getConnectionPorts();
            this._otdrPort = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Degree)arg).getOtdrPort();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.McCapabilitiesG, org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.Degree]");
    }

    public DegreeKey key() {
        return key;
    }
    
    public Map<CircuitPacksKey, CircuitPacks> getCircuitPacks() {
        return _circuitPacks;
    }
    
    public Map<ConnectionPortsKey, ConnectionPorts> getConnectionPorts() {
        return _connectionPorts;
    }
    
    public Uint16 getDegreeNumber() {
        return _degreeNumber;
    }
    
    public Uint16 getMaxWavelengths() {
        return _maxWavelengths;
    }
    
    public McCapabilities getMcCapabilities() {
        return _mcCapabilities;
    }
    
    public OtdrPort getOtdrPort() {
        return _otdrPort;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Degree>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    public DegreeBuilder withKey(final DegreeKey key) {
        this.key = key;
        return this;
    }
    public DegreeBuilder setCircuitPacks(final Map<CircuitPacksKey, CircuitPacks> values) {
        this._circuitPacks = values;
        return this;
    }
    
    /**
      * Utility migration setter.
      *
      * <b>IMPORTANT NOTE</b>: This method does not completely match previous mechanics, as the list is processed as
      *                        during this method's execution. Any future modifications of the list are <b>NOT</b>
      *                        reflected in this builder nor its products.
      *
      * @param values Legacy List of values
      * @return this builder
      * @throws IllegalArgumentException if the list contains entries with the same key
      * @throws NullPointerException if the list contains a null entry
      * @deprecated Use {#link #setCircuitPacks(Map)} instead.
      */
    /*@Deprecated(forRemoval = true)
    public DegreeBuilder setCircuitPacks(final List<CircuitPacks> values) {
        return setCircuitPacks(CodeHelpers.compatMap(values));
    }*/
    public DegreeBuilder setConnectionPorts(final Map<ConnectionPortsKey, ConnectionPorts> values) {
        this._connectionPorts = values;
        return this;
    }
    
    /**
      * Utility migration setter.
      *
      * <b>IMPORTANT NOTE</b>: This method does not completely match previous mechanics, as the list is processed as
      *                        during this method's execution. Any future modifications of the list are <b>NOT</b>
      *                        reflected in this builder nor its products.
      *
      * @param values Legacy List of values
      * @return this builder
      * @throws IllegalArgumentException if the list contains entries with the same key
      * @throws NullPointerException if the list contains a null entry
      * @deprecated Use {#link #setConnectionPorts(Map)} instead.
    
    @Deprecated(forRemoval = true)
    public DegreeBuilder setConnectionPorts(final List<ConnectionPorts> values) {
        return setConnectionPorts(CodeHelpers.compatMap(values));
    }*/
    
    public DegreeBuilder setDegreeNumber(final Uint16 value) {
        this._degreeNumber = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setDegreeNumber(Uint16)} instead.
     
    @Deprecated(forRemoval = true)
    public DegreeBuilder setDegreeNumber(final Integer value) {
        return setDegreeNumber(CodeHelpers.compatUint(value));
    }*/
    
    public DegreeBuilder setMaxWavelengths(final Uint16 value) {
        this._maxWavelengths = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setMaxWavelengths(Uint16)} instead.
     
    @Deprecated(forRemoval = true)
    public DegreeBuilder setMaxWavelengths(final Integer value) {
        return setMaxWavelengths(CodeHelpers.compatUint(value));
    }*/
    
    public DegreeBuilder setMcCapabilities(final McCapabilities value) {
        this._mcCapabilities = value;
        return this;
    }
    
    public DegreeBuilder setOtdrPort(final OtdrPort value) {
        this._otdrPort = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public DegreeBuilder addAugmentation(Augmentation<Degree> augmentation) {
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
    public DegreeBuilder addAugmentation(Class<? extends Augmentation<Degree>> augmentationType, Augmentation<Degree> augmentationValue) {
        return augmentationValue == null ? removeAugmentation(augmentationType) : doAddAugmentation(augmentationType, augmentationValue);
    }*/
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public DegreeBuilder removeAugmentation(Class<? extends Augmentation<Degree>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }
    
    private DegreeBuilder doAddAugmentation(Class<? extends Augmentation<Degree>> augmentationType, Augmentation<Degree> augmentationValue) {
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    @Override
    public Degree build() {
        return new DegreeImpl(this);
    }

    private static final class DegreeImpl
        extends AbstractAugmentable<Degree>
        implements Degree {
    
        private final Map<CircuitPacksKey, CircuitPacks> _circuitPacks;
        private final Map<ConnectionPortsKey, ConnectionPorts> _connectionPorts;
        private final Uint16 _degreeNumber;
        private final Uint16 _maxWavelengths;
        private final McCapabilities _mcCapabilities;
        private final OtdrPort _otdrPort;
        private final DegreeKey key;
    
        DegreeImpl(DegreeBuilder base) {
            super(base.augmentation);
            if (base.key() != null) {
                this.key = base.key();
            } else {
                this.key = new DegreeKey(base.getDegreeNumber());
            }
            this._degreeNumber = key.getDegreeNumber();
            this._circuitPacks = CodeHelpers.emptyToNull(base.getCircuitPacks());
            this._connectionPorts = CodeHelpers.emptyToNull(base.getConnectionPorts());
            this._maxWavelengths = base.getMaxWavelengths();
            this._mcCapabilities = base.getMcCapabilities();
            this._otdrPort = base.getOtdrPort();
        }
    
        @Override
        public DegreeKey key() {
            return key;
        }
        
        @Override
        public Map<CircuitPacksKey, CircuitPacks> getCircuitPacks() {
            return _circuitPacks;
        }
        
        @Override
        public Map<ConnectionPortsKey, ConnectionPorts> getConnectionPorts() {
            return _connectionPorts;
        }
        
        @Override
        public Uint16 getDegreeNumber() {
            return _degreeNumber;
        }
        
        @Override
        public Uint16 getMaxWavelengths() {
            return _maxWavelengths;
        }
        
        @Override
        public McCapabilities getMcCapabilities() {
            return _mcCapabilities;
        }
        
        @Override
        public OtdrPort getOtdrPort() {
            return _otdrPort;
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
            result = prime * result + Objects.hashCode(_connectionPorts);
            result = prime * result + Objects.hashCode(_degreeNumber);
            result = prime * result + Objects.hashCode(_maxWavelengths);
            result = prime * result + Objects.hashCode(_mcCapabilities);
            result = prime * result + Objects.hashCode(_otdrPort);
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
            if (!Degree.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            Degree other = (Degree)obj;
            if (!Objects.equals(_circuitPacks, other.getCircuitPacks())) {
                return false;
            }
            if (!Objects.equals(_connectionPorts, other.getConnectionPorts())) {
                return false;
            }
            if (!Objects.equals(_degreeNumber, other.getDegreeNumber())) {
                return false;
            }
            if (!Objects.equals(_maxWavelengths, other.getMaxWavelengths())) {
                return false;
            }
            if (!Objects.equals(_mcCapabilities, other.getMcCapabilities())) {
                return false;
            }
            if (!Objects.equals(_otdrPort, other.getOtdrPort())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                DegreeImpl otherImpl = (DegreeImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<Degree>>, Augmentation<Degree>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Degree");
            CodeHelpers.appendValue(helper, "_circuitPacks", _circuitPacks);
            CodeHelpers.appendValue(helper, "_connectionPorts", _connectionPorts);
            CodeHelpers.appendValue(helper, "_degreeNumber", _degreeNumber);
            CodeHelpers.appendValue(helper, "_maxWavelengths", _maxWavelengths);
            CodeHelpers.appendValue(helper, "_mcCapabilities", _mcCapabilities);
            CodeHelpers.appendValue(helper, "_otdrPort", _otdrPort);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
