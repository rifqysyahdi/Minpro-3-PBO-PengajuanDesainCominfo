package model;

public abstract class PengajuanDesain implements Trackable {
    private int idPengajuan;
    private Pemohon pemohon;
    private String catatanRevisi;
    private String deadline;
    private String status;

    public PengajuanDesain(int idPengajuan, Pemohon pemohon, String catatanRevisi, String deadline) {
        if (idPengajuan <= 0) {
            throw new IllegalArgumentException("ID Pengajuan harus angka positif.");
        }
        if (pemohon == null) {
            throw new IllegalArgumentException("Data pemohon tidak boleh kosong.");
        }
        this.idPengajuan = idPengajuan;
        this.pemohon = pemohon;
        setCatatanRevisi(catatanRevisi);
        if (deadline == null || deadline.trim().isEmpty()) {
            throw new IllegalArgumentException("Deadline tidak boleh kosong.");
        }
        this.deadline = deadline.trim();
        this.status = "Diterima";
    }

    public abstract String getKategori();

    public abstract void tampilkanDetail();

    public void updateInformasi(String catatanRevisi) {
        setCatatanRevisi(catatanRevisi);
    }

    public void updateInformasi(String catatanRevisi, String status) {
        setCatatanRevisi(catatanRevisi);
        setStatus(status);
    }

    @Override
    public void updateStatus(String statusBaru) {
        setStatus(statusBaru);
    }

    @Override
    public String getRincianStatus() {
        return "[" + getKategori() + "] ID: " + idPengajuan + " | Status: " + status;
    }

    public int getIdPengajuan() {
        return idPengajuan;
    }

    public Pemohon getPemohon() {
        return pemohon;
    }

    public String getCatatanRevisi() {
        return catatanRevisi;
    }

    public void setCatatanRevisi(String catatanRevisi) {
        if (catatanRevisi == null || catatanRevisi.trim().isEmpty()) {
            throw new IllegalArgumentException("Catatan tidak boleh kosong.");
        }
        this.catatanRevisi = catatanRevisi.trim();
    }

    public String getDeadline() {
        return deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status tidak boleh kosong.");
        }
        this.status = status.trim();
    }
}