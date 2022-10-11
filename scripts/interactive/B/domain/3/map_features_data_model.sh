# simple data model
SDM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/data/WebStoresSimpleDataModel.xml"

# feature mapped data model
FMDM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/data/WebStoresMappedDataModel.xml"

# maps features and data model elements
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/mapping/MapFeaturesAndDataModel.xsl $SDM > $FMDM~;
xml_pp $FMDM~ > $FMDM;
rm $FMDM~;