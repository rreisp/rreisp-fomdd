# applies mapping models
echo "Applying mapping models..."
echo

# receives $1 parameter from eclipse (project location)

# --- FEATURE x DATA MAPPING --------------------------------------------------------
echo "Feature x Data Mapping"
# simple data model
SDM=$1"/models/domain/WebStores/analysis/data/WebStoresSimpleDataModel.xml"
# feature mapped data model
FMDM=$1"/models/domain/WebStores/analysis/data/WebStoresMappedDataModel.xml"

$1/scripts/interactive/A/mvc/domain/3/map_features_data_model.sh $1 $SDM $FMDM;
echo $FMDM;
echo

# --- FEATURE x STATE MAPPING --------------------------------------------------------
echo "Feature x State Mapping"
# simple state model
SSM=$1"/models/domain/WebStores/analysis/state/WebStoresSimpleStateModel.xml"
# feature mapped state model
FMSM=$1"/models/domain/WebStores/analysis/state/WebStoresMappedStateModel.xml"

$1/scripts/interactive/A/mvc/domain/3/map_features_state_model.sh $1 $SSM $FMSM;
echo $FMSM;
echo

# --- FEATURE x STATE MAPPING --------------------------------------------------------
echo "Layout x State Mapping"

$1/scripts/interactive/A/mvc/domain/3/map_layout_state_model.sh $1 $FMSM;
echo $FMSM;
echo

echo "Done!"