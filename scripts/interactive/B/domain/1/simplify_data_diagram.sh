# emf data model
EDM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/data/WebStoresDataModel.uml"

# simple data model
SDM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/data/WebStoresSimpleDataModel.xml"

# simplifies data model notation
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/simplify/SimplifyDataModel.xsl $EDM > $SDM~;
xml_pp $SDM~ > $SDM;
rm $SDM~;