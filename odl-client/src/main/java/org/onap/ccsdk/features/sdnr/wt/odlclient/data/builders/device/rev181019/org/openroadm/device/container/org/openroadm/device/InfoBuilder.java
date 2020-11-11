  
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
import java.lang.Deprecated;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.Short;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.NodeIdType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.NodeTypes;
import org.opendaylight.yang.gen.v1.http.org.openroadm.common.types.rev181019.OpenroadmVersionType;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon.Source;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.device.common.GeoLocation;
import org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.org.openroadm.device.container.org.openroadm.device.Info;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.inet.types.rev130715.IpAddress;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.DateAndTime;
import org.opendaylight.yang.gen.v1.urn.ietf.params.xml.ns.yang.ietf.yang.types.rev130715.MacAddress;
import org.opendaylight.yangtools.concepts.Builder;
import org.opendaylight.yangtools.yang.binding.AbstractAugmentable;
import org.opendaylight.yangtools.yang.binding.Augmentation;
import org.opendaylight.yangtools.yang.binding.AugmentationHolder;
import org.opendaylight.yangtools.yang.binding.CodeHelpers;
import org.opendaylight.yangtools.yang.binding.DataObject;
import org.opendaylight.yangtools.yang.common.Uint16;
import org.opendaylight.yangtools.yang.common.Uint32;
import org.opendaylight.yangtools.yang.common.Uint8;

/**
 * Class that builds {@link InfoBuilder} instances. Overall design of the class is that of a
 * <a href="https://en.wikipedia.org/wiki/Fluent_interface">fluent interface</a>, where method chaining is used.
 *
 * <p>
 * In general, this class is supposed to be used like this template:
 * <pre>
 *   <code>
 *     InfoBuilder createTarget(int fooXyzzy, int barBaz) {
 *         return new InfoBuilderBuilder()
 *             .setFoo(new FooBuilder().setXyzzy(fooXyzzy).build())
 *             .setBar(new BarBuilder().setBaz(barBaz).build())
 *             .build();
 *     }
 *   </code>
 * </pre>
 *
 * <p>
 * This pattern is supported by the immutable nature of InfoBuilder, as instances can be freely passed around without
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
 * @see InfoBuilder
 * @see Builder
 *
 */
public class InfoBuilder implements Builder<Info> {

    private String _clli;
    private DateAndTime _currentDatetime;
    private IpAddress _currentDefaultGateway;
    private IpAddress _currentIpAddress;
    private Uint8 _currentPrefixLength;
    private IpAddress _defaultGateway;
    private GeoLocation _geoLocation;
    private IpAddress _ipAddress;
    private MacAddress _macAddress;
    private Uint16 _maxDegrees;
    private Uint16 _maxNumBin15minHistoricalPm;
    private Uint16 _maxNumBin24hourHistoricalPm;
    private Uint16 _maxSrgs;
    private String _model;
    private NodeIdType _nodeId;
    private Uint32 _nodeNumber;
    private NodeTypes _nodeType;
    private OpenroadmVersionType _openroadmVersion;
    private Uint8 _prefixLength;
    private String _serialId;
    private String _softwareVersion;
    private Source _source;
    private String _template;
    private String _vendor;


    Map<Class<? extends Augmentation<Info>>, Augmentation<Info>> augmentation = Collections.emptyMap();

    public InfoBuilder() {
    }
    public InfoBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon arg) {
        this._nodeId = arg.getNodeId();
        this._nodeNumber = arg.getNodeNumber();
        this._nodeType = arg.getNodeType();
        this._clli = arg.getClli();
        this._ipAddress = arg.getIpAddress();
        this._prefixLength = arg.getPrefixLength();
        this._defaultGateway = arg.getDefaultGateway();
        this._source = arg.getSource();
        this._currentIpAddress = arg.getCurrentIpAddress();
        this._currentPrefixLength = arg.getCurrentPrefixLength();
        this._currentDefaultGateway = arg.getCurrentDefaultGateway();
        this._macAddress = arg.getMacAddress();
        this._softwareVersion = arg.getSoftwareVersion();
        this._openroadmVersion = arg.getOpenroadmVersion();
        this._template = arg.getTemplate();
        this._currentDatetime = arg.getCurrentDatetime();
        this._geoLocation = arg.getGeoLocation();
        this._vendor = arg.getVendor();
        this._model = arg.getModel();
        this._serialId = arg.getSerialId();
    }
    public InfoBuilder(org.opendaylight.yang.gen.v1.http.org.openroadm.physical.types.rev181019.NodeInfo arg) {
        this._vendor = arg.getVendor();
        this._model = arg.getModel();
        this._serialId = arg.getSerialId();
    }

    public InfoBuilder(Info base) {
        if (base instanceof AugmentationHolder) {
            @SuppressWarnings("unchecked")
            Map<Class<? extends Augmentation<Info>>, Augmentation<Info>> aug =((AugmentationHolder<Info>) base).augmentations();
            if (!aug.isEmpty()) {
                this.augmentation = new HashMap<>(aug);
            }
        }
        this._clli = base.getClli();
        this._currentDatetime = base.getCurrentDatetime();
        this._currentDefaultGateway = base.getCurrentDefaultGateway();
        this._currentIpAddress = base.getCurrentIpAddress();
        this._currentPrefixLength = base.getCurrentPrefixLength();
        this._defaultGateway = base.getDefaultGateway();
        this._geoLocation = base.getGeoLocation();
        this._ipAddress = base.getIpAddress();
        this._macAddress = base.getMacAddress();
        this._maxDegrees = base.getMaxDegrees();
        this._maxNumBin15minHistoricalPm = base.getMaxNumBin15minHistoricalPm();
        this._maxNumBin24hourHistoricalPm = base.getMaxNumBin24hourHistoricalPm();
        this._maxSrgs = base.getMaxSrgs();
        this._model = base.getModel();
        this._nodeId = base.getNodeId();
        this._nodeNumber = base.getNodeNumber();
        this._nodeType = base.getNodeType();
        this._openroadmVersion = base.getOpenroadmVersion();
        this._prefixLength = base.getPrefixLength();
        this._serialId = base.getSerialId();
        this._softwareVersion = base.getSoftwareVersion();
        this._source = base.getSource();
        this._template = base.getTemplate();
        this._vendor = base.getVendor();
    }

    /**
     * Set fields from given grouping argument. Valid argument is instance of one of following types:
     * <ul>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.physical.types.rev181019.NodeInfo</li>
     * <li>org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon</li>
     * </ul>
     *
     * @param arg grouping object
     * @throws IllegalArgumentException if given argument is none of valid types
    */
    public void fieldsFrom(DataObject arg) {
        boolean isValidArg = false;
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.physical.types.rev181019.NodeInfo) {
            this._vendor = ((org.opendaylight.yang.gen.v1.http.org.openroadm.physical.types.rev181019.NodeInfo)arg).getVendor();
            this._model = ((org.opendaylight.yang.gen.v1.http.org.openroadm.physical.types.rev181019.NodeInfo)arg).getModel();
            this._serialId = ((org.opendaylight.yang.gen.v1.http.org.openroadm.physical.types.rev181019.NodeInfo)arg).getSerialId();
            isValidArg = true;
        }
        if (arg instanceof org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon) {
            this._nodeId = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getNodeId();
            this._nodeNumber = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getNodeNumber();
            this._nodeType = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getNodeType();
            this._clli = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getClli();
            this._ipAddress = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getIpAddress();
            this._prefixLength = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getPrefixLength();
            this._defaultGateway = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getDefaultGateway();
            this._source = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getSource();
            this._currentIpAddress = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getCurrentIpAddress();
            this._currentPrefixLength = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getCurrentPrefixLength();
            this._currentDefaultGateway = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getCurrentDefaultGateway();
            this._macAddress = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getMacAddress();
            this._softwareVersion = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getSoftwareVersion();
            this._openroadmVersion = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getOpenroadmVersion();
            this._template = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getTemplate();
            this._currentDatetime = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getCurrentDatetime();
            this._geoLocation = ((org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon)arg).getGeoLocation();
            isValidArg = true;
        }
        CodeHelpers.validValue(isValidArg, arg, "[org.opendaylight.yang.gen.v1.http.org.openroadm.physical.types.rev181019.NodeInfo, org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon]");
    }

    public String getClli() {
        return _clli;
    }
    
    public DateAndTime getCurrentDatetime() {
        return _currentDatetime;
    }
    
    public IpAddress getCurrentDefaultGateway() {
        return _currentDefaultGateway;
    }
    
    public IpAddress getCurrentIpAddress() {
        return _currentIpAddress;
    }
    
    public Uint8 getCurrentPrefixLength() {
        return _currentPrefixLength;
    }
    
    public IpAddress getDefaultGateway() {
        return _defaultGateway;
    }
    
    public GeoLocation getGeoLocation() {
        return _geoLocation;
    }
    
    public IpAddress getIpAddress() {
        return _ipAddress;
    }
    
    public MacAddress getMacAddress() {
        return _macAddress;
    }
    
    public Uint16 getMaxDegrees() {
        return _maxDegrees;
    }
    
    public Uint16 getMaxNumBin15minHistoricalPm() {
        return _maxNumBin15minHistoricalPm;
    }
    
    public Uint16 getMaxNumBin24hourHistoricalPm() {
        return _maxNumBin24hourHistoricalPm;
    }
    
    public Uint16 getMaxSrgs() {
        return _maxSrgs;
    }
    
    public String getModel() {
        return _model;
    }
    
    public NodeIdType getNodeId() {
        return _nodeId;
    }
    
    public Uint32 getNodeNumber() {
        return _nodeNumber;
    }
    
    public NodeTypes getNodeType() {
        return _nodeType;
    }
    
    public OpenroadmVersionType getOpenroadmVersion() {
        return _openroadmVersion;
    }
    
    public Uint8 getPrefixLength() {
        return _prefixLength;
    }
    
    public String getSerialId() {
        return _serialId;
    }
    
    public String getSoftwareVersion() {
        return _softwareVersion;
    }
    
    public Source getSource() {
        return _source;
    }
    
    public String getTemplate() {
        return _template;
    }
    
    public String getVendor() {
        return _vendor;
    }

    @SuppressWarnings({ "unchecked", "checkstyle:methodTypeParameterName"})
    public <E$$ extends Augmentation<Info>> E$$ augmentation(Class<E$$> augmentationType) {
        return (E$$) augmentation.get(Objects.requireNonNull(augmentationType));
    }

    
    public InfoBuilder setClli(final String value) {
        this._clli = value;
        return this;
    }
    
    public InfoBuilder setCurrentDatetime(final DateAndTime value) {
        this._currentDatetime = value;
        return this;
    }
    
    public InfoBuilder setCurrentDefaultGateway(final IpAddress value) {
        this._currentDefaultGateway = value;
        return this;
    }
    
    public InfoBuilder setCurrentIpAddress(final IpAddress value) {
        this._currentIpAddress = value;
        return this;
    }
    
    private static void checkCurrentPrefixLengthRange(final short value) {
        if (value <= (short)128) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..128]]", value);
    }
    
    public InfoBuilder setCurrentPrefixLength(final Uint8 value) {
        if (value != null) {
            checkCurrentPrefixLengthRange(value.shortValue());
            
        }
        this._currentPrefixLength = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setCurrentPrefixLength(Uint8)} instead.
     
    @Deprecated(forRemoval = true)
    public InfoBuilder setCurrentPrefixLength(final Short value) {
        return setCurrentPrefixLength(CodeHelpers.compatUint(value));
    }*/
    
    public InfoBuilder setDefaultGateway(final IpAddress value) {
        this._defaultGateway = value;
        return this;
    }
    
    public InfoBuilder setGeoLocation(final GeoLocation value) {
        this._geoLocation = value;
        return this;
    }
    
    public InfoBuilder setIpAddress(final IpAddress value) {
        this._ipAddress = value;
        return this;
    }
    
    public InfoBuilder setMacAddress(final MacAddress value) {
        this._macAddress = value;
        return this;
    }
    
    public InfoBuilder setMaxDegrees(final Uint16 value) {
        this._maxDegrees = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setMaxDegrees(Uint16)} instead.
     
    @Deprecated(forRemoval = true)
    public InfoBuilder setMaxDegrees(final Integer value) {
        return setMaxDegrees(CodeHelpers.compatUint(value));
    }*/
    
    public InfoBuilder setMaxNumBin15minHistoricalPm(final Uint16 value) {
        this._maxNumBin15minHistoricalPm = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setMaxNumBin15minHistoricalPm(Uint16)} instead.
     
    @Deprecated(forRemoval = true)
    public InfoBuilder setMaxNumBin15minHistoricalPm(final Integer value) {
        return setMaxNumBin15minHistoricalPm(CodeHelpers.compatUint(value));
    }*/
    
    public InfoBuilder setMaxNumBin24hourHistoricalPm(final Uint16 value) {
        this._maxNumBin24hourHistoricalPm = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setMaxNumBin24hourHistoricalPm(Uint16)} instead.
     
    @Deprecated(forRemoval = true)
    public InfoBuilder setMaxNumBin24hourHistoricalPm(final Integer value) {
        return setMaxNumBin24hourHistoricalPm(CodeHelpers.compatUint(value));
    }*/
    
    public InfoBuilder setMaxSrgs(final Uint16 value) {
        this._maxSrgs = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setMaxSrgs(Uint16)} instead.
     
    @Deprecated(forRemoval = true)
    public InfoBuilder setMaxSrgs(final Integer value) {
        return setMaxSrgs(CodeHelpers.compatUint(value));
    }*/
    
    public InfoBuilder setModel(final String value) {
        this._model = value;
        return this;
    }
    
    public InfoBuilder setNodeId(final NodeIdType value) {
        this._nodeId = value;
        return this;
    }
    
    public InfoBuilder setNodeNumber(final Uint32 value) {
        this._nodeNumber = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setNodeNumber(Uint32)} instead.
     
    @Deprecated(forRemoval = true)
    public InfoBuilder setNodeNumber(final Long value) {
        return setNodeNumber(CodeHelpers.compatUint(value));
    }*/
    
    public InfoBuilder setNodeType(final NodeTypes value) {
        this._nodeType = value;
        return this;
    }
    
    public InfoBuilder setOpenroadmVersion(final OpenroadmVersionType value) {
        this._openroadmVersion = value;
        return this;
    }
    
    private static void checkPrefixLengthRange(final short value) {
        if (value <= (short)128) {
            return;
        }
        CodeHelpers.throwInvalidRange("[[0..128]]", value);
    }
    
    public InfoBuilder setPrefixLength(final Uint8 value) {
        if (value != null) {
            checkPrefixLengthRange(value.shortValue());
            
        }
        this._prefixLength = value;
        return this;
    }
    
    /**
     * Utility migration setter.
     *
     * @param value field value in legacy type
     * @return this builder
     * @deprecated Use {#link setPrefixLength(Uint8)} instead.
     
    @Deprecated(forRemoval = true)
    public InfoBuilder setPrefixLength(final Short value) {
        return setPrefixLength(CodeHelpers.compatUint(value));
    }*/
    
    public InfoBuilder setSerialId(final String value) {
        this._serialId = value;
        return this;
    }
    
    public InfoBuilder setSoftwareVersion(final String value) {
        this._softwareVersion = value;
        return this;
    }
    
    public InfoBuilder setSource(final Source value) {
        this._source = value;
        return this;
    }
    
    public InfoBuilder setTemplate(final String value) {
        this._template = value;
        return this;
    }
    
    public InfoBuilder setVendor(final String value) {
        this._vendor = value;
        return this;
    }
    
    /**
      * Add an augmentation to this builder's product.
      *
      * @param augmentation augmentation to be added
      * @return this builder
      * @throws NullPointerException if {@code augmentation} is null
      */
    public InfoBuilder addAugmentation(Augmentation<Info> augmentation) {
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
    public InfoBuilder addAugmentation(Class<? extends Augmentation<Info>> augmentationType, Augmentation<Info> augmentationValue) {
        return augmentationValue == null ? removeAugmentation(augmentationType) : doAddAugmentation(augmentationType, augmentationValue);
    }*/
    
    /**
      * Remove an augmentation from this builder's product. If this builder does not track such an augmentation
      * type, this method does nothing.
      *
      * @param augmentationType augmentation type to be removed
      * @return this builder
      */
    public InfoBuilder removeAugmentation(Class<? extends Augmentation<Info>> augmentationType) {
        if (this.augmentation instanceof HashMap) {
            this.augmentation.remove(augmentationType);
        }
        return this;
    }
    
    private InfoBuilder doAddAugmentation(Class<? extends Augmentation<Info>> augmentationType, Augmentation<Info> augmentationValue) {
        if (!(this.augmentation instanceof HashMap)) {
            this.augmentation = new HashMap<>();
        }
    
        this.augmentation.put(augmentationType, augmentationValue);
        return this;
    }

    @Override
    public Info build() {
        return new InfoImpl(this);
    }

    private static final class InfoImpl
        extends AbstractAugmentable<Info>
        implements Info {
    
        private final String _clli;
        private final DateAndTime _currentDatetime;
        private final IpAddress _currentDefaultGateway;
        private final IpAddress _currentIpAddress;
        private final Uint8 _currentPrefixLength;
        private final IpAddress _defaultGateway;
        private final GeoLocation _geoLocation;
        private final IpAddress _ipAddress;
        private final MacAddress _macAddress;
        private final Uint16 _maxDegrees;
        private final Uint16 _maxNumBin15minHistoricalPm;
        private final Uint16 _maxNumBin24hourHistoricalPm;
        private final Uint16 _maxSrgs;
        private final String _model;
        private final NodeIdType _nodeId;
        private final Uint32 _nodeNumber;
        private final NodeTypes _nodeType;
        private final OpenroadmVersionType _openroadmVersion;
        private final Uint8 _prefixLength;
        private final String _serialId;
        private final String _softwareVersion;
        private final org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon.Source _source;
        private final String _template;
        private final String _vendor;
    
        InfoImpl(InfoBuilder base) {
            super(base.augmentation);
            this._clli = base.getClli();
            this._currentDatetime = base.getCurrentDatetime();
            this._currentDefaultGateway = base.getCurrentDefaultGateway();
            this._currentIpAddress = base.getCurrentIpAddress();
            this._currentPrefixLength = base.getCurrentPrefixLength();
            this._defaultGateway = base.getDefaultGateway();
            this._geoLocation = base.getGeoLocation();
            this._ipAddress = base.getIpAddress();
            this._macAddress = base.getMacAddress();
            this._maxDegrees = base.getMaxDegrees();
            this._maxNumBin15minHistoricalPm = base.getMaxNumBin15minHistoricalPm();
            this._maxNumBin24hourHistoricalPm = base.getMaxNumBin24hourHistoricalPm();
            this._maxSrgs = base.getMaxSrgs();
            this._model = base.getModel();
            this._nodeId = base.getNodeId();
            this._nodeNumber = base.getNodeNumber();
            this._nodeType = base.getNodeType();
            this._openroadmVersion = base.getOpenroadmVersion();
            this._prefixLength = base.getPrefixLength();
            this._serialId = base.getSerialId();
            this._softwareVersion = base.getSoftwareVersion();
            this._source = base.getSource();
            this._template = base.getTemplate();
            this._vendor = base.getVendor();
        }
    
        @Override
        public String getClli() {
            return _clli;
        }
        
        @Override
        public DateAndTime getCurrentDatetime() {
            return _currentDatetime;
        }
        
        @Override
        public IpAddress getCurrentDefaultGateway() {
            return _currentDefaultGateway;
        }
        
        @Override
        public IpAddress getCurrentIpAddress() {
            return _currentIpAddress;
        }
        
        @Override
        public Uint8 getCurrentPrefixLength() {
            return _currentPrefixLength;
        }
        
        @Override
        public IpAddress getDefaultGateway() {
            return _defaultGateway;
        }
        
        @Override
        public GeoLocation getGeoLocation() {
            return _geoLocation;
        }
        
        @Override
        public IpAddress getIpAddress() {
            return _ipAddress;
        }
        
        @Override
        public MacAddress getMacAddress() {
            return _macAddress;
        }
        
        @Override
        public Uint16 getMaxDegrees() {
            return _maxDegrees;
        }
        
        @Override
        public Uint16 getMaxNumBin15minHistoricalPm() {
            return _maxNumBin15minHistoricalPm;
        }
        
        @Override
        public Uint16 getMaxNumBin24hourHistoricalPm() {
            return _maxNumBin24hourHistoricalPm;
        }
        
        @Override
        public Uint16 getMaxSrgs() {
            return _maxSrgs;
        }
        
        @Override
        public String getModel() {
            return _model;
        }
        
        @Override
        public NodeIdType getNodeId() {
            return _nodeId;
        }
        
        @Override
        public Uint32 getNodeNumber() {
            return _nodeNumber;
        }
        
        @Override
        public NodeTypes getNodeType() {
            return _nodeType;
        }
        
        @Override
        public OpenroadmVersionType getOpenroadmVersion() {
            return _openroadmVersion;
        }
        
        @Override
        public Uint8 getPrefixLength() {
            return _prefixLength;
        }
        
        @Override
        public String getSerialId() {
            return _serialId;
        }
        
        @Override
        public String getSoftwareVersion() {
            return _softwareVersion;
        }
        
        @Override
        public org.opendaylight.yang.gen.v1.http.org.openroadm.device.rev181019.DeviceCommon.Source getSource() {
            return _source;
        }
        
        @Override
        public String getTemplate() {
            return _template;
        }
        
        @Override
        public String getVendor() {
            return _vendor;
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
            result = prime * result + Objects.hashCode(_clli);
            result = prime * result + Objects.hashCode(_currentDatetime);
            result = prime * result + Objects.hashCode(_currentDefaultGateway);
            result = prime * result + Objects.hashCode(_currentIpAddress);
            result = prime * result + Objects.hashCode(_currentPrefixLength);
            result = prime * result + Objects.hashCode(_defaultGateway);
            result = prime * result + Objects.hashCode(_geoLocation);
            result = prime * result + Objects.hashCode(_ipAddress);
            result = prime * result + Objects.hashCode(_macAddress);
            result = prime * result + Objects.hashCode(_maxDegrees);
            result = prime * result + Objects.hashCode(_maxNumBin15minHistoricalPm);
            result = prime * result + Objects.hashCode(_maxNumBin24hourHistoricalPm);
            result = prime * result + Objects.hashCode(_maxSrgs);
            result = prime * result + Objects.hashCode(_model);
            result = prime * result + Objects.hashCode(_nodeId);
            result = prime * result + Objects.hashCode(_nodeNumber);
            result = prime * result + Objects.hashCode(_nodeType);
            result = prime * result + Objects.hashCode(_openroadmVersion);
            result = prime * result + Objects.hashCode(_prefixLength);
            result = prime * result + Objects.hashCode(_serialId);
            result = prime * result + Objects.hashCode(_softwareVersion);
            result = prime * result + Objects.hashCode(_source);
            result = prime * result + Objects.hashCode(_template);
            result = prime * result + Objects.hashCode(_vendor);
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
            if (!Info.class.equals(((DataObject)obj).implementedInterface())) {
                return false;
            }
            Info other = (Info)obj;
            if (!Objects.equals(_clli, other.getClli())) {
                return false;
            }
            if (!Objects.equals(_currentDatetime, other.getCurrentDatetime())) {
                return false;
            }
            if (!Objects.equals(_currentDefaultGateway, other.getCurrentDefaultGateway())) {
                return false;
            }
            if (!Objects.equals(_currentIpAddress, other.getCurrentIpAddress())) {
                return false;
            }
            if (!Objects.equals(_currentPrefixLength, other.getCurrentPrefixLength())) {
                return false;
            }
            if (!Objects.equals(_defaultGateway, other.getDefaultGateway())) {
                return false;
            }
            if (!Objects.equals(_geoLocation, other.getGeoLocation())) {
                return false;
            }
            if (!Objects.equals(_ipAddress, other.getIpAddress())) {
                return false;
            }
            if (!Objects.equals(_macAddress, other.getMacAddress())) {
                return false;
            }
            if (!Objects.equals(_maxDegrees, other.getMaxDegrees())) {
                return false;
            }
            if (!Objects.equals(_maxNumBin15minHistoricalPm, other.getMaxNumBin15minHistoricalPm())) {
                return false;
            }
            if (!Objects.equals(_maxNumBin24hourHistoricalPm, other.getMaxNumBin24hourHistoricalPm())) {
                return false;
            }
            if (!Objects.equals(_maxSrgs, other.getMaxSrgs())) {
                return false;
            }
            if (!Objects.equals(_model, other.getModel())) {
                return false;
            }
            if (!Objects.equals(_nodeId, other.getNodeId())) {
                return false;
            }
            if (!Objects.equals(_nodeNumber, other.getNodeNumber())) {
                return false;
            }
            if (!Objects.equals(_nodeType, other.getNodeType())) {
                return false;
            }
            if (!Objects.equals(_openroadmVersion, other.getOpenroadmVersion())) {
                return false;
            }
            if (!Objects.equals(_prefixLength, other.getPrefixLength())) {
                return false;
            }
            if (!Objects.equals(_serialId, other.getSerialId())) {
                return false;
            }
            if (!Objects.equals(_softwareVersion, other.getSoftwareVersion())) {
                return false;
            }
            if (!Objects.equals(_source, other.getSource())) {
                return false;
            }
            if (!Objects.equals(_template, other.getTemplate())) {
                return false;
            }
            if (!Objects.equals(_vendor, other.getVendor())) {
                return false;
            }
            if (getClass() == obj.getClass()) {
                // Simple case: we are comparing against self
                InfoImpl otherImpl = (InfoImpl) obj;
                if (!Objects.equals(augmentations(), otherImpl.augmentations())) {
                    return false;
                }
            } else {
                // Hard case: compare our augments with presence there...
                for (Map.Entry<Class<? extends Augmentation<Info>>, Augmentation<Info>> e : augmentations().entrySet()) {
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
            final MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Info");
            CodeHelpers.appendValue(helper, "_clli", _clli);
            CodeHelpers.appendValue(helper, "_currentDatetime", _currentDatetime);
            CodeHelpers.appendValue(helper, "_currentDefaultGateway", _currentDefaultGateway);
            CodeHelpers.appendValue(helper, "_currentIpAddress", _currentIpAddress);
            CodeHelpers.appendValue(helper, "_currentPrefixLength", _currentPrefixLength);
            CodeHelpers.appendValue(helper, "_defaultGateway", _defaultGateway);
            CodeHelpers.appendValue(helper, "_geoLocation", _geoLocation);
            CodeHelpers.appendValue(helper, "_ipAddress", _ipAddress);
            CodeHelpers.appendValue(helper, "_macAddress", _macAddress);
            CodeHelpers.appendValue(helper, "_maxDegrees", _maxDegrees);
            CodeHelpers.appendValue(helper, "_maxNumBin15minHistoricalPm", _maxNumBin15minHistoricalPm);
            CodeHelpers.appendValue(helper, "_maxNumBin24hourHistoricalPm", _maxNumBin24hourHistoricalPm);
            CodeHelpers.appendValue(helper, "_maxSrgs", _maxSrgs);
            CodeHelpers.appendValue(helper, "_model", _model);
            CodeHelpers.appendValue(helper, "_nodeId", _nodeId);
            CodeHelpers.appendValue(helper, "_nodeNumber", _nodeNumber);
            CodeHelpers.appendValue(helper, "_nodeType", _nodeType);
            CodeHelpers.appendValue(helper, "_openroadmVersion", _openroadmVersion);
            CodeHelpers.appendValue(helper, "_prefixLength", _prefixLength);
            CodeHelpers.appendValue(helper, "_serialId", _serialId);
            CodeHelpers.appendValue(helper, "_softwareVersion", _softwareVersion);
            CodeHelpers.appendValue(helper, "_source", _source);
            CodeHelpers.appendValue(helper, "_template", _template);
            CodeHelpers.appendValue(helper, "_vendor", _vendor);
            CodeHelpers.appendValue(helper, "augmentation", augmentations().values());
            return helper.toString();
        }
    }
}
