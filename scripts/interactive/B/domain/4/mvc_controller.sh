# feature and layout mapped state model
FLMSM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/state/WebStoresMappedStateModel.xml"

# abstract mvc controller
MVCC="/home/rreisp/workspace/FOMDD/models/domain/WebStores/design/mvc/WebStoresMVCController.xml"

# Transforms state model into project controllers
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/analysis2project/State2Control.xsl $FLMSM > $MVCC~;
xml_pp $MVCC~ > $MVCC;
rm $MVCC~;

