#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <array>
#include <queue>
#include <sstream>

using namespace std;

int n, m;
int parent[1000001];

int a, b;

int topNode(int x) {
	int cpy = x;
	while (parent[cpy] != cpy) {
		cpy = parent[cpy];
	}
	parent[x] = cpy;
	return cpy;
}

bool find() {
	return topNode(a) == topNode(b);
}

void concat() {
	if (find()) {
		return;
	}
	parent[topNode(a)] = a;
	parent[topNode(b)] = b;
	parent[a] = b;
	parent[b] = b;
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n;
	for (int i = 1; i <= n; i++) {
		parent[i] = i;
	}

	cin >> m;
	for (a = 1; a <= n; a++) {
		for (b = 1; b <= n; b++) {
			int tmp;
			cin >> tmp;
			if (tmp == 1) {
				concat();
			}
		}
	}

	cin >> a;
	for (int i = 1; i < m; i++) {
		cin >> b;
		if (!find()) {
			cout << "NO\n";
			return 0;
		}
		a = b;
	}
	cout << "YES\n";
}