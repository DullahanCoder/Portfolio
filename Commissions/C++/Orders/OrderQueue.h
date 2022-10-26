#pragma pointers_to_members(full_generallity, virtual_inheritance)
#include "Order.h"

class OrderQueue
{
public:
	OrderQueue();
	void addOrderToQueue(Order* order);
	void removeOrderFromQueue();
	Order& getFirstOrder();
	bool empty();
	void printAllOrders();
private:
	Order* head;
	Order* tail;

};