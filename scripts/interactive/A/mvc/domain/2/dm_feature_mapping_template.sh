# generates a mapping template for feature and data model elements
# $1 (project location)
# $2 (simple data model)
# $3 (feature x data mapping model)

xmlstarlet tr --omit-decl $1/transformations/mapping/GenerateFeatureDataModelMappingTemplate.xsl $2 > $3~;
xml_pp $3~ > $3;
rm $3~;