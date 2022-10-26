#pragma pointers_to_members(full_generallity, virtual_inheritance)

#include <iostream>
#include "Order.h"

Order::Order(int number, string name) {
	_number = new int(number);
	_name = new string(name);
}

string Order::getCustomerName() {
	return *_name;
}

void Order::printOrder() {
	cout << *_number << ". " << getCustomerName() << endl;
}

Order::~Order() {
	delete _number;
	delete _name;
}