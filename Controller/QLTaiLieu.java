package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import Model.Bao;
import Model.ChucNang;
import Model.Sach;
import Model.TaiLieu;

public class QLTaiLieu implements ChucNang {
	private List<TaiLieu> list;
	
	private Scanner sc = new Scanner(System.in);
	
	public QLTaiLieu() {
		list = new ArrayList<>();
	}
	
	public List<TaiLieu> getList() {
		return list;
	}

	public void setList(List<TaiLieu> list) {
		this.list = list;
	}

	private int TimViTri(String maTaiLieu) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getMaTaiLieu().equalsIgnoreCase(maTaiLieu)) {
				return i;
			}
		}
		return -1;
	}
	
	private TaiLieu nhap() {
		String maTaiLieu, tenNXB;
		int soBanPhatHanh;
		String regex = "[ABCD]{1}\\d{3}";
		while (true) {
			try {
				System.out.println("Mã tài liệu: ");
				maTaiLieu = sc.nextLine().toUpperCase();
				if (TimViTri(maTaiLieu) == -1 && maTaiLieu.matches(regex)) {
					break;
				}
				else {
					throw new ValidException("Mã tài liệu bị trùng hoặc không đúng định dạng!!!");
				}
			} catch (ValidException e) {
				System.err.println(e);
			}
		}
		System.out.println("Tên nxb: ");
		tenNXB = sc.nextLine();
		while (true) {
			try {
				System.out.println("Số bản phát hành: ");
				soBanPhatHanh = Integer.parseInt(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.err.println(e);
			}
		}
		return new TaiLieu(maTaiLieu, tenNXB, soBanPhatHanh);
	}
	
	@Override
	public void NhapSach() {
		TaiLieu tl = nhap();
		String tenTG, tenSach;
		int soTrang;
		System.out.println("Tên tác giả: ");
		tenTG = sc.nextLine();
		System.out.println("Tên sách: ");
		tenSach = sc.nextLine();
		while (true) {
			try {
				System.out.println("Số trang: ");
				soTrang = Integer.parseInt(sc.nextLine());
				break;
			} catch (NumberFormatException e) {
				System.err.println(e);
			}
		}
		list.add(new Sach(tl.getMaTaiLieu(), tl.getTenNXB(), tl.getSoBanPhatHanh(), tenTG, tenSach, soTrang));
	}

	@Override
	public void NhapBao() {
		TaiLieu tl = nhap();
		String ngayPhatHanh;
		String regex = "\\d{1,2}/\\d{1,2}/\\d{4}";
		while (true) {
			try {
				System.out.println("Ngày phát hành: ");
				ngayPhatHanh = sc.nextLine();
				if (ngayPhatHanh.matches(regex)) {
					break;
				}
				else {
					throw new ValidException("Ngày phát hành không đúng định dạng!!!");
				}
			} catch (ValidException e) {
				System.err.println(e);
			}
		}
		list.add(new Bao(tl.getMaTaiLieu(), tl.getTenNXB(), tl.getSoBanPhatHanh(), ngayPhatHanh));
		
	}

	@Override
	public void HienThiDS() {
		for (TaiLieu taiLieu : list) {
			System.out.println(taiLieu);
		}
		System.out.println("-----------------");
		System.out.println("Tong: " +list.size());
	}

	@Override
	public void luuFile(String fname) {
		IOFile.write(fname, list);
	}

	@Override
	public void docFile(String fname) {
		setList(IOFile.read(fname));
	}

	@Override
	public void xoa() {
		System.out.println("Nhập vào mã cần xóa: ");
		String maTaiLieu = sc.nextLine();
		int vt = TimViTri(maTaiLieu);
		if (vt == -1) {
			System.out.println("Không tìm thấy mã cần xóa!");
		}
		else {
			list.remove(vt);
			System.out.println("Xóa thành công!");
		}
	}

	@Override
	public void sua() {
		System.out.println("Nhập vào mã cần sửa: ");
		String maTaiLieu = sc.nextLine();
		int vt = TimViTri(maTaiLieu);
		if (vt == -1) {
			System.out.println("Không tìm thấy mã cần sửa!");
		}
		else {
			TaiLieu tl = list.get(vt);
			// sửa phần chung
			System.out.println("Tên nxb: ");
			String tenNXB = sc.nextLine();
			int soBanPhatHanh;
			while (true) {
				try {
					System.out.println("Số bản phát hành: ");
					soBanPhatHanh = Integer.parseInt(sc.nextLine());
					break;
				} catch (NumberFormatException e) {
					System.err.println(e);
				}
			}
			tl.setTenNXB(tenNXB);
			tl.setSoBanPhatHanh(soBanPhatHanh);
			
			// sửa phần riêng
			if (tl instanceof Sach) {
				String tenTG, tenSach;
				int soTrang;
				System.out.println("Tên tác giả: ");
				tenTG = sc.nextLine();
				System.out.println("Tên sách: ");
				tenSach = sc.nextLine();
				while (true) {
					try {
						System.out.println("Số trang: ");
						soTrang = Integer.parseInt(sc.nextLine());
						break;
					} catch (NumberFormatException e) {
						System.err.println(e);
					}
				}
				((Sach) tl).setTenTG(tenTG);
				((Sach) tl).setTenSach(tenSach);
				((Sach) tl).setSoTrang(soTrang);
			}
			
			if (tl instanceof Bao) {
				String ngayPhatHanh;
				String regex = "\\d{1,2}/\\d{1,2}/\\d{4}";
				while (true) {
					try {
						System.out.println("Ngày phát hành: ");
						ngayPhatHanh = sc.nextLine();
						if (ngayPhatHanh.matches(regex)) {
							break;
						}
						else {
							throw new ValidException("Ngày phát hành không đúng định dạng!!!");
						}
					} catch (ValidException e) {
						System.err.println(e);
					}
				}
				((Bao) tl).setNgayPhatHanh(ngayPhatHanh);
			}
			System.out.println("Sửa thành công!");
		}
	}

	@Override
	public void timTheoTenSach() {
		List<Sach> DSSach = new ArrayList<>();
		for (TaiLieu taiLieu : list) {
			if (taiLieu instanceof Sach) {
				DSSach.add((Sach) taiLieu);
			}
		}
		System.out.println("Nhập tên sách cần tìm: ");
		String tenSach = sc.nextLine();
		int count = 0;
		for (Sach sach : DSSach) {
			if (sach.getTenSach().toLowerCase().indexOf(tenSach.toLowerCase()) >= 0) {
				System.out.println("Tìm thấy "+sach+"!");
				++count;
			}
		}
		if (count == 0) {
			System.out.println("Không tìm thấy sách!");
		}
	}

	@Override
	public void timBaoTuNamDenNam() {
		List<Bao> DSBao = new ArrayList<>();
		for (TaiLieu taiLieu : list) {
			if (taiLieu instanceof Bao) {
				DSBao.add((Bao) taiLieu);
			}
		}
		try {
			System.out.println("Năm bắt đầu: ");
			int namBatDau = sc.nextInt();
			System.out.println("Năm kết thúc: ");
			int namKetThuc = sc.nextInt();
			int count = 0;
			for (Bao bao : DSBao) {
				if (bao.getNam() >= namBatDau && bao.getNam() <= namKetThuc) {
					System.out.println("Tìm thấy "+bao+"!");
					++count;
				}
			}
			if (count == 0) {
				System.out.println("Không tìm thấy báo!");
			}
		} catch(Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void sapXepSoBanPhatHanh() {
		Collections.sort(list);
		HienThiDS();
	}

	@Override
	public void sapXepSachTheoThuTu() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sapXepSachTheoTenTG() {
		List<Sach> DSSach = new ArrayList<>();
		for (TaiLieu taiLieu : list) {
			if (taiLieu instanceof Sach) {
				DSSach.add((Sach) taiLieu);
			}
		}
		DSSach.sort(new Comparator<Sach>() {

			@Override
			public int compare(Sach o1, Sach o2) {
				String ten1 = o1.getTenTG();
				String ten2 = o2.getTenTG();
				String t1 = ten1.substring(ten1.lastIndexOf(" ")+1)+ten1;
				String t2 = ten2.substring(ten2.lastIndexOf(" ")+1)+ten2;
				return t1.compareToIgnoreCase(t2);
			}
			
		});
		for (Sach sach: DSSach) {
			System.out.println(sach);
		}
	}

	@Override
	public void sapXepBaoTheoNgayPhatHanhVaSoBanPhatHanh() {
		List<Bao> DSBao = new ArrayList<>();
		for (TaiLieu taiLieu : list) {
			if (taiLieu instanceof Bao) {
				DSBao.add((Bao) taiLieu);
			}
		}
		DSBao.sort(new Comparator<Bao>() {

			@Override
			public int compare(Bao o1, Bao o2) {
				StringTokenizer tokenizer1 = new StringTokenizer(o1.getNgayPhatHanh());
				StringTokenizer tokenizer2 = new StringTokenizer(o2.getNgayPhatHanh());
				String ngay1 = "";
				while (tokenizer1.hasMoreTokens()) {
					ngay1 = tokenizer1.nextToken()+ngay1;
				}
				String ngay2 = "";
				while (tokenizer2.hasMoreTokens()) {
					ngay2 = tokenizer2.nextToken()+ngay2;
				}
				System.out.println(ngay1);
				System.out.println(ngay2);
				if (ngay1.equalsIgnoreCase(ngay2)) {
					return o1.getSoBanPhatHanh() - o2.getSoBanPhatHanh();
				}
				else {
					return ngay1.compareToIgnoreCase(ngay2);
				}
			}
			
		});
		for (Bao bao: DSBao) {
			System.out.println(bao);
		}
	}

	@Override
	public void thongKeSoSachVaBaoTheoNXB() {
		Map<String, Long> count = list.stream().collect(Collectors.groupingBy(TaiLieu::getTenNXB, Collectors.counting()));
		Iterator i = count.keySet().iterator();
		while (i.hasNext()) {
			Object key = i.next();
			System.out.println(key+": "+count.get(key));
		}
	}

	@Override
	public void thongKeSoBaoPhatHanhTheoTungNam() {
		List<Bao> DSBao = new ArrayList<>();
		for (TaiLieu taiLieu: list) {
			if (taiLieu instanceof Bao) {
				DSBao.add((Bao) taiLieu);
			}
		}
		Map<Integer, Integer> sum = DSBao.stream().collect(Collectors.groupingBy(Bao::getNam, Collectors.summingInt(Bao::getSoBanPhatHanh)));
		for (Map.Entry<Integer, Integer> e : sum.entrySet()) {
			System.out.println(e.getKey()+": "+e.getValue());
		}
	}

}
