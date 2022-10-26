#ifndef INFIXTOPOSTFIX_H
#define INFIXTOPOSTFIX_H

#include "mystack.h"
#include <stdio.h>

using namespace std;

class infixToPostfix
{
private:
	string infx; //infix Expression
	string pfx; //postfix Expression
	myStack stack;
public:
	infixToPostfix() {
		stack = myStack();
	}

	void getInfix(string infix) {
		infx = infix;
	}

	void showInfix() {
		cout << "Infix Expression: " << infx << endl;
	}

	void showPostfix() {
		convertTwoPostfix();
		cout << "Postfix Expression: " << pfx << endl;
	}

	void convertTwoPostfix() {
		pfx = "";
		int a;
		for (char& sym : infx) {
			a = 10;
			switch (sym) {
			case '+':
			case '-':
			case '*':
			case '/':
				while (true) {
					if (a < 0 || stack.topVal() < 0)
						break;
					char c = stack.getTopChar();//pop()
					if (c == '(')
						break;
					if (precedence(c, sym))
						pfx.append(1, stack.pop());
					a--;
				}
				stack.push(sym);
				break;
			case '(':
				stack.push(sym);
				break;
			case ')':
				while (true) {
					if (a < 0 || stack.topVal() < 0)
						break;
					char c = stack.pop();
					if (c == '(')
						break;
					pfx.append(1, c);
					a--;
				}

				break;
			default:
				pfx.append(1, sym);
				break;
			}
		}
		pfx += stack.popAll();
	}

	bool precedence(char firstOperator, char secondOperator) {
		int precedence1 = getPrecedence(firstOperator);
		int precedence2 = getPrecedence(secondOperator);
		return precedence1 >= precedence2;
	}

	int getPrecedence(char op) {
		if (op == '/' || op == '*') return 1;
		return 0;
	}
};

#endif