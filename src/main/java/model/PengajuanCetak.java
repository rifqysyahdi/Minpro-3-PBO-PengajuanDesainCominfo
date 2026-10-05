package model;

public final class PengajuanCetak extends PengajuanDesain {
    private String ukuranMedia;
    private int jumlahCetak;

    public PengajuanCetak(int idPengajuan, Pemohon pemohon, String catatanRevisi, String deadline, String ukuranMedia, int jumlahCetak) {
        super(idPengajuan, pemohon, catatanRevisi, deadline);
        setUkuranMedia(ukuranMedia);
        setJumlahCetak(jumlahCetak);
    }

    public String getUkuranMedia() {
        return ukuranMedia;
    }

    public void setUkuranMedia(String ukuranMedia) {
        if (ukuranMedia == null || ukuranMedia.trim().isEmpty()) {
            throw new IllegalArgumentException("Ukuran media tidak boleh kosong.");
        }
        this.ukuranMedia = ukuranMedia.trim();
    }

    public int getJumlahCetak() {
        return jumlahCetak;
    }

    public void setJumlahCetak(int jumlahCetak) {
        if (jumlahCetak <= 0) {
            throw new IllegalArgumentException("Jumlah cetak minimal harus 1.");
        }
        this.jumlahCetak = jumlahCetak;
    }

    @Override
    public String getKategori() {
        return "Desain Cetak";
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("Kategori       : " + getKategori());
        System.out.println("ID Pengajuan   : " + getIdPengajuan());
        System.out.println("Pemohon        : " + getPemohon().getNamaLengkap() + " (" + getPemohon().getDepartemenBiro() + ")");
        System.out.println("Catatan/Info   : " + getCatatanRevisi());
        System.out.println("Tenggat Waktu  : " + getDeadline());
        System.out.println("Status         : " + getStatus());
        System.out.println("Ukuran Media   : " + ukuranMedia);
        System.out.println("Jumlah Cetak   : " + jumlahCetak + " pcs");
    }
}