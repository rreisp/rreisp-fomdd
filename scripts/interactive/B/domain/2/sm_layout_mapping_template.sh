# simple state model
SSM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/state/WebStoresSimpleStateModel.xml"

# mapping model for layout and state model elements
LSMM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/mapping/WebStoresLayoutStateMappingModel.xml"

# generate a mapping template for layout and state model elements
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/mapping/GenerateLayoutStateModelMappingTemplate1.xsl $SSM > $LSMM~;
xml_pp $LSMM~ > $LSMM;
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/mapping/GenerateLayoutStateModelMappingTemplate2.xsl $LSMM > $LSMM~;
xml_pp $LSMM~ > $LSMM;
rm $LSMM~;