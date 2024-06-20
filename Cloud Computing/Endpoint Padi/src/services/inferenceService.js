const tf = require('@tensorflow/tfjs-node');
const InputError = require('../exceptions/InputError');

async function predictClassification(model, image) {
    try {
        const tensor = tf.node
            .decodeJpeg(image)
            .resizeNearestNeighbor([224, 224])
            .expandDims()
            .toFloat();

        const classes = [
            'bacterial_leaf_blight',
            'bacterial_leaf_streak',
            'bacterial_panicle_blight',
            'blast',
            'brown_spot',
            'dead_heart',
            'downy_mildew',
            'hispa',
            'normal',
            'tungro'
        ];


        const prediction = model.predict(tensor);

        const classResult = tf.argMax(prediction, 1).dataSync()[0];
        const label = classes[classResult];


        if (label === 'bacterial_leaf_blight') {
            suggestion = [
                "1. Aplikasikan bakterisida berbahan dasar tembaga di pagi hari",
                "2. Pastikan praktik irigasi yang benar, hindari stres air",
                "3. Pantau lapangan secara teratur untuk tanda-tanda infeksi awal",
                "4. Hapus dan hancurkan tanaman yang terinfeksi untuk mencegah penyebaran",
                "5. Tingkatkan jarak tanam untuk memperbaiki sirkulasi udara",
                "6. Gunakan varietas yang tahan yang sesuai dengan iklim lokal",
                "7. Jaga kebersihan lapangan dengan menghilangkan puing-puing dan gulma"
            ];
        }
        if (label === 'bacterial_leaf_streak') {
            suggestion = [
                "1. Aplikasikan bakterisida berbahan dasar tembaga di pagi hari.",
                "2. Jaga kebersihan lapangan dengan menghilangkan puing-puing dan gulma.",
                "3. Pastikan praktik irigasi yang benar.",
                "4. Pantau lapangan secara teratur untuk tanda-tanda infeksi awal.",
                "5. Gunakan varietas yang tahan yang sesuai dengan iklim lokal.",
                "6. Hindari pupuk nitrogen tinggi untuk mengurangi kerentanan.",
                "7. Jaga jarak tanam yang tepat untuk sirkulasi udara yang lebih baik.",
                "8. Pastikan drainase yang baik untuk mencegah genangan air.",
                "9. Hapus dan hancurkan bagian tanaman yang terinfeksi.",
                "10. Praktikkan rotasi tanaman dengan tanaman non-inang.",
                "11. Gunakan benih bebas penyakit yang bersertifikat.",
                "12. Hindari menanam di ladang yang sebelumnya terinfeksi."
            ];
        }
        if (label === 'bacterial_panicle_blight') {
            suggestion = [
                "1. Aplikasikan bakterisida berbahan dasar tembaga di pagi hari.",
                "2. Pantau lapangan secara teratur untuk tanda-tanda infeksi awal.",
                "3. Hapus dan hancurkan malai yang terinfeksi",
                "4. Tingkatkan jarak tanam untuk memperbaiki sirkulasi udara.",
                "5. Gunakan varietas yang tahan yang sesuai dengan iklim lokal.",
                "6. Pastikan praktik irigasi yang benar, hindari stres air.",
                "7. Jaga kebersihan lapangan dengan menghilangkan puing-puing dan gulma.",
                "8. Hindari pemupukan nitrogen yang berlebihan.",
                "9. Pastikan drainase yang baik untuk mencegah genangan air."
            ];
        }
        if (label === 'blast') {
            suggestion = [
                "1. Aplikasikan fungisida di pagi hari.",
                "2. Hindari pemupukan nitrogen yang berlebihan.",
                "3. Pantau lapangan untuk gejala awal.",
                "4. Hapus dan hancurkan puing-puing tanaman yang terinfeksi.",
                "5. Gunakan varietas yang tahan yang sesuai dengan iklim lokal.",
                "6. Jaga kebersihan lapangan dengan menghilangkan puing-puing dan gulma.",
                "7. Pastikan jarak tanam yang tepat untuk sirkulasi udara.",
                "8. Terapkan manajemen air yang tepat untuk menghindari stres air.",
                "9. Pastikan sirkulasi udara yang baik dengan menjaga jarak tanam yang tepat.",
                "10. Hindari penanaman padat untuk mengurangi kelembaban.",
                "11. Gunakan pemupukan seimbang untuk memperkuat kesehatan tanaman."
            ];
        }
        if (label === 'brown_spot') {
            suggestion = [
                "1. Aplikasikan fungisida untuk mengendalikan penyebaran.",
                "2. Pastikan pemupukan yang seimbang.",
                "3. Pantau untuk tanda-tanda infeksi awal.",
                "4. Tingkatkan kesehatan tanah dengan bahan organik.",
                "5. Gunakan benih bebas penyakit.",
                "6. Hapus dan hancurkan bagian tanaman yang terinfeksi.",
                "7. Tingkatkan drainase lapangan untuk mencegah genangan air.",
                "8. Hindari stres air dengan menjaga irigasi yang konsisten.",
                "9. Gunakan varietas yang tahan yang sesuai dengan kondisi lokal.",
                "10. Jaga kebersihan lapangan dengan menghilangkan puing-puing."
            ];
        }
        if (label === 'dead_heart') {
            suggestion = [
                "1. Kendalikan hama serangga dengan insektisida yang tepat.",
                "2. Hapus dan hancurkan tanaman yang terinfeksi.",
                "3. Pastikan pH tanah berada dalam kisaran yang optimal untuk penyerapan fosfor oleh tanaman",
                "4. Jaga kesuburan tanah yang tepat.",
                "5. Gunakan varietas yang tahan terhadap hama.",
                "6. Aplikasikan insektisida secara preventif.",
                "7. Tingkatkan kesehatan dan kesuburan tanah.",
                "8. Pastikan drainase yang baik untuk mencegah genangan air.",
                "9. Pantau untuk infeksi sekunder.",
                "10. Jaga kebersihan lapangan dengan menghilangkan puing-puing dan gulma."
            ];
        }
        if (label === 'downy_mildew') {
            suggestion = [
                "1. Aplikasikan fungisida di pagi hari.",
                "2. Pastikan sirkulasi udara yang baik dengan menjaga jarak tanam.",
                "3. Pantau untuk tanda-tanda infeksi awal.",
                "4. Hapus dan hancurkan bagian tanaman yang terinfeksi.",
                "5. Gunakan varietas yang tahan yang sesuai dengan iklim lokal.",
                "6. Hindari genangan air dengan menjaga irigasi yang tepat.",
                "7. Tingkatkan drainase lapangan untuk mencegah genangan air.",
                "8. Praktikkan rotasi tanaman dengan tanaman non-inang.",
                "9. Gunakan benih bebas penyakit."
            ];
        }
        if (label === 'hispa') {
            suggestion = [
                "1. Aplikasikan insektisida di pagi hari.",
                "2. Praktikkan sanitasi lapangan dengan menghilangkan puing-puing.",
                "3. Pantau untuk tanda-tanda aktivitas hama awal.",
                "4. Hapus dan hancurkan daun yang terinfestasi.",
                "5. Gunakan varietas yang tahan yang sesuai dengan iklim lokal.",
                "6. Pastikan drainase yang baik untuk mencegah genangan air.",
                "7. Praktikkan rotasi tanaman untuk memutus siklus hama."
            ];
        }
        if (label === 'normal') {
            suggestion = [
                "1. Jaga praktik irigasi yang teratur.",
                "2. Pantau tanda-tanda awal stres tanaman.",
                "3. Aplikasikan pemupukan seimbang.",
                "4. Pastikan jarak tanam yang tepat untuk sirkulasi udara.",
                "5. Lakukan pemeriksaan lapangan secara teratur untuk tanda-tanda stres.",
                "6. Pantau tanda-tanda awal penyakit atau hama.",
                "7. Pantau genangan air dan tingkatkan drainase.",
                "8. Jaga kebersihan lapangan dengan menghilangkan puing-puing."
            ];
        }
        if (label === 'tungro') {
            suggestion = [
                "1. Gunakan varietas yang tahan yang sesuai dengan iklim lokal.",
                "2. Kendalikan vektor serangga dengan insektisida yang tepat.",
                "3. Hapus dan hancurkan tanaman yang terinfeksi.",
                "4. Pantau tanda-tanda awal aktivitas vektor.",
                "5. Jaga kebersihan lapangan dengan menghilangkan puing-puing.",
                "6. Hindari menanam di dekat ladang yang terinfeksi.",
                "7. Tingkatkan drainase lapangan untuk mencegah genangan air.",
                "8. Gunakan benih bebas penyakit.",
                "9. Jaga sanitasi lapangan yang tepat."
            ];
        }


        return { suggestion, label };
    } catch (error) {
        throw new InputError(`Terjadi kesalahan input: ${error.message}`);
    }
}

module.exports = predictClassification;
