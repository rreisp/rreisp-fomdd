<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML">
<xsl:template match="@*|node()">
		<xsl:apply-templates select="@*|node()"/>
</xsl:template>

<xsl:template name="newline"><xsl:text>
</xsl:text></xsl:template>

<xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyz'" />
<xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />

<xsl:template match="//datamodel">
	<xsl:element name="models">
		<xsl:apply-templates select="table"/>
		<xsl:apply-templates select="view"/>
	</xsl:element>
</xsl:template>

<xsl:template match="table">
	<xsl:element name="model">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>
		<xsl:apply-templates select="attribute"/>
		<xsl:apply-templates select="operation"/>
		<xsl:apply-templates select="association">
			<xsl:with-param name="x" select="@name"/>
		</xsl:apply-templates>
	</xsl:element>
</xsl:template>

<xsl:template match="view">
	<xsl:element name="facade">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>
		<xsl:apply-templates select="attribute"/>
		<xsl:apply-templates select="operation"/>
		<xsl:apply-templates select="association">
			<xsl:with-param name="x" select="@name"/>
		</xsl:apply-templates>
	</xsl:element>
</xsl:template>

<xsl:template match="attribute">
	<xsl:element name="attribute">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
		<xsl:apply-templates select="@*|node()"/>
	</xsl:element>
	<xsl:call-template name="newline"/>
</xsl:template>

<xsl:template match="operation">
	<xsl:element name="operation">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
		<xsl:apply-templates select="@*|node()"/>
	</xsl:element>
</xsl:template>

<xsl:template match="association">
	<xsl:param name="x"/>
	<xsl:if test="@src = $x">
		<xsl:element name="attribute">
			<xsl:attribute name="name"><xsl:value-of select="concat(translate(substring(../@name,1,1),$uppercase,$smallcase),substring(../@name,2,string-length(@dst)+2))"/><xsl:value-of select="@dst"/></xsl:attribute>
			<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>
			<xsl:attribute name="type">
				<xsl:value-of select="@dst"/>
			</xsl:attribute>
			<xsl:attribute name="metaType">
				<xsl:if test="@type = '1..1'">
					<xsl:text>OneToOne</xsl:text>
				</xsl:if>
				<xsl:if test="@type = '1..*'">
					<xsl:text>OneToMany</xsl:text>
				</xsl:if>
				<xsl:if test="@type = '*..*'">
					<xsl:text>ManyToMany</xsl:text>
				</xsl:if>				
			</xsl:attribute>
			<xsl:attribute name="direction">
				<xsl:text>src</xsl:text>
			</xsl:attribute>
		</xsl:element>
	</xsl:if>
	<xsl:if test="@dst = $x">
		<xsl:element name="attribute">
			<xsl:attribute name="name"><xsl:value-of select="concat(translate(substring(@dst,1,1),$uppercase,$smallcase),substring(../@name,2,string-length(@dst)+2))"/><xsl:value-of select="@src"/></xsl:attribute>	
			<xsl:attribute name="feature"><xsl:value-of select="@feature"/></xsl:attribute>
			<xsl:attribute name="type">
				<xsl:value-of select="@src"/>
			</xsl:attribute>
			<xsl:attribute name="metaType">
				<xsl:if test="@type = '1..1'">
					<xsl:text>OneToOne</xsl:text>
				</xsl:if>
				<xsl:if test="@type = '1..*'">
					<xsl:text>ManyToOne</xsl:text>
				</xsl:if>
				<xsl:if test="@type = '*..*'">
					<xsl:text>ManyToMany</xsl:text>
				</xsl:if>
			</xsl:attribute>					
			<xsl:attribute name="direction">
				<xsl:text>dst</xsl:text>
			</xsl:attribute>
		</xsl:element>
	</xsl:if>
</xsl:template>

<xsl:template match="parameters">
	<xsl:element name="parameters">
		<xsl:apply-templates select="@*|node()"/>
	</xsl:element>
</xsl:template>

<xsl:template match="parameter">
	<xsl:element name="parameter">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>	
	</xsl:element>
</xsl:template>
</xsl:stylesheet>