<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML">
<xsl:template match="@*|node()">
		<xsl:apply-templates select="@*|node()"/>
</xsl:template>

<xsl:template name="newline"><xsl:text>
</xsl:text></xsl:template>

<xsl:template match="//datamodel">
	<xsl:element name="mapping"><xsl:call-template name="newline" />
		<xsl:apply-templates select="table"/>
		<xsl:apply-templates select="view"/>
	</xsl:element>
</xsl:template>

<xsl:template match="table">
	<xsl:element name="table">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"></xsl:attribute>
	</xsl:element>
	<xsl:apply-templates select="attribute"/>
	<xsl:apply-templates select="operation"/>
	<xsl:call-template name="newline" />
</xsl:template>

<xsl:template match="view">
	<xsl:element name="view">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"></xsl:attribute>
	</xsl:element>
	<xsl:apply-templates select="attribute"/>
	<xsl:apply-templates select="operation"/>
	<xsl:call-template name="newline" />
</xsl:template>

<xsl:template match="attribute">
	<xsl:element name="attribute">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"></xsl:attribute>
	</xsl:element>
	<xsl:call-template name="newline" />
</xsl:template>

<xsl:template match="operation">
	<xsl:element name="operation">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"></xsl:attribute>
	</xsl:element>
	<xsl:call-template name="newline" />
</xsl:template>

</xsl:stylesheet>