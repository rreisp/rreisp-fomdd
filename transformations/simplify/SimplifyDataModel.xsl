<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xmi="http://schema.omg.org/spec/XMI/2.1" xmlns:uml="http://www.eclipse.org/uml2/3.0.0/UML" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:DataModeling="http:///schemas/DataModeling/_lEBNEK0EEd6mis3eHak5Vg/0" xsi:schemaLocation="http:///schemas/DataModeling/_lEBNEK0EEd6mis3eHak5Vg/0 ../target/analysis/metamodel/DataModeling.profile.uml#_lEDpUK0EEd6mis3eHak5Vg">
<xsl:template match="@*|node()">
		<xsl:apply-templates select="@*|node()"/>
</xsl:template>

<xsl:template name="newline"><xsl:text>
</xsl:text></xsl:template>

<xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyz'" />
<xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />

<xsl:variable name="all_classes">
	<xsl:apply-templates select="//packagedElement[@xmi:type='uml:Class']"/>
</xsl:variable> 

<xsl:variable name="all_one2many_associations">
	<xsl:apply-templates select="//packagedElement[@xmi:type='uml:Association' and @name='1..*']"/>
</xsl:variable> 

<xsl:variable name="all_many2one_associations">
	<xsl:apply-templates select="//packagedElement[@xmi:type='uml:Association' and @name='*..1']"/>
</xsl:variable> 

<xsl:variable name="all_many2many_associations">
	<xsl:apply-templates select="//packagedElement[@xmi:type='uml:Association' and @name='*..*']"/>
</xsl:variable> 

<xsl:variable name="all_tables">
	<xsl:apply-templates select="//DataModeling:Table"/>
</xsl:variable> 

<xsl:variable name="all_views">
	<xsl:apply-templates select="//DataModeling:View"/>
</xsl:variable>

<xsl:variable name="all_primitive_types">
	<xsl:apply-templates select="//packagedElement[@xmi:type='uml:PrimitiveType']"/>
</xsl:variable>

<xsl:template match="//uml:Package">
	<xsl:element name="datamodel">
		<xsl:apply-templates select="packagedElement[@xmi:type='uml:Class']"/>
	</xsl:element>
</xsl:template>

<xsl:template match="packagedElement[@xmi:type='uml:Class']">
	<xsl:variable name="id" select="@xmi:id" />
	<xsl:variable name="isTable" select="count(//DataModeling:Table[@base_Class=$id])"/>
	<xsl:variable name="isView" select="count(//DataModeling:View[@base_Class=$id])"/>
	<xsl:if test="$isTable > 0">
		<xsl:element name="table">
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:call-template name="newline"/>
			<xsl:apply-templates select="@*|node()"/>
			<xsl:call-template name="getMembersFromRelationships">
				<xsl:with-param name="x" select="@xmi:id"/>
			</xsl:call-template>
			<xsl:call-template name="getMembersFromRelationshipsReverse">
				<xsl:with-param name="x" select="@xmi:id"/>
			</xsl:call-template>			
		</xsl:element>
	</xsl:if>
	<xsl:if test="$isView > 0">
		<xsl:element name="view">
			<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
			<xsl:call-template name="newline"/>
			<xsl:apply-templates select="@*|node()"/>
			<xsl:call-template name="getMembersFromRelationships">
				<xsl:with-param name="x" select="@xmi:id"/>
			</xsl:call-template>
			<xsl:call-template name="getMembersFromRelationshipsReverse">
				<xsl:with-param name="x" select="@xmi:id"/>
			</xsl:call-template>
		</xsl:element>
	</xsl:if>
</xsl:template>

<xsl:template match="ownedAttribute">
	<xsl:element name="attribute">
		<xsl:attribute name="name">
			<xsl:value-of select="concat(translate(substring(../@name,1,1),$uppercase,$smallcase),substring(../@name,2,string-length(../@name)))"/><xsl:value-of select="concat(translate(substring(@name,1,1),$smallcase,$uppercase),substring(@name,2,string-length(@name)))"/>
		</xsl:attribute>
		<xsl:if test="@type">
			<xsl:variable name="type" select="@type"/>
			<xsl:attribute name="type">
				<xsl:value-of select="//packagedElement[@xmi:id=$type]/@name"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:apply-templates select="@*|node()"/>
	</xsl:element>
</xsl:template>

<xsl:template match="ownedOperation">
	<xsl:element name="operation">
		<xsl:attribute name="name">
			<xsl:value-of select="concat(translate(substring(../@name,1,1),$uppercase,$smallcase),substring(../@name,2,string-length(../@name)))"/><xsl:value-of select="concat(translate(substring(@name,1,1),$smallcase,$uppercase),substring(@name,2,string-length(@name)))"/>
		</xsl:attribute>
		<xsl:attribute name="type">
			<xsl:apply-templates select="ownedParameter[@direction='return']"/>
		</xsl:attribute>
		<xsl:call-template name="newline"/>
		<xsl:if test="ownedParameter[@name !='']">
			<xsl:element name="parameters">
				<xsl:apply-templates select="ownedParameter[@name !='']"/>
			</xsl:element>
		</xsl:if>
	</xsl:element>
</xsl:template>

<xsl:template match="ownedParameter[@direction='return']">
	<xsl:if test="@type">
		<xsl:variable name="type" select="@type" />
		<xsl:value-of select="//packagedElement[@xmi:id=$type]/@name"/>
	</xsl:if>
	<xsl:if test="not(@type)">
		<xsl:if test="type">
			<xsl:value-of select="substring(type/@href,55,string-length(type/@href))"/>
		</xsl:if>
		<xsl:if test="not(type)">
			<xsl:text>void</xsl:text>
		</xsl:if>	
	</xsl:if>
</xsl:template>

<xsl:template match="ownedParameter[@name !='']">
	<xsl:element name="parameter">
		<xsl:attribute name="name"><xsl:value-of select="@name"/></xsl:attribute>
		<xsl:if test="@type">
			<xsl:variable name="type" select="@type"/>
			<xsl:attribute name="type">
				<xsl:value-of select="//packagedElement[@xmi:id=$type]/@name"/>
			</xsl:attribute>
		</xsl:if>
		<xsl:if test="not(@type)">
			<xsl:apply-templates select="@*|node()"/>
		</xsl:if>		
	</xsl:element>
</xsl:template>

<xsl:template match="type">
	<xsl:attribute name="type">
		<xsl:value-of select="substring(@href,55,string-length(@href))"/>
	</xsl:attribute>
</xsl:template>

<xsl:template match="//packagedElement[@xmi:type='uml:Association']">
	<xsl:element name="association">
		<xsl:apply-templates select="@*|node()"/>
		<xsl:attribute name="type"><xsl:value-of select="@name"/></xsl:attribute>
	</xsl:element>
</xsl:template>

<xsl:template match="ownedEnd[@name='src']">
	<xsl:attribute name="src">
		<xsl:call-template name="getClassName">
			<xsl:with-param name="x" select="@type"/>
		</xsl:call-template>
	</xsl:attribute>
</xsl:template>

<xsl:template match="ownedEnd[@name='dst']">
	<xsl:attribute name="dst">
		<xsl:call-template name="getClassName">
			<xsl:with-param name="x" select="@type"/>
		</xsl:call-template>
	</xsl:attribute>
</xsl:template>

<xsl:template name="getClassName">
	<xsl:param name="x"/>
	<xsl:value-of select='//uml:Package/packagedElement[@xmi:id=$x]/@name'/>
</xsl:template>

<xsl:template name="getMembersFromRelationships">
	<xsl:param name="x"/>
	<xsl:apply-templates select="//packagedElement[@xmi:type='uml:Association' and ownedEnd[@name='src']/@type=$x]"/>	
</xsl:template>

<xsl:template name="getMembersFromRelationshipsReverse">
	<xsl:param name="x"/>
	<xsl:apply-templates select="//packagedElement[@xmi:type='uml:Association' and ownedEnd[@name='dst']/@type=$x]"/>	
</xsl:template>

</xsl:stylesheet>