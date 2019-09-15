package com.demo.helpers;

import java.io.File;
import java.io.FilenameFilter;

public class FileListFilter implements FilenameFilter {
	private String prefix;
	private String extension;
	private boolean isCaseSensitive;
	public FileListFilter(String prefix, String extension, boolean isCaseSensitive) {
		this.prefix = Utility.Trim(prefix);
		this.extension = Utility.Trim(extension);
		this.isCaseSensitive = isCaseSensitive;
	}
	public boolean accept(File directory, String filename) {
		boolean fileOK = true;
		if (prefix != null && prefix != "") {
			if(isCaseSensitive){
				fileOK &= filename.startsWith(prefix);
			}else{
				fileOK &= filename.toLowerCase().startsWith(prefix.toLowerCase());
			}
		}
		if (extension != null && extension != "") {
			fileOK &= filename.toLowerCase().endsWith('.' + extension.toLowerCase());
		}
		return fileOK;
	}
	public boolean acceptManual(String filename) {
		boolean fileOK = true;
		if (prefix != null) {
			if(isCaseSensitive){
				fileOK &= filename.startsWith(prefix);
			}else{
				fileOK &= filename.toLowerCase().startsWith(prefix.toLowerCase());
			}
		}
		if (extension != null && extension != "") {
			fileOK &= filename.toLowerCase().endsWith('.' + extension.toLowerCase());
		}
		return fileOK;
	}
}
