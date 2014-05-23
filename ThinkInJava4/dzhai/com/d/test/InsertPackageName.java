package com.d.test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

public class InsertPackageName {
	private static final String INSERT_CONTENT = "package arrays;";
	private static final String PACKAGE_NAME = "/src";

	public static void main(String[] args) throws Exception {
		String relativelyPath = System.getProperty("user.dir");

	}

	private static void insertPackageName(String filename, int pos, String insertContent) throws Exception {
		File tmp = File.createTempFile("tmp", null);
		tmp.deleteOnExit();
		try {
			RandomAccessFile raf = new RandomAccessFile(filename, "rw");
			FileOutputStream tmpOut = new FileOutputStream(tmp);
			FileInputStream tmpIn = new FileInputStream(tmp);
			raf.seek(pos);// 首先的话是0
			byte[] buf = new byte[64];
			int hasRead = 0;
			while ((hasRead = raf.read(buf)) > 0) {
				// 把原有内容读入临时文件
				tmpOut.write(buf, 0, hasRead);
			}
			raf.seek(pos);
			raf.write((insertContent + "\n").getBytes());
			// 追加临时文件的内容
			while ((hasRead = tmpIn.read(buf)) > 0) {
				raf.write(buf, 0, hasRead);
			}
		} catch (Exception e) {

		}
	}

	private void valPackage(String fileName,String packagename) {
		File root = new File(fileName);
		File[] lists = root.listFiles();
		for (File f : lists) {
			if (f.isDirectory()) {
				
			}else{
				
			}
		}
	}

	private static boolean hasPackageName(File file, String content) throws IOException {
		if (file.isFile()) {
			try {
				FileReader fr = new FileReader(file.getAbsolutePath());
				BufferedReader br = new BufferedReader(fr);
				String line = "";
				line = br.readLine();
				int i=0;
				while (null != line) {
					line = br.readLine();
                    if(line.contains(content)){
                    	return true;
                    }
                    if(i>50){
                    	return false;
                    }
                    i++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
