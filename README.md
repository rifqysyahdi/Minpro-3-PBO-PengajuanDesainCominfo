# Sistem Pengajuan Desain Departemen COMINFO

---

## Deskripsi Singkat Program

COMINFO (Communication and Media Information) adalah salah satu departemen di himpunan mahasiswa Sistem Informasi, INFORSA. Di dalamnya ada divisi Visual Creative yang menangani semua urusan desain, mulai dari feeds, poster, sampai kebutuhan visual lain untuk seluruh departemen/biro di INFORSA. Selama ini pengajuan desain dilakukan lewat formulir manual yang isinya nama pemohon, departemen/biro, jenis desain, informasi tambahan, dan deadline pembuatan.

Untuk tugas mini project 3 mata kuliah Pemrograman Berorientasi Objek (PBO), saya membuat program berbasis CLI dengan Java untuk menggantikan formulir manual tersebut. Lewat program ini pemohon bisa menambah, melihat, mengubah, dan menghapus pengajuan desain. Pengajuan dibedakan jadi dua jenis, yaitu **Desain Digital** (misalnya untuk Instagram Feed, Story, atau Web Banner) dan **Desain Cetak** (misalnya poster atau sertifikat fisik), karena keduanya butuh data yang berbeda. Program disusun dengan struktur MVC, menerapkan abstraction dan polymorphism, serta memakai interface sebagai nilai tambah.

---

## Penjelasan Struktur Package

Project ini dibagi menjadi 5 package.

![Struktur Package](/aset/struktur-package.png)

Package **cominfo** hanya berisi class `Main`, yaitu pintu masuk program. Di sini dibuat object `PengajuanController` dan `PengajuanView`, lalu view dijalankan.

Package **model** berisi class yang menyimpan data dan aturannya. Ada `Pemohon`, class abstract `PengajuanDesain` beserta dua turunannya `PengajuanDigital` dan `PengajuanCetak`, dan interface `Trackable`. Validasi data pada constructor dan setter juga ditaruh di package ini.

Package **view** berisi `PengajuanView`, yang tugasnya hanya menampilkan menu, membaca input dari user, dan menampilkan hasilnya. View tidak memproses data sendiri, semuanya diteruskan ke controller.

Package **controller** berisi `PengajuanController`, yang menyimpan ArrayList data pengajuan dan menjalankan seluruh logika program, yaitu menambah, mencari, mengubah, dan menghapus data.

Package **util** berisi `InputValidator`, class khusus untuk membaca dan memvalidasi input dari user, seperti input yang kosong, bukan angka, atau di luar pilihan menu.

---

## Penjelasan Alur Program

Program dijalankan lewat class `Main` di package `cominfo`, yang membuat object `PengajuanController` dan `PengajuanView`, lalu memanggil `view.jalankan()`. Saat `PengajuanController` dibuat, method `muatDataAwal()` langsung mengisi 2 data dummy ke ArrayList supaya sudah ada data saat program pertama kali dibuka.

`PengajuanView.jalankan()` menampilkan menu utama dengan 5 pilihan (Tambah, Tampilkan, Update, Hapus, Keluar) dalam perulangan `while` yang baru berhenti kalau user memilih 5. Pilihan menu dibaca lewat `InputValidator`, jadi kalau user mengetik huruf atau angka di luar pilihan, program memberi pemberitahuan dan meminta input ulang, tidak sampai crash.

![Menu Utama](/aset/menu.png)

Kalau user memilih **Tambah Pengajuan**, program meminta ID (harus angka positif dan belum dipakai), nama, departemen/biro, catatan, dan deadline. Input yang dikosongkan akan ditolak dan user diminta mengisi lagi. Setelah itu user memilih jenis desain:

- Kalau pilih **Digital**, user memilih target platform (Instagram Feed / Story / Web Banner) dan format file (PNG / JPG / PDF) lewat menu pilihan, bukan ketik manual.
- Kalau pilih **Cetak**, user memilih ukuran media (A4 Hardpaper / A3 Poster / Banner 2x1m) lewat menu pilihan, lalu mengisi jumlah cetak yang harus lebih dari 0.

Data yang sudah lengkap dikirim ke controller lewat `tambahPengajuanDigital()` atau `tambahPengajuanCetak()`. Controller yang membuat object `PengajuanDigital` atau `PengajuanCetak`, lalu menyimpannya ke ArrayList. Kalau ada data yang tidak lolos validasi di model, view menangkap pesan errornya dan menampilkannya ke user.

![Tambah Pengajuan Digital](/aset/tambah-digital.png)

![Tambah Pengajuan Cetak](/aset/tambah-cetak.png)

![Validasi Input](/aset/validasi-input.png)

Untuk **Tampilkan Pengajuan**, seluruh ArrayList ditelusuri dan tiap object memanggil method `tampilkanDetail()` miliknya sendiri, lalu ditambah baris `Tracker` dari `getRincianStatus()`. Kalau belum ada data, program menampilkan pesan bahwa data masih kosong.

![Tampilkan Pengajuan](/aset/tampilkan.png)

Pada **Update**, user memasukkan ID lalu program mencarinya lewat `cariBerdasarkanId()` di controller. Kalau ketemu, user memilih mau mengubah catatan saja atau catatan sekaligus status, kemudian controller memanggil `updateRevisi()` atau `updateRevisiDanStatus()`. Kalau ID tidak ditemukan, program menampilkan pesan data tidak ditemukan.

![Update Catatan](/aset/update-catatan.png)

![Update Catatan dan Status](/aset/update-catatan-dan-status.png)

Pada **Hapus**, user juga memasukkan ID, lalu controller menghapus datanya dari ArrayList lewat `hapusPengajuan()`. Kalau ID tidak cocok, program memberi pesan tidak ditemukan.

![Hapus Pengajuan](/aset/hapus.png)

Terakhir, menu **Keluar** menghentikan perulangan dan program selesai.

![Keluar](/aset/keluar.png)

---

## Penjelasan Penerapan Encapsulation dan Inheritance

**Encapsulation** semua atribut di class `Pemohon`, `PengajuanDesain`, `PengajuanDigital`, dan `PengajuanCetak` dideklarasikan `private`, jadi tidak bisa diakses langsung dari luar class. Untuk mengambil atau mengubah nilainya harus lewat method `public` seperti getter dan setter (`getNamaLengkap()`, `getIdPengajuan()`, `setStatus()`, dan seterusnya). Setter-nya juga diberi validasi, kalau nilainya kosong atau tidak masuk akal (misalnya jumlah cetak 0), setter melempar `IllegalArgumentException` dengan pesan yang menjelaskan kesalahannya. Keyword `final` dipakai pada atribut yang nilainya tidak berubah setelah dibuat, seperti `listPengajuan` di controller, `idPengajuan` dan `pemohon` di `PengajuanDesain`, seluruh atribut di `Pemohon`, serta atribut spesifik pada subclass. Selain itu, class `PengajuanDigital` dan `PengajuanCetak` juga ditandai sebagai `final class` karena merupakan kelas ujung yang tidak diturunkan lagi.

![Encapsulation Pemohon](/aset/encapsulation-pemohon.png)

![Penggunaan final](/aset/final.png)

**Inheritance** `PengajuanDesain` dibuat sebagai **superclass abstract** yang menyimpan atribut dan method umum (id, pemohon, catatan, deadline, status). Dua class turunannya, `PengajuanDigital` dan `PengajuanCetak`, memakai keyword `extends PengajuanDesain` dan memanggil `super(...)` di constructor untuk mewariskan data dari superclass, lalu menambahkan atribut khusus masing-masing (target platform dan format file untuk Digital, ukuran media dan jumlah cetak untuk Cetak).

![Inheritance-superclass](/aset/super-class.png)

![Inheritance-subclass-digital](/aset/subclass-digital.png)

![Inheritance-subclass-cetak](/aset/subclass-cetak.png)

---

## Penjelasan Penerapan Polymorphism dan Abstraction

**Abstraction** `PengajuanDesain` adalah abstract class, jadi tidak bisa dibuat object-nya langsung. Di dalamnya ada dua abstract method, yaitu `getKategori()` dan `tampilkanDetail()`, yang hanya berisi deklarasi tanpa isi. Kedua method ini wajib ditulis ulang oleh setiap subclass, karena cara menampilkan detail pengajuan Digital dan Cetak memang berbeda.

![Abstraction](/aset/abstraction.png)

**Polymorphism (Method Overriding)** `getKategori()` dan `tampilkanDetail()` di-override di `PengajuanDigital` dan `PengajuanCetak` dengan anotasi `@Override`. Digital menampilkan target rilis dan format file, sedangkan Cetak menampilkan ukuran media dan jumlah cetak. Di `PengajuanView`, ArrayList bertipe `PengajuanDesain` ditelusuri dengan satu perulangan yang memanggil `p.tampilkanDetail()`, dan Java otomatis menjalankan versi method sesuai jenis object-nya.

![Overriding-digital](/aset/overriding-digital.png)

![Overriding-cetak](/aset/overriding-cetak.png)

![Pemanggilan di View](/aset/pemanggilan-view.png)

**Polymorphism (Method Overloading)** di `PengajuanDesain.java` ada dua method `updateInformasi()` dengan parameter berbeda: satu hanya menerima `catatanRevisi`, satu lagi menerima `catatanRevisi` dan `status` sekaligus. Keduanya dipanggil oleh controller sesuai pilihan user di menu update.

![Overloading](/aset/overloading.png)

---

## Penjelasan Letak Penerapan Nilai Tambah

**Interface** nilai tambah yang saya terapkan adalah interface `Trackable` di package `model`, berisi dua method, yaitu `updateStatus()` untuk memperbarui status pengajuan dan `getRincianStatus()` untuk mengambil ringkasan status. Interface ini diimplementasikan oleh `PengajuanDesain` dengan `implements Trackable`, lalu kedua method-nya diisi dengan `@Override`. Karena `PengajuanDigital` dan `PengajuanCetak` turunan dari `PengajuanDesain`, keduanya ikut punya kemampuan tracking. Hasil `getRincianStatus()` bisa dilihat di menu Tampilkan Pengajuan pada baris `Tracker`.

![Interface Trackable](/aset/interface.png)

![Implementasi Interface](/aset/implementasi-interface.png)
