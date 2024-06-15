const {
    addDiscussionHandler,
    getAllDiscussionsHandler,
    getDiscussionByIdHandler,
    editDiscussionByIdHandler,
    deleteDiscussionByIdHandler,
    addCommentHandler,
    addLikeHandler,
  } = require('./handler');
const routes = [
    {
      method: 'POST',
      path: '/discussions',
      options: {
        payload: {
          maxBytes: 209715200,
          output: 'stream',
          parse: true,
          allow: 'multipart/form-data',
          multipart: true,
        },
      },
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
    {
      method: 'POST',
      path: '/discussions/{discussionId}/comments',
      handler: addCommentHandler,
    },

    {
      method: 'POST',
      path: '/discussions/{discussionId}/likes',
      handler: addLikeHandler,
    },
  ];
  
  module.exports = routes;