<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML">
<xsl:template match="@*|node()">
		<xsl:apply-templates select="@*|node()"/>
</xsl:template>

<xsl:template name="newline"><xsl:text>
</xsl:text></xsl:template>

<xsl:template match="//statemodel">
	<xsl:element name="mapping"><xsl:call-template name="newline" />
		<xsl:apply-templates select="//screen" />
		<xsl:apply-templates select="//state" />
		<xsl:apply-templates select="//element" />
		<xsl:apply-templates select="//transition" />
	</xsl:element>
</xsl:template>

<xsl:template match="screen">
	<xsl:if test="not(@name = 'ExternalURL')">
		<xsl:element name="element">
			<xsl:attribute name="type">screen</xsl:attribute>
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		</xsl:element>
	</xsl:if>
</xsl:template>

<xsl:template match="state">
	<xsl:element name="element">
		<xsl:attribute name="type">state</xsl:attribute>
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
	</xsl:element>
</xsl:template>

<xsl:template match="element">
	<xsl:variable name="depth" match="node()">  
		<xsl:value-of select="count(ancestor::node())-4" />
	</xsl:variable>		
	<xsl:element name="element">
		<xsl:attribute name="type">element</xsl:attribute>
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="depth"><xsl:value-of select="$depth"/></xsl:attribute>
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="etype"><xsl:value-of select="@type"/></xsl:attribute>
	</xsl:element>
</xsl:template>

<xsl:template match="transition">
		<xsl:element name="element">
		<xsl:attribute name="type">transition</xsl:attribute>
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="source"><xsl:value-of select="@source"/></xsl:attribute>
		<xsl:attribute name="target"><xsl:value-of select="@target"/></xsl:attribute>
	</xsl:element>
</xsl:template>

</xsl:stylesheet>