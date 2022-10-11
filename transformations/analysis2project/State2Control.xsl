<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML">

<xsl:template match="@*|node()">
	<xsl:apply-templates select="@*|node()" />
</xsl:template>

<xsl:template name="newline"><xsl:text>
</xsl:text></xsl:template>

<xsl:template match="//statemodel">
	<xsl:element name="controllers"><xsl:call-template name="newline" />
		<xsl:apply-templates select="screen" />
	</xsl:element>
</xsl:template>

<xsl:template match="screen">
	<xsl:element name="screen">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>
		<xsl:call-template name="newline" />
		<xsl:apply-templates select="state" />
	</xsl:element>
</xsl:template>

<xsl:template match="state">
	<xsl:apply-templates select="element" />
</xsl:template>

<xsl:template match="element">
	<xsl:apply-templates select="transition" />
	<xsl:apply-templates select="element" />
</xsl:template>

<xsl:template match="transition">
	<xsl:element name="handler">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>		
		<xsl:attribute name="source"><xsl:value-of select="@source"/></xsl:attribute>
		<xsl:attribute name="target"><xsl:value-of select="@target"/></xsl:attribute>
	</xsl:element>
</xsl:template>
</xsl:stylesheet>