# simplifies state model notation
# $1 (project location)
# $2 (emf state model)
# $3 (simple state model)

java  -cp ./bin core/transformation/SimplifyStateModel $2 $3~;
xml_pp $3~ > $3;
rm $3~;