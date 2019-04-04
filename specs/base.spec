Getting Started with Gauge
==========================

This is an executable specification file. This file follows markdown syntax. Every heading in this file denotes a scenario. Every bulleted point denotes a step.
To execute this specification, use `mvn test`

Mercedes
-----------
Tags:Mercedes

* "csavask" ve "123" ile login ol
* Teklif Girişi sayfasına ilerle
* Arac Kredisi sayfasına ilerle
* Genel Bilgiler kısmını doldur
* TCKN girişinden sonra verilerin geldiğini kontrol et
* Arac Bilgileri kısmını doldur
* Finansal Bilgiler kısmını doldur
* Ekranın sağ üst menüsünde yer alan BİLEŞEN butonuna tıkla
* Elementler tablosuna değerleri gir
* Teklif kayıt işlemlerini tamamla
* İletişim bilgileri işlemlerini tamamla
* Döküman yükleme ekranının işlemlerini tamamla




Kredi onay
---------------
Tags:krediOnay
* "ssezek4" ve "123" ile login ol
* Navigation > Teklif Değerlendirme > kredi Değerlendirme Sorgulama Ekranı açılır.
* Sol navigasyon arama filtresine tıkla
* Proposal Number alanında sözleşme numarası "18011821" yaz ve  teklif arat
* Teklifin üzerine çift tıklanır.
* Start Editing butonu tıkla
* Approve butonuna tıklanır.




AAA
---------------------
*"csavask" ve "123" ile login ol
*"9" saniye bekle
*"btnDokumanYuklemePanelAcKapa" elementine tıkla
*"2" saniye bekle
*"btnYeniDokumanSetiYukle" elementine tıkla
*"popupYeniDokumanSetiYukle" elementi var mı
*"2" saniye bekle
*"btnYeniDokumanSetiYukleDokumanTuru" elementine tıkla
*"btnYeniDokumanSetiYukleDokumanTuruBasvuru" elementine tıkla
*"2" saniye bekle
*"btnYeniDokumanSetiYukleBirDosyaSeciniz" elementine tıkla "PoC_TestCase.xlsx" dosya yükle
*"2" saniye bekle
*"btnYeniDokumanSetiYukleYukle" elementine tıkla
*"txtYeniDokumanSetiYukleYuklemeBasarili" elementi var mı
*"txtYeniDokumanSetiYukleYuklemeBasarili" elementinin text değeri "Yükleme Başarılı." değerine eşit mi
*"7" saniye bekle
*"btnYeniDokumanSetiYukleYuklemeBasariliCarpi" elementine tıkla
*"2" saniye bekle
*"btnYeniDokumanSetiYukleCarpi" elementine tıkla
*"3" saniye bekle
*"btnDokumanYuklemeSeciliDokumanlariYukle" elementine tıkla
*"2" saniye bekle
*"txtDokumanYuklemeAktarmaIslemiBasarili" elementinin text değeri "Aktarma İşlemi Başarılı." değerine eşit mi
*"btnDokumanYuklemeOnayaGonder" elementine tıkla
*"10" saniye bekle
*"textDokumanYuklemeOnayaGonderBasarili" elementinin text değeri "Onaya Gönderme İşlemi Başarılı." değerine eşit mi
*Döküman yönetimi adımını tamamla
