# simple state model
SSM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/state/WebStoresSimpleStateModel.xml"

# mapping model for features and state model elements
FSMM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/mapping/WebStoresFeatureStateMappingModel.xml"

# generate a mapping template for feature and state model elements
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/mapping/GenerateFeatureStateModelMappingTemplate1.xsl $SSM > $FSMM~;
xml_pp $FSMM~ > $FSMM;
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/mapping/GenerateFeatureStateModelMappingTemplate2.xsl $FSMM > $FSMM~;
xml_pp $FSMM~ > $FSMM;
rm $FSMM~;