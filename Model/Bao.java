package Model;

import java.io.Serializable;
import java.util.Calendar;

public class Bao extends TaiLieu implements ITaiLieu, Serializable {
	private String ngayPhatHanh;
	
	public Bao() {
		
	}
	
	public Bao(String maTaiLieu, String tenNXB, int soBanPhatHanh, String ngayPhatHanh) {
		super(maTaiLieu, tenNXB, soBanPhatHanh);
		this.ngayPhatHanh = ngayPhatHanh;
	}

	public String getNgayPhatHanh() {
		return ngayPhatHanh;
	}

	public void setNgayPhatHanh(String ngayPhatHanh) {
		this.ngayPhatHanh = ngayPhatHanh;
	}

	@Override
	public String getCode() {
		String c = getMaTaiLieu().toUpperCase()+ngayPhatHanh.substring(ngayPhatHanh.lastIndexOf("/")+1);
		return c;
	}
	
	public int getNam() {
		try {
			return Integer.parseInt(ngayPhatHanh.substring(ngayPhatHanh.lastIndexOf("/")+1));
		}
		catch(NumberFormatException e) {
			Calendar c = Calendar.getInstance();
			return c.get(Calendar.YEAR);
		}
	}

	@Override
	public String toString() {
		return getMaTaiLieu()+"\t"+getTenNXB()+"\t"+getNgayPhatHanh()+"\t"+getSoBanPhatHanh();
	}
}
