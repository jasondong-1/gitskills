package com.idea.tag.model;

/**噪声实体类*/
public class MNoise {
	private boolean isNoise;//是否爲噪聲數據
	private String reason;//產生噪聲原因
	
	public boolean isNoise() {
		return isNoise;
	}
	public void setNoise(boolean isNoise) {
		this.isNoise = isNoise;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "Noise [isNoise=" + isNoise + ", reason=" + reason + "]";
	}
}
