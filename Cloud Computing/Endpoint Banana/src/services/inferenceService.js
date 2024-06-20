const tf = require('@tensorflow/tfjs-node');
const InputError = require('../exceptions/InputError');

async function predictClassification(model, image) {
    try {
        const tensor = tf.node
            .decodeImage(image)
            .resizeNearestNeighbor([224, 224])
            .expandDims()
            .toFloat();

        const classes = [
            'Cordana', 
            'Healthy', 
            'Panama Disease', 
            'Yellow and Black Sigatoka'
        ];

        const prediction = model.predict(tensor);
        const classResult = tf.argMax(prediction, 1).dataSync()[0];
        const label = classes[classResult];

        let suggestion;

        if (label === 'Cordana') {
            suggestion = [
                "1. Gunakan obat anti bakteri yang mengandung tembaga.",
                "2. Pemangkasan daun yang terinfeksi.",
                "3. Peningkatan drainase tanah."
            ];
        }

        if (label === 'Healthy') {
            suggestion = [
                "1. Gunakan pupuk yang seimbang untuk memperkuat pertumbuhan tanaman.",
                "2. Pemantauan rutin untuk mencegah serangan penyakit.",
                "3. Jaga kelembaban tanah yang cukup."
            ];
        }

        if (label === 'Panama Disease') {
            suggestion = [
                "1. Jaga kebersihan kebun.",
                "2. Gunakan agen biokontrol seperti Trichoderma.",
                "3. Tanam varietas pisang yang tahan penyakit Panama."
            ];
        }

        if (label === 'Yellow and Black Sigatoka') {
            suggestion = [
                "1. Gunakan fungisida propiconazole yang direkomendasikan.",
                "2. Pemangkasan daun yang terinfeksi.",
                "3. Peningkatan sirkulasi udara dengan pengaturan jarak tanam."
            ];
        }

        return { label, suggestion };
    } catch (error) {
        throw new InputError(`Terjadi kesalahan dalam melakukan prediksi`);
    }
}

module.exports = predictClassification;
