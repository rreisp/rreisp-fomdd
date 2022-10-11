# simplifies data model notation
# $1 (project location)
# $2 (emf data model)
# $3 (simple data model)

xmlstarlet tr --omit-decl $1/transformations/simplify/SimplifyDataModel.xsl $2 > $3~;
xml_pp $3~ > $3;
rm $3~;