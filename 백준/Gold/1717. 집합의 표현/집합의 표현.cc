#include <iostream>
#include <algorithm>
#include <string>
#include <vector>
#include <array>
#include <queue>

using namespace std;

int n, m;
int arr[1000001];
int parent[1000001];

int z, a, b;
string buffer = "";

int topNode(int x) {
	int cpy = x;
	while (parent[cpy] != cpy) {
		cpy = parent[cpy];
	}
	parent[x] = cpy;
	return cpy;
}

void concat() {
	if (topNode(a) == topNode(b)) {
		return;
	}
	parent[topNode(a)] = a;
	parent[topNode(b)] = b;
	parent[a] = b;
	parent[b] = b;
}

void find() {
	if (topNode(a) == topNode(b)) {
		buffer.append("YES\n");		
	} else {
		buffer.append("NO\n");
	}
}

int main() {
	ios::sync_with_stdio(false);
	cin.tie(NULL);

	cin >> n >> m;
	for (int i = 0; i <= n; i++) {
		arr[i] = i;
		parent[i] = i;
	}

	for (int i = 0; i < m; i++) {
		cin >> z >> a >> b;
		if (z == 0) {
			concat();
		} else {
			find();
		}
	}
	cout << buffer;
}