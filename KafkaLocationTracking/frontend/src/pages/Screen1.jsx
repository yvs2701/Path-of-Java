import ClientOnly from "../components/ClientOnly";
import CanvasWithMarker from "../components/CanvasWithMarker";

const Screen1 = () => {
  const submitMarkerCoords = async (x, y) => {
    const res = await fetch(`${import.meta.env.VITE_POST_LOC_COORDS}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ x, y }),
    });
    const data = await res.json();
    console.log("'submit marker coords' response:", data);
    return data;
  };

  return (
    <>
      <h1>Kafka Frontend Screen-1</h1>
      <div
        style={{ width: "600px", height: "400px", border: "1px solid black" }}
      >
        <ClientOnly>
          <CanvasWithMarker mcolor="#ff7474" handleClick={submitMarkerCoords} />
        </ClientOnly>
      </div>
      <a href="/screen2" className="screen1-link navlink">
        Goto: Screen 2
      </a>
    </>
  );
};

export default Screen1;
