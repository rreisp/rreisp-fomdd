# simple data model
SDM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/data/WebStoresSimpleDataModel.xml"

# mapping model for features and data model elements
FDMM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/mapping/WebStoresFeatureDataMappingModel.xml"

# generate a mapping template for feature and data model elements 
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/mapping/GenerateFeatureDataModelMappingTemplate.xsl $SDM > $FDMM~;
xml_pp $FDMM~ > $FDMM;
rm $FDMM~;