# generates a mapping model for features and state model elements
# $1 (project location)
# $2 (simple state model)
# $3 (feature x state mapping model)

xmlstarlet tr --omit-decl $1/transformations/mapping/GenerateFeatureStateModelMappingTemplate1.xsl $2 > $3~;
xml_pp $3~ > $3;
xmlstarlet tr --omit-decl $1/transformations/mapping/GenerateFeatureStateModelMappingTemplate2.xsl $3 > $3~;
xml_pp $3~ > $3;
rm $3~;