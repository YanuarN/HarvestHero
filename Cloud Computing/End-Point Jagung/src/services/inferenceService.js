const tf = require('@tensorflow/tfjs-node');
const InputError = require('../exceptions/InputError');
 
async function predictClassification(model, image) {
    try {
      const tensor = tf.node
      .decodeImage(image)
      .resizeNearestNeighbor([224, 224])
      .expandDims()
      .toFloat()

    const classes = ['Bright', 'Common Rust', 'Gray Leaf Spot', 'Mold', 'Northern Leaf Blight', 'Phosporus Deficiency', 'Phosporus Deficiency', 'Potasium Deficiency', 'Healthy'];
  
  
    const prediction = model.predict(tensor);
  
    const classResult = tf.argMax(prediction, 1).dataSync()[0];
    const label = classes[classResult];

    let suggestion;
  
    if (label === 'Bright') {
        suggestion = ["1. Bersihkan sisa-sisa tanaman yang terinfeksi setelah panen;",
                      "2. Pastikan drainase lahan baik untuk menghindari kondisi terlalu lembap;",
                      "3. Rutin periksa tanaman untuk tanda-tanda infeksi seperti bercak-bercak coklat panjang pada daun;",
                      "4. Aplikasikan fungisida yang efektif terhadap Blight seperti mancozeb, chlorothalonil, atau azoxystrobin sesuai petunjuk pada label produk;",
                      "5. Hindari irigasi berlebihan dan pastikan jarak tanam yang cukup untuk sirkulasi udara;",
                      "6. Lakukan penjarangan tanaman untuk meningkatkan sirkulasi udara dan mengurangi kelembapan;",
                      "7. Kumpulkan dan bakar sisa-sisa tanaman yang terinfeksi setelah panen."];
      }
      
      if (label === 'Common Rust') {
        suggestion = ["1. Pastikan drainase lahan baik untuk menghindari kondisi terlalu lembap;",
                      "2. Aplikasikan fungisida yang efektif terhadap Common Rust seperti mancozeb atau azoxystrobin sesuai petunjuk pada label produk;",
                      "3. Kumpulkan dan bakar sisa-sisa tanaman yang terinfeksi setelah panen;",
                      "4. Lakukan penjarangan tanaman untuk meningkatkan sirkulasi udara dan mengurangi kelembapan;",
                      "5. Bersihkan sisa-sisa tanaman yang terinfeksi setelah panen;",
                      "6. Hindari irigasi berlebihan dan pastikan jarak tanam yang cukup untuk sirkulasi udara;",
                      "7. Rutin periksa tanaman untuk tanda-tanda infeksi seperti pustula merah kecoklatan pada daun."];
      }
      
      if (label === 'Gray Leaf Spot') {
        suggestion = ["1. Aplikasikan fungisida yang efektif terhadap Gray Leaf Spot seperti azoxystrobin atau propiconazole sesuai petunjuk pada label produk;",
                      "2. Pastikan drainase lahan baik untuk menghindari kondisi terlalu lembap;",
                      "3. Lakukan penjarangan tanaman untuk meningkatkan sirkulasi udara dan mengurangi kelembapan;",
                      "4. Hindari irigasi berlebihan dan pastikan jarak tanam yang cukup untuk sirkulasi udara;",
                      "5. Kumpulkan dan bakar sisa-sisa tanaman yang terinfeksi setelah panen;",
                      "6. Rutin periksa tanaman untuk tanda-tanda infeksi seperti bercak-bercak kecil berbentuk persegi panjang dengan warna abu-abu atau coklat;",
                      "7. Bersihkan sisa-sisa tanaman yang terinfeksi setelah panen."];
      }
      
      if (label === 'Mold') {
        suggestion = ["1. Aplikasikan fungisida yang efektif terhadap Mold seperti mancozeb atau azoxystrobin sesuai petunjuk pada label produk;",
                      "2. Lakukan penjarangan tanaman untuk meningkatkan sirkulasi udara dan mengurangi kelembapan;",
                      "3. Kumpulkan dan bakar sisa-sisa tanaman yang terinfeksi setelah panen;",
                      "4. Hindari irigasi berlebihan dan pastikan jarak tanam yang cukup untuk sirkulasi udara;",
                      "5. Rutin periksa tanaman untuk tanda-tanda infeksi seperti pertumbuhan jamur berwarna putih, abu-abu, atau hitam pada daun, batang, atau tongkol;",
                      "6. Pastikan drainase lahan baik untuk menghindari kondisi terlalu lembap;",
                      "7. Bersihkan sisa-sisa tanaman yang terinfeksi setelah panen."];
      }
      
      if (label === 'Northern Leaf Blight') {
        suggestion = ["1. Aplikasikan fungisida yang efektif terhadap Gray Leaf Spot seperti azoxystrobin atau propiconazole sesuai petunjuk pada label produk;",
                      "2. Pastikan drainase lahan baik untuk menghindari kondisi terlalu lembap;",
                      "3. Lakukan penjarangan tanaman untuk meningkatkan sirkulasi udara dan mengurangi kelembapan;",
                      "4. Hindari irigasi berlebihan dan pastikan jarak tanam yang cukup untuk sirkulasi udara;",
                      "5. Kumpulkan dan bakar sisa-sisa tanaman yang terinfeksi setelah panen;",
                      "6. Rutin periksa tanaman untuk tanda-tanda infeksi seperti bercak-bercak kecil berbentuk persegi panjang dengan warna abu-abu atau coklat;",
                      "7. Bersihkan sisa-sisa tanaman yang terinfeksi setelah panen."];
      }
      
      if (label === 'Phosporus Deficiency') {
        suggestion = ["1. Berikan pupuk yang mengandung fosfor, seperti superfosfat, sesuai dengan dosis yang direkomendasikan;",
                      "2. Pertimbangkan aplikasi pupuk fosfor dengan cara disemprot langsung ke daun jika kekurangan fosfor terjadi dalam fase pertumbuhan tanaman yang kritis;",
                      "3. Pastikan pH tanah berada dalam kisaran yang optimal untuk penyerapan fosfor oleh tanaman;",
                      "4. Identifikasi gejala kekurangan fosfor pada tanaman, seperti daun berwarna keunguan atau ungu tua, pertumbuhan lambat, dan perakaran yang kurang berkembang;",
                      "5. Lakukan pengapuran jika pH tanah terlalu rendah, karena kekurangan fosfor bisa disebabkan oleh ketersediaan fosfat yang rendah dalam tanah yang bersifat asam;",
                      "6. Berikan perhatian khusus pada tanaman jagung muda karena mereka lebih rentan terhadap kekurangan fosfor;",
                      "7. Lakukan analisis tanah secara teratur untuk memantau tingkat fosfor dalam tanah dan melakukan tindakan korektif jika diperlukan."];
      }

      if (label === 'Potasium Deficiency') {
        suggestion = ["1. Berikan pupuk yang mengandung kalium, seperti klorida kalium atau sulfat kalium, sesuai dengan dosis yang direkomendasikan;",
                      "2. Pertimbangkan aplikasi pupuk kalium dengan cara disemprot langsung ke daun jika kekurangan kalium terjadi dalam fase pertumbuhan tanaman yang kritis;",
                      "3. Pastikan pH tanah berada dalam kisaran yang optimal untuk penyerapan kalium oleh tanaman;",
                      "4. Identifikasi gejala kekurangan kalium pada tanaman, seperti ujung daun yang menguning, tepi daun yang mengering, dan pertumbuhan tanaman yang terhambat;",
                      "5. Hindari penggunaan pupuk nitrogen berlebihan, karena dapat mengurangi penyerapan kalium oleh tanaman;",
                      "6. Berikan perhatian khusus pada tanaman jagung muda karena mereka lebih rentan terhadap kekurangan kalium;",
                      "7. Lakukan analisis tanah secara teratur untuk memantau tingkat kalium dalam tanah dan melakukan tindakan korektif jika diperlukan."];
      }
      
      if (label === 'Healthy') {
        suggestion = ["1. Berikan pupuk yang mengandung unsur hara penting, seperti pupuk NPK yang seimbang;",
                      "2. Pastikan tanah memiliki pH yang sesuai untuk penyerapan nutrisi yang optimal;",
                      "3. Lakukan penyiraman secara teratur, tetapi hindari penyiraman berlebihan;",
                      "4. Berikan perlindungan terhadap hama dan penyakit dengan pemantauan rutin dan tindakan pengendalian yang tepat;",
                      "5. Gunakan teknik penanaman yang tepat, termasuk pemilihan varietas yang sesuai dan pengaturan pola tanam yang baik."];
      }

    
    return { label, suggestion };
    } catch (error) {
        throw new InputError(`Terjadi kesalahan dalam melakukan prediksi`)
    }
}
 
module.exports = predictClassification;