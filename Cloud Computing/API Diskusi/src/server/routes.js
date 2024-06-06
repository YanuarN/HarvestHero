const {
    addDiscussionHandler,
    getAllDiscussionsHandler,
    getDiscussionByIdHandler,
    editDiscussionByIdHandler,
    deleteDiscussionByIdHandler,
  } = require('./handler');
const routes = [
    {
      method: 'POST',
      path: '/discussions',
      handler: addDiscussionHandler,
    },
    {
      method: 'GET',
      path: '/discussions',
      handler: getAllDiscussionsHandler,
    },
    {
      method: 'GET',
      path: '/discussions/{discussionId}',
      handler: getDiscussionByIdHandler,
    },
    {
      method: 'PUT',
      path: '/discussions/{discussionId}',
      handler: editDiscussionByIdHandler,
    },
    {
      method: 'DELETE',
      path: '/discussions/{discussionId}',
      handler: deleteDiscussionByIdHandler,
    },
  ];
  
  module.exports = routes;