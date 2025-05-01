export type IItineraryItem = {
  id?: number;
  detail: string;
};

export type IBudgetItem = {
  id?: number;
  itemName: string;
  price: number;
};

export type ITravelBucket = {
  id?: number;
  title: string;
  description: string;
  itineraryItems: IItineraryItem[];
  budgetItems: IBudgetItem[];
};
