const { Firestore } = require('@google-cloud/firestore');
const { nanoid } = require('nanoid');

const db = new Firestore();
const discussionsCollection = db.collection('discussions');

// Handler untuk menambah diskusi
const addDiscussionHandler = async (request, h) => {
  const {
    username, title, content, likes, comments,
  } = request.payload;

  if (!username || !title || !content) {
    const response = h.response({
      status: 'fail',
      message: 'Gagal menambahkan diskusi. Mohon isi nama user, judul, dan isi diskusi',
    });
    response.code(400);
    return response;
  }

  const id = nanoid(16);
  const createdAt = new Date().toISOString();
  const updatedAt = createdAt;

  const newDiscussion = {
    id,
    username,
    title,
    content,
    likes: likes || 0,
    comments: comments || [],
    createdAt,
    updatedAt,
  };

  try {
    await discussionsCollection.doc(id).set(newDiscussion);
    const response = h.response({
      status: 'success',
      message: 'Diskusi berhasil ditambahkan',
      data: {
        discussionId: id,
      },
    });
    response.code(201);
    return response;
  } catch (error) {
    const response = h.response({
      status: 'fail',
      message: 'Diskusi gagal ditambahkan',
    });
    response.code(500);
    return response;
  }
};

// Handler untuk mendapatkan semua diskusi
const getAllDiscussionsHandler = async (request, h) => {
  try {
    const snapshot = await discussionsCollection.get();
    const discussions = snapshot.docs.map(doc => doc.data());
    const response = h.response({
      status: 'success',
      data: {
        discussions,
      },
    });
    response.code(200);
    return response;
  } catch (error) {
    const response = h.response({
      status: 'fail',
      message: 'Gagal mendapatkan diskusi',
    });
    response.code(500);
    return response;
  }
};

// Handler untuk mendapatkan diskusi berdasarkan ID
const getDiscussionByIdHandler = async (request, h) => {
  const { discussionId } = request.params;
  try {
    const doc = await discussionsCollection.doc(discussionId).get();
    if (doc.exists) {
      const response = h.response({
        status: 'success',
        data: {
          discussion: doc.data(),
        },
      });
      response.code(200);
      return response;
    } else {
      const response = h.response({
        status: 'fail',
        message: 'Diskusi tidak ditemukan',
      });
      response.code(404);
      return response;
    }
  } catch (error) {
    const response = h.response({
      status: 'fail',
      message: 'Gagal mendapatkan diskusi',
    });
    response.code(500);
    return response;
  }
};

// Handler untuk mengedit diskusi berdasarkan ID
const editDiscussionByIdHandler = async (request, h) => {
  const { discussionId } = request.params;
  const {
    username, title, content, likes, comments,
  } = request.payload;

  if (!username || !title || !content) {
    const response = h.response({
      status: 'fail',
      message: 'Gagal memperbarui diskusi. Mohon isi nama user, judul, dan isi diskusi',
    });
    response.code(400);
    return response;
  }

  const updatedAt = new Date().toISOString();
  try {
    const doc = await discussionsCollection.doc(discussionId).get();
    if (doc.exists) {
      await discussionsCollection.doc(discussionId).update({
        username,
        title,
        content,
        likes: likes !== undefined ? likes : doc.data().likes,
        comments: comments !== undefined ? comments : doc.data().comments,
        updatedAt,
      });
      const response = h.response({
        status: 'success',
        message: 'Diskusi berhasil diperbarui',
      });
      response.code(200);
      return response;
    } else {
      const response = h.response({
        status: 'fail',
        message: 'Gagal memperbarui diskusi. Id tidak ditemukan',
      });
      response.code(404);
      return response;
    }
  } catch (error) {
    const response = h.response({
      status: 'fail',
      message: 'Gagal memperbarui diskusi',
    });
    response.code(500);
    return response;
  }
};

// Handler untuk menghapus diskusi berdasarkan ID
const deleteDiscussionByIdHandler = async (request, h) => {
  const { discussionId } = request.params;
  try {
    const doc = await discussionsCollection.doc(discussionId).get();
    if (doc.exists) {
      await discussionsCollection.doc(discussionId).delete();
      const response = h.response({
        status: 'success',
        message: 'Diskusi berhasil dihapus',
      });
      response.code(200);
      return response;
    } else {
      const response = h.response({
        status: 'fail',
        message: 'Diskusi gagal dihapus. Id tidak ditemukan',
      });
      response.code(404);
      return response;
    }
  } catch (error) {
    const response = h.response({
      status: 'fail',
      message: 'Gagal menghapus diskusi',
    });
    response.code(500);
    return response;
  }
};

module.exports = {
  addDiscussionHandler,
  getAllDiscussionsHandler,
  getDiscussionByIdHandler,
  editDiscussionByIdHandler,
  deleteDiscussionByIdHandler,
};
