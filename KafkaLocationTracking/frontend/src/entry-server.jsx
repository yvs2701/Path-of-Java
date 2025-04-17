import { StrictMode } from "react";
import { renderToString } from "react-dom/server";
import { StaticRouter } from "react-router-dom/server";
import Router from "./Router";

/**
 * @param {string} url
 */
export function render(url) {
  url = "/" + url;
  console.log("SSR URL:", url);

  const html = renderToString(
    <StrictMode>
      <StaticRouter location={url}>
        <Router />
      </StaticRouter>
    </StrictMode>
  );
  return { html };
}
