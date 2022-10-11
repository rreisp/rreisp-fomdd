# maps layout and state model elements
# $1 (project location)
# $2 (feature x state mapped model)

xmlstarlet tr --omit-decl $1/transformations/mapping/MapLayoutAndStateModel.xsl $2 > $2~;
xml_pp $2~ > $2;
rm $2~;
