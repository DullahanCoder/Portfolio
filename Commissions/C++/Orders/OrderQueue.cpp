#include "OrderQueue.h"

OrderQueue::OrderQueue() {}

void OrderQueue::addOrderToQueue(Order* order) {

	if (tail == NULL)
		tail = order;
	else {
		if (head == NULL) {
			tail->prev_order = order;
			head = order;
		}
		else {
			head->prev_order = order;
			head->next_order = head;
			head = order;
		}

	}
}

void OrderQueue::removeOrderFromQueue() {
	Order *next_tail = tail->prev_order;
	tail->~Order();
	tail = next_tail;
}

Order& OrderQueue::getFirstOrder() {
	return *tail;
}

void OrderQueue::printAllOrders() {
	Order* o = tail;
	cout << "\nCustomer Queue\n";
	while (o != NULL) {
		if (o != NULL) {
			o->printOrder();
			o = o->prev_order;
		}
		else
			break;
	}
}

bool OrderQueue::empty() {
	return tail == NULL;
}