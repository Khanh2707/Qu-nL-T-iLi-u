package run;

import java.util.Scanner;

import Controller.QLTaiLieu;

public class Main {
	public static void main(String[] args) {
		QLTaiLieu qlTaiLieu = new QLTaiLieu();
		String fname = "C:\\Users\\nthtp\\eclipse-workspace\\Book\\src\\run\\Data_TaiLieu.dat";
		while (true) {
			System.out.println("1. Nhập sách");
			System.out.println("2. Nhập báo");
			System.out.println("3. Hiển thị danh sách");
			System.out.println("4. Lưu file");
			System.out.println("5. Đọc file");
			System.out.println("6. Xóa ");
			System.out.println("7. Sửa");
			System.out.println("8. Tìm sách theo tên");
			System.out.println("9. Tìm báo từ năm đến năm");
			System.out.println("10. Sắp xếp theo số bản phát hành");
			System.out.println("11. Sắp xếp sách theo tên tác giả");
			System.out.println("12. Sắp xếp báo theo ngày phát hành và số bản phát hành");
			System.out.println("13. Thống kê số sách và báo theo NXB");
			System.out.println("14. Thống kê số báo phát hành theo từng năm");
			System.out.println("0. Thoát");
			int choice;
			Scanner sc = new Scanner(System.in);
			choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 0:
				System.exit(0);
			case 1:
				qlTaiLieu.NhapSach();
				break;
			case 2:
				qlTaiLieu.NhapBao();
				break;
			case 3:
				qlTaiLieu.HienThiDS();
				break;
			case 4:
				qlTaiLieu.luuFile(fname);
				break;
			case 5:
				qlTaiLieu.docFile(fname);
				break;
			case 6:
				qlTaiLieu.xoa();
				break;
			case 7:
				qlTaiLieu.sua();
				break;
			case 8:
				qlTaiLieu.timTheoTenSach();
				break;
			case 9:
				qlTaiLieu.timBaoTuNamDenNam();
				break;
			case 10:
				qlTaiLieu.sapXepSoBanPhatHanh();
				break;
			case 11:
				qlTaiLieu.sapXepSachTheoTenTG();
				break;
			case 12:
				qlTaiLieu.sapXepBaoTheoNgayPhatHanhVaSoBanPhatHanh();
				break;
			case 13:
				qlTaiLieu.thongKeSoSachVaBaoTheoNXB();;
				break;
			case 14:
				qlTaiLieu.thongKeSoBaoPhatHanhTheoTungNam();;
				break;
			default: 
				System.out.println("Lựa chọn không hợp lệ!!!");
				break;
			}
		}
	}
}
