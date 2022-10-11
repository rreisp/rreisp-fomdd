# feature mapped data model
FMDM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/data/WebStoresMappedDataModel.xml"

# abstract mvc model
MVCM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/design/mvc/WebStoresMVCModel.xml"

# Transforms data model into project models
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/analysis2project/Data2Model.xsl $FMDM > $MVCM~;
xml_pp $MVCM~ > $MVCM;
rm $MVCM~;
