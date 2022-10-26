#include <iostream>
#include "OrderQueue.h"
#include <string>

using namespace std;

int main()
{
	string full_name;
	int orderNumber = 1;
	OrderQueue Oq = OrderQueue();


	while (true) {

		cout << "Enter customer full name (q to quit): ";
		getline(cin, full_name);

		if (full_name == "q") {
			break;
		}

		Order* order = new Order(orderNumber, full_name);

		Oq.addOrderToQueue(order);

		orderNumber++;
	}

	while (!Oq.empty()) {
		Oq.printAllOrders();
		cout << "\n>> Fullfilling order for: " << Oq.getFirstOrder().getCustomerName() << endl;
		Oq.removeOrderFromQueue();
	}

}
