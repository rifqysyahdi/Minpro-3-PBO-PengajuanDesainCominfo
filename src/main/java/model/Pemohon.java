package model;

public class Pemohon {
    private String namaLengkap;
    private String departemenBiro;

    public Pemohon(String namaLengkap, String departemenBiro) {
        setNamaLengkap(namaLengkap);
        setDepartemenBiro(departemenBiro);
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        if (namaLengkap == null || namaLengkap.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama pemohon tidak boleh kosong.");
        }
        this.namaLengkap = namaLengkap.trim();
    }

    public String getDepartemenBiro() {
        return departemenBiro;
    }

    public void setDepartemenBiro(String departemenBiro) {
        if (departemenBiro == null || departemenBiro.trim().isEmpty()) {
            throw new IllegalArgumentException("Departemen/Biro tidak boleh kosong.");
        }
        this.departemenBiro = departemenBiro.trim();
    }
}