const tf = require('@tensorflow/tfjs-node');

async function loadModel() {
    console.log('Loading model from URL:', process.env.MODEL_URL);
    const model = await tf.loadLayersModel(process.env.MODEL_URL);
    console.log('Model loaded successfully.');
    return model;
}

module.exports = loadModel;
