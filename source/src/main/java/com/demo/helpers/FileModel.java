package com.demo.helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FileModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String fullpath;
	private boolean directory;
	private List<FileModel> childs = new ArrayList<FileModel>();

	public FileModel() {
		super();
	}

	public FileModel(String name, String fullpath, boolean directory) {
		super();
		this.name = name;
		this.fullpath = fullpath;
		this.directory = directory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFullpath() {
		return fullpath;
	}

	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}

	public boolean isDirectory() {
		return directory;
	}

	public void setDirectory(boolean directory) {
		this.directory = directory;
	}

	public List<FileModel> getChilds() {
		return childs;
	}

	public void setChilds(List<FileModel> childs) {
		this.childs = childs;
	}
}
