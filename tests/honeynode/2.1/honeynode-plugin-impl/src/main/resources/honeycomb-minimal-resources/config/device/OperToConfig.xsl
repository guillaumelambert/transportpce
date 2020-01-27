﻿<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
xmlns:d="urn:ietf:params:xml:ns:netconf:base:1.0"
xmlns:oor="http://org/openroadm/device"
exclude-result-prefixes="oor d">

  <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

  <xsl:template match="/">
    <xsl:element name="config" namespace="urn:ietf:params:xml:ns:netconf:base:1.0">
    <xsl:element name="org-openroadm-device" namespace="http://org/openroadm/device">
    <xsl:element name="info">
      <xsl:apply-templates select="d:data/oor:org-openroadm-device/oor:info"/>
    </xsl:element>

    <xsl:copy-of select="d:data/oor:org-openroadm-device/oor:users" />

    <xsl:for-each select="d:data/oor:org-openroadm-device/oor:shelves">
      <xsl:element name="shelves" >
        <xsl:call-template name="shelve-body"/>
      </xsl:element>
    </xsl:for-each>

    <xsl:for-each select="d:data/oor:org-openroadm-device/oor:circuit-packs">
      <xsl:element name="circuit-packs" >
        <xsl:call-template name="cp-body"/>
      </xsl:element>
    </xsl:for-each>

    <xsl:for-each select="d:data/oor:org-openroadm-device/oor:interface">
        <xsl:element name="interface" >
            <xsl:call-template name="inter-body"/>
        </xsl:element>
    </xsl:for-each>

    <xsl:for-each select="d:data/oor:org-openroadm-device/oor:protocols">
        <xsl:element name="protocols" >
            <xsl:call-template name="proto-body"/>
        </xsl:element>
    </xsl:for-each>

    <xsl:for-each select="d:data/oor:org-openroadm-device/oor:degree">
      <xsl:element name="degree" >
        <xsl:call-template name="degree-body"/>
      </xsl:element>
    </xsl:for-each>

    <xsl:for-each select="d:data/oor:org-openroadm-device/oor:shared-risk-group">
      <xsl:element name="shared-risk-group" >
        <xsl:call-template name="srg-body"/>
      </xsl:element>
    </xsl:for-each>

    </xsl:element>
    </xsl:element>
  </xsl:template>

  <xsl:template match="oor:info">
    <xsl:for-each select="./*">
      <xsl:if test="name(.) = 'node-id' or name(.) = 'node-number' or name(.) = 'clli' or name(.) = 'ipAddress' or name(.) = 'prefix-length' or name(.) = 'defaultGateway' or name(.) = 'template' or name(.) = 'geoLocation'">
        <xsl:copy-of select="." />
      </xsl:if>
     </xsl:for-each>
  </xsl:template>

  <xsl:template name="shelve-body">
    <xsl:for-each select="./*">
      <xsl:if test="name(.) ='shelf-name' or name(.) = 'shelf-type' or name(.) = 'rack' or name(.) = 'shelf-position' or name(.) = 'administrative-state' or name(.) = 'equipment-state' or name(.) = 'due-date'">
        <xsl:copy-of select="." />
      </xsl:if>
    </xsl:for-each>
  </xsl:template>

  <xsl:template name="cp-body">
    <xsl:for-each select="./*">
      <xsl:if test="name(.) = 'circuit-pack-type' or name(.) = 'circuit-pack-product-code' or name(.) = 'circuit-pack-name' or name(.) = 'administrative-state' or name(.) = 'equipment-state' or name(.) = 'circuit-pack-mode' or name(.) = 'shelf' or name(.) = 'slot' or name(.) = 'subSlot' or name(.) = 'due-date' or name(.) = 'parent-circuit-pack'">
        <xsl:copy-of select="." />
      </xsl:if>
      <xsl:if test="name(.) = 'ports'">
        <xsl:for-each select=".">
          <xsl:element name="ports" >
            <xsl:call-template name="ports-body"/>
          </xsl:element>
        </xsl:for-each>
      </xsl:if>
     </xsl:for-each>
  </xsl:template>

  <xsl:template name="inter-body">
    <xsl:for-each select="./*">
        <xsl:if test="not(name(.) = 'operational-state' or name(.)='ethernet')">
         <xsl:copy-of select="." />
        </xsl:if>
        <xsl:if test="name(.)='ethernet'">
            <xsl:element name="ethernet" >
                <xsl:call-template name="eth-body"/>
            </xsl:element>
        </xsl:if>
    </xsl:for-each>
  </xsl:template>

   <xsl:template name="eth-body">
    <xsl:for-each select="./*">
      <xsl:if test="not(name(.) = 'curr-speed' or name(.)='curr-duplex') ">
         <xsl:copy-of select="." />
      </xsl:if>
    </xsl:for-each>
  </xsl:template>

  <xsl:template name="proto-body">
    <xsl:for-each select="./*">
      <xsl:if test="name(.) = 'lldp'">
        <xsl:for-each select=".">
          <xsl:element name="lldp" >
            <xsl:call-template name="lldp-body"/>
          </xsl:element>
        </xsl:for-each>
      </xsl:if>
     </xsl:for-each>
  </xsl:template>

  <xsl:template name="ports-body">
    <xsl:for-each select="./*">
      <xsl:if test="name(.) = 'port-name' or name(.) = 'port-type' or name(.) = 'port-qual' or name(.) = 'circuit-id' or name(.) = 'administrative-state' or name(.) = 'logical-connection-point' or name(.) = 'otdr-port' ">
         <xsl:copy-of select="." />
      </xsl:if>
    </xsl:for-each>
  </xsl:template>

  <xsl:template name="lldp-body">
    <xsl:for-each select="./*">
      <xsl:if test="not(name(.) = 'nbr-list') ">
         <xsl:copy-of select="." />
      </xsl:if>
    </xsl:for-each>
  </xsl:template>

  <xsl:template name="degree-body">
    <xsl:for-each select="./*">
      <xsl:if test="not(name(.) = 'max-wavelengths')">
         <xsl:copy-of select="." />
      </xsl:if>
    </xsl:for-each>
  </xsl:template>

  <xsl:template name="srg-body">
    <xsl:for-each select="./*">
      <xsl:if test="not(name(.) = 'max-add-drop-ports' or name(.) = 'wavelengthDuplication')">
         <xsl:copy-of select="." />
      </xsl:if>
    </xsl:for-each>
  </xsl:template>

 </xsl:stylesheet>
