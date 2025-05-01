import { useState } from "react";
import { ITravelBucket } from "../types/TravelBucket";
import "../styles/TravelBucketCard.scss";

type TravelBucketCardProps = {
  bucket: ITravelBucket;
};

const TravelBucketCard: React.FC<TravelBucketCardProps> = ({ bucket }) => {
  const [activeTab, setActiveTab] = useState<"itinerary" | "budget">(
    "itinerary"
  );

  return (
    <div className="bucket-card">
      <div className="bucket-card-header">
        <h3>{bucket.title}</h3>
        <p>{bucket.description}</p>
      </div>
      <div className="bucket-card-tabs">
        <button
          className={activeTab === "itinerary" ? "active" : ""}
          onClick={() => setActiveTab("itinerary")}
        >
          Itinerary
        </button>
        <button
          className={activeTab === "budget" ? "active" : ""}
          onClick={() => setActiveTab("budget")}
        >
          Budget
        </button>
      </div>

      <div className="bucket-card-content">
        {activeTab === "itinerary" ? (
          <ul>
            {bucket.itineraryItems &&
              bucket.itineraryItems.map((item, idx) => (
                <li key={idx}>{item.detail}</li>
              ))}
          </ul>
        ) : activeTab === "budget" ? (
          bucket.budgetItems && (
            <table>
              <thead>
                <tr>
                  <th>Item Name</th>
                  <th>Item Price</th>
                </tr>
              </thead>
              <tbody>
                {bucket.budgetItems.map((item, idx) => (
                  <tr key={idx}>
                    <td>{item.itemName}</td>
                    <td>{item.price}</td>
                  </tr>
                ))}
              </tbody>
              <tfoot>
                <tr>
                  <td>Total</td>
                  <td>
                    {bucket.budgetItems
                      .map((item) => item.price)
                      .reduce((acc, curr) => acc + curr, 0)}
                  </td>
                </tr>
              </tfoot>
            </table>
          )
        ) : null}
      </div>
    </div>
  );
};

export default TravelBucketCard;
