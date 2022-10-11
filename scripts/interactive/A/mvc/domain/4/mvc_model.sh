# generates MVC Model entities
# $1 (project location)
# $2 (feature mapped data model)
# $3 (abstract mvc model)

# Transforms data model into project models
xmlstarlet tr --omit-decl $1/transformations/analysis2project/Data2Model.xsl $2 > $3~;
xml_pp $3~ > $3;
rm $3~;
