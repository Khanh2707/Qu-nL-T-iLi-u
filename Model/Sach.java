package Model;

import java.io.Serializable;
import java.util.StringTokenizer;

public class Sach extends TaiLieu implements ITaiLieu, Serializable {
	private String tenTG, tenSach;
	private int soTrang;
	
	public Sach() {
		
	}
	
	public Sach(String maTaiLieu, String tenNXB, int soBanPhatHanh, String tenTG, String tenSach, int soTrang) {
		super(maTaiLieu, tenNXB, soBanPhatHanh);
		this.tenTG = tenTG;
		this.tenSach = tenSach;
		this.soTrang = soTrang;
	}

	public String getTenTG() {
		return tenTG;
	}


	public void setTenTG(String tenTG) {
		this.tenTG = tenTG;
	}


	public String getTenSach() {
		return tenSach;
	}


	public void setTenSach(String tenSach) {
		this.tenSach = tenSach;
	}


	public int getSoTrang() {
		return soTrang;
	}


	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
	}


	@Override
	public String getCode() {
		String c = getMaTaiLieu().toUpperCase();
		StringTokenizer stringTokenizer = new StringTokenizer(tenSach.trim().toUpperCase());
		while (stringTokenizer.hasMoreTokens()) {
			c += stringTokenizer.nextToken().charAt(0);
		}
		return c;
	}


	@Override
	public String toString() {
		return getMaTaiLieu()+"\t"+getTenSach()+"\t"+getTenTG()+"\t"+getSoTrang()+"\t"+getTenNXB()+"\t"+getSoBanPhatHanh();
	}
}
