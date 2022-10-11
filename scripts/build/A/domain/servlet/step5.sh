# generate SPL code artifacts

echo "Generating SPL code artifacts..."
echo

# receives $1 parameter from eclipse (project location)

# project model
PM=$1"/models/domain/WebStores/design/mvc/WebStoresMVCModel.xml"

# project view
PV=$1"/models/domain/WebStores/design/mvc/WebStoresMVCView.xml"

# project controller
PC=$1"/models/domain/WebStores/design/mvc/WebStoresMVCController.xml"

JSP_PATH="/home/gabriel/workspaceMestrado/FOMDD/target/A/ServletJPA/domain/WebStores/Base/src/main/webapp"

# target path
TARGET=$1"/target/A/JSF/domain/WebStores/"

# apllies cartridges
java -cp ./bin JSFCartridgeA $PM $PV $PC $TARGET

echo
echo "Indenting JSPs.."
for f in $JSP_PATH/*.jsp; do
	xml_pp "$f" > "$f"2
	echo "$f"
	mv "$f"2 "$f"
done