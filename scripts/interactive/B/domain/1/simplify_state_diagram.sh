# emf state model
ESM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/state/WebStoresStateModel.uml"
# simple state model
SSM="/home/rreisp/workspace/FOMDD/models/domain/WebStores/analysis/state/WebStoresSimpleStateModel.xml"

# simplifies state model notation
java  -cp ./bin core/transformation/SimplifyStateModel $ESM $SSM~;
xml_pp $SSM~ > $SSM;
rm $SSM~;