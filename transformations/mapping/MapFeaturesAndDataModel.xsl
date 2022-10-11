<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML">
<xsl:template match="@*|node()">
		<xsl:apply-templates select="@*|node()"/>
</xsl:template>

<xsl:variable name="mappings" select="document('../../models/domain/WebStores/analysis/mapping/WebStoresFeatureDataMappingModel.xml')/mapping"/>

<xsl:template name="newline"><xsl:text>
</xsl:text></xsl:template>

<xsl:template match="//datamodel">
	<xsl:element name="datamodel">
		<xsl:apply-templates select="table"/>
		<xsl:apply-templates select="view"/>
	</xsl:element>
</xsl:template>

<xsl:template match="table">
	<xsl:variable name="tableName" select="@name"/>
	<xsl:element name="table">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature">
			<xsl:if test="$mappings/table[@name = $tableName]">
				<xsl:value-of select="$mappings/table[@name = $tableName]/@feature"/>
			</xsl:if>
			<xsl:if test="not($mappings/table[@name = $tableName])"></xsl:if>
		</xsl:attribute>
		<xsl:apply-templates select="attribute"/>
		<xsl:apply-templates select="operation"/>
		<xsl:apply-templates select="association"/>
	</xsl:element>
</xsl:template>

<xsl:template match="view">
	<xsl:variable name="viewName" select="@name"/>
	<xsl:element name="view">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="feature">
			<xsl:if test="$mappings/view[@name = $viewName]">
				<xsl:value-of select="$mappings/view[@name = $viewName]/@feature"/>
			</xsl:if>
			<xsl:if test="not($mappings/view[@name = $viewName])"></xsl:if>
		</xsl:attribute>
		<xsl:apply-templates select="attribute"/>
		<xsl:apply-templates select="operation"/>
		<xsl:apply-templates select="association"/>
	</xsl:element>
</xsl:template>

<xsl:template match="attribute">
	<xsl:variable name="attributeName" select="@name"/>
	<xsl:element name="attribute">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>	
		<xsl:attribute name="feature">
			<xsl:if test="$mappings/attribute[@name = $attributeName]">
				<xsl:value-of select="$mappings/attribute[@name = $attributeName]/@feature"/>
			</xsl:if>
			<xsl:if test="not($mappings/attribute[@name = $attributeName])"></xsl:if>
		</xsl:attribute>
	</xsl:element>
</xsl:template>

<xsl:template match="operation">
	<xsl:variable name="operationName" select="@name"/>
	<xsl:element name="operation">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
		<xsl:attribute name="feature">
			<xsl:if test="$mappings/operation[@name = $operationName]">
				<xsl:value-of select="$mappings/operation[@name = $operationName]/@feature"/>
			</xsl:if>
			<xsl:if test="not($mappings/operation[@name = $operationName])"></xsl:if>
		</xsl:attribute>
		<xsl:apply-templates select="@*|node()"/>
	</xsl:element>
</xsl:template>

<xsl:template match="parameters">
	<xsl:copy-of select="."/>
</xsl:template>

<xsl:template match="association">
	<xsl:variable name="associationDst" select="@dst"/>
	<xsl:variable name="associationSrc" select="@src"/>
	
	<xsl:element name="association">
		<xsl:attribute name="dst"><xsl:value-of select="@dst"/></xsl:attribute>
		<xsl:attribute name="src"><xsl:value-of select="@src"/></xsl:attribute>
		<xsl:attribute name="type"><xsl:value-of select="@type"/></xsl:attribute>
		<xsl:attribute name="feature">
			<xsl:value-of select="$mappings/table[@name=$associationDst]/@feature"/>
		</xsl:attribute>		
	</xsl:element>
</xsl:template>
</xsl:stylesheet>