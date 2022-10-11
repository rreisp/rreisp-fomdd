# feature mapped state model
FMSM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/state/WebStoresMappedStateModel.xml"

# maps features and state model elements
xmlstarlet tr --omit-decl /home/rreisp/workspace/FOMDD/transformations/mapping/MapLayoutAndStateModel.xsl $FMSM > $FMSM~;
xml_pp $FMSM~ > $FMSM;
rm $FMSM~;
