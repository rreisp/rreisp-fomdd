# generates abstract architectural entities

echo "Generating MVC entities..."
echo

# receives $1 parameter from eclipse (project location)

# --- MVC Model --------------------------------------------------------
echo "Models"
# feature mapped data model
FMDM=$1"/models/domain/WebStores/analysis/data/WebStoresMappedDataModel.xml"
# abstract mvc model
MVCM=$1"/models/domain/WebStores/design/mvc/WebStoresMVCModel.xml"

$1/scripts/interactive/A/mvc/domain/4/mvc_model.sh $1 $FMDM $MVCM;
echo $MVCM;
echo

# --- MVC View --------------------------------------------------------
echo "Views"
# feature and layout mapped state model
FLMSM=$1"/models/domain/WebStores/analysis/state/WebStoresMappedStateModel.xml"

# abstract mvc view
MVCV=$1"/models/domain/WebStores/design/mvc/WebStoresMVCView.xml"

$1/scripts/interactive/A/mvc/domain/4/mvc_view.sh $1 $FLMSM $MVCV;
echo $MVCV;
echo

# --- MVC Controller --------------------------------------------------------
echo "Controllers"
# feature and layout mapped state model
FLMSM=$1"/models/domain/WebStores/analysis/state/WebStoresMappedStateModel.xml"

# abstract mvc controller
MVCC=$1"/models/domain/WebStores/design/mvc/WebStoresMVCController.xml"

$1/scripts/interactive/A/mvc/domain/4/mvc_controller.sh $1 $FLMSM $MVCC;
echo $MVCC;
echo

echo "Done!"