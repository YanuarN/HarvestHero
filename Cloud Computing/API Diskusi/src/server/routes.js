const {
    addDiscussionHandler,
    getAllDiscussionsHandler,
    getDiscussionByIdHandler,
    editDiscussionByIdHandler,
    deleteDiscussionByIdHandler,
    addCommentHandler,
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
    {
      method: 'POST',
      path: '/discussions/{discussionId}/comments',
      handler: addCommentHandler,
    },
  ];
  
  module.exports = routes;