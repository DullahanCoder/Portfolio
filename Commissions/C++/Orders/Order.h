#pragma pointers_to_members(full_generallity, virtual_inheritance)
#include <iostream>
#include <string>

using namespace std;
class Order
{
public:
	Order(int number, string name);
	string getCustomerName();
	void printOrder();
	friend class OrderQueue;
	~Order();
private:
	Order* next_order;
	Order* prev_order;
	int* _number;
	string* _name;
};