<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML">
<xsl:template match="@*|node()">
		<xsl:apply-templates select="@*|node()"/>
</xsl:template>

<xsl:variable name="mappings" select="document('../../models/domain/WebStores/analysis/mapping/WebStoresFeatureStateMappingModel.xml')/mapping"/>

<xsl:template name="newline"><xsl:text>
</xsl:text></xsl:template>

<xsl:template match="//statemodel">
	<xsl:element name="statemodel">
		<xsl:apply-templates select="screen"/>
	</xsl:element>
</xsl:template>

<xsl:template match="screen">
	<xsl:variable name="screenName" select="@name"/>
	<xsl:if test="not($screenName = 'ExternalURL')">
		<xsl:element name="screen">
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:attribute name="feature">
				<xsl:if test="$mappings/screen[@name=$screenName]">
					<xsl:value-of select="$mappings/screen[@name=$screenName]/@feature"/>
				</xsl:if>
			</xsl:attribute>
			<xsl:apply-templates select="state"/>
		</xsl:element>
	</xsl:if>
</xsl:template>

<xsl:template match="state">
	<xsl:variable name="stateName" select="@name"/>
	<xsl:element name="state">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature">
			<xsl:if test="$mappings/state[@name=$stateName]">
				<xsl:value-of select="$mappings/state[@name=$stateName]/@feature"/>
			</xsl:if>
		</xsl:attribute>		
		<xsl:apply-templates select="element"/>
	</xsl:element>
</xsl:template>

<xsl:template match="element">
	<xsl:variable name="elementName" select="@name"/>
	<xsl:element name="element">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
		<xsl:attribute name="feature">
			<xsl:if test="$mappings/element[@name=$elementName]">
				<xsl:value-of select="$mappings/element[@name=$elementName]/@feature"/>
			</xsl:if>
		</xsl:attribute>
		<xsl:apply-templates select="transition"/>
		<xsl:apply-templates select="element"/>
	</xsl:element>
</xsl:template>

<xsl:template match="transition">
	<xsl:variable name="transitionName" select="@name"/>
	<xsl:variable name="transitionSource" select="@source"/>
	<xsl:variable name="transitionTarget" select="@target"/>
	<xsl:element name="transition">
		
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature">
			<xsl:if test="$mappings/transition[@name=$transitionName and @source=$transitionSource and @target=$transitionTarget]">
				<xsl:value-of select="$mappings/transition[@name=$transitionName and @source=$transitionSource and @target=$transitionTarget]/@feature"/>
			</xsl:if>
		</xsl:attribute>
		<xsl:attribute name="source"><xsl:value-of select="@source"/></xsl:attribute>
		<xsl:attribute name="target"><xsl:value-of select="@target"/></xsl:attribute>		
	</xsl:element>
</xsl:template>

</xsl:stylesheet>