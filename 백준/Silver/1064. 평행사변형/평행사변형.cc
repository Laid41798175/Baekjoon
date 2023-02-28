#include <iostream>
#include <cmath>
using namespace std;

double line_length(double x1, double y1, double x2, double y2) {
	x1 -= x2;
	y1 -= y2;
	return sqrt(x1 * x1 + y1 * y1);
}

bool is_in_line(double x1, double y1, double x2, double y2, double x3, double y3) {
	if (x1 == x2 && x2 == x3) {
		return true;
	}

	return (y2 - y1) * (x3 - x2) == (y3 - y2) * (x2 - x1);
}

int main() {
	double x1, y1, x2, y2, x3, y3, A, B, C;
	cin >> x1 >> y1 >> x2 >> y2 >> x3 >> y3;
	A = line_length(x1, y1, x2, y2);
	B = line_length(x1, y1, x3, y3);
	C = line_length(x2, y2, x3, y3);

	if (is_in_line(x1, y1, x2, y2, x3, y3)) {
		cout << -1;
	} else {
		cout << fixed;
		cout.precision(16);
		cout << 2 * (max(A, max(B, C)) - min(A, min(B, C)));
	}
}