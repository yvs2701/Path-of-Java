import React, { useState, useEffect } from "react";

/** This HOC will be used to render only a basic HTML (fallback) in the server
 * when the component will update and rerender frequently and preparing
 * intial HTML template doesn't make sense. */
const ClientOnly = ({
  children,
  fallback = <span>This will be rendered in browser.</span>,
}) => {
  const [hasMounted, setHasMounted] = useState(false);

  useEffect(() => {
    setHasMounted(true);
  }, []);

  if (!hasMounted) return fallback;
  return <>{children}</>;
};

export default ClientOnly;
