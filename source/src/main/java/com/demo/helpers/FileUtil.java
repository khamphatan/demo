package com.demo.helpers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.mozilla.universalchardet.UniversalDetector;
import org.w3c.dom.Document;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileUtil {
	private static Logger logger = LogManager.getLogger();
	public static String NL = System.getProperty("line.separator");
	public static final String FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String PATH_SEPARATOR = System.getProperty("path.separator");
	
	public static final String pathSeparator = "\\";
	public static final String extensionSeparator = ".";
	public static final String TAB = "\t";
	
	public static final String ENCODING_UTF8 = "UTF-8";
	public static final String ENCODING_TIS620 = "TIS-620";
	public static final String ENCODING_X_WINDOWS_874 = "x-windows-874";
	public static final String ENCODING_WINDOWS_1252 = "Windows-1252";
	public static final String ENCODING_ISO = "ISO-8859-1";
	public static final String ENCODING_US_ASCII = "US-ASCII";
	
	
	
	public static class OrderByModifyDateComparator implements Comparator<File> {
		private boolean isAscOrderBy = true;
		public OrderByModifyDateComparator(boolean isAscOrderBy){
			this.isAscOrderBy = isAscOrderBy;
		}
	    @Override
	    public int compare(File obj1, File obj2) {
	    	if(isAscOrderBy){
	    		return new Date(obj1.lastModified()).compareTo(new Date(obj2.lastModified()));
	    	}else{
	    		return new Date(obj2.lastModified()).compareTo(new Date(obj1.lastModified()));
	    	}
	    }
	}
	public static class OrderByFilenameComparator implements Comparator<File> {
		private boolean isAscOrderBy = true;
		public OrderByFilenameComparator(boolean isAscOrderBy){
			this.isAscOrderBy = isAscOrderBy;
		}
	    @Override
	    public int compare(File obj1, File obj2) {
	    	if(isAscOrderBy){
	    		return obj1.getName().compareTo(obj2.getName());
	    	}else{
	    		return obj2.getName().compareTo(obj1.getName());
	    	}
	    }
	}
	
	public static class OrderByModifyDateComparatorOfFileModel implements Comparator<FileModel> {
		private boolean isAscOrderBy = true;
		public OrderByModifyDateComparatorOfFileModel(boolean isAscOrderBy){
			this.isAscOrderBy = isAscOrderBy;
		}
	    @Override
	    public int compare(FileModel obj1, FileModel obj2) {
	    	try{
	    		if(isAscOrderBy){
		    		return new Date(new File(obj1.getFullpath()).lastModified()).compareTo(new Date(new File(obj2.getFullpath()).lastModified()));
		    	}else{
		    		return new Date(new File(obj2.getFullpath()).lastModified()).compareTo(new Date(new File(obj1.getFullpath()).lastModified()));
		    	}
	    	}catch (Exception e) {
	    		logger.error("Exception !!!", e);
				e.printStackTrace();
				return 0;
			}
	    }
	}
	public static class OrderByFilenameComparatorOfFileModel implements Comparator<FileModel> {
		private boolean isAscOrderBy = true;
		public OrderByFilenameComparatorOfFileModel(boolean isAscOrderBy){
			this.isAscOrderBy = isAscOrderBy;
		}
	    @Override
	    public int compare(FileModel obj1, FileModel obj2) {
	    	if(isAscOrderBy){
	    		return obj1.getName().compareTo(obj2.getName());
	    	}else{
	    		return obj2.getName().compareTo(obj1.getName());
	    	}
	    }
	}
	
	/**
	 * Create Directory
	 * @param strManyDirectories
	 */
	public static void CreateDirectory(String strManyDirectories){
		try{
			File file = new File(strManyDirectories);
			if (!file.exists() && file.mkdirs()){
				System.out.println("---> Directories : " + strManyDirectories + " created.!!!");
			}
		}catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
	}
	/**
	 * isExists
	 * @param filePathOrDirectories
	 * @return
	 */
	public static boolean IsExists(String filePathOrDirectories){
		try{
			return new File(filePathOrDirectories).exists();
		}catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
			return false;
		}
	}
	public static boolean IsFileExists(String filePath){
		try{
			File file = new File(filePath);
			return file.exists() && file.isFile();
		}catch (Exception e) {
			logger.error("Exception !!! :: filePath = "+filePath, e);
			e.printStackTrace();
			return false;
		}
	}
	public static boolean IsDirectoryExists(String filePathOrDirectories){
		try{
			File file = new File(filePathOrDirectories);
			return file.exists() && file.isDirectory();
		}catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
			return false;
		}
	}
	public static String GetEncoding(String filePath){
		String encoding = "";
		try{
			byte[] buf = new byte[4096];
			@SuppressWarnings("resource")
			FileInputStream fis = new java.io.FileInputStream(filePath);
	        UniversalDetector detector = new UniversalDetector(null);
	        int nread;
	        while ((nread = fis.read(buf)) > 0 && !detector.isDone()) detector.handleData(buf, 0, nread);
	        detector.dataEnd();
	        encoding = detector.getDetectedCharset();
	        detector.reset();
			System.out.println("\t- File:\\"+filePath+"\t- Encoding = "+encoding);
		}catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
		return encoding;
	}

	/**
	 * getDataFromTextFile
	 * @param filePath : *.txt
	 * @param startLine : filePath
	 * @param encoding
	 * @return String of List
	 */
	public static List<String> GetDataFromTextFile(String filePath, int startLine, String encoding) {
		//System.out.println(" getDataFromTextFile() - filePath = " + filePath);
		logger.info(" getDataFromTextFile() - filePath = " + filePath);
		File file = new File(filePath);
		if (!file.exists()) return null;

		List<String> list = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), encoding));
			String text = null;
			//---> repeat until all lines is read
			int line = 0;
			while ((text = reader.readLine()) != null) {
				line++;
				if (line >= startLine) list.add(text);
			}
		} catch (FileNotFoundException e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * getDataFromTextFile : encoding = UTF-8
	 * @param filePath : *.txt (startLine = 1)
	 * @return
	 */
	public static List<String> GetDataFromTextFile(String filePath) {
		return GetDataFromTextFile(filePath, 1, ENCODING_UTF8);
	}

	/**
	 * WriteDataToTextFile : encoding = UTF-8
	 * @param outputFilePath
	 * @param list
	 * @param append
	 * @return True is Success.
	 */
	public static boolean WriteDataToTextFile(String outputFilePath, List<String> list, boolean append) {
		return WriteDataToTextFile(outputFilePath, list, append, ENCODING_UTF8);
	}
	/**
	 * WriteDataToTextFile
	 * @param outputFilePath
	 * @param list
	 * @param append
	 * @param encoding
	 * @return True is Success.
	 */
	public static boolean WriteDataToTextFile(String outputFilePath, List<String> list, boolean append, String encoding) {
		//System.out.println(" writeDataToTextFile() - outputFilePath = " + outputFilePath);
		logger.info("---> writeDataToTextFile() - outputFilePath = " + outputFilePath);
		boolean isSuccess = false;
		BufferedWriter out = null;
		try {
			logger.info("---> writeDataToTextFile() - list = " + (list!=null?list.size():0));
			if (list != null && list.size() > 0) {
				isSuccess = true;
				File file = new File(outputFilePath);
				if (!file.exists()) file.createNewFile();
				out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), encoding));
				for (int i = 0; i < list.size(); i++) {
					out.write(list.get(i));
					if (i <= (list.size() - 2)) out.write(NL);
				}
			}
		} catch (FileNotFoundException e) {
			isSuccess = false;
			logger.error("Exception !!!", e);
			e.printStackTrace();
		} catch (IOException e) {
			isSuccess = false;
			logger.error("Exception !!!", e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
	/**
	 * writeDataToTextFile : encoding = UTF-8
	 * @param outputFilePath
	 * @param strMsg
	 * @param append
	 * @return True is Success.
	 */
	public static boolean WriteDataToTextFile(String outputFilePath, String strMsg, boolean append) {
		return WriteDataToTextFile(outputFilePath, strMsg, append, ENCODING_UTF8);
	}
	/**
	 * WriteDataToTextFile
	 * @param outputFilePath
	 * @param strMsg
	 * @param append
	 * @param encoding
	 * @return True is Success.
	 */
	public static boolean WriteDataToTextFile(String outputFilePath, String strMsg, boolean append, String encoding) {
		//System.out.println(" writeDataToTextFile() - outputFilePath = " + outputFilePath);
		logger.info(" writeDataToTextFile() - outputFilePath = " + outputFilePath);
		boolean isSuccess = false;
		BufferedWriter out = null;
		try {
			File file = new File(outputFilePath);
			if (!file.exists()) file.createNewFile();
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, append), encoding));
			out.write(strMsg);
		} catch (FileNotFoundException e) {
			isSuccess = false;
			logger.error("Exception !!!", e);
			e.printStackTrace();
		} catch (IOException e) {
			isSuccess = false;
			logger.error("Exception !!!", e);
			e.printStackTrace();
		} finally {
			try {
				if (out != null) out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
	/**
	 * This method writes a DOM document to a file
	 * @param doc
	 * @param filename
	 * @throws Exception
	 */
	public static void WriteXmlFile(Document doc, String filename) throws Exception {
		// Prepare the DOM document for writing
        Source source = new DOMSource(doc);
        // Prepare the output file
        File file = new File(filename);
        Result result = new StreamResult(file);
        // Write the DOM document to the file
        Transformer xformer = TransformerFactory.newInstance().newTransformer();
        xformer.transform(source, result);
	}
	/**
	 * COPY
	 * @param fromFileName
	 * @param toFileName
	 * @param isOverwrite
	 * @throws IOException
	 */
	public static void Copy(String fromFileName, String toFileName, boolean isOverwrite) throws IOException {
		File fromFile = new File(fromFileName);
		File toFile = new File(toFileName);

		if (!fromFile.exists())
			throw new IOException("FileCopy: " + "no such source file: " + fromFileName);
		if (!fromFile.isFile())
			throw new IOException("FileCopy: " + "can't copy directory: " + fromFileName);
		if (!fromFile.canRead())
			throw new IOException("FileCopy: " + "source file is unreadable: " + fromFileName);

		if (toFile.isDirectory()) toFile = new File(toFile, fromFile.getName());

		if (toFile.exists()) {
			if(!isOverwrite){
				return;
				//throw new IOException("FileCopy: destination file is existing file: " + toFileName);
			}
			if (!toFile.canWrite()) throw new IOException("FileCopy: destination file is unwriteable: " + toFileName);
			/*
			System.out.print("Overwrite existing file " + toFile.getName() + "? (Y/N): ");
			System.out.flush();
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String response = in.readLine();
			if (!response.equals("Y") && !response.equals("y")) throw new IOException("FileCopy: " + "existing file was not overwritten.");
			*/
		} else {
			String parent = toFile.getParent();
			if (parent == null) parent = System.getProperty("user.dir");
			File dir = new File(parent);
			if (!dir.exists()) throw new IOException("FileCopy: destination directory doesn't exist: " + parent);
			if (dir.isFile()) throw new IOException("FileCopy: destination is not a directory: " + parent);
			if (!dir.canWrite()) throw new IOException("FileCopy: destination directory is unwriteable: " + parent);
		}

		FileInputStream from = null;
		FileOutputStream to = null;
		try {
			from = new FileInputStream(fromFile);
			to = new FileOutputStream(toFile);
			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = from.read(buffer)) != -1) to.write(buffer, 0, bytesRead); // write
		} finally {
			if (from != null) try { from.close(); } catch (IOException e) { }
			if (to != null) try { to.close(); } catch (IOException e) { }
		}
	}
	/**
	 * CopyDirectory
	 * @param source
	 * @param target
	 * @throws IOException
	 */
	public static void CopyDirectory(File sourceLocation , File targetLocation) throws IOException {
	    if (sourceLocation.isDirectory()) {
	        if (!targetLocation.exists()) {
	            targetLocation.mkdir();
	        }

	        String[] children = sourceLocation.list();
	        for (int i=0; i<children.length; i++) {
	        	CopyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
	        }
	    } else {
	        InputStream in = new FileInputStream(sourceLocation);
	        OutputStream out = new FileOutputStream(targetLocation);

	        // Copy the bits from instream to outstream
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        in.close();
	        out.close();
	    }
	}
	/**
	 * Move File
	 * @param filePath
	 * @param directory
	 * @return
	 */
	public static boolean Move(String filePath, String directory){
		boolean isSuccess = false;
		try{
			//System.out.println("\t---> move !!! = "+filePath +", TO "+directory);
			
			// File (or directory) to be moved
			File file = new File(filePath);
			if(file.exists() && file.isFile()){
				// Destination directory
				CreateDirectory(directory);
				File dir = new File(directory);
				File f = new File(dir, file.getName());
				if(f.exists()) f.delete();
				// Move file to new directory
				isSuccess = file.renameTo(f);
			}
			//System.out.println("\t---> move !!! = "+isSuccess);
			logger.info("\t  Move file result=" + isSuccess + ",  Source="+filePath +", destination="+directory);
		}catch (Exception e){
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
		return isSuccess;
	}
	/**
	 * RenameTo
	 * @param filePathIn
	 * @param filePathOut
	 * @return
	 */
	public static boolean RenameTo(String filePathIn, String filePathOut){
		boolean isSuccess = false;
		try{
			File file = new File(filePathIn);
			if(file.exists() && file.isFile()){
				File fileOut = new File(filePathOut);
				isSuccess = file.renameTo(fileOut);
			}
		}catch (Exception e){
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
		return isSuccess;
	}
	/**
	 * Delete File
	 * @param filePath
	 * @return
	 */
	public static boolean Delete(String filePath){
		try{
			System.out.println("\t---> Delete File !!! = "+filePath);
			File file = new File(filePath);
			if(file.exists() && file.isFile()){
				return file.delete();
			}
			return true;
		}catch (Exception e){
			logger.error("Exception !!!", e);
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * DeleteDirectory
	 * @param rootDir
	 * @param rootDir
	 * @return True is Success.
	 */
	public static boolean DeleteDirectory(File rootDir, boolean isDeleteRoot){
		try{
			if(rootDir.isDirectory()){
				File[] files = rootDir.listFiles();
				if(files!=null&&files.length>0){
					for (File file : files) {
						if(file.isDirectory()){
							DeleteDirectory(file, true);
						}else{
							System.out.println(" \t - Delete File : "+file.getAbsolutePath());
							logger.info(" \t - Delete File : "+file.getAbsolutePath());
							file.delete();
						}
					}
				}
				if(isDeleteRoot){
					System.out.println(" \t - Delete Folder : "+rootDir.getAbsolutePath());
					logger.info(" \t - Delete Folder : "+rootDir.getAbsolutePath());
					rootDir.delete();
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * ListFiles
	 * @param rootDirectory
	 * @param preFixFilenames []
	 * @param extensions []
	 * @param isCaseSensitive
	 * @param isSelectAllDirectory
	 * @param isAscOrderBy : True is Order By ModifyDate ASC
	 * @return List of File
	 */
	public static List<File> ListFiles(String rootDirectory, String[] preFixFilenames, String[] extensions, boolean isCaseSensitive, boolean isSelectAllDirectory, boolean isAscOrderBy){
		List<File> list = new ArrayList<File>();
		for (String prefix : preFixFilenames) {
			List<File> listTmp = ListFiles(rootDirectory, prefix, extensions, isCaseSensitive, isSelectAllDirectory, isAscOrderBy);
			if(listTmp!=null && listTmp.size()>0){
				for (File tmp : listTmp) {
					boolean isHave = false;
					for (File f : list) {
						isHave = tmp.getAbsolutePath().equalsIgnoreCase(f.getAbsolutePath());
						if(isHave) break;
					}
					if(!isHave) list.add(tmp);
				}
			}
		}
		Collections.sort(list, new OrderByModifyDateComparator(isAscOrderBy));
		return list;
	}
	/**
	 * ListFiles
	 * @param rootDirectory
	 * @param preFixFilename
	 * @param extensions []
	 * @param isCaseSensitive
	 * @param isSelectAllDirectory
	 * @param isAscOrderBy : True is Order By ModifyDate ASC
	 * @return List of File
	 */
	public static List<File> ListFiles(String rootDirectory, String preFixFilename, String[] extensions, boolean isCaseSensitive, boolean isSelectAllDirectory, boolean isAscOrderBy){
		List<File> list = new ArrayList<File>();
		for (String ext : extensions) {
			List<File> listTmp = ListFiles(rootDirectory, preFixFilename, ext, isCaseSensitive, isSelectAllDirectory, isAscOrderBy);
			if(listTmp!=null && listTmp.size()>0){
				for (File tmp : listTmp) {
					boolean isHave = false;
					for (File f : list) {
						isHave = tmp.getAbsolutePath().equalsIgnoreCase(f.getAbsolutePath());
						if(isHave) break;
					}
					if(!isHave) list.add(tmp);
				}
			}
		}
		Collections.sort(list, new OrderByModifyDateComparator(isAscOrderBy));
		
		return list;
	}
	/**
	 * ListFiles
	 * @param rootDirectory
	 * @param preFixFilename
	 * @param extension
	 * @param isCaseSensitive
	 * @param isSelectAllDirectory
	 * @param isAscOrderBy : True is Order By ModifyDate ASC
	 * @return List of File
	 */
	public static List<File> ListFiles(String rootDirectory, String preFixFilename, String extension, boolean isCaseSensitive, boolean isSelectAllDirectory, boolean isAscOrderBy){
		List<File> list = new ArrayList<File>();
		try{
			FileListFilter filter = new FileListFilter(preFixFilename, extension, isCaseSensitive);
			File dir = new File(rootDirectory);
			if(dir!=null){
				if(dir.isDirectory()){
					File[] files = dir.listFiles();
					if(files!=null&&files.length>0){
						for (File f : files){
							if(isSelectAllDirectory){
								if(f.isDirectory()){
									AddListFiles(list, f.getAbsolutePath(), filter);
								}else if(f.isFile() && filter.acceptManual(f.getName())){
									list.add(f);
								}
							}else if(f.isFile() && filter.acceptManual(f.getName())){
								list.add(f);
							}
						}
					}
				}else if(dir.isFile() && filter.acceptManual(dir.getName())){
					list.add(dir);
				}
			}
		} catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
		Collections.sort(list, new OrderByModifyDateComparator(isAscOrderBy));
		return list;
	}
	private static void AddListFiles(List<File> list, String rootDirectory, FileListFilter filter){
		try{
			File dir = new File(rootDirectory);
			if(dir!=null){
				if(dir.isDirectory()){
					File[] files = dir.listFiles();
					if(files!=null&&files.length>0){
						for (File f : files){
							if(f.isDirectory()){
								AddListFiles(list, f.getAbsolutePath(), filter);
							}else if(f.isFile() && filter.acceptManual(f.getName())){
								list.add(f);
							}
						}
					}
				}else if(dir.isFile() && filter.acceptManual(dir.getName())){
					list.add(dir);
				}
			}
		} catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
	}
	
	public static FileModel ListFilesOfFileModel(String rootDirectory, String preFixFilename, String extension, boolean isCaseSensitive, boolean isSelectAllDirectory, boolean isAscOrderBy){
		FileModel model = null;
		try{
			FileListFilter filter = new FileListFilter(preFixFilename, extension, isCaseSensitive);
			File dir = new File(rootDirectory);
			if(dir!=null){
				//System.out.println(" - ListFilesOfFileModel :: Name :: ["+dir.getName()+"]");
				if(dir.isDirectory()){
					model = new FileModel(dir.getName(), dir.getAbsolutePath(), true);
					File[] files = dir.listFiles();
					if(files!=null&&files.length>0){
						for (File f : files){
							if(isSelectAllDirectory){
								if(f.isDirectory()){
									AddListFilesOfFileModel(model, f.getAbsolutePath(), filter, isAscOrderBy);
								}else if(f.isFile() && filter.acceptManual(f.getName())){
									model.getChilds().add(new FileModel(f.getName(), f.getAbsolutePath(), false));
								}
							}else if(f.isFile() && filter.acceptManual(f.getName())){
								model.getChilds().add(new FileModel(f.getName(), f.getAbsolutePath(), false));
							}
						}
						Collections.sort(model.getChilds(), new OrderByFilenameComparatorOfFileModel(isAscOrderBy));
					}
				}else if(dir.isFile() && filter.acceptManual(dir.getName())){
					model = new FileModel(dir.getName(), dir.getAbsolutePath(), false);
				}
			}
		} catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
		return model;
	}
	private static void AddListFilesOfFileModel(FileModel fileModel, String rootDirectory, FileListFilter filter, boolean isAscOrderBy){
		try{
			File dir = new File(rootDirectory);
			if(dir!=null){
				//System.out.println("\t - AddListFilesOfFileModel :: Name :: ["+dir.getName()+"]");
				if(dir.isDirectory()){
					FileModel model = new FileModel(dir.getName(), dir.getAbsolutePath(), true);
					File[] files = dir.listFiles();
					if(files!=null&&files.length>0){
						for (File f : files){
							if(f.isDirectory()){
								AddListFilesOfFileModel(model, f.getAbsolutePath(), filter, isAscOrderBy);
							}else if(f.isFile() && filter.acceptManual(f.getName())){
								model.getChilds().add(new FileModel(f.getName(), f.getAbsolutePath(), false));
							}
						}
						Collections.sort(model.getChilds(), new OrderByFilenameComparatorOfFileModel(isAscOrderBy));
					}
					fileModel.getChilds().add(model);
				}else if(dir.isFile() && filter.acceptManual(dir.getName())){
					fileModel.getChilds().add(new FileModel(dir.getName(), dir.getAbsolutePath(), false));
				}
			}
		} catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * ListDirectorysTopLevel
	 * @param rootDirectory
	 * @return List of Directorys
	 */
	public static List<File> ListDirectorysTopLevel(String rootDirectory){
		List<File> list = new ArrayList<File>();
		try{
			File dir = new File(rootDirectory);
			File[] files = dir.listFiles();
			if(files!=null&&files.length>0){
				for (File f : files) if(f.isDirectory()) list.add(f);
			}
		}catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
		return list;
	}
	
	public static boolean IsImage(String filePath){
		try{
			String mimetype= new MimetypesFileTypeMap().getContentType(filePath);
			logger.info(" *** isImage - File://"+filePath+" : mimetype = " + mimetype);
			return mimetype.toLowerCase().contains("image");
		}catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
			 return false;
		}
	}
	/**
	 * gets extension
	 * @param fullPath
	 * @return
	 */
	public static String Extension(String fullPath) {
		int dot = fullPath.lastIndexOf(extensionSeparator);
	    return fullPath.substring(dot + 1);
	}
	/**
	 * gets filename without extension
	 * @param fullPath
	 * @param pathSeparator
	 * @param extensionSeparator
	 * @return
	 */
	public static String FileNameOnly(String fullPath, String pathSeparator, String extensionSeparator) {
	    int dot = fullPath.lastIndexOf(extensionSeparator);
	    int sep = fullPath.lastIndexOf(pathSeparator);
	    return fullPath.substring(sep + 1, dot);
	}
	public static String FileNameOnly(String fullPath) {
	    int dot = fullPath.lastIndexOf(extensionSeparator);
	    int sep = fullPath.lastIndexOf(pathSeparator);
	    return fullPath.substring(sep + 1, dot);
	}
	/**
	 * gets filename with extension
	 * @param fullPath
	 * @return
	 */
	public static String FileName(String fullPath) {
		File file = new File(fullPath);
	    return file.getName();
	}
	/**
	 * gets file path
	 * @param fullPath
	 * @param pathSeparator
	 * @return
	 */
	public static String Path(String fullPath, String pathSeparator) {
	    int sep = fullPath.lastIndexOf(pathSeparator);
	    return fullPath.substring(0, sep);
	}
	/**
	 * pathFolder
	 * @param fullPath
	 * @param isFile
	 * @return Folder
	 */
	public static String PathFolder(String fullPath, boolean isFile) {
		File file = new File(fullPath);
	    return isFile ? file.getParent() : file.getAbsolutePath();
	}
	/**
	 * The process cannot access the file because it is being used by another process
	 * @param fileName
	 * @return
	 */
	public static boolean IsAnotherProcess(String fileName){
		/*FileLock lock = null;
		FileChannel channel = null;
		boolean iss = false;
		try{
		    channel = new RandomAccessFile(new File(fileName), "rw").getChannel();
		    lock = channel.tryLock();
		}catch (FileNotFoundException fx) {
			iss = false;
		}catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
			iss = true;
		}finally{
			try{if(lock!=null) {lock.release();}}catch (Exception e) {}
			try{if(channel!=null)channel.close();}catch (Exception e) {}
		}*/
		return IsFileLocked(fileName);
	}
	/**
	 * Is File Locked
	 * @param fileName
	 * @return True is locked.
	 */
	public static boolean IsFileLocked(String fileName){
		boolean isLocked = false;
		try{
			File file = new File(fileName);
			long fileTimestamp = file.lastModified();
			if(!file.setLastModified(System.currentTimeMillis())){
				isLocked = true;
			}else{
				file.setLastModified(fileTimestamp);
			}
		}catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
			isLocked = true;
		}
		return isLocked;
	}
	
	public static boolean SetLastModified(String fileName){
		return SetLastModified(fileName, new Date());
	}
	public static boolean SetLastModified(String fileName, Date date){
		boolean iss = false;
		try{
			File file = new File(fileName);
			file.setLastModified(date.getTime());
			iss = true;
		}catch (Exception e) {
			logger.error("Exception !!!", e);
			e.printStackTrace();
		}
		return iss;
	}
	
	/**
	 * changes extension to new extension
	 * example: x = changeExtension("data.txt", ".java") will assign "data.java" to x.
	 * @param originalName
	 * @param newExtension
	 * @return
	 */
	public static String ChangeExtension(String originalName, String newExtension) {
	    int lastDot = originalName.lastIndexOf(".");
	    if (lastDot != -1) {
	        return originalName.substring(0, lastDot) + newExtension;
	    } else {
	        return originalName + newExtension;
	    }
	}
	/**
	 * Generate File name
	 * @param folder
	 * @param prefix
	 * @param ext
	 * @param isReturnWithExt : True = File name with extension, False = File name without extension
	 * @return File name
	 */
	public static String GenerateFileName(String folder, String prefix, String ext, boolean isReturnWithExt){
		//---> Generate File name.
		String nameTmp = "";
		String name = "";
		String f = PathFolder(folder, false)+pathSeparator;
		for (int c = 0; c < 100; c++) {
			name = prefix + UUID.randomUUID();
			nameTmp = name + FileUtil.extensionSeparator + ext;
			if(!FileUtil.IsFileExists(f + nameTmp)) break;
		}
		return isReturnWithExt ? nameTmp : name;
	}
	
	public static byte[] LoadFile(String filepath){
		byte[] fileContent = null;
	    try{
	    	//create file object
	    	File file = new File(filepath);
	    	//create FileInputStream object
	    	@SuppressWarnings("resource")
			FileInputStream fin = new FileInputStream(file);
	    	/*
	       * Create byte array large enough to hold the content of the file.
	       * Use File.length to determine size of the file in bytes.
	       */
	    	fileContent = new byte[(int)file.length()];
	       /*
	        * To read content of the file in byte array, use
	        * int read(byte[] byteArray) method of java FileInputStream class.
	        */
	     	fin.read(fileContent);
	     	//create string from byte array
	     	//String strFileContent = new String(fileContent);
	       	//System.out.println("File content : ");
	       	//System.out.println(strFileContent);
	    }catch(FileNotFoundException e){
	    	System.out.println("File not found" + e);
	    }catch(IOException ioe){
	    	System.out.println("Exception while reading the file " + ioe);
	    }
	    return fileContent;
	}
	
	@SuppressWarnings("unused")
	private static void PrintCharSets(){
		Map<?, ?> charSets = Charset.availableCharsets();
	    Iterator<?> it = charSets.keySet().iterator();
	    while(it.hasNext()) {
	      String csName = (String)it.next();
	      System.out.print(csName);
	      Iterator<?> aliases = ((Charset)charSets.get(csName)).aliases().iterator();
	      if(aliases.hasNext())
	        System.out.print(": ");
	      while(aliases.hasNext()) {
	        System.out.print(aliases.next());
	        if(aliases.hasNext()) System.out.print(", ");
	      }
	      System.out.println();
	    }
	}
	/**
	 * GetCreateDateByCMD : "cmd /c dir "+filepath+" /tc"
	 * @param filePath
	 * @return CreateDate
	 */
	public static Date GetCreateDateByCMD(String filePath){
		Date date = null;
		try{
			//System.out.println("GetCreateDate : File: " + filePath);
    		Process proc = Runtime.getRuntime().exec("cmd /c dir \""+filePath+"\" /tc");
    		BufferedReader br =  new BufferedReader( new InputStreamReader(proc.getInputStream()));
 
    		String data ="";
    		//it's quite stupid but work
    		for(int i=0; i<6; i++) data = br.readLine();
    		//System.out.println("Extracted value : " + data);
 
    		//split by space
    		StringTokenizer st = new StringTokenizer(data);
    		String strDate = Utility.Trim(st.nextToken());//Get date
    		String time = Utility.Trim(st.nextToken()) + " " + Utility.Trim(st.nextToken());//Get time
    		//String size = st.nextToken();//Get size
    		//String fn = st.nextToken();//Get Filename
    		
    		if(!Utility.VerifyIsEmpty(strDate)){
    			if(strDate.contains("-")) strDate = strDate.replace("-", "/");
    			String[] arr = strDate.split("/");
    			if(arr!=null && arr.length>2){
    				try{
						date = Utility.ParseToDateTime(strDate+" "+time, "MM/dd/yyyy hh:mm a");
					}catch(Exception e){}
    			}
    		}
 
    		/*System.out.println("Creation Date  : " + date);
    		System.out.println("Creation Time  : " + time);
    		System.out.println("Size  : " + size + " bytes.");
    		System.out.println("Filename  : " + fn);*/
 
    	}catch(Exception e){
    		e.printStackTrace();
    	}finally{
    		try{
    			if(date==null) date = new Date(new File(filePath).lastModified());
			}catch(Exception e){}
    	}
		return date;
	}
	/**
	 * �� Rename File ������ҧ�ҹҹ���� : Current Date - iOverDay
	 * <p>��˹���� Rename file ������ҧ���ҡ���� ����ѹ</p>
	 * @param filePath
	 * @param iOverDay : 1,2,3,N,...
	 * @param isCreateDate : True is Create Date, False is LastModified
	 * @return True is Success.
	 */
	public static boolean RenameOverDay(String filePath, int iOverDay, boolean isCreateDate){
		boolean iss = false;
		String msg = " - RenameOverDay(iOverDay = "+iOverDay+", isCreateDate = "+isCreateDate+", File = "+filePath+")";
		try{
			Date date = isCreateDate ? GetCreateDateByCMD(filePath) : new Date(new File(filePath).lastModified());
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DATE, (iOverDay*(-1)));
			
			int iFileDate = Utility.INumber(Utility.StrDateTime("yyyyMMdd", date));
			int iCurrentDate = Utility.INumber(Utility.StrDateTime("yyyyMMdd", calendar.getTime()));
			msg += " :: iFileDate["+iFileDate+"], iCurrentDate["+iCurrentDate+"]";
			
			if(iFileDate <= iCurrentDate){
				String newExt = "OverDay";
				String ext = Utility.Trim(FileUtil.Extension(filePath));
				String fn = ext.contains(newExt) ? FileUtil.FileNameOnly(filePath) : FileUtil.FileName(filePath);
				String rootFolder =  FileUtil.PathFolder(filePath, true) + FileUtil.pathSeparator;
				String newFilePath = rootFolder + fn + FileUtil.extensionSeparator + Utility.Number("#0", iOverDay) + newExt;
				msg += " :: Rename To ["+newFilePath+"]";
				iss = RenameTo(filePath, newFilePath);
			}
		}catch(Exception e){
    		e.printStackTrace();
    	}
		msg = "["+(iss?"SUCCESS":"FAIL")+"]" + msg;
		System.out.println(msg); logger.info(msg);
		return iss;
	}
	
	public static void main(String []chai) throws Exception{
		/*String filePath = "D:\\TEMP\\FXTH\\INPUT\\INV\\SAPInvoice201402041114.txt";
		String filePath2 = "D:\\TEMP\\FXTH\\INPUT\\INV\\SAPInvoice201402041114-New.txt";
		//List<String> list = FileUtil.GetDataFromTextFile(filePath, 2);
		//FileUtil.WriteDataToTextFile(filePath2, list, false);
		
		String encoding = GetEncoding(filePath);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF8"));
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath2),"UTF8"));
		out.write(new String(IOUtils.toString(reader).getBytes("UTF8")));
		out.close();*/
		
		/*String filePath = "D:\\TEMP\\FXTH\\INPUT\\INV\\SAPInvoice201402041114.txt";
		String rootFolderIndex = FileUtil.PathFolder(filePath, true);
		System.out.println("---> FileUtil.PathFolder :: "+filePath+"\t- "+rootFolderIndex);*/
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.US);
		{
			System.out.println("- OrderByModifyDateComparator is ASC !!!");
			List<File> files = FileUtil.ListFiles("D:\\TEMP\\FXTH\\INPUT\\File2DS\\invoice", "", "pdf", false, true);
			if(files!=null && files.size()>0){
				for(File f : files){
					System.out.println((sdf.format(new Date(f.lastModified())))+ " -> "+FileUtil.FileName(f.getAbsolutePath()));
				}
			}
		}
		{
			System.out.println("- OrderByModifyDateComparator is DESC !!!");
			List<File> files = FileUtil.ListFiles("D:\\TEMP\\FXTH\\INPUT\\File2DS\\invoice", "", "pdf", false, false);
			if(files!=null && files.size()>0){
				for(File f : files){
					System.out.println((sdf.format(new Date(f.lastModified())))+ " -> "+FileUtil.FileName(f.getAbsolutePath()));
				}
			}
		}*/
		
		/*String fileName = "D:\\TEMP\\Test_delete_files\\25570317\\temp-import-docushare - Copy.pdf";
		System.out.println("---> FileUtil.IsAnotherProcess :: "+FileUtil.IsAnotherProcess(fileName));
		System.out.println("---> FileUtil.IsFileLocked :: "+FileUtil.IsFileLocked(fileName));*/
		
		//XXX Test Encoding File
		/*{
			String filePath = "D:\\MY-PROJECT\\JAVA\\Linde\\Document\\FileByCustomer\\GOLDD_20140526.txt";
			//String filePathNew = "D:\\MY-PROJECT\\JAVA\\Linde\\Document\\FileByCustomer\\GOLDD_20140526_XXXX.txt";
			//System.out.println("---> Test Convert File :: "+filePath);
			
			
			List<String> list = FileUtil.GetDataFromTextFile(filePath, 1, FileUtil.ENCODING_UTF8);
			//List<String> list = FileUtil.GetDataFromTextFile(filePath, 1, FileUtil.ENCODING_X_WINDOWS_874);
			if(list!=null && list.size()>0){
				for(String line : list) System.out.println(line);
			}
			//FileUtil.WriteDataToTextFile(filePathNew, list, false);
			
			
		    
			File file = new File(filePath);
			File newPath = new File(filePath+".TXT");
			int fileSize = (int)file.length();
			byte[] bytes = new byte[fileSize];
			InputStream in = new FileInputStream(file);
			int nread = in.read(bytes);
			in.close();
			assert nread == fileSize;
			String content = "\uFEFF" + new String(bytes, "Windows-1252");
			bytes = content.getBytes("UTF-8");
			OutputStream out = new FileOutputStream(newPath);
			out.write(bytes);
			out.close();
		}*/
		
		//PrintCharSets();
		
		String filePath = "D:\\TEMP\\XXXX1.pdf";
		System.out.println("GetCreateDateByCMD : "+Utility.StrDateTime("yyyy-MM-dd HH:mm", GetCreateDateByCMD(filePath)));
		System.out.println("RenameOverDay : "+RenameOverDay(filePath, 2, true));
		
		
		System.out.println("FINISH !!!");
	}
	
}
