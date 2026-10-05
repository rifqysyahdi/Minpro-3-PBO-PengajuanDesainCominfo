package model;

public final class PengajuanDigital extends PengajuanDesain {
    private String targetPlatform;
    private String formatFile;

    public PengajuanDigital(int idPengajuan, Pemohon pemohon, String catatanRevisi, String deadline, String targetPlatform, String formatFile) {
        super(idPengajuan, pemohon, catatanRevisi, deadline);
        setTargetPlatform(targetPlatform);
        setFormatFile(formatFile);
    }

    public String getTargetPlatform() {
        return targetPlatform;
    }

    public void setTargetPlatform(String targetPlatform) {
        if (targetPlatform == null || targetPlatform.trim().isEmpty()) {
            throw new IllegalArgumentException("Target platform tidak boleh kosong.");
        }
        this.targetPlatform = targetPlatform.trim();
    }

    public String getFormatFile() {
        return formatFile;
    }

    public void setFormatFile(String formatFile) {
        if (formatFile == null || formatFile.trim().isEmpty()) {
            throw new IllegalArgumentException("Format file tidak boleh kosong.");
        }
        this.formatFile = formatFile.trim();
    }

    @Override
    public String getKategori() {
        return "Desain Digital";
    }

    @Override
    public void tampilkanDetail() {
        System.out.println("Kategori       : " + getKategori());
        System.out.println("ID Pengajuan   : " + getIdPengajuan());
        System.out.println("Pemohon        : " + getPemohon().getNamaLengkap() + " (" + getPemohon().getDepartemenBiro() + ")");
        System.out.println("Catatan/Info   : " + getCatatanRevisi());
        System.out.println("Tenggat Waktu  : " + getDeadline());
        System.out.println("Status         : " + getStatus());
        System.out.println("Target Rilis   : " + targetPlatform);
        System.out.println("Format File    : " + formatFile);
    }
}