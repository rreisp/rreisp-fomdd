<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML">

<xsl:template match="@*|node()">
	<xsl:apply-templates select="@*|node()" />
</xsl:template>

<xsl:template name="newline"><xsl:text>
</xsl:text></xsl:template>

<xsl:template match="//statemodel">
	<xsl:element name="views"><xsl:call-template name="newline" />
		<xsl:apply-templates select="screen" />
	</xsl:element>
</xsl:template>

<xsl:template match="screen">
	<xsl:if test="not(@name = 'ExternalURL')">
		<xsl:element name="screen">
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>
			<xsl:attribute name="layout"><xsl:value-of select="@layout"/></xsl:attribute>	
			<xsl:call-template name="newline" />
			<xsl:apply-templates select="state" />
		</xsl:element>
	</xsl:if>
</xsl:template>

<xsl:template match="state">
	<xsl:apply-templates select="element" />
</xsl:template>

<xsl:template match="element">
	<xsl:variable name="depth" match="node()">  
		<xsl:value-of select="count(ancestor::node())-4" />
	</xsl:variable>
	<xsl:variable name="elementName" select="@name"/>
	<xsl:if test="not(ancestor::element[@name = $elementName])">
		<xsl:element name="element">
			<xsl:attribute name="depth"><xsl:value-of select="$depth"/></xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
			<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>
			<xsl:attribute name="at"><xsl:value-of select="@at"/></xsl:attribute>
			<xsl:apply-templates select="@*|node()" />
		</xsl:element>
	</xsl:if>
</xsl:template>

<xsl:template match="transition">
	<xsl:copy-of select="."/>
</xsl:template>
	
</xsl:stylesheet>