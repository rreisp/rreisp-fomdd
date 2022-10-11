# feature and layout mapped state model
FLMSM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/state/WebStoresMappedStateModel.xml"

# abstract mvc view
MVCV="/home/rreisp/workspace/FOMDD/models/domain/WebStores/design/mvc/WebStoresMVCView.xml"

# Transforms state model into project views
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/analysis2project/State2View.xsl $FLMSM > $MVCV~;
xml_pp $MVCV~ > $MVCV;
rm $MVCV~;
