# generates MVC View entities
# $1 (project location)
# $2 (feature and layout mapped state model)
# $3 (abstract mvc view)

xmlstarlet tr --omit-decl $1/transformations/analysis2project/State2View.xsl $2 > $3~;
xml_pp $3~ > $3;
rm $3~;
