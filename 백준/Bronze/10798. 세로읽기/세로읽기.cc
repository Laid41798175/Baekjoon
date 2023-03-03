#include <iostream>
using namespace std;

int main(void)
{
	char x[5][16] = { 0 };

	for (int i = 0; i < 5; i++)
	{
		cin >> x[i];
	}

	for (int k = 0; k < 15; k++)
	{
		for (int j = 0; j < 5; j++)
		{
			if (x[j][k]==0)
			{
				continue;
			}
			cout << x[j][k];
		}
	}

	return 0;
}