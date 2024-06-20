FROM node:18.17.1
WORKDIR /app
ENV PORT 3000
COPY . .
COPY package*.json ./
ENV MODEL_URL=""
RUN npm install
EXPOSE 3000
CMD [ "npm", "run", "start"]
