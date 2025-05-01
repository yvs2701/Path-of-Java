import { useState } from "react";
import { useNavigate } from "react-router";
import {
  IBudgetItem,
  IItineraryItem,
  ITravelBucket,
} from "../types/TravelBucket";
import PrintURLFooter from "../components/PrintURLFooter";
import "../styles/newBucket.scss";

const NewBucket: React.FC = () => {
  const initBudgetInput: IBudgetItem = {
    itemName: "",
    price: 0,
  };
  const initItineraryInput: IItineraryItem = {
    detail: "",
  };
  const [newBucket, setNewBucket] = useState<ITravelBucket>({
    title: "",
    description: "",
    itineraryItems: [],
    budgetItems: [],
  });

  const [itineraryInput, setItineraryInput] =
    useState<IItineraryItem>(initItineraryInput);
  const [budgetInput, setBudgetInput] = useState<IBudgetItem>(initBudgetInput);
  const [formSubmitting, setFormSubmitting] = useState<boolean>(false);

  const navigate = useNavigate();

  const addItineraryItem = () => {
    if (itineraryInput.detail.trim()) {
      setNewBucket({
        ...newBucket,
        itineraryItems: [...newBucket.itineraryItems, { ...itineraryInput }],
      });
      setItineraryInput(initItineraryInput);
    }
  };
  const removeItineraryItem = (idx: number, items: IItineraryItem[]) => {
    if (idx >= 0 && idx < items.length) {
      items.splice(idx, 1);
      setNewBucket({
        ...newBucket,
        itineraryItems: items,
      });
    }
  };

  const addBudgetItem = () => {
    if (budgetInput.itemName.trim() && budgetInput.price) {
      setNewBucket({
        ...newBucket,
        budgetItems: [...newBucket.budgetItems, { ...budgetInput }],
      });
      setBudgetInput(initBudgetInput);
    }
  };
  const removeBudgetItem = (idx: number, items: IBudgetItem[]) => {
    if (idx >= 0 && idx < items.length) {
      items.splice(idx, 1);
      setNewBucket({
        ...newBucket,
        budgetItems: items,
      });
    }
  };

  const handleSubmit = () => {
    setFormSubmitting(true);
    fetch("http://localhost:8080/api/travelbuckets", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(newBucket),
    })
      .then((res) => res.json())
      .then(() => {
        setFormSubmitting(false);
        navigate("/home"); // go to home page to view new card
      })
      .catch((err) =>
        console.error("Error occured while creating a bucket list item", err)
      );
  };

  return (
    <>
      <div className="bucket-form">
        <h2>Add New Journey</h2>
        <div className="bucket-form-section">
          <input
            type="text"
            placeholder="Title"
            value={newBucket.title}
            onChange={(e) =>
              setNewBucket({ ...newBucket, title: e.target.value })
            }
          />
          <input
            type="text"
            placeholder="Description"
            value={newBucket.description}
            onChange={(e) =>
              setNewBucket({ ...newBucket, description: e.target.value })
            }
          />
        </div>
        <div className="bucket-form-section">
          <h3>Itinerary</h3>
          <input
            type="text"
            placeholder="Itinerary detail"
            value={itineraryInput.detail}
            onChange={(e) =>
              setItineraryInput({ ...itineraryInput, detail: e.target.value })
            }
          />
          <button onClick={addItineraryItem}>Add Itinerary</button>
          {newBucket.itineraryItems.length > 0 && (
            <table className="itinerary-table">
              <thead>
                <tr>
                  <th>Itinerary detail</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                {newBucket.itineraryItems.map((item, idx, items) => (
                  <tr key={idx}>
                    <td>{item.detail}</td>
                    <td>
                      <button onClick={() => removeItineraryItem(idx, items)}>
                        X
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          )}
        </div>

        <div className="bucket-form-section">
          <h3>Budget</h3>
          <input
            type="text"
            placeholder="Item name"
            value={budgetInput.itemName}
            onChange={(e) =>
              setBudgetInput({ ...budgetInput, itemName: e.target.value })
            }
          />
          <input
            type="number"
            placeholder="Item price"
            value={budgetInput.price}
            onChange={(e) =>
              setBudgetInput({
                ...budgetInput,
                price: parseFloat(e.target.value),
              })
            }
          />
          <button onClick={addBudgetItem}>Add Budget Item</button>
          {newBucket.budgetItems.length > 0 && (
            <table className="budget-table">
              <thead>
                <tr>
                  <th>Item Name</th>
                  <th>Item Price</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
                {newBucket.budgetItems.map((item, idx, items) => (
                  <tr key={idx}>
                    <td>{item.itemName}</td>
                    <td>{item.price}</td>
                    <td>
                      <button onClick={() => removeBudgetItem(idx, items)}>
                        X
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
              <tfoot>
                <tr>
                  <td>Total</td>
                  <td>
                    {newBucket.budgetItems
                      .map((item) => item.price)
                      .reduce((acc, curr) => acc + curr, 0)}
                  </td>
                </tr>
              </tfoot>
            </table>
          )}
        </div>
        <button onClick={handleSubmit} disabled={formSubmitting}>
          {formSubmitting ? "Please wait..." : "Submit"}
        </button>
      </div>
      <PrintURLFooter />
    </>
  );
};

export default NewBucket;
