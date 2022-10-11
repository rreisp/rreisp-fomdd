import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import javax.swing.*;

public class LineCounter {

	public static void main(String[] args) {
		int lines = 0;

		JFileChooser fc = new JFileChooser();
		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		String ext = JOptionPane.showInputDialog(null);
		fc.showOpenDialog(null);
		File dir = fc.getSelectedFile();

		class SimpleFileFilter implements FileFilter
		{
			private String ext;

			public SimpleFileFilter(String ext){
				this.ext = ext;
			}

			public boolean accept(File file)
			{
				if (file.getName().toLowerCase().endsWith(ext)) return true;
				else return false;
			}
		}

		SimpleFileFilter sff = new SimpleFileFilter(ext);
		File[] files = dir.listFiles();

		for(int i=0;i<files.length;i++){
			if(sff.accept(files[i])){
				try {
					FileInputStream fis = new FileInputStream(files[i]);
					BufferedInputStream bis =  new BufferedInputStream(fis);
					DataInputStream dis = new DataInputStream(bis);
					while (dis.available() != 0) {
						dis.readLine();
						lines ++;
					}
					fis.close();
					bis.close();
					dis.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
			}
		}

		System.out.println("Linhas para "+ ext + " "+ lines);
		System.exit(0);
	}
}