const Hapi = require('@hapi/hapi');
const routes = require('./routes.js');

(async () => {
    const server = Hapi.server({
        port: 3000,
        host: 'localhost',
        routes: {
            cors: {
              origin: ['*'],
            },
        },
    })

    server.route(routes);

    await server.start();
    console.log(`Server start at: ${server.info.uri}`);
})();