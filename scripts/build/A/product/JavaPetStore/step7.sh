# reverts files language to original
FOLDER="/home/rreisp/workspace/FOMDD/target/domain/WebStores/Base/web"

java  -cp ./bin core/transformation/RevertJavaScript $FOLDER;
java  -cp ./bin core/transformation/RevertJSP $FOLDER;
