#ifndef MYSTACK_H
#define MYSTACK_H
#include <iostream>
using namespace std;
class myStack {
private:
	int top;

public:
	char sym[10];

	myStack() { top = -1; }

	void push(char c)
	{
		top++;
		sym[top] = c;
	}

	char pop()
	{
		char c = sym[top];
		top--;
		return c;
	}

	char getTopChar() {
		return sym[top];
	}

	int topVal() {
		return top;
	}

	string popAll() {
		string str = "";
		while (top >= 0) {
			char c = pop();
			if (c != '(')
				str.append(1, c);
		}

		return str;
	}
};

#endif // !MYSTACK_H