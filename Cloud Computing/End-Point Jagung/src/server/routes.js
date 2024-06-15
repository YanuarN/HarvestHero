const { postPredictHandler, getHistoriesHandler } = require('../server/handler');


const routes = [
    {
        path: '/predict',
        method: 'POST',
        handler: postPredictHandler,
        options: {
            payload: {
                maxBytes: 5 * 1024 * 1024,
                allow: 'multipart/form-data',
                multipart: true,

            }
        }
    },
    {
        path: '/predict/histories',
        method: 'GET',
        handler: getHistoriesHandler,
    }
];

module.exports = routes;
