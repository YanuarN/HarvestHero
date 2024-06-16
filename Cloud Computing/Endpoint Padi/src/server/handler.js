const predictClassification = require('../services/inferenceService');
const { Firestore } = require('@google-cloud/firestore');
const storeData = require('../services/storeData');
const crypto = require('crypto');

async function postPredictHandler(request, h) {
    const { image } = request.payload;
    const { model } = request.server.app;

    const { confidenceScore, label, suggestion } = await predictClassification(model, image);
    const id = crypto.randomUUID();
    const createdAt = new Date().toISOString();

    const data = {
        "id": id,
        "createdAt": createdAt,
        "result": label,
        "suggestion": suggestion,
        "confidenceScore": confidenceScore
    }

    const response = h.response({
        status: 'success',
        message: confidenceScore > 99 ? 'Model is predicted successfully' : 'Model is predicted successfully',
        data
    })

    await storeData(id, data);
    response.code(201);
    return response;
}

const db = new Firestore();
async function getHistoriesHandler(_, h) {
    try {
        const predictionsCollection = db.collection('prediksipadi');
        const snapshot = await predictionsCollection.get();

        const predictions = [];
        snapshot.forEach((doc) => {
            const data = doc.data();
            predictions.push(data);
        });

        return h.response({
            status: 'success',
            data: prediksipadi,
        }).code(200);
    } catch (error) {
        console.error('Error fetching predictions:', error);
        return h.response({
            status: 'fail',
            message: 'Failed to retrieve predictions',
        }).code(500);
    }

}


module.exports = {
    postPredictHandler,
    getHistoriesHandler
};
