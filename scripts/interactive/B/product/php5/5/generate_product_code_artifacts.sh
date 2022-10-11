# generate product artifacts

# project model
PM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/design/mvc/WebStoresMVCModel.xml"

# project view
PV="/home/rreisp/workspace/FOMDD/models/domain/WebStores/design/mvc/WebStoresMVCView.xml"

# project controller
PC="/home/rreisp/workspace/FOMDD/models/domain/WebStores/design/mvc/WebStoresMVCController.xml"

# target path
TARGET="/home/rreisp/workspace/FOMDD/target/B/PHP5/product/JavaPetStore/"

# apllies cartridges
java -cp ./bin PHPCartridgeB $PM $PV $PC $TARGET
