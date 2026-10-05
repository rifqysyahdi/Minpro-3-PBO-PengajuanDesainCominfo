package view;

import controller.PengajuanController;
import model.PengajuanDesain;
import util.InputValidator;

import java.util.ArrayList;
import java.util.Scanner;

public class PengajuanView {
    private final PengajuanController controller;
    private final Scanner scanner;

    public PengajuanView(PengajuanController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }
    
    public void jalankan() {
        boolean lanjut = true;
        while (lanjut) {
            System.out.println("\n=== SISTEM PENGAJUAN DESAIN COMINFO ===");
            System.out.println("1. Tambah Pengajuan Desain");
            System.out.println("2. Tampilkan Semua Pengajuan");
            System.out.println("3. Update Catatan & Status");
            System.out.println("4. Hapus Pengajuan");
            System.out.println("5. Keluar");

            int pilihan = InputValidator.bacaPilihan(scanner, "Pilih menu (1-5): ", 1, 5);

            switch (pilihan) {
                case 1 -> menuTambah();
                case 2 -> menuTampilkan();
                case 3 -> menuUpdate();
                case 4 -> menuHapus();
                case 5 -> {
                    System.out.println("Sistem selesai.");
                    lanjut = false;
                }
            }
        }
    }

    private void menuTambah() {
        System.out.println("\n--- Tambah Pengajuan Baru ---");
        int id;
        while (true) {
            id = InputValidator.bacaAngkaPositif(scanner, "Masukkan ID Pengajuan: ");
            if (controller.idSudahAda(id)) {
                System.out.println("ID sudah dipakai, silakan gunakan ID lain.");
            } else {
                break;
            }
        }

        String nama = InputValidator.bacaString(scanner, "Nama Pemohon: ");
        String dept = InputValidator.bacaString(scanner, "Departemen/Biro: ");
        String info = InputValidator.bacaString(scanner, "Catatan/Kebutuhan: ");
        String deadline = InputValidator.bacaString(scanner, "Deadline: ");

        System.out.println("\nPilih Kategori Desain:");
        System.out.println("1. Desain Digital");
        System.out.println("2. Desain Cetak");
        int tipe = InputValidator.bacaPilihan(scanner, "Pilih jenis (1/2): ", 1, 2);

        try {
            if (tipe == 1) {
                System.out.println("\nPilih Target Platform:");
                System.out.println("1. Instagram Feed");
                System.out.println("2. Instagram Story");
                System.out.println("3. Web Banner");
                int optPlatform = InputValidator.bacaPilihan(scanner, "Pilih platform (1-3): ", 1, 3);
                
                String platform;
                platform = switch (optPlatform) {
                    case 1 -> "Instagram Feed";
                    case 2 -> "Instagram Story";
                    case 3 -> "Web Banner";
                    default -> "Media Digital";
                };

                System.out.println("\nPilih Format File:");
                System.out.println("1. PNG");
                System.out.println("2. JPG");
                System.out.println("3. PDF");
                int optFormat = InputValidator.bacaPilihan(scanner, "Pilih format (1-3): ", 1, 3);
                
                String format;
                format = switch (optFormat) {
                    case 1 -> "PNG";
                    case 2 -> "JPG";
                    case 3 -> "PDF";
                    default -> "PNG";
                };

                if (controller.tambahPengajuanDigital(id, nama, dept, info, deadline, platform, format)) {
                    System.out.println("Pengajuan desain digital berhasil disimpan.");
                }
            } else {
                System.out.println("\nPilih Ukuran Media:");
                System.out.println("1. A4 Hardpaper");
                System.out.println("2. A3 Poster");
                System.out.println("3. Banner 2x1m");
                int optUkuran = InputValidator.bacaPilihan(scanner, "Pilih ukuran (1-3): ", 1, 3);
                
                String ukuran;
                ukuran = switch (optUkuran) {
                    case 1 -> "A4 Hardpaper";
                    case 2 -> "A3 Poster";
                    case 3 -> "Banner 2x1m";
                    default -> "A4 Standard";
                };

                int jumlah = InputValidator.bacaAngkaPositif(scanner, "Jumlah Cetak (pcs): ");

                if (controller.tambahPengajuanCetak(id, nama, dept, info, deadline, ukuran, jumlah)) {
                    System.out.println("Pengajuan desain cetak berhasil disimpan.");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    private void menuTampilkan() {
        System.out.println("\n--- Daftar Pengajuan Desain ---");
        ArrayList<PengajuanDesain> list = controller.getSemuaPengajuan();
        if (list.isEmpty()) {
            System.out.println("Belum ada data pengajuan.");
            return;
        }

        for (PengajuanDesain p : list) {
            p.tampilkanDetail();
            System.out.println("Tracker: " + p.getRincianStatus());
            System.out.println();
        }
    }

    private void menuUpdate() {
        System.out.println("\n--- Update Catatan / Detail Revisi ---");
        int id = InputValidator.bacaAngkaPositif(scanner, "Masukkan ID Pengajuan: ");
        PengajuanDesain p = controller.cariBerdasarkanId(id);

        if (p == null) {
            System.out.println("Data pengajuan tidak ditemukan.");
            return;
        }

        System.out.println("Catatan saat ini : " + p.getCatatanRevisi());
        System.out.println("Status saat ini  : " + p.getStatus());

        System.out.println("\nPilih Jenis Update:");
        System.out.println("1. Update Catatan saja");
        System.out.println("2. Update Catatan dan Status");
        int mode = InputValidator.bacaPilihan(scanner, "Pilih opsi (1/2): ", 1, 2);

        String catatanBaru = InputValidator.bacaString(scanner, "Masukkan Catatan/Revisi Baru: ");

        try {
            if (mode == 1) {
                controller.updateRevisi(id, catatanBaru);
            } else {
                String statusBaru = InputValidator.bacaString(scanner, "Masukkan Status Baru: ");
                controller.updateRevisiDanStatus(id, catatanBaru, statusBaru);
            }
            System.out.println("Data pengajuan berhasil diperbarui.");
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal memperbarui data: " + e.getMessage());
        }
    }

    private void menuHapus() {
        System.out.println("\n--- Hapus Data Pengajuan ---");
        int id = InputValidator.bacaAngkaPositif(scanner, "Masukkan ID Pengajuan: ");
        boolean berhasil = controller.hapusPengajuan(id);

        if (berhasil) {
            System.out.println("Data pengajuan berhasil dihapus.");
        } else {
            System.out.println("Data pengajuan tidak ditemukan.");
        }
    }
}