import express from "express";
import bodyParser from "body-parser";
import { Kafka, logLevel } from "kafkajs";
import { Server } from "socket.io";
import ssrRouter from "./server-router.js";
import { socketEvents } from "./src/utils/socket-events.js";

// Server and SSR:
const app = express();

app.use(bodyParser.json());
app.use(ssrRouter); // also loads env variables depending on the current environment

/** @type {Number} */
const port = process.env.SERVER_PORT || 8081;
/** @type {Number} */
const WS_PORT = process.env.WS_PORT || 3000;

app.listen(port, () => {
  console.log(`Server started at http://localhost:${port}`);
});

// Websocket and Kafka
const io = new Server(WS_PORT, {
  serveClient: false,
  cors: {
    origin: [process.env.VITE_BACKEND_URL],
  },
});

io.on(socketEvents.connect, (socket) => {
  console.log(socket.id + " connected.");

  socket.on(socketEvents.disconnect, () => {
    console.log(socket.id + " disconnected.");
  });
});

const kafka = new Kafka({
  clientId: process.env.KAFKA_CLIENT_ID,
  brokers: process.env.KAFKA_BROKERS?.split(","),
  logLevel: logLevel.NOTHING,
});

const consumer = kafka.consumer({ groupId: process.env.KAFKA_GROUP_ID });
await consumer.connect();
await consumer.subscribe({ topic: process.env.KAFKA_TOPIC_NAME });

await consumer.run({
  eachMessage: async ({ _topic, _partition, message }) => {
    console.log(message.value.toString());
    io.volatile.emit(socketEvents.loc_update, message.value.toString());
  },
});
