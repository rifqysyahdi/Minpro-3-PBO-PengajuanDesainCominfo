# Sistem Pengajuan Desain Departemen COMINFO

---

## Deskripsi Singkat Program

COMINFO (Communication and Media Information) adalah salah satu departemen di himpunan mahasiswa Sistem Informasi, INFORSA. Di dalamnya ada divisi Visual Creative yang menangani semua urusan desain, mulai dari feeds, poster, sampai kebutuhan visual lain untuk seluruh departemen/biro di INFORSA. Selama ini pengajuan desain dilakukan lewat formulir manual yang isinya nama pemohon, departemen/biro, jenis desain, informasi tambahan, dan deadline pembuatan.

Untuk tugas mini project 3 mata kuliah Pemrograman Berorientasi Objek (PBO), saya membuat program berbasis CLI dengan Java untuk menggantikan formulir manual tersebut. Lewat program ini pemohon bisa menambah, melihat, mengubah, dan menghapus pengajuan desain. Pengajuan dibedakan jadi dua jenis, yaitu **Desain Digital** (misalnya untuk Instagram Feed, Story, atau Web Banner) dan **Desain Cetak** (misalnya poster atau sertifikat fisik), karena keduanya butuh data yang berbeda. Program disusun dengan struktur MVC, menerapkan abstraction dan polymorphism, serta memakai interface sebagai nilai tambah.

---

## Penjelasan Struktur Package

Project ini dibagi menjadi 5 package.

![Struktur Package](<img width="338" height="270" alt="image" src="https://github.com/user-attachments/assets/67d31bf3-ef74-4483-99ce-893e7b4690d6" />)

Package **cominfo** hanya berisi class `Main`, yaitu pintu masuk program. Di sini dibuat object `PengajuanController` dan `PengajuanView`, lalu view dijalankan.

Package **model** berisi class yang menyimpan data dan aturannya. Ada `Pemohon`, class abstract `PengajuanDesain` beserta dua turunannya `PengajuanDigital` dan `PengajuanCetak`, dan interface `Trackable`. Validasi data pada constructor dan setter juga ditaruh di package ini.

Package **view** berisi `PengajuanView`, yang tugasnya hanya menampilkan menu, membaca input dari user, dan menampilkan hasilnya. View tidak memproses data sendiri, semuanya diteruskan ke controller.

Package **controller** berisi `PengajuanController`, yang menyimpan ArrayList data pengajuan dan menjalankan seluruh logika program, yaitu menambah, mencari, mengubah, dan menghapus data.

Package **util** berisi `InputValidator`, class khusus untuk membaca dan memvalidasi input dari user, seperti input yang kosong, bukan angka, atau di luar pilihan menu.

---

## Penjelasan Alur Program

Program dijalankan lewat class `Main` di package `cominfo`, yang membuat object `PengajuanController` dan `PengajuanView`, lalu memanggil `view.jalankan()`. Saat `PengajuanController` dibuat, method `muatDataAwal()` langsung mengisi 2 data dummy ke ArrayList supaya sudah ada data saat program pertama kali dibuka.

`PengajuanView.jalankan()` menampilkan menu utama dengan 5 pilihan (Tambah, Tampilkan, Update, Hapus, Keluar) dalam perulangan `while` yang baru berhenti kalau user memilih 5. Pilihan menu dibaca lewat `InputValidator`, jadi kalau user mengetik huruf atau angka di luar pilihan, program memberi pemberitahuan dan meminta input ulang, tidak sampai crash.

![Menu Utama](<img width="567" height="212" alt="image" src="https://github.com/user-attachments/assets/2f9288ef-0d51-487d-ab77-4c8a2f519068" />)

Kalau user memilih **Tambah Pengajuan**, program meminta ID (harus angka positif dan belum dipakai), nama, departemen/biro, catatan, dan deadline. Input yang dikosongkan akan ditolak dan user diminta mengisi lagi. Setelah itu user memilih jenis desain:

- Kalau pilih **Digital**, user memilih target platform (Instagram Feed / Story / Web Banner) dan format file (PNG / JPG / PDF) lewat menu pilihan, bukan ketik manual.
- Kalau pilih **Cetak**, user memilih ukuran media (A4 Hardpaper / A3 Poster / Banner 2x1m) lewat menu pilihan, lalu mengisi jumlah cetak yang harus lebih dari 0.

Data yang sudah lengkap dikirim ke controller lewat `tambahPengajuanDigital()` atau `tambahPengajuanCetak()`. Controller yang membuat object `PengajuanDigital` atau `PengajuanCetak`, lalu menyimpannya ke ArrayList. Kalau ada data yang tidak lolos validasi di model, view menangkap pesan errornya dan menampilkannya ke user.

![Tambah Pengajuan Digital](<img width="580" height="650" alt="image" src="https://github.com/user-attachments/assets/244833ed-c978-4b25-8c1b-d422180b851f" />)

![Tambah Pengajuan Cetak](<img width="550" height="570" alt="image" src="https://github.com/user-attachments/assets/d83de7e6-32e7-418a-b251-7d77f74fbe41" />)

![Validasi Input](<img width="397" height="275" alt="image" src="https://github.com/user-attachments/assets/a0b7cf87-3c86-4de8-9f43-3b9eb5b67962" />)

Untuk **Tampilkan Pengajuan**, seluruh ArrayList ditelusuri dan tiap object memanggil method `tampilkanDetail()` miliknya sendiri, lalu ditambah baris `Tracker` dari `getRincianStatus()`. Kalau belum ada data, program menampilkan pesan bahwa data masih kosong.

![Tampilkan Pengajuan](<img width="519" height="619" alt="image" src="https://github.com/user-attachments/assets/c54f6420-96ae-4fab-baf8-2b1ad2a0d38c" />)

Pada **Update**, user memasukkan ID lalu program mencarinya lewat `cariBerdasarkanId()` di controller. Kalau ketemu, user memilih mau mengubah catatan saja atau catatan sekaligus status, kemudian controller memanggil `updateRevisi()` atau `updateRevisiDanStatus()`. Kalau ID tidak ditemukan, program menampilkan pesan data tidak ditemukan.

![Update Catatan](<img width="438" height="325" alt="image" src="https://github.com/user-attachments/assets/b425cb1e-bdbf-4fdf-bc93-155d935b7962" />)

![Update Catatan dan Status](<img width="390" height="344" alt="image" src="https://github.com/user-attachments/assets/0c5a32b4-b4c7-4797-a38e-dcbaec388c62" />)

Pada **Hapus**, user juga memasukkan ID, lalu controller menghapus datanya dari ArrayList lewat `hapusPengajuan()`. Kalau ID tidak cocok, program memberi pesan tidak ditemukan.

![Hapus Pengajuan](<img width="391" height="198" alt="image" src="https://github.com/user-attachments/assets/ef0b5d5e-3abe-404e-bb9d-8d8d3f960446" />)

Terakhir, menu **Keluar** menghentikan perulangan dan program selesai.

![Keluar](<img width="563" height="227" alt="image" src="https://github.com/user-attachments/assets/84b1df48-0aca-4a3a-a9ab-47b09f0d8fdf" />)

---

## Penjelasan Penerapan Encapsulation dan Inheritance

**Encapsulation** semua atribut di class `Pemohon`, `PengajuanDesain`, `PengajuanDigital`, dan `PengajuanCetak` dideklarasikan `private`, jadi tidak bisa diakses langsung dari luar class. Untuk mengambil atau mengubah nilainya harus lewat method `public` seperti getter dan setter (`getNamaLengkap()`, `getIdPengajuan()`, `setStatus()`, dan seterusnya). Setter-nya juga diberi validasi, kalau nilainya kosong atau tidak masuk akal (misalnya jumlah cetak 0), setter melempar `IllegalArgumentException` dengan pesan yang menjelaskan kesalahannya. Keyword `final` dipakai pada atribut yang nilainya tidak berubah setelah dibuat, yaitu `listPengajuan` di controller serta `idPengajuan`, `pemohon`, dan `deadline` di `PengajuanDesain`.

![Encapsulation Pemohon](<img width="856" height="711" alt="image" src="https://github.com/user-attachments/assets/f896fbfc-42f9-4fe5-9f9b-2c2c5c274d45" />)

![Encapsulation PengajuanDesain](<img width="497" height="767" alt="image" src="https://github.com/user-attachments/assets/89d2057a-228b-496a-9c06-1f219827ab1a" />)

![Penggunaan final](<img width="514" height="76" alt="image" src="https://github.com/user-attachments/assets/c0902e74-a755-4027-932e-9101f86d3504" />)

**Inheritance** `PengajuanDesain` dibuat sebagai **superclass abstract** yang menyimpan atribut dan method umum (id, pemohon, catatan, deadline, status). Dua class turunannya, `PengajuanDigital` dan `PengajuanCetak`, memakai keyword `extends PengajuanDesain` dan memanggil `super(...)` di constructor untuk mewariskan data dari superclass, lalu menambahkan atribut khusus masing-masing (target platform dan format file untuk Digital, ukuran media dan jumlah cetak untuk Cetak).

![Inheritance-superclass](<img width="550" height="179" alt="image" src="https://github.com/user-attachments/assets/c2e26823-0d21-4cec-ab95-5ee550e067ca" />)

![Inheritance-subclass-digital](<img width="1242" height="218" alt="image" src="https://github.com/user-attachments/assets/5e3e7efb-2d61-467c-bfb7-ada8777a4fce" />)

![Inheritance-subclass-cetak](<img width="1148" height="214" alt="image" src="https://github.com/user-attachments/assets/e1ffee35-46d9-4937-b49c-0674b2565498" />)

---

## Penjelasan Penerapan Polymorphism dan Abstraction

**Abstraction** `PengajuanDesain` adalah abstract class, jadi tidak bisa dibuat object-nya langsung. Di dalamnya ada dua abstract method, yaitu `getKategori()` dan `tampilkanDetail()`, yang hanya berisi deklarasi tanpa isi. Kedua method ini wajib ditulis ulang oleh setiap subclass, karena cara menampilkan detail pengajuan Digital dan Cetak memang berbeda.

![Abstraction](<img width="903" height="603" alt="image" src="https://github.com/user-attachments/assets/5a5b7acf-4285-4caa-8b45-6525dfc39c54" />)

**Polymorphism (Method Overriding)** `getKategori()` dan `tampilkanDetail()` di-override di `PengajuanDigital` dan `PengajuanCetak` dengan anotasi `@Override`. Digital menampilkan target rilis dan format file, sedangkan Cetak menampilkan ukuran media dan jumlah cetak. Di `PengajuanView`, ArrayList bertipe `PengajuanDesain` ditelusuri dengan satu perulangan yang memanggil `p.tampilkanDetail()`, dan Java otomatis menjalankan versi method sesuai jenis object-nya.

![Overriding-digital](<img width="1167" height="331" alt="image" src="https://github.com/user-attachments/assets/7fc19f48-243d-4e41-be18-05480216d6e2" />)

![Overriding-cetak](<img width="1094" height="325" alt="image" src="https://github.com/user-attachments/assets/ebe98de7-e84a-489a-823c-69168e6a2052" />)

![Pemanggilan di View](<img width="688" height="281" alt="image" src="https://github.com/user-attachments/assets/d6736bdf-473a-4700-8158-9e3362c1a4b3" />)

**Polymorphism (Method Overloading)** di `PengajuanDesain.java` ada dua method `updateInformasi()` dengan parameter berbeda: satu hanya menerima `catatanRevisi`, satu lagi menerima `catatanRevisi` dan `status` sekaligus. Keduanya dipanggil oleh controller sesuai pilihan user di menu update.

![Overloading](<img width="654" height="188" alt="image" src="https://github.com/user-attachments/assets/b7ed3c39-0770-41bb-a592-ea12198b2b7d" />)

---

## Penjelasan Letak Penerapan Nilai Tambah

**Interface** nilai tambah yang saya terapkan adalah interface `Trackable` di package `model`, berisi dua method, yaitu `updateStatus()` untuk memperbarui status pengajuan dan `getRincianStatus()` untuk mengambil ringkasan status. Interface ini diimplementasikan oleh `PengajuanDesain` dengan `implements Trackable`, lalu kedua method-nya diisi dengan `@Override`. Karena `PengajuanDigital` dan `PengajuanCetak` turunan dari `PengajuanDesain`, keduanya ikut punya kemampuan tracking. Hasil `getRincianStatus()` bisa dilihat di menu Tampilkan Pengajuan pada baris `Tracker`.

![Interface Trackable](<img width="429" height="130" alt="image" src="https://github.com/user-attachments/assets/46530ce1-5a1b-4955-9725-c9921a22a6d4" />)

![Implementasi Interface](<img width="753" height="201" alt="image" src="https://github.com/user-attachments/assets/e90df54b-f2f9-4f55-afd7-8157e5746163" />)
