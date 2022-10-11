# maps features and state model elements
# $1 (project location)
# $2 (simple state model)
# $3 (feature x state mapping model)

xmlstarlet tr --omit-decl $1/transformations/mapping/MapFeaturesAndStateModel.xsl $2 > $3~;
xml_pp $3~ > $3;
rm $3~;
