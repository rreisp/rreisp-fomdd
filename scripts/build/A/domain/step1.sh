# simplifies input diagram notation
echo "Simplifying input models..."
echo

# receives $1 parameter from eclipse (project location)

# --- DATA MODEL --------------------------------------------------------
echo "Data Model";
# emf data model
EDM=$1"/models/domain/WebStores/analysis/data/WebStoresDataModel.uml"
# simple data model
SDM=$1"/models/domain/WebStores/analysis/data/WebStoresSimpleDataModel.xml"

$1/scripts/interactive/A/mvc/domain/1/simplify_data_diagram.sh $1 $EDM $SDM;
echo $SDM;
echo

# --- STATE MODEL --------------------------------------------------------
echo "State Model";

# emf state model
ESM=$1"/models/domain/WebStores/analysis/state/WebStoresStateModel.uml"

# simple state model
SSM=$1"/models/domain/WebStores/analysis/state/WebStoresSimpleStateModel.xml"

$1/scripts/interactive/A/mvc/domain/1/simplify_state_diagram.sh $1 $ESM $SSM;
echo $SSM;
echo

echo "Done!"
