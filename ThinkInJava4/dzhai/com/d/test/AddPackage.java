package com.d.test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class AddPackage {
  private static final String INSERT_CONTENT="package typeinfo;";
  private static final String PACKAGE_NAME="/src/typeinfo";
	public static void main(String[] args) throws Exception {

		String relativelyPath = System.getProperty("user.dir");
		File root = new File(relativelyPath + PACKAGE_NAME);
		File[] lists = root.listFiles();
		for (File f : lists) {
			if (f.isFile() && f.getName().endsWith(".java")) {
               insert(f.getPath(), 0, INSERT_CONTENT);
			}
		}

	}

	static void insert(String filename, int pos, String insertContent) throws Exception {// pos是插入的位置
		File tmp = File.createTempFile("tmp", null);
		tmp.deleteOnExit();
		try {
			RandomAccessFile raf = new RandomAccessFile(filename, "rw");
			FileOutputStream tmpOut = new FileOutputStream(tmp);
			FileInputStream tmpIn = new FileInputStream(tmp);
			raf.seek(pos);
			String fristLine=raf.readLine();
			if(fristLine.equals(INSERT_CONTENT)){
				return;
			}
			raf.seek(pos);// 首先的话是0
			byte[] buf = new byte[64];
			int hasRead = 0;
			while ((hasRead = raf.read(buf)) > 0) {
				// 把原有内容读入临时文件
				tmpOut.write(buf, 0, hasRead);

			}
			raf.seek(pos);
			raf.write((insertContent+"\n").getBytes());
			// 追加临时文件的内容
			while ((hasRead = tmpIn.read(buf)) > 0) {
				raf.write(buf, 0, hasRead);
			}
		} catch (Exception e) {

		}
	}
}