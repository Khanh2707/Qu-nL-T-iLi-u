package Model;

import java.io.Serializable;

public class TaiLieu implements Serializable, Comparable<TaiLieu> {
	private String maTaiLieu, tenNXB;
	private int soBanPhatHanh;
	
	public TaiLieu() {
	
	}

	public TaiLieu(String maTaiLieu, String tenNXB, int soBanPhatHanh) {
		this.maTaiLieu = maTaiLieu;
		this.tenNXB = tenNXB;
		this.soBanPhatHanh = soBanPhatHanh;
	}

	public String getMaTaiLieu() {
		return maTaiLieu;
	}

	public void setMaTaiLieu(String maTaiLieu) {
		this.maTaiLieu = maTaiLieu;
	}

	public String getTenNXB() {
		return tenNXB;
	}

	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}

	public int getSoBanPhatHanh() {
		return soBanPhatHanh;
	}

	public void setSoBanPhatHanh(int soBanPhatHanh) {
		this.soBanPhatHanh = soBanPhatHanh;
	}

	@Override
	public int compareTo(TaiLieu o) {
		return soBanPhatHanh - o.getSoBanPhatHanh();
	}

	
}
