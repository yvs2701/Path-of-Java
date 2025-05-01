import { useEffect, useState } from "react";
import TravelBucketCard from "../components/TravelBucketCard";
import { ITravelBucket } from "../types/TravelBucket";
import "../styles/home.scss";
import PrintURLFooter from "../components/PrintURLFooter";

const Home: React.FC = () => {
  const [buckets, setBuckets] = useState<ITravelBucket[]>([]);

  useEffect(() => {
    const fetchTravelBuckets = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/travelbuckets");
        const data = await res.json();
        return data;
      } catch (err) {
        throw err;
      }
    };
    fetchTravelBuckets()
      .then((data) => setBuckets(data))
      .catch((err) =>
        console.error("Error occured while fetching travel buckets:", err)
      );
  }, []);
  return (
    <>
      <div className="bucket-list">
        {buckets.map((bucket) => (
          <TravelBucketCard key={bucket.id} bucket={bucket} />
        ))}
      </div>
      <PrintURLFooter />
    </>
  );
};

export default Home;
