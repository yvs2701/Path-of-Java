const PrintURLFooter: React.FC = () => {
  return (
    <div className="print-only-footer">URL: "{window?.location?.href}"</div>
  );
};

export default PrintURLFooter;
