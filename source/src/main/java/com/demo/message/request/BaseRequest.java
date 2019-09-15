package com.demo.message.request;

public class BaseRequest {
	private String deviceAppId;
	private String deviceModel;
	private String devicePlatform;
	private String deviceVersion;

	public String getDeviceAppId() {
		return deviceAppId;
	}

	public void setDeviceAppId(String deviceAppId) {
		this.deviceAppId = deviceAppId;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public String getDevicePlatform() {
		return devicePlatform;
	}

	public void setDevicePlatform(String devicePlatform) {
		this.devicePlatform = devicePlatform;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public void setDeviceVersion(String deviceVersion) {
		this.deviceVersion = deviceVersion;
	}
}