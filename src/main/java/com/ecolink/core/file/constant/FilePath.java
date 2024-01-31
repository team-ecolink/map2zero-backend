package com.ecolink.core.file.constant;

public enum FilePath {
	PROFILE_PHOTO("/avatar");

	public static final String PREFIX = "public";
	private final String path;

	FilePath(String path) {
		this.path = path;
	}

	public String getPath(Object id) {
		return PREFIX + path + "/" + id.toString();
	}

}
