# generates a mapping model for layout and state model elements

# $1 (project location)
# $2 (simple state model)
# $3 (layout x state mapping model)

# generate a mapping template for layout and state model elements
xmlstarlet tr --omit-decl $1/transformations/mapping/GenerateLayoutStateModelMappingTemplate1.xsl $2 > $3~;
xml_pp $3~ > $3;
xmlstarlet tr --omit-decl $1/transformations/mapping/GenerateLayoutStateModelMappingTemplate2.xsl $3 > $3~;
xml_pp $3~ > $3;
rm $3~;