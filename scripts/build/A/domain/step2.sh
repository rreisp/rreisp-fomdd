# generates mapping templates for datamodel and statemodel
echo "Generating mapping models..."
echo

# receives $1 parameter from eclipse (project location)

# --- FEATURE x DATA MAPPING --------------------------------------------------------
echo "Feature x Data Mapping";
# simple data model
SDM=$1"/models/domain/WebStores/analysis/data/WebStoresSimpleDataModel.xml"
# feature x data map
FDMM=$1"/models/domain/WebStores/analysis/mapping/WebStoresFeatureDataMappingModel.xml"

$1/scripts/interactive/A/mvc/domain/2/dm_feature_mapping_template.sh $1 $SDM $FDMM;
echo $FDMM;
echo

# --- FEATURE x STATE MAPPING --------------------------------------------------------
echo "Feature x State Mapping";
# simple state model
SSM=$1"/models/domain/WebStores/analysis/state/WebStoresSimpleStateModel.xml"
# feature x state map
FSMM=$1"/models/domain/WebStores/analysis/mapping/WebStoresFeatureStateMappingModel.xml"

$1/scripts/interactive/A/mvc/domain/2/sm_feature_mapping_template.sh $1 $SSM $FSMM;
echo $FSMM
echo

# --- LAYOUT x STATE MAPPING --------------------------------------------------------
echo "Layout x State Mapping";
# layout x state model
LSMM=$1"/models/domain/WebStores/analysis/mapping/WebStoresLayoutStateMappingModel.xml"

$1/scripts/interactive/A/mvc/domain/2/sm_layout_mapping_template.sh $1 $SSM $LSMM
echo $LSMM
echo

echo "Done!"