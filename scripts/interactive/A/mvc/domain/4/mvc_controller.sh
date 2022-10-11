# generates MVC Controller entities
# $1 (project location)
# $2 (feature and layout mapped state model)
# $3 (abstract mvc controller)

xmlstarlet tr --omit-decl $1/transformations/analysis2project/State2Control.xsl $2 > $3~;
xml_pp $3~ > $3;
rm $3~;

