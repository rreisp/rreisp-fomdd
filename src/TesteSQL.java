import java.io.File;

import core.transformation.relational.*;

public class TesteSQL {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Data2MySQL bd = new Data2MySQL();
		System.out.println(bd.translate(new File("/home/gabriel/workspaceMestrado/FOMDD/models/domain/WebStores/analysis/data/WebStoresSimpleDataModel.xml")));
	}

}
