package controller;

import model.PengajuanDesain;
import model.PengajuanDigital;
import model.PengajuanCetak;
import model.Pemohon;

import java.util.ArrayList;

public class PengajuanController {
    private final ArrayList<PengajuanDesain> listPengajuan;

    public PengajuanController() {
        this.listPengajuan = new ArrayList<>();
        muatDataAwal();
    }

    private void muatDataAwal() {
        try {
            Pemohon pemohon1 = new Pemohon("Yahya Jailani", "Biro EDEN");
            PengajuanDigital p1 = new PengajuanDigital(1, pemohon1, "Poster INSTAND", "09/09/2026", "Instagram Feed", "PNG");

            Pemohon pemohon2 = new Pemohon("Ahmad Ahdasuki", "Departemen PSD");
            PengajuanCetak p2 = new PengajuanCetak(2, pemohon2, "Sertifikat ISC", "17/10/2026", "A4 Hardpaper", 50);

            listPengajuan.add(p1);
            listPengajuan.add(p2);
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal muat data awal: " + e.getMessage());
        }
    }

    public boolean idSudahAda(int id) {
        return cariBerdasarkanId(id) != null;
    }

    public boolean tambahPengajuanDigital(int id, String nama, String dept, String info, String deadline, String platform, String format) {
        if (idSudahAda(id)) {
            return false;
        }
        Pemohon pemohon = new Pemohon(nama, dept);
        PengajuanDigital pd = new PengajuanDigital(id, pemohon, info, deadline, platform, format);
        listPengajuan.add(pd);
        return true;
    }

    public boolean tambahPengajuanCetak(int id, String nama, String dept, String info, String deadline, String ukuran, int jumlah) {
        if (idSudahAda(id)) {
            return false;
        }
        Pemohon pemohon = new Pemohon(nama, dept);
        PengajuanCetak pc = new PengajuanCetak(id, pemohon, info, deadline, ukuran, jumlah);
        listPengajuan.add(pc);
        return true;
    }

    public ArrayList<PengajuanDesain> getSemuaPengajuan() {
        return listPengajuan;
    }

    public PengajuanDesain cariBerdasarkanId(int id) {
        for (PengajuanDesain p : listPengajuan) {
            if (p.getIdPengajuan() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean updateRevisi(int id, String catatanBaru) {
        PengajuanDesain target = cariBerdasarkanId(id);
        if (target != null) {
            target.updateInformasi(catatanBaru);
            return true;
        }
        return false;
    }

    public boolean updateRevisiDanStatus(int id, String catatanBaru, String statusBaru) {
        PengajuanDesain target = cariBerdasarkanId(id);
        if (target != null) {
            target.updateInformasi(catatanBaru, statusBaru);
            return true;
        }
        return false;
    }

    public boolean hapusPengajuan(int id) {
        PengajuanDesain target = cariBerdasarkanId(id);
        if (target != null) {
            listPengajuan.remove(target);
            return true;
        }
        return false;
    }
}