import ClientOnly from "../components/ClientOnly";
import CanvasWithMarker from "../components/CanvasWithMarker";
import { io } from "socket.io-client";
import { useEffect, useState } from "react";
import { socketEvents } from "../utils/socket-events";

const Screen2 = () => {
  const socket = io(import.meta.env.VITE_WS_URL, { autoConnect: false });

  const w = 600,
    h = 400;
  const [markers, setMarkers] = useState([{ mx: null, my: null, mr: 5 }]);

  useEffect(() => {
    if (!socket.isConnected) {
      socket.connect();
    }

    socket
      .on(socketEvents.connect, () => {
        console.log("Connected!");
      })
      .on(socketEvents.loc_update, (jsonCoords) => {
        /**@type {{x : Number | undefined, y: Number | undefined } | undefined} */
        const coords = JSON.parse(jsonCoords);
        setMarkers((prev) => [
          { mx: coords?.x, my: coords?.y, mr: prev[0].mr },
          ...prev,
        ]);
      })
      .on(socketEvents.disconnect, () => {
        console.log("disconnect");
      });

    return () => {
      socket.removeAllListeners();
    };
  }, []);

  return (
    <>
      <h1>Kafka Frontend Screen-2</h1>
      <div
        style={{ width: `${w}px`, height: `${h}px`, border: "1px solid black" }}
      >
        <ClientOnly>
          {/* React key makes the following component rerender when mx or my change */}
          <CanvasWithMarker
            key={markers.length}
            mcolor="#747bff"
            clickDisabled={true}
            width={w}
            height={h}
            markersInit={markers}
          />
        </ClientOnly>
      </div>
      <a href="/screen1" className="screen2-link navlink">
        Goto: Screen 1
      </a>
    </>
  );
};

export default Screen2;
