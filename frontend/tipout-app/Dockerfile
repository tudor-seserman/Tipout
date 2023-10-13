FROM node:alpine as ts-compiler
WORKDIR /app
COPY package*.json ./
COPY tsconfig*.json ./
RUN npm i
COPY . .
EXPOSE 5173
CMD ["npm", "run", "dev"]
