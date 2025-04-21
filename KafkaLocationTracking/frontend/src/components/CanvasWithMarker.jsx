import React, { useRef, useEffect, useState, useCallback } from "react";
const CanvasWithMarker = ({
  mcolor = "red",
  width = 600,
  height = 400,
  markersInit = [{ mx: null, my: null, mr: 5 }],
  clickDisabled = false,
  handleClick,
}) => {
  const canvasRef = useRef(null);
  const lastCall = useRef(0);
  const delay = 500; // (in ms) = 1/2 second

  const [markers, setMarkers] = useState(markersInit);

  useEffect(() => {
    const canvas = canvasRef.current;
    if (!canvas) return;

    const ctx = canvas.getContext("2d");
    if (!ctx) return;

    const drawMarker = (dot_x = null, dot_y = null, size = 0) => {
      if (dot_x === null || dot_y === null || size === null || size === 0)
        return;

      // draw a crosshair marker
      ctx.beginPath();
      ctx.arc(dot_x, dot_y, size, 0, 2 * Math.PI);
      ctx.arc(dot_x, dot_y, size / 2, 0, 2 * Math.PI);

      ctx.moveTo(dot_x - 1.5 * size, dot_y);
      ctx.lineTo(dot_x - size / 4, dot_y);
      ctx.moveTo(dot_x + 1.5 * size, dot_y);
      ctx.lineTo(dot_x + size / 4, dot_y);

      ctx.moveTo(dot_x, dot_y - 1.5 * size);
      ctx.lineTo(dot_x, dot_y - size / 4);
      ctx.moveTo(dot_x, dot_y + 1.5 * size);
      ctx.lineTo(dot_x, dot_y + size / 4);

      ctx.strokeStyle = mcolor;
      ctx.stroke();
    };

    /** @type {function({mx, my, mr}, {mx, my, mr}): void} */
    const drawLine = (marker1, marker2) => {
      if (!marker1 || !marker2) return;
      if (
        marker1.mx === null ||
        marker1.mx === undefined ||
        marker1.my === null ||
        marker1.my === undefined ||
        marker1.mr === null ||
        marker1.mr === undefined ||
        marker2.mx === null ||
        marker2.mx === undefined ||
        marker2.my === null ||
        marker2.my === undefined ||
        marker2.mr === null ||
        marker2.mr === undefined
      ) {
        return;
      }

      ctx.beginPath();
      ctx.moveTo(marker1.mx, marker1.my);
      ctx.lineTo(marker2.mx, marker2.my);
      ctx.strokeStyle = mcolor;
      ctx.lineWidth = marker1.mr;
      ctx.stroke();
    };

    ctx.clearRect(0, 0, canvas.width, canvas.height);
    markers.forEach((marker, idx, markerArr) => {
      drawMarker(marker.mx, marker.my, marker.mr);
      idx > 0 && drawLine(marker, markerArr[idx - 1]);
    });
  }, [markers]);

  const setMarkerCoords = useCallback(async (e) => {
    const now = new Date().getTime();
    if (now - lastCall.current < delay) {
      // throttles this function
      return;
    }
    lastCall.current = now;

    if (!canvasRef.current) return;
    const rect = canvasRef.current.getBoundingClientRect();

    // Calculate click coordinates relative to the canvas
    const mx = e.clientX - rect.left;
    const my = e.clientY - rect.top;
    setMarkers((prev) => [{ mx, my, mr: prev[0].mr }, ...prev]);

    // call the backend service to store the coords
    if (!clickDisabled) {
      await handleClick(mx, my);
    }
  }, []);

  return (
    <canvas
      ref={canvasRef}
      width={width}
      height={height}
      onClick={clickDisabled ? undefined : setMarkerCoords}
    />
  );
};

export default CanvasWithMarker;
