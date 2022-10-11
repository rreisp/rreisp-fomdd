<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML">
<xsl:template match="@*|node()">
		<xsl:apply-templates select="@*|node()"/>
</xsl:template>

<xsl:template name="newline"><xsl:text>
</xsl:text></xsl:template>

<xsl:template match="//mapping">
	<xsl:element name="mapping"><xsl:call-template name="newline" />
		<xsl:for-each select="element">
      		<xsl:sort select="@name"/>
			
			<xsl:if test="@type = 'screen'">
				<xsl:element name="screen">
					<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
					<xsl:attribute name="feature"/>
				</xsl:element>
			</xsl:if>
			<xsl:if test="@type = 'state'">
				<xsl:element name="state">
					<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
					<xsl:attribute name="feature"/>
				</xsl:element>
			</xsl:if>			
			<xsl:if test="@type = 'element'">
				<xsl:element name="element">
					<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
					<xsl:attribute name="feature"/>
				</xsl:element>
			</xsl:if>
			<xsl:if test="@type = 'transition'">
				<xsl:element name="transition">
					<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
					<xsl:attribute name="feature"/>
					<xsl:attribute name="source"><xsl:value-of select="@source"/></xsl:attribute>
					<xsl:attribute name="target"><xsl:value-of select="@target"/></xsl:attribute>
				</xsl:element>
			</xsl:if>			
		</xsl:for-each>
	</xsl:element>
</xsl:template>
</xsl:stylesheet>